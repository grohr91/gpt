package br.unisc.core.controller;

import br.unisc.core.dto.VwIndividuoGrupoDTO;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoIndividuo;
import br.unisc.core.model.Individuo;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraTabela;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ImportacaoController {

    protected EntityManager em;
    protected SysConfiguracao configuracao;

    public ImportacaoController(EntityManager em, SysConfiguracao configuracao) {
        this.em = em;
        this.configuracao = configuracao;
    }

    public void processarImportacao() {
        processarVwIndividuoGrupo();
    }

    public String processarVwIndividuoGrupo() {
        SysRegraTabela regraIndividuo = findRegraByNmTabela("individuo", configuracao);
        SysRegraTabela regraGrupo = findRegraByNmTabela("grupo", configuracao);
        SysRegraTabela regraGrupoIndividuo = findRegraByNmTabela("grupo_individuo", configuracao);

        VwIndividuoGrupoDTOController igc = new VwIndividuoGrupoDTOController(em, configuracao);
        List<VwIndividuoGrupoDTO> individuoGrupoList = igc.findVwIndividuoGrupoByRegra(regraIndividuo, regraGrupo, regraGrupoIndividuo);
        int countRight = 0;
        int countWrong = 0;
        for (VwIndividuoGrupoDTO ig : individuoGrupoList) {
            try {
                Individuo i = igc.salvaIndividuo(ig.toIndividuo(), regraIndividuo, configuracao);
                Grupo g = igc.salvaGrupo(ig.toIndividuo().getGrupo(), regraGrupo, configuracao);
                GrupoIndividuo gi = igc.salvaGrupoIndividuo(i, g, regraGrupoIndividuo, configuracao);
                countRight++;
            } catch (Exception ex) {
                countWrong++;
            }
        }
        System.out.println("Indivíduos inseridos ou alterados: " + countRight + " de " + individuoGrupoList.size());
        System.out.println("Indivíduos não tratados: " + countWrong + " de " + individuoGrupoList.size());
        return ("Indivíduos inseridos ou alterados: " + countRight + " de " + individuoGrupoList.size());
    }

    private SysRegraTabela findRegraByNmTabela(String nmTabela, SysConfiguracao configuracao) {
        return em.createNamedQuery("SysRegraTabela.findByNmTabelaAndConfiguracao", SysRegraTabela.class)
                .setParameter("nmTabela", nmTabela)
                .setParameter("idConfiguracao", configuracao.getId())
                .getResultList().get(0);
    }

}

package br.unisc.core.controller;

import br.unisc.core.dto.VwIndividuoAtividadeDTO;
import br.unisc.core.model.Desafio;
import br.unisc.core.model.Individuo;
import br.unisc.core.model.IndividuoAtividade;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegra;
import br.unisc.web.model.SysRegraTabela;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class VwIndividuoAtividadeDTOController {

    protected EntityManager em;
    protected SysConfiguracao configuracao;

    public VwIndividuoAtividadeDTOController(EntityManager em, SysConfiguracao configuracao) {
        this.em = em;
        this.configuracao = configuracao;
    }

    public List<VwIndividuoAtividadeDTO> findVwIndividuoAtividadeByRegra(
            SysRegraTabela regraIndividuoAtividade, SysRegraTabela regraDesafio) {
        if (regraIndividuoAtividade.getFgImportar()) {
            SysRegraTabelaController srtc = new SysRegraTabelaController(em);

            //cria filtro de atributos que pertencem a view
            String where = " WHERE 1=1 " + srtc.criaWhereByRegraTabela(regraIndividuoAtividade, true);
            where += srtc.criaWhereByRegraTabela(regraDesafio, true);

            Query q = configuracao.getConn().getEm().createNativeQuery(""
                    + "SELECT DISTINCT * FROM vw_individuo_atividade "
                    + where
                    + " ORDER BY id_individuo", VwIndividuoAtividadeDTO.class);
            return q.getResultList();
        }
        return new ArrayList<VwIndividuoAtividadeDTO>();
    }

    public IndividuoAtividade salvaIndividuoAtividade(IndividuoAtividade obj, SysRegraTabela regra, SysConfiguracao configuracao) {
        if (regra.getFgImportar()) {
            VwIndividuoGrupoDTOController igc = new VwIndividuoGrupoDTOController(em, configuracao);
            Individuo individuoBD = igc.findIndividuoByIdExterno(obj.getIndividuo().getIdExterno());
            Desafio desafioBD = igc.findDesafioByIdExterno(obj.getDesafio().getIdExterno());

            //nao insere individuo nem desafios que nao estejam ja criados no BD
            if (individuoBD == null || individuoBD.getId() == null
                    || desafioBD == null || desafioBD.getId() == null) {
                return null;
            }else {
                obj.setDesafio(desafioBD);
                obj.setIndividuo(individuoBD);
            }

            obj.setDtUltimaSincronizacao(new Date());
            obj.setFgAtivo(true);
            obj.setConfiguracao(configuracao);
            IndividuoAtividade iaBD = findIndividuoAtividadeByIdsExternos(individuoBD, desafioBD);
            if (iaBD != null) {
                obj.setId(iaBD.getId());

                //remocao lógica
                if (SysRegraTabela.SG_TIPO_REMOCAO_REGRA_REMOCAO == regra.getSgTipoRemocao()) {
                    removerIndividuoAtividadeByRegra(obj, regra);
                }
            }

            //aplica regra de filtros configurados na tela da ferramenta
            if (individuoAtividadeFiltrado(obj, regra, SysRegra.SG_TIPO_REGRA_FILTRO)) {
                return null;
            }

            if (SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR == regra.getSgTipoInsercao()) {
                obj = em.merge(obj);
            } else if (SysRegraTabela.SG_TIPO_INSERCAO_APENAS_INSERIR == regra.getSgTipoInsercao()) {
                if (iaBD == null) {
                    em.persist(obj);
                }
            }
            em.flush();
        }
        return obj;
    }

    public IndividuoAtividade findIndividuoAtividadeByIdsExternos(Individuo i, Desafio d) {
        Query q = em.createNativeQuery("SELECT ia.* FROM individuo_atividade ia "
                + "JOIN individuo i ON ia.id_individuo = i.id "
                + "JOIN desafio d ON ia.id_desafio = d.id "
                + "	AND i.id_externo = ?1 "
                + "	AND d.id_externo = ?2", IndividuoAtividade.class);
        q.setParameter(1, i.getId());
        q.setParameter(2, d.getId());
        List<IndividuoAtividade> individuoAtividadeList = q.getResultList();

        //Do not exists in DB, return a new one
        if (individuoAtividadeList.isEmpty()) {
            IndividuoAtividade id = new IndividuoAtividade(i, d, true);
            return id;
        }
        return individuoAtividadeList.get(0);
    }

    private boolean individuoAtividadeFiltrado(IndividuoAtividade obj, SysRegraTabela regra, int tipoRegra) {
        boolean filtrado = false;
        SysRegraController src = new SysRegraController(em);
        for (SysRegra sr : regra.getSysRegraList()) {
            if (tipoRegra == sr.getSgTipoRegra()) {
                filtrado = true;
                //se valor do atributo for igual ao definido no filtro, entao nao filtrado
                String valorAtributo = obj.getVal(sr.getAtributo().getNmAtributo());
                if (src.compareByRegra(valorAtributo, sr)) {
                    filtrado = false;
                    break;
                }
            }
        }
        return filtrado;
    }

    private IndividuoAtividade removerIndividuoAtividadeByRegra(IndividuoAtividade obj, SysRegraTabela regra) {
        if (individuoAtividadeFiltrado(obj, regra, SysRegra.SG_TIPO_REGRA_REMOCAO)) {
            obj.setFgAtivo(false);
        }
        return obj;
    }

    public IndividuoAtividade aplicaTransformacaoByRegra(IndividuoAtividade obj, SysRegraTabela regraTabela) {
        SysRegraController src = new SysRegraController(em);
        for (SysRegra sr : regraTabela.getSysRegraList()) {
            if (SysRegra.SG_TIPO_REGRA_TRANSFORMACAO == sr.getSgTipoRegra()) {
                //se valor do atributo for igual ao definido na transformacao, entao seta valor
                String valorAtributo = obj.getVal(sr.getAtributo().getNmAtributo());
                if (src.compareByRegra(valorAtributo, sr)) {
                    obj.setVal(sr.getAtributo().getNmAtributo(), sr.getVlRegraNovo());
                }
            }
        }
        return obj;
    }

}

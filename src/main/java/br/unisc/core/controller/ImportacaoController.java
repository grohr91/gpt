package br.unisc.core.controller;

import br.unisc.core.dto.VwIndividuoAtividadeDTO;
import br.unisc.core.dto.VwIndividuoGrupoDTO;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoIndividuo;
import br.unisc.core.model.Individuo;
import br.unisc.core.model.IndividuoAtividade;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraTabela;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ImportacaoController {

    protected EntityManager em;
    protected SysConfiguracao configuracao;
    protected SimpleDateFormat sdf;

    public ImportacaoController(EntityManager em, SysConfiguracao configuracao) {
        this.em = em;
        this.configuracao = configuracao;
        this.sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    }

    public void processarImportacao() {
        Date dtInicioImportacao = new Date();
        System.out.println(sdf.format(dtInicioImportacao) + " #GPT - --------------- Início da Importação ---------------");
        processarVwIndividuoGrupo(dtInicioImportacao);
//        processarVwIndividuoAtividade(dtInicioImportacao);
        System.out.println(sdf.format(dtInicioImportacao) + " #GPT - --------------- Final da Importação ---------------");

    }

    private void processarVwIndividuoGrupo(Date dtImportacao) {
        System.out.println(sdf.format(new Date()) + " #GPT - Processamento de vwIndividuoGrupo iniciada");
        SysRegraTabelaController srtc = new SysRegraTabelaController(em);
        SysRegraTabela regraIndividuo = srtc.findRegraByNmTabela("individuo", configuracao);
        SysRegraTabela regraGrupo = srtc.findRegraByNmTabela("grupo", configuracao);
        SysRegraTabela regraGrupoIndividuo = srtc.findRegraByNmTabela("grupo_individuo", configuracao);

        VwIndividuoGrupoDTOController igc = new VwIndividuoGrupoDTOController(em, configuracao);
        List<VwIndividuoGrupoDTO> individuoGrupoList = igc.findVwIndividuoGrupoByRegra(regraIndividuo, regraGrupo, regraGrupoIndividuo);
        int totalRegistros = individuoGrupoList.size();
        int totalProcessados = 0;
        for (VwIndividuoGrupoDTO ig : individuoGrupoList) {
            try {
                //individuo
                Individuo i = igc.aplicaTransformacaoByRegra(ig.toIndividuo(), regraIndividuo);
                i = igc.salvaIndividuo(i, regraIndividuo, configuracao);

                //grupo
                Grupo g = igc.aplicaTransformacaoGrupoByRegra(i.getGrupo(), regraGrupo);
                g = igc.salvaGrupo(g, regraGrupo, configuracao);
                
                //individuo_grupo
                GrupoIndividuo gi = igc.salvaGrupoIndividuo(i, g, regraGrupoIndividuo, configuracao);
                if (totalProcessados % 100 == 0) {
                    System.out.println(sdf.format(new Date()) + " #GPT - individuo_atividade: " + totalProcessados + "/" + totalRegistros);
                }
                totalProcessados++;
            } catch (Exception ex) {
            }
        }
        System.out.println(sdf.format(new Date()) + " #GPT - individuo_atividade: " + totalProcessados + "/" + totalRegistros);
        em.flush();
        executaRemocaoNaoSincronizadosByRegra(regraIndividuo, dtImportacao);
        executaRemocaoNaoSincronizadosByRegra(regraGrupo, dtImportacao);
        executaRemocaoNaoSincronizadosByRegra(regraGrupoIndividuo, dtImportacao);

        System.out.println(sdf.format(new Date()) + " #GPT - Processamento de vwIndividuoGrupo finalizada\n");
    }

    private void processarVwIndividuoAtividade(Date dtImportacao) {
        System.out.println(sdf.format(new Date()) + " #GPT - Processamento de vwIndividuoAtividade iniciada");
        SysRegraTabelaController srtc = new SysRegraTabelaController(em);
        SysRegraTabela regraIndividuoAtividade = srtc.findRegraByNmTabela("individuo_atividade", configuracao);
        SysRegraTabela regraDesafio = srtc.findRegraByNmTabela("desafio", configuracao);

        VwIndividuoAtividadeDTOController iac = new VwIndividuoAtividadeDTOController(em, configuracao);
        List<VwIndividuoAtividadeDTO> individuoAtividadeList = iac.findVwIndividuoAtividadeByRegra(regraIndividuoAtividade, regraDesafio);
        int totalRegistros = individuoAtividadeList.size();
        int totalProcessados = 0;
        for (VwIndividuoAtividadeDTO iaDTO : individuoAtividadeList) {
            try {
                IndividuoAtividade iaConvertido = iaDTO.toIndividuoAtividade();
                IndividuoAtividade ia = iac.salvaIndividuoAtividade(iaConvertido.getIndividuo(),
                        iaConvertido.getDesafio(), regraIndividuoAtividade, configuracao);

                if (totalProcessados % 100 == 0) {
                    System.out.println(sdf.format(new Date()) + " #GPT - individuo_atividade: " + totalProcessados + "/" + totalRegistros);
                }
                totalProcessados++;
            } catch (Exception ex) {
            }
        }
        System.out.println(sdf.format(new Date()) + " #GPT - individuo_atividade: " + totalProcessados + "/" + totalRegistros);
        em.flush();
        executaRemocaoNaoSincronizadosByRegra(regraIndividuoAtividade, dtImportacao);
        executaRemocaoNaoSincronizadosByRegra(regraDesafio, dtImportacao);

        System.out.println(sdf.format(new Date()) + " #GPT - Processamento de vwIndividuoAtividade finalizada\n");
    }

    private void executaRemocaoNaoSincronizadosByRegra(SysRegraTabela regraTabela, Date dtSincronizacao) {
        if (SysRegraTabela.SG_TIPO_REMOCAO_NAO_SINCRONIZADOS == regraTabela.getSgTipoRemocao()) {
            Query q = em.createNativeQuery("UPDATE " + regraTabela.getTabela().getNmTabela()
                    + " SET fg_ativo = false "
                    + " WHERE dt_ultima_sincronizacao < ?1");
            q.setParameter(1, dtSincronizacao);
            q.executeUpdate();
            em.flush();
        }
    }

}

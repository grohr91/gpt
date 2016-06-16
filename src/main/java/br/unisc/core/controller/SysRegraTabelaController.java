package br.unisc.core.controller;

import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegra;
import br.unisc.web.model.SysRegraTabela;
import br.unisc.web.model.SysTabela;
import br.unisc.web.model.SysTipoAtributoOperacao;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class SysRegraTabelaController {

    protected EntityManager em;

    public SysRegraTabelaController(EntityManager em) {
        this.em = em;
    }

    public List<SysRegraTabela> save(List<SysRegraTabela> objList, SysConfiguracao configuracao) {
        for (SysRegraTabela srt : objList) {
            srt.setConfiguracao(configuracao);
            srt = save(srt);
        }

        return objList;
    }

    public SysRegraTabela save(SysRegraTabela obj) {

        if (obj.getSgTipoInsercao() == null) {
            obj.setSgTipoInsercao(SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR);
        }

        if (obj.getSgTipoRemocao() == null) {
            obj.setSgTipoRemocao(SysRegraTabela.SG_TIPO_REMOCAO_NAO_REMOVER);
        }

        if (obj.getTabela() != null && obj.getTabela().getId() != null) {
            obj.setTabela(em.find(SysTabela.class, obj.getTabela().getId()));
        }

        if (obj.getId() == null) {
            em.persist(obj);
            em.flush();
            return obj;
        }
        return em.merge(obj);
    }

    public String criaWhereByRegraTabela(SysRegraTabela regraTabela, Boolean filtroView) {
        String where = "";
        if (regraTabela.getSysRegraList() != null) {
            for (SysRegra sr : regraTabela.getSysRegraList()) {
                if (SysRegra.SG_TIPO_REGRA_FILTRO == sr.getSgTipoRegra()
                        && filtroView.equals(sr.getAtributo().getFgColunaView())) {
                    SysTipoAtributoOperacao tao = em.createNamedQuery("SysTipoAtributoOperacao.findByTipoAtributoAndOperacao", SysTipoAtributoOperacao.class)
                            .setParameter("idTipoAtributo", sr.getAtributo().getTipoAtributo().getId())
                            .setParameter("idOperacao", sr.getOperacao().getId()).getResultList().get(0);
                    String valor = tao.getCondicaoByValor(sr.getVlRegra());
                    where += " OR " + sr.getAtributo().getNmAtributo() + " " + valor;
                }
            }
        }
        return where;
    }

    public SysRegraTabela findRegraByNmTabela(String nmTabela, SysConfiguracao configuracao) {
        return em.createNamedQuery("SysRegraTabela.findByNmTabelaAndConfiguracao", SysRegraTabela.class)
                .setParameter("nmTabela", nmTabela)
                .setParameter("idConfiguracao", configuracao.getId())
                .getResultList().get(0);
    }

}

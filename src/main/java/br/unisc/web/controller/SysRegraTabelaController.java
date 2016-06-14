package br.unisc.web.controller;

import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraTabela;
import br.unisc.web.model.SysTabela;
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

}

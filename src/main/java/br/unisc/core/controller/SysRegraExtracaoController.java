package br.unisc.core.controller;

import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraExtracao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class SysRegraExtracaoController {

    protected EntityManager em;

    public SysRegraExtracaoController(EntityManager em) {
        this.em = em;
    }

    public List<SysRegraExtracao> save(List<SysRegraExtracao> objList, SysConfiguracao configuracao) {
        if (objList == null || objList.isEmpty()) {
            objList = em.createNamedQuery("SysRegraExtracao.findByConfiguracao", SysRegraExtracao.class)
                    .setParameter("idConfiguracao", configuracao.getId()).getResultList();
        }

        if (objList == null || objList.isEmpty()) {
            objList = criaRegrasExtracao(configuracao);
        } else {
            for (SysRegraExtracao se : objList) {
                se.setConfiguracao(configuracao);
                se = save(se);
            }
        }
        return objList;
    }

    public SysRegraExtracao save(SysRegraExtracao obj) {
        if (obj.getId() == null) {
            em.persist(obj);
            em.flush();
            return obj;
        }
        return em.merge(obj);
    }

    private List<SysRegraExtracao> criaRegrasExtracao(SysConfiguracao configuracao) {
        SysRegraExtracao sre1 = new SysRegraExtracao("vw_individuo_grupo", configuracao);
        SysRegraExtracao sre2 = new SysRegraExtracao("vw_individuo_atividade", configuracao);
        SysRegraExtracao sre3 = new SysRegraExtracao("vw_grupo_atividade", configuracao);
        em.persist(sre1);
        em.persist(sre2);
        em.persist(sre3);
        em.flush();
        List<SysRegraExtracao> regraExtracaoList = new ArrayList<SysRegraExtracao>();
        regraExtracaoList.add(sre1);
        regraExtracaoList.add(sre2);
        regraExtracaoList.add(sre3);
        return regraExtracaoList;
    }

}

package br.unisc.web.controller;

import br.unisc.web.model.SysAutomacao;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraTabela;
import br.unisc.web.model.SysTabela;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class SysAutomacaoController {

    protected EntityManager em;

    public SysAutomacaoController(EntityManager em) {
        this.em = em;
    }

    public List<SysAutomacao> save(List<SysAutomacao> objList, SysConfiguracao configuracao) {
        for (SysAutomacao sa : objList) {
            if (sa.getNrDiaSemana()!= null) {
                sa.setConfiguracao(configuracao);
                sa = save(sa);
            }
        }
        return objList;
    }

    public SysAutomacao save(SysAutomacao obj) {
        
        if (obj.getId() == null) {
            em.persist(obj);
            em.flush();
            return obj;
        }
        return em.merge(obj);
    }

}

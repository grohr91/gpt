package br.unisc.web.controller;

import br.unisc.web.model.SysConfiguracao;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ConfiguracaoController {

    protected EntityManager em;

    public ConfiguracaoController(EntityManager em) {
        this.em = em;
    }

    public SysConfiguracao save(SysConfiguracao obj) {
        if (obj.getId() == null) {
            em.persist(obj);
            em.flush();
            return obj;
        }
        return em.merge(obj);
    }

}

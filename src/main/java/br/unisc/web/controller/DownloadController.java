package br.unisc.web.controller;

import br.unisc.web.dto.ConnectionDTO;
import br.unisc.core.model.Desafio;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoAtividade;
import br.unisc.core.model.GrupoIndividuo;
import br.unisc.core.model.Individuo;
import br.unisc.core.model.IndividuoAtividade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class DownloadController {

    protected EntityManager em;
    protected ConnectionDTO conn;

    public DownloadController(EntityManager em) {
        this.em = em;
    }

    public List<Individuo> findAllIndividuo() {
        Query q = em.createNativeQuery("SELECT * FROM individuo ORDER BY nm_individuo", Individuo.class);
        return q.getResultList();
    }

    public List<Grupo> findAllGrupo() {
        Query q = em.createNativeQuery("SELECT * FROM grupo ORDER BY nm_grupo", Grupo.class);
        return q.getResultList();
    }

    public List<GrupoIndividuo> findAllGrupoIndividuo() {
        Query q = em.createNativeQuery("SELECT * FROM grupo_individuo ORDER BY id_grupo_individuo", GrupoIndividuo.class);
        return q.getResultList();
    }

    public List<IndividuoAtividade> findAllIndividuoDesafio() {
        Query q = em.createNativeQuery("SELECT * FROM grupo_individuo ORDER BY id_grupo_individuo", GrupoIndividuo.class);
        return q.getResultList();
    }

    public List<GrupoAtividade> findAllGrupoDesafio() {
        Query q = em.createNativeQuery("SELECT * FROM grupo_desafio ORDER BY id_grupo_desafio", GrupoAtividade.class);
        return q.getResultList();
    }

    public List<Desafio> findAllDesafio() {
        Query q = em.createNativeQuery("SELECT * FROM desafio ORDER BY nm_desafio", Desafio.class);
        return q.getResultList();
    }

}

package br.unisc.core.controller;

import br.unisc.web.dto.ConnectionDTO;
import br.unisc.core.dto.VwIndividuoGrupoDTO;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoIndividuo;
import br.unisc.core.model.Individuo;
import br.unisc.util.DBUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class VwIndividuoGrupoDTOController {

    protected EntityManager em;
    protected ConnectionDTO conn;

    public VwIndividuoGrupoDTOController(EntityManager em, ConnectionDTO c) {
        this.em = em;
        this.conn = c;
    }

    public List<VwIndividuoGrupoDTO> findIndividuoGrupo() {
        Query q = conn.getEm().createNativeQuery("SELECT DISTINCT * FROM vw_individuo_grupo "
                + "ORDER BY id_individuo ", VwIndividuoGrupoDTO.class);
        return q.getResultList();
    }

    public Individuo insertOrUpdate(VwIndividuoGrupoDTO obj) {
        Individuo i = em.merge(obj.toIndividuo());
        em.flush();

        // grupo
        Grupo g = null;
        if (obj.toIndividuo().getGrupo() != null && obj.toIndividuo().getGrupo().getId() != null) {
            g = em.merge(obj.toIndividuo().getGrupo());
        }

        // grupo_individuo
        if (g != null) {
            GrupoIndividuo gi = findIndividuoGrupo(g, i);
            gi = em.merge(gi);
        }

        em.flush();
        return i;
    }

    public GrupoIndividuo findIndividuoGrupo(Grupo g, Individuo i) {
        Query q = em.createNativeQuery("SELECT * FROM grupo_individuo "
                + "WHERE id_grupo = ?1 AND id_individuo = ?2 "
                + "ORDER BY id_individuo ASC ", GrupoIndividuo.class);
        q.setParameter(1, g.getId());
        q.setParameter(2, i.getId());
        List<GrupoIndividuo> grupoIndividuoList = q.getResultList();

        //Do not exists in DB, return a new one
        if (grupoIndividuoList.isEmpty()) {
            GrupoIndividuo gi = new GrupoIndividuo(g, i);
            return gi;
        }
        return grupoIndividuoList.get(0);
    }

}

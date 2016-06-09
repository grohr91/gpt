package br.unisc.core.controller;

import br.unisc.web.dto.ConnectionDTO;
import br.unisc.core.dto.VwGrupoAtividadeDTO;
import br.unisc.core.model.Desafio;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoAtividade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class VwGrupoAtividadeDTOController {

    protected EntityManager em;
    protected ConnectionDTO conn;

    public VwGrupoAtividadeDTOController(EntityManager em, ConnectionDTO c) {
        this.em = em;
        this.conn = c;
    }

    public List<VwGrupoAtividadeDTO> findGrupoAtividade() {
        Query q = conn.getEm().createNativeQuery("SELECT * FROM vw_grupo_atividade "
                + "ORDER BY id_grupo", VwGrupoAtividadeDTO.class);
        return q.getResultList();
    }

    public GrupoAtividade insertOrUpdate(VwGrupoAtividadeDTO obj) {
        GrupoAtividade gdVw = obj.toGrupoAtividade();
        Desafio d = em.merge(gdVw.getDesafio());
        em.flush();

        GrupoAtividade gd = findGrupoDesafio(gdVw.getGrupo(), d);
        gd.setVlAtingido(gdVw.getVlAtingido());
        gd.setSgAtingido(gdVw.getSgAtingido());
        gd.setDtAtingido(gdVw.getDtAtingido());

        gd = em.merge(gd);
        em.flush();
        return gd;
    }

    public GrupoAtividade findGrupoDesafio(Grupo g, Desafio d) {
        Query q = em.createNativeQuery("SELECT * FROM grupo_atividade "
                + "WHERE id_grupo = ?1 AND id_desafio = ?2", GrupoAtividade.class);
        q.setParameter(1, g.getId());
        q.setParameter(2, d.getId());
        List<GrupoAtividade> grupoDesafioList = q.getResultList();

        //Do not exists in DB, return a new one
        if (grupoDesafioList.isEmpty()) {
            GrupoAtividade gd = new GrupoAtividade(g, d);
            return gd;
        }
        return grupoDesafioList.get(0);
    }

}

package br.unisc.core.controller;

import br.unisc.web.dto.ConnectionDTO;
import br.unisc.core.dto.VwIndividuoAtividadeDTO;
import br.unisc.core.model.Desafio;
import br.unisc.core.model.Individuo;
import br.unisc.core.model.IndividuoAtividade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class VwIndividuoAtividadeDTOController {

    protected EntityManager em;
    protected ConnectionDTO conn;

    public VwIndividuoAtividadeDTOController(EntityManager em, ConnectionDTO c) {
        this.em = em;
        this.conn = c;
    }

    public List<VwIndividuoAtividadeDTO> findIndividuoAtividade() {
        Query q = conn.getEm().createNativeQuery("SELECT * FROM vw_individuo_atividade "
                + "ORDER BY id_individuo ", VwIndividuoAtividadeDTO.class);
        return q.getResultList();
    }

    public IndividuoAtividade insertOrUpdate(VwIndividuoAtividadeDTO obj) {
        IndividuoAtividade idVw = obj.toIndividuoAtividade();
        Desafio d = em.merge(idVw.getDesafio());
        em.flush();

        IndividuoAtividade id = findIndividuoAtividade(idVw.getIndividuo(), d);
        id.setVlAtingido(idVw.getVlAtingido());
        id.setSgAtingido(idVw.getSgAtingido());
        id.setDtAtingido(idVw.getDtAtingido());

        id = em.merge(id);
        em.flush();
        return id;
    }

    public IndividuoAtividade findIndividuoAtividade(Individuo i, Desafio d) {
        Query q = em.createNativeQuery("SELECT * FROM individuo_atividade "
                + "WHERE id_individuo = ?1 AND id_desafio = ?2", IndividuoAtividade.class);
        q.setParameter(1, i.getId());
        q.setParameter(2, d.getId());
        List<IndividuoAtividade> individuoAtividadeList = q.getResultList();

        //Do not exists in DB, return a new one
        if (individuoAtividadeList.isEmpty()) {
            IndividuoAtividade id = new IndividuoAtividade(i, d);
            return id;
        }
        return individuoAtividadeList.get(0);
    }

}

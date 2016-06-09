package br.unisc.core.dto;

import br.unisc.core.model.Grupo;
import br.unisc.core.model.Individuo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "vw_individuo_grupo")
public class VwIndividuoGrupoDTO {

    @Id
    @Column(name = "id")
    private Integer id;
    // player
    @Column(name = "id_individuo")
    private Integer idIndividuo;
    @Column(name = "nm_individuo")
    private String nmIndividuo;

    // player group
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Column(name = "nm_grupo")
    private String nmGrupo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdIndividuo() {
        return idIndividuo;
    }

    public void setIdIndividuo(Integer idIndividuo) {
        this.idIndividuo = idIndividuo;
    }

    public String getNmIndividuo() {
        return nmIndividuo;
    }

    public void setNmIndividuo(String nmIndividuo) {
        this.nmIndividuo = nmIndividuo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public Individuo toIndividuo() {
        Individuo i = new Individuo(idIndividuo, nmIndividuo, Boolean.TRUE);
        Grupo g = new Grupo(idGrupo, nmGrupo, Boolean.TRUE);
        i.setGrupo(g);
        return i;
    }

}

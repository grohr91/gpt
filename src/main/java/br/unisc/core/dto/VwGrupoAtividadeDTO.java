package br.unisc.core.dto;

import br.unisc.core.model.Desafio;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoAtividade;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "vw_grupo_atividade")
public class VwGrupoAtividadeDTO {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Column(name = "id_desafio")
    private Integer idDesafio;
    @Column(name = "nm_desafio")
    private String nmDesafio;
    @Column(name = "vl_atingido")
    private Float vlAtingido;
    @Column(name = "sg_atingido")
    private String sgAtingido;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atingido")
    private Date dtAtingido;

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdDesafio() {
        return idDesafio;
    }

    public void setIdDesafio(Integer idDesafio) {
        this.idDesafio = idDesafio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmDesafio() {
        return nmDesafio;
    }

    public void setNmDesafio(String nmDesafio) {
        this.nmDesafio = nmDesafio;
    }

    public Float getVlAtingido() {
        return vlAtingido;
    }

    public void setVlAtingido(Float vlAtingido) {
        this.vlAtingido = vlAtingido;
    }

    public String getSgAtingido() {
        return sgAtingido;
    }

    public void setSgAtingido(String sgAtingido) {
        this.sgAtingido = sgAtingido;
    }

    public Date getDtAtingido() {
        return dtAtingido;
    }

    public void setDtAtingido(Date dtAtingido) {
        this.dtAtingido = dtAtingido;
    }

    public GrupoAtividade toGrupoAtividade() {
        GrupoAtividade gd = new GrupoAtividade();
        Grupo g = new Grupo(idGrupo, Boolean.TRUE);
        Desafio d = new Desafio(idDesafio, nmDesafio, Boolean.TRUE);

        gd.setDesafio(d);
        gd.setGrupo(g);
        gd.setDtAtingido(dtAtingido);
        gd.setSgAtingido(sgAtingido);
        gd.setVlAtingido(vlAtingido);
        gd.setFgAtivo(Boolean.TRUE);
        return gd;
    }

}

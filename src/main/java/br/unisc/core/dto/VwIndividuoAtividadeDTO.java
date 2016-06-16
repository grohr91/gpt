package br.unisc.core.dto;

import br.unisc.core.model.Desafio;
import br.unisc.core.model.Individuo;
import br.unisc.core.model.IndividuoAtividade;
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
@Table(name = "vw_individuo_atividade")
public class VwIndividuoAtividadeDTO {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_individuo")
    private Integer idIndividuo;
    @Column(name = "id_desafio")
    private Integer idDesafio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_ocorrencia")
    private Date dtOcorrencia;
    @Column(name = "vl_atingido")
    private Float vlAtingido;
    @Column(name = "sg_atingido")
    private String sgAtingido;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atingido")
    private Date dtAtingido;
    @Column(name = "vl_planejado")
    private Float vlPlanejado;
    @Column(name = "sg_planejado")
    private String sgPlanejado;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_planejado")
    private Date dtPlanejado;

    public Integer getIdIndividuo() {
        return idIndividuo;
    }

    public void setIdIndividuo(Integer idIndividuo) {
        this.idIndividuo = idIndividuo;
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

    public Date getDtOcorrencia() {
        return dtOcorrencia;
    }

    public void setDtOcorrencia(Date dtOcorrencia) {
        this.dtOcorrencia = dtOcorrencia;
    }

    public Float getVlPlanejado() {
        return vlPlanejado;
    }

    public void setVlPlanejado(Float vlPlanejado) {
        this.vlPlanejado = vlPlanejado;
    }

    public String getSgPlanejado() {
        return sgPlanejado;
    }

    public void setSgPlanejado(String sgPlanejado) {
        this.sgPlanejado = sgPlanejado;
    }

    public Date getDtPlanejado() {
        return dtPlanejado;
    }

    public void setDtPlanejado(Date dtPlanejado) {
        this.dtPlanejado = dtPlanejado;
    }

    public IndividuoAtividade toIndividuoAtividade() {
        IndividuoAtividade id = new IndividuoAtividade();
        Individuo i = new Individuo(idIndividuo, Boolean.TRUE);
        Desafio d = new Desafio(idDesafio, Boolean.TRUE);

        id.setIndividuo(i);
        id.setDesafio(d);
        id.setDtAtingido(dtAtingido);
        id.setSgAtingido(sgAtingido);
        id.setVlAtingido(vlAtingido);
        id.setDtOcorrencia(dtOcorrencia);
        id.setDtPlanejado(dtPlanejado);
        id.setSgPlanejado(sgPlanejado);
        id.setVlPlanejado(vlPlanejado);
        id.setFgAtivo(Boolean.TRUE);
        return id;
    }

}

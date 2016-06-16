/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

import br.unisc.web.model.SysConfiguracao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "individuo_atividade")
@NamedQueries({
    @NamedQuery(name = "IndividuoAtividade.findAll", query = "SELECT i FROM IndividuoAtividade i"),
    @NamedQuery(name = "IndividuoAtividade.findById", query = "SELECT i FROM IndividuoAtividade i WHERE i.id = :id"),
    @NamedQuery(name = "IndividuoAtividade.findByIdExterno", query = "SELECT i FROM IndividuoAtividade i WHERE i.idExterno = :idExterno"),
    @NamedQuery(name = "IndividuoAtividade.findByDtOcorrencia", query = "SELECT i FROM IndividuoAtividade i WHERE i.dtOcorrencia = :dtOcorrencia"),
    @NamedQuery(name = "IndividuoAtividade.findByVlAtingido", query = "SELECT i FROM IndividuoAtividade i WHERE i.vlAtingido = :vlAtingido"),
    @NamedQuery(name = "IndividuoAtividade.findByDtAtingido", query = "SELECT i FROM IndividuoAtividade i WHERE i.dtAtingido = :dtAtingido"),
    @NamedQuery(name = "IndividuoAtividade.findBySgAtingido", query = "SELECT i FROM IndividuoAtividade i WHERE i.sgAtingido = :sgAtingido"),
    @NamedQuery(name = "IndividuoAtividade.findByDtUltimaSincronizacao", query = "SELECT i FROM IndividuoAtividade i WHERE i.dtUltimaSincronizacao = :dtUltimaSincronizacao")})
public class IndividuoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_externo")
    private int idExterno;
    @Column(name = "dt_ocorrencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtOcorrencia;
    @Column(name = "vl_atingido")
    private Float vlAtingido;
    @Column(name = "dt_atingido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtingido;
    @Column(name = "sg_atingido")
    private String sgAtingido;
    @Column(name = "vl_planejado")
    private Float vlPlanejado;
    @Column(name = "dt_planejado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPlanejado;
    @Column(name = "sg_planejado")
    private String sgPlanejado;
    @Basic(optional = false)
    @Column(name = "fg_ativo")
    private boolean fgAtivo;
    @Basic(optional = false)
    @Column(name = "dt_ultima_sincronizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaSincronizacao;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;
    @JoinColumn(name = "id_individuo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Individuo individuo;
    @JoinColumn(name = "id_desafio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Desafio desafio;

    public IndividuoAtividade() {
    }

    public IndividuoAtividade(Integer id) {
        this.id = id;
    }

    public IndividuoAtividade(Integer id, int idExterno, Date dtUltimaSincronizacao) {
        this.id = id;
        this.idExterno = idExterno;
        this.dtUltimaSincronizacao = dtUltimaSincronizacao;
    }

    public IndividuoAtividade(Individuo i, Desafio d, Boolean ativo) {
        this.individuo = i;
        this.desafio = d;
        this.fgAtivo = ativo;
        this.dtUltimaSincronizacao = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(int idExterno) {
        this.idExterno = idExterno;
    }

    public Date getDtOcorrencia() {
        return dtOcorrencia;
    }

    public void setDtOcorrencia(Date dtOcorrencia) {
        this.dtOcorrencia = dtOcorrencia;
    }

    public Float getVlAtingido() {
        return vlAtingido;
    }

    public void setVlAtingido(Float vlAtingido) {
        this.vlAtingido = vlAtingido;
    }

    public Date getDtAtingido() {
        return dtAtingido;
    }

    public void setDtAtingido(Date dtAtingido) {
        this.dtAtingido = dtAtingido;
    }

    public String getSgAtingido() {
        return sgAtingido;
    }

    public void setSgAtingido(String sgAtingido) {
        this.sgAtingido = sgAtingido;
    }

    public Date getDtUltimaSincronizacao() {
        return dtUltimaSincronizacao;
    }

    public void setDtUltimaSincronizacao(Date dtUltimaSincronizacao) {
        this.dtUltimaSincronizacao = dtUltimaSincronizacao;
    }

    public boolean isFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
    }

    public Individuo getIndividuo() {
        return individuo;
    }

    public void setIndividuo(Individuo individuo) {
        this.individuo = individuo;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndividuoAtividade)) {
            return false;
        }
        IndividuoAtividade other = (IndividuoAtividade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.IndividuoAtividade[ id=" + id + " ]";
    }

    public Float getVlPlanejado() {
        return vlPlanejado;
    }

    public void setVlPlanejado(Float vlPlanejado) {
        this.vlPlanejado = vlPlanejado;
    }

    public Date getDtPlanejado() {
        return dtPlanejado;
    }

    public void setDtPlanejado(Date dtPlanejado) {
        this.dtPlanejado = dtPlanejado;
    }

    public String getSgPlanejado() {
        return sgPlanejado;
    }

    public void setSgPlanejado(String sgPlanejado) {
        this.sgPlanejado = sgPlanejado;
    }

    public String getVal(String nmAtributo) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (StringUtils.isBlank(nmAtributo)) {
                return "";
            } else if (nmAtributo.equals("nm_desafio")) {
                if (this.desafio == null || StringUtils.isBlank(this.desafio.getNmDesafio())) {
                    return "null";
                }
                return this.desafio.getNmDesafio();
            } else if (nmAtributo.equals("nm_individuo")) {
                if (this.individuo == null || StringUtils.isBlank(this.individuo.getNmIndividuo())) {
                    return "null";
                }
                return this.individuo.getNmIndividuo();
            } else if (nmAtributo.equals("dt_ocorrencia")) {
                return sdf.format(this.dtOcorrencia);
            } else if (nmAtributo.equals("vl_atingido")) {
                return this.vlAtingido.toString();
            } else if (nmAtributo.equals("dt_atingido")) {
                return this.dtAtingido.toString();
            } else if (nmAtributo.equals("sg_atingido")) {
                return sdf.format(this.sgAtingido);
            } else if (nmAtributo.equals("vl_planejado")) {
                return this.vlPlanejado.toString();
            } else if (nmAtributo.equals("dt_planejado")) {
                return this.dtPlanejado.toString();
            } else if (nmAtributo.equals("sg_planejado")) {
                return this.sgPlanejado;
            }
        } catch (Exception ex) {

        }

        return "";
    }
}

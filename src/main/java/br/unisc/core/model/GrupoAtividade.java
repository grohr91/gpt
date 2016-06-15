/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

import br.unisc.web.model.SysConfiguracao;
import java.io.Serializable;
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

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "grupo_atividade")
@NamedQueries({
    @NamedQuery(name = "GrupoAtividade.findAll", query = "SELECT g FROM GrupoAtividade g"),
    @NamedQuery(name = "GrupoAtividade.findById", query = "SELECT g FROM GrupoAtividade g WHERE g.id = :id"),
    @NamedQuery(name = "GrupoAtividade.findByIdExterno", query = "SELECT g FROM GrupoAtividade g WHERE g.idExterno = :idExterno"),
    @NamedQuery(name = "GrupoAtividade.findByDtOcorrencia", query = "SELECT g FROM GrupoAtividade g WHERE g.dtOcorrencia = :dtOcorrencia"),
    @NamedQuery(name = "GrupoAtividade.findByVlAtingido", query = "SELECT g FROM GrupoAtividade g WHERE g.vlAtingido = :vlAtingido"),
    @NamedQuery(name = "GrupoAtividade.findByDtAtingido", query = "SELECT g FROM GrupoAtividade g WHERE g.dtAtingido = :dtAtingido"),
    @NamedQuery(name = "GrupoAtividade.findBySgAtingido", query = "SELECT g FROM GrupoAtividade g WHERE g.sgAtingido = :sgAtingido"),
    @NamedQuery(name = "GrupoAtividade.findByDtUltimaSincronizacao", query = "SELECT g FROM GrupoAtividade g WHERE g.dtUltimaSincronizacao = :dtUltimaSincronizacao")})
public class GrupoAtividade implements Serializable {

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
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grupo grupo;
    @JoinColumn(name = "id_desafio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Desafio desafio;

    public GrupoAtividade() {
    }

    public GrupoAtividade(Integer id) {
        this.id = id;
    }

    public GrupoAtividade(Grupo g, Desafio d) {
        this.grupo = g;
        this.desafio = d;
    }

    public GrupoAtividade(Integer id, int idExterno, Date dtUltimaSincronizacao) {
        this.id = id;
        this.idExterno = idExterno;
        this.dtUltimaSincronizacao = dtUltimaSincronizacao;
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
        if (!(object instanceof GrupoAtividade)) {
            return false;
        }
        GrupoAtividade other = (GrupoAtividade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.GrupoAtividade[ id=" + id + " ]";
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

import br.unisc.web.model.SysConfiguracao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "desafio")
@NamedQueries({
    @NamedQuery(name = "Desafio.findAll", query = "SELECT d FROM Desafio d ORDER BY d.nmDesafio"),
    @NamedQuery(name = "Desafio.findById", query = "SELECT d FROM Desafio d WHERE d.id = :id"),
    @NamedQuery(name = "Desafio.findByIdExterno", query = "SELECT d FROM Desafio d WHERE d.idExterno = :idExterno"),
    @NamedQuery(name = "Desafio.findByNmDesafio", query = "SELECT d FROM Desafio d WHERE d.nmDesafio = :nmDesafio"),
    @NamedQuery(name = "Desafio.findByDtUltimaSincronizacao", query = "SELECT d FROM Desafio d WHERE d.dtUltimaSincronizacao = :dtUltimaSincronizacao")})
public class Desafio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_externo")
    private int idExterno;
    @Basic(optional = false)
    @Column(name = "nm_desafio")
    private String nmDesafio;
    @Basic(optional = false)
    @Column(name = "fg_ativo")
    private boolean fgAtivo;
    @Basic(optional = false)
    @Column(name = "dt_ultima_sincronizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaSincronizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desafio")
    private List<Meta> metaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desafio")
    private List<IndividuoAtividade> individuoAtividadeList;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desafio")
    private List<GrupoAtividade> grupoAtividadeList;

    public Desafio() {
    }

    public Desafio(Integer idDesafio) {
        this.id = idDesafio;
    }

    public Desafio(Integer idDesafio, String nmDesafio) {
        this.idExterno = idDesafio;
        this.nmDesafio = nmDesafio;
    }

    public Desafio(Integer idDesafio, Boolean fgAtivo) {
        this.idExterno = idDesafio;
        this.nmDesafio = nmDesafio;
        this.fgAtivo = fgAtivo;
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

    public String getNmDesafio() {
        return nmDesafio;
    }

    public void setNmDesafio(String nmDesafio) {
        this.nmDesafio = nmDesafio;
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

    public List<Meta> getMetaList() {
        return metaList;
    }

    public void setMetaList(List<Meta> metaList) {
        this.metaList = metaList;
    }

    public List<IndividuoAtividade> getIndividuoAtividadeList() {
        return individuoAtividadeList;
    }

    public void setIndividuoAtividadeList(List<IndividuoAtividade> individuoAtividadeList) {
        this.individuoAtividadeList = individuoAtividadeList;
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
    }

    public List<GrupoAtividade> getGrupoAtividadeList() {
        return grupoAtividadeList;
    }

    public void setGrupoAtividadeList(List<GrupoAtividade> grupoAtividadeList) {
        this.grupoAtividadeList = grupoAtividadeList;
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
        if (!(object instanceof Desafio)) {
            return false;
        }
        Desafio other = (Desafio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Desafio[ id=" + id + " ]";
    }

}

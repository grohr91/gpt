/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

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
@Table(name = "meta")
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m"),
    @NamedQuery(name = "Meta.findById", query = "SELECT m FROM Meta m WHERE m.id = :id"),
    @NamedQuery(name = "Meta.findByNmMeta", query = "SELECT m FROM Meta m WHERE m.nmMeta = :nmMeta"),
    @NamedQuery(name = "Meta.findByDtDeadline", query = "SELECT m FROM Meta m WHERE m.dtDeadline = :dtDeadline"),
    @NamedQuery(name = "Meta.findByVlAtingir", query = "SELECT m FROM Meta m WHERE m.vlAtingir = :vlAtingir"),
    @NamedQuery(name = "Meta.findBySgAtingir", query = "SELECT m FROM Meta m WHERE m.sgAtingir = :sgAtingir"),
    @NamedQuery(name = "Meta.findByXpReconpensa", query = "SELECT m FROM Meta m WHERE m.xpReconpensa = :xpReconpensa"),
    @NamedQuery(name = "Meta.findByVlReconpensa", query = "SELECT m FROM Meta m WHERE m.vlReconpensa = :vlReconpensa")})
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_meta")
    private String nmMeta;
    @Column(name = "dt_deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDeadline;
    @Column(name = "vl_atingir")
    private Integer vlAtingir;
    @Column(name = "sg_atingir")
    private String sgAtingir;
    @Column(name = "xp_reconpensa")
    private Integer xpReconpensa;
    @Column(name = "vl_reconpensa")
    private Float vlReconpensa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meta")
    private List<ReconpensaEmblema> reconpensaEmblemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meta")
    private List<ReconpensaItem> reconpensaItemList;
    @JoinColumn(name = "id_desafio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Desafio desafio;

    public Meta() {
    }

    public Meta(Integer id) {
        this.id = id;
    }

    public Meta(Integer id, String nmMeta) {
        this.id = id;
        this.nmMeta = nmMeta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmMeta() {
        return nmMeta;
    }

    public void setNmMeta(String nmMeta) {
        this.nmMeta = nmMeta;
    }

    public Date getDtDeadline() {
        return dtDeadline;
    }

    public void setDtDeadline(Date dtDeadline) {
        this.dtDeadline = dtDeadline;
    }

    public Integer getVlAtingir() {
        return vlAtingir;
    }

    public void setVlAtingir(Integer vlAtingir) {
        this.vlAtingir = vlAtingir;
    }

    public String getSgAtingir() {
        return sgAtingir;
    }

    public void setSgAtingir(String sgAtingir) {
        this.sgAtingir = sgAtingir;
    }

    public Integer getXpReconpensa() {
        return xpReconpensa;
    }

    public void setXpReconpensa(Integer xpReconpensa) {
        this.xpReconpensa = xpReconpensa;
    }

    public Float getVlReconpensa() {
        return vlReconpensa;
    }

    public void setVlReconpensa(Float vlReconpensa) {
        this.vlReconpensa = vlReconpensa;
    }

    public List<ReconpensaEmblema> getReconpensaEmblemaList() {
        return reconpensaEmblemaList;
    }

    public void setReconpensaEmblemaList(List<ReconpensaEmblema> reconpensaEmblemaList) {
        this.reconpensaEmblemaList = reconpensaEmblemaList;
    }

    public List<ReconpensaItem> getReconpensaItemList() {
        return reconpensaItemList;
    }

    public void setReconpensaItemList(List<ReconpensaItem> reconpensaItemList) {
        this.reconpensaItemList = reconpensaItemList;
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
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Meta[ id=" + id + " ]";
    }

}

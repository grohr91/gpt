/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "emblema")
@NamedQueries({
    @NamedQuery(name = "Emblema.findAll", query = "SELECT e FROM Emblema e"),
    @NamedQuery(name = "Emblema.findById", query = "SELECT e FROM Emblema e WHERE e.id = :id"),
    @NamedQuery(name = "Emblema.findByNmEmblema", query = "SELECT e FROM Emblema e WHERE e.nmEmblema = :nmEmblema"),
    @NamedQuery(name = "Emblema.findByVlEmblema", query = "SELECT e FROM Emblema e WHERE e.vlEmblema = :vlEmblema")})
public class Emblema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_emblema")
    private String nmEmblema;
    @Basic(optional = false)
    @Column(name = "vl_emblema")
    private float vlEmblema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emblema")
    private List<ReconpensaEmblema> reconpensaEmblemaList;

    public Emblema() {
    }

    public Emblema(Integer id) {
        this.id = id;
    }

    public Emblema(Integer id, String nmEmblema, float vlEmblema) {
        this.id = id;
        this.nmEmblema = nmEmblema;
        this.vlEmblema = vlEmblema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmEmblema() {
        return nmEmblema;
    }

    public void setNmEmblema(String nmEmblema) {
        this.nmEmblema = nmEmblema;
    }

    public float getVlEmblema() {
        return vlEmblema;
    }

    public void setVlEmblema(float vlEmblema) {
        this.vlEmblema = vlEmblema;
    }

    public List<ReconpensaEmblema> getReconpensaEmblemaList() {
        return reconpensaEmblemaList;
    }

    public void setReconpensaEmblemaList(List<ReconpensaEmblema> reconpensaEmblemaList) {
        this.reconpensaEmblemaList = reconpensaEmblemaList;
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
        if (!(object instanceof Emblema)) {
            return false;
        }
        Emblema other = (Emblema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Emblema[ id=" + id + " ]";
    }
    
}

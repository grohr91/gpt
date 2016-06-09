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
@Table(name = "nivel")
@NamedQueries({
    @NamedQuery(name = "Nivel.findAll", query = "SELECT n FROM Nivel n"),
    @NamedQuery(name = "Nivel.findById", query = "SELECT n FROM Nivel n WHERE n.id = :id"),
    @NamedQuery(name = "Nivel.findByNrNivel", query = "SELECT n FROM Nivel n WHERE n.nrNivel = :nrNivel"),
    @NamedQuery(name = "Nivel.findByXpProximoNivel", query = "SELECT n FROM Nivel n WHERE n.xpProximoNivel = :xpProximoNivel")})
public class Nivel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nr_nivel")
    private int nrNivel;
    @Basic(optional = false)
    @Column(name = "xp_proximo_nivel")
    private float xpProximoNivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivel")
    private List<IndividuoNivel> individuoNivelList;

    public Nivel() {
    }

    public Nivel(Integer id) {
        this.id = id;
    }

    public Nivel(Integer id, int nrNivel, float xpProximoNivel) {
        this.id = id;
        this.nrNivel = nrNivel;
        this.xpProximoNivel = xpProximoNivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNrNivel() {
        return nrNivel;
    }

    public void setNrNivel(int nrNivel) {
        this.nrNivel = nrNivel;
    }

    public float getXpProximoNivel() {
        return xpProximoNivel;
    }

    public void setXpProximoNivel(float xpProximoNivel) {
        this.xpProximoNivel = xpProximoNivel;
    }

    public List<IndividuoNivel> getIndividuoNivelList() {
        return individuoNivelList;
    }

    public void setIndividuoNivelList(List<IndividuoNivel> individuoNivelList) {
        this.individuoNivelList = individuoNivelList;
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
        if (!(object instanceof Nivel)) {
            return false;
        }
        Nivel other = (Nivel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Nivel[ id=" + id + " ]";
    }
    
}

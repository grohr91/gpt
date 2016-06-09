/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

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
@Table(name = "individuo_nivel")
@NamedQueries({
    @NamedQuery(name = "IndividuoNivel.findAll", query = "SELECT i FROM IndividuoNivel i"),
    @NamedQuery(name = "IndividuoNivel.findById", query = "SELECT i FROM IndividuoNivel i WHERE i.id = :id"),
    @NamedQuery(name = "IndividuoNivel.findByDtInicio", query = "SELECT i FROM IndividuoNivel i WHERE i.dtInicio = :dtInicio"),
    @NamedQuery(name = "IndividuoNivel.findByDtFim", query = "SELECT i FROM IndividuoNivel i WHERE i.dtFim = :dtFim"),
    @NamedQuery(name = "IndividuoNivel.findByFgAtual", query = "SELECT i FROM IndividuoNivel i WHERE i.fgAtual = :fgAtual")})
public class IndividuoNivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dt_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInicio;
    @Column(name = "dt_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFim;
    @Basic(optional = false)
    @Column(name = "fg_atual")
    private boolean fgAtual;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Nivel nivel;
    @JoinColumn(name = "id_individuo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Individuo individuo;

    public IndividuoNivel() {
    }

    public IndividuoNivel(Integer id) {
        this.id = id;
    }

    public IndividuoNivel(Integer id, boolean fgAtual) {
        this.id = id;
        this.fgAtual = fgAtual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public boolean getFgAtual() {
        return fgAtual;
    }

    public void setFgAtual(boolean fgAtual) {
        this.fgAtual = fgAtual;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Individuo getIndividuo() {
        return individuo;
    }

    public void setIndividuo(Individuo individuo) {
        this.individuo = individuo;
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
        if (!(object instanceof IndividuoNivel)) {
            return false;
        }
        IndividuoNivel other = (IndividuoNivel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.IndividuoNivel[ id=" + id + " ]";
    }

}

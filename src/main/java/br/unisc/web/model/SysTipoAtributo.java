/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.web.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "sys_tipo_atributo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysTipoAtributo.findAll", query = "SELECT s FROM SysTipoAtributo s"),
    @NamedQuery(name = "SysTipoAtributo.findById", query = "SELECT s FROM SysTipoAtributo s WHERE s.id = :id"),
    @NamedQuery(name = "SysTipoAtributo.findByNmTipoAtributo", query = "SELECT s FROM SysTipoAtributo s WHERE s.nmTipoAtributo = :nmTipoAtributo")})
public class SysTipoAtributo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_tipo_atributo")
    private String nmTipoAtributo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAtributo")
    private List<SysTipoAtributoOperacao> sysTipoAtributoOperacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAtributo")
    private List<SysAtributo> sysAtributoList;

    public SysTipoAtributo() {
    }

    public SysTipoAtributo(Integer id) {
        this.id = id;
    }

    public SysTipoAtributo(Integer id, String nmTipoAtributo) {
        this.id = id;
        this.nmTipoAtributo = nmTipoAtributo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmTipoAtributo() {
        return nmTipoAtributo;
    }

    public void setNmTipoAtributo(String nmTipoAtributo) {
        this.nmTipoAtributo = nmTipoAtributo;
    }

    @XmlTransient
    public List<SysTipoAtributoOperacao> getSysTipoAtributoOperacaoList() {
        return sysTipoAtributoOperacaoList;
    }

    public void setSysTipoAtributoOperacaoList(List<SysTipoAtributoOperacao> sysTipoAtributoOperacaoList) {
        this.sysTipoAtributoOperacaoList = sysTipoAtributoOperacaoList;
    }

    @XmlTransient
    public List<SysAtributo> getSysAtributoList() {
        return sysAtributoList;
    }

    public void setSysAtributoList(List<SysAtributo> sysAtributoList) {
        this.sysAtributoList = sysAtributoList;
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
        if (!(object instanceof SysTipoAtributo)) {
            return false;
        }
        SysTipoAtributo other = (SysTipoAtributo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysTipoAtributo[ id=" + id + " ]";
    }
    
}

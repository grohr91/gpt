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
@Table(name = "sys_tabela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysTabela.findAll", query = "SELECT s FROM SysTabela s ORDER BY s.nmTabela"),
    @NamedQuery(name = "SysTabela.findById", query = "SELECT s FROM SysTabela s WHERE s.id = :id"),
    @NamedQuery(name = "SysTabela.findByNmTabela", query = "SELECT s FROM SysTabela s WHERE s.nmTabela = :nmTabela")})
public class SysTabela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_tabela")
    private String nmTabela;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabela")
    private List<SysRegraTabela> sysRegraTabelaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabela")
    private List<SysAtributo> sysAtributoList;

    public SysTabela() {
    }

    public SysTabela(Integer id) {
        this.id = id;
    }

    public SysTabela(Integer id, String nmTabela) {
        this.id = id;
        this.nmTabela = nmTabela;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmTabela() {
        return nmTabela;
    }

    public void setNmTabela(String nmTabela) {
        this.nmTabela = nmTabela;
    }

    @XmlTransient
    public List<SysRegraTabela> getSysRegraTabelaList() {
        return sysRegraTabelaList;
    }

    public void setSysRegraTabelaList(List<SysRegraTabela> sysRegraTabelaList) {
        this.sysRegraTabelaList = sysRegraTabelaList;
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
        if (!(object instanceof SysTabela)) {
            return false;
        }
        SysTabela other = (SysTabela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysTabela[ id=" + id + " ]";
    }
    
}

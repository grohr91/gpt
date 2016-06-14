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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sys_atributo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysAtributo.findAll", query = "SELECT s FROM SysAtributo s"),
    @NamedQuery(name = "SysAtributo.findById", query = "SELECT s FROM SysAtributo s WHERE s.id = :id"),
    @NamedQuery(name = "SysAtributo.findByNmAtributo", query = "SELECT s FROM SysAtributo s WHERE s.nmAtributo = :nmAtributo"),
    @NamedQuery(name = "SysAtributo.findByIdTabela", query = "SELECT s FROM SysAtributo s WHERE s.tabela.id = :idTabela ORDER BY s.nmAtributo")
})
public class SysAtributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_atributo")
    private String nmAtributo;
    @JoinColumn(name = "id_tipo_atributo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysTipoAtributo tipoAtributo;
    @JoinColumn(name = "id_tabela", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysTabela tabela;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributo")
    private List<SysRegra> sysRegraList;

    public SysAtributo() {
    }

    public SysAtributo(Integer id) {
        this.id = id;
    }

    public SysAtributo(Integer id, String nmAtributo) {
        this.id = id;
        this.nmAtributo = nmAtributo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmAtributo() {
        return nmAtributo;
    }

    public void setNmAtributo(String nmAtributo) {
        this.nmAtributo = nmAtributo;
    }

    public SysTipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(SysTipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public SysTabela getTabela() {
        return tabela;
    }

    public void setTabela(SysTabela tabela) {
        this.tabela = tabela;
    }

    @XmlTransient
    public List<SysRegra> getSysRegraList() {
        return sysRegraList;
    }

    public void setSysRegraList(List<SysRegra> sysRegraList) {
        this.sysRegraList = sysRegraList;
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
        if (!(object instanceof SysAtributo)) {
            return false;
        }
        SysAtributo other = (SysAtributo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysAtributo[ id=" + id + " ]";
    }

}

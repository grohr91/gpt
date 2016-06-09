/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.web.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "sys_regra_filtro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysRegraFiltro.findAll", query = "SELECT s FROM SysRegraFiltro s"),
    @NamedQuery(name = "SysRegraFiltro.findById", query = "SELECT s FROM SysRegraFiltro s WHERE s.id = :id"),
    @NamedQuery(name = "SysRegraFiltro.findByFgAtivo", query = "SELECT s FROM SysRegraFiltro s WHERE s.fgAtivo = :fgAtivo")})
public class SysRegraFiltro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fg_ativo")
    private boolean fgAtivo;
    @JoinColumn(name = "id_tabela", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysTabela tabela;
    @OneToMany(mappedBy = "regraFiltro")
    private List<SysRegra> sysRegraList;

    public SysRegraFiltro() {
    }

    public SysRegraFiltro(Integer id) {
        this.id = id;
    }

    public SysRegraFiltro(Integer id, boolean fgAtivo) {
        this.id = id;
        this.fgAtivo = fgAtivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
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
        if (!(object instanceof SysRegraFiltro)) {
            return false;
        }
        SysRegraFiltro other = (SysRegraFiltro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysRegraFiltro[ id=" + id + " ]";
    }

}

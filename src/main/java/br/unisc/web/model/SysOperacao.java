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
@Table(name = "sys_operacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysOperacao.findAll", query = "SELECT s FROM SysOperacao s"),
    @NamedQuery(name = "SysOperacao.findById", query = "SELECT s FROM SysOperacao s WHERE s.id = :id"),
    @NamedQuery(name = "SysOperacao.findByNmOperacao", query = "SELECT s FROM SysOperacao s WHERE s.nmOperacao = :nmOperacao")})
public class SysOperacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_operacao")
    private String nmOperacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operacao")
    private List<SysTipoAtributoOperacao> sysTipoAtributoOperacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operacao")
    private List<SysRegra> sysRegraList;

    public SysOperacao() {
    }

    public SysOperacao(Integer id) {
        this.id = id;
    }

    public SysOperacao(Integer id, String nmOperacao) {
        this.id = id;
        this.nmOperacao = nmOperacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmOperacao() {
        return nmOperacao;
    }

    public void setNmOperacao(String nmOperacao) {
        this.nmOperacao = nmOperacao;
    }

    @XmlTransient
    public List<SysTipoAtributoOperacao> getSysTipoAtributoOperacaoList() {
        return sysTipoAtributoOperacaoList;
    }

    public void setSysTipoAtributoOperacaoList(List<SysTipoAtributoOperacao> sysTipoAtributoOperacaoList) {
        this.sysTipoAtributoOperacaoList = sysTipoAtributoOperacaoList;
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
        if (!(object instanceof SysOperacao)) {
            return false;
        }
        SysOperacao other = (SysOperacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysOperacao[ id=" + id + " ]";
    }

}

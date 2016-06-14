/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.web.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "sys_tipo_atributo_operacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysTipoAtributoOperacao.findAll", query = "SELECT s FROM SysTipoAtributoOperacao s"),
    @NamedQuery(name = "SysTipoAtributoOperacao.findById", query = "SELECT s FROM SysTipoAtributoOperacao s WHERE s.id = :id"),
    @NamedQuery(name = "SysTipoAtributoOperacao.findByTipoAtributo", query = "SELECT s FROM SysTipoAtributoOperacao s WHERE s.tipoAtributo.id = :idTipoAtributo")
})
public class SysTipoAtributoOperacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_tipo_atributo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysTipoAtributo tipoAtributo;
    @JoinColumn(name = "id_operacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysOperacao operacao;

    public SysTipoAtributoOperacao() {
    }

    public SysTipoAtributoOperacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysTipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(SysTipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public SysOperacao getOperacao() {
        return operacao;
    }

    public void setOperacao(SysOperacao operacao) {
        this.operacao = operacao;
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
        if (!(object instanceof SysTipoAtributoOperacao)) {
            return false;
        }
        SysTipoAtributoOperacao other = (SysTipoAtributoOperacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysTipoAtributoOperacao[ id=" + id + " ]";
    }

}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "sys_regra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysRegra.findAll", query = "SELECT s FROM SysRegra s"),
    @NamedQuery(name = "SysRegra.findById", query = "SELECT s FROM SysRegra s WHERE s.id = :id"),
    @NamedQuery(name = "SysRegra.findByVlRegra", query = "SELECT s FROM SysRegra s WHERE s.vlRegra = :vlRegra")})
public class SysRegra implements Serializable {

    public static final int SG_TIPO_REGRA_FILTRO = 1;
    public static final int SG_TIPO_REGRA_REMOCAO = 2;
    public static final int SG_TIPO_REGRA_TRANSFORMACAO = 3;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "vl_regra")
    private String vlRegra;
    @Basic(optional = false)
    @Column(name = "vl_regra_novo")
    private String vlRegraNovo;
    @Basic(optional = false)
    @Column(name = "sg_tipo_regra")
    private Integer sgTipoRegra;
    @JoinColumn(name = "id_regra_tabela", referencedColumnName = "id")
    @ManyToOne
    private SysRegraTabela regraTabela;
    @JoinColumn(name = "id_operacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysOperacao operacao;
    @JoinColumn(name = "id_atributo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysAtributo atributo;

    public SysRegra() {
    }

    public SysRegra(Integer id) {
        this.id = id;
    }

    public SysRegra(Integer id, String vlRegra) {
        this.id = id;
        this.vlRegra = vlRegra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVlRegra() {
        return vlRegra;
    }

    public void setVlRegra(String vlRegra) {
        this.vlRegra = vlRegra;
    }

    public SysRegraTabela getRegraTabela() {
        return regraTabela;
    }

    public void setRegraTabela(SysRegraTabela regraTabela) {
        this.regraTabela = regraTabela;
    }

    public Integer getSgTipoRegra() {
        return sgTipoRegra;
    }

    public void setSgTipoRegra(Integer sgTipoRegra) {
        this.sgTipoRegra = sgTipoRegra;
    }

    public SysOperacao getOperacao() {
        return operacao;
    }

    public void setOperacao(SysOperacao operacao) {
        this.operacao = operacao;
    }

    public SysAtributo getAtributo() {
        return atributo;
    }

    public void setAtributo(SysAtributo atributo) {
        this.atributo = atributo;
    }

    public String getVlRegraNovo() {
        return vlRegraNovo;
    }

    public void setVlRegraNovo(String vlRegraNovo) {
        this.vlRegraNovo = vlRegraNovo;
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
        if (!(object instanceof SysRegra)) {
            return false;
        }
        SysRegra other = (SysRegra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysRegra[ id=" + id + " ]";
    }

}

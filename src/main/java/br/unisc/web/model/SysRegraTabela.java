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
@Table(name = "sys_regra_tabela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysRegraTabela.findAll", query = "SELECT s FROM SysRegraTabela s"),
    @NamedQuery(name = "SysRegraTabela.findById", query = "SELECT s FROM SysRegraTabela s WHERE s.id = :id"),
    @NamedQuery(name = "SysRegraTabela.findByFgImportar", query = "SELECT s FROM SysRegraTabela s WHERE s.fgImportar = :fgImportar"),
    @NamedQuery(name = "SysRegraTabela.findBySgTipoInsercao", query = "SELECT s FROM SysRegraTabela s WHERE s.sgTipoInsercao = :sgTipoInsercao"),
    @NamedQuery(name = "SysRegraTabela.findBySgTipoRemocao", query = "SELECT s FROM SysRegraTabela s WHERE s.sgTipoRemocao = :sgTipoRemocao")})
public class SysRegraTabela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fg_importar")
    private boolean fgImportar;
    @Basic(optional = false)
    @Column(name = "sg_tipo_insercao")
    private int sgTipoInsercao;
    @Basic(optional = false)
    @Column(name = "sg_tipo_remocao")
    private int sgTipoRemocao;
    @JoinColumn(name = "id_tabela", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysTabela tabela;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;
    @OneToMany(mappedBy = "regraTabela")
    private List<SysRegra> sysRegraList;

    public SysRegraTabela() {
    }

    public SysRegraTabela(Integer id) {
        this.id = id;
    }

    public SysRegraTabela(Integer id, boolean fgImportar, int sgTipoInsercao, int sgTipoRemocao) {
        this.id = id;
        this.fgImportar = fgImportar;
        this.sgTipoInsercao = sgTipoInsercao;
        this.sgTipoRemocao = sgTipoRemocao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getFgImportar() {
        return fgImportar;
    }

    public void setFgImportar(boolean fgImportar) {
        this.fgImportar = fgImportar;
    }

    public int getSgTipoInsercao() {
        return sgTipoInsercao;
    }

    public void setSgTipoInsercao(int sgTipoInsercao) {
        this.sgTipoInsercao = sgTipoInsercao;
    }

    public int getSgTipoRemocao() {
        return sgTipoRemocao;
    }

    public void setSgTipoRemocao(int sgTipoRemocao) {
        this.sgTipoRemocao = sgTipoRemocao;
    }

    public SysTabela getTabela() {
        return tabela;
    }

    public void setTabela(SysTabela tabela) {
        this.tabela = tabela;
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
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
        if (!(object instanceof SysRegraTabela)) {
            return false;
        }
        SysRegraTabela other = (SysRegraTabela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysRegraTabela[ id=" + id + " ]";
    }

}

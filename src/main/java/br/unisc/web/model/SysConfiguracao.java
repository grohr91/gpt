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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "sys_configuracao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysConfiguracao.findAll", query = "SELECT s FROM SysConfiguracao s ORDER BY s.nmConfiguracao"),
    @NamedQuery(name = "SysConfiguracao.findById", query = "SELECT s FROM SysConfiguracao s WHERE s.id = :id"),
    @NamedQuery(name = "SysConfiguracao.findByNmConfiguracao", query = "SELECT s FROM SysConfiguracao s WHERE s.nmConfiguracao = :nmConfiguracao"),
    @NamedQuery(name = "SysConfiguracao.findBySgTipoImportacao", query = "SELECT s FROM SysConfiguracao s WHERE s.sgTipoImportacao = :sgTipoImportacao"),
    @NamedQuery(name = "SysConfiguracao.findBySgTipoBd", query = "SELECT s FROM SysConfiguracao s WHERE s.sgTipoBd = :sgTipoBd"),
    @NamedQuery(name = "SysConfiguracao.findByNrIpHost", query = "SELECT s FROM SysConfiguracao s WHERE s.nrIpHost = :nrIpHost"),
    @NamedQuery(name = "SysConfiguracao.findByNrPort", query = "SELECT s FROM SysConfiguracao s WHERE s.nrPort = :nrPort"),
    @NamedQuery(name = "SysConfiguracao.findByNmSchema", query = "SELECT s FROM SysConfiguracao s WHERE s.nmSchema = :nmSchema"),
    @NamedQuery(name = "SysConfiguracao.findByNmDatabase", query = "SELECT s FROM SysConfiguracao s WHERE s.nmDatabase = :nmDatabase"),
    @NamedQuery(name = "SysConfiguracao.findByNmUser", query = "SELECT s FROM SysConfiguracao s WHERE s.nmUser = :nmUser"),
    @NamedQuery(name = "SysConfiguracao.findByCdPass", query = "SELECT s FROM SysConfiguracao s WHERE s.cdPass = :cdPass"),
    @NamedQuery(name = "SysConfiguracao.findByDsDiretorioArquivos", query = "SELECT s FROM SysConfiguracao s WHERE s.dsDiretorioArquivos = :dsDiretorioArquivos")})
public class SysConfiguracao implements Serializable {

    public static final int TIPO_IMPORTACAO_BANCO_DE_DADOS = 1;
    public static final int TIPO_IMPORTACAO_CSV = 2;
    public static final int TIPO_BD_POSTGRES = 1;
    public static final int TIPO_BD_MYSQL = 2;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_identificador")
    private Integer idIdentificador;
    @Basic(optional = false)
    @Column(name = "nm_configuracao")
    private String nmConfiguracao;
    @Basic(optional = false)
    @Column(name = "sg_tipo_importacao")
    private Integer sgTipoImportacao;
    @Column(name = "sg_tipo_bd")
    private Integer sgTipoBd;
    @Column(name = "nr_ip_host")
    private String nrIpHost;
    @Column(name = "nr_port")
    private Integer nrPort;
    @Column(name = "nm_schema")
    private String nmSchema;
    @Column(name = "nm_database")
    private String nmDatabase;
    @Column(name = "nm_user")
    private String nmUser;
    @Column(name = "cd_pass")
    private String cdPass;
    @Column(name = "ds_diretorio_arquivos")
    private String dsDiretorioArquivos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "configuracao")
    private List<SysAutomacao> automacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "configuracao")
    private List<SysRegraExtracao> regraExtracaoList;

    public SysConfiguracao() {
    }

    public SysConfiguracao(Integer id) {
        this.id = id;
    }

    public SysConfiguracao(Integer id, String nmConfiguracao, Integer sgTipoImportacao) {
        this.id = id;
        this.nmConfiguracao = nmConfiguracao;
        this.sgTipoImportacao = sgTipoImportacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmConfiguracao() {
        return nmConfiguracao;
    }

    public void setNmConfiguracao(String nmConfiguracao) {
        this.nmConfiguracao = nmConfiguracao;
    }

    public Integer getSgTipoImportacao() {
        return sgTipoImportacao;
    }

    public void setSgTipoImportacao(Integer sgTipoImportacao) {
        this.sgTipoImportacao = sgTipoImportacao;
    }

    public Integer getSgTipoBd() {
        return sgTipoBd;
    }

    public void setSgTipoBd(Integer sgTipoBd) {
        this.sgTipoBd = sgTipoBd;
    }

    public String getNrIpHost() {
        return nrIpHost;
    }

    public void setNrIpHost(String nrIpHost) {
        this.nrIpHost = nrIpHost;
    }

    public Integer getNrPort() {
        return nrPort;
    }

    public void setNrPort(Integer nrPort) {
        this.nrPort = nrPort;
    }

    public String getNmSchema() {
        return nmSchema;
    }

    public void setNmSchema(String nmSchema) {
        this.nmSchema = nmSchema;
    }

    public String getNmDatabase() {
        return nmDatabase;
    }

    public void setNmDatabase(String nmDatabase) {
        this.nmDatabase = nmDatabase;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getCdPass() {
        return cdPass;
    }

    public void setCdPass(String cdPass) {
        this.cdPass = cdPass;
    }

    public String getDsDiretorioArquivos() {
        return dsDiretorioArquivos;
    }

    public void setDsDiretorioArquivos(String dsDiretorioArquivos) {
        this.dsDiretorioArquivos = dsDiretorioArquivos;
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
        if (!(object instanceof SysConfiguracao)) {
            return false;
        }
        SysConfiguracao other = (SysConfiguracao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysConfiguracao[ id=" + id + " ]";
    }

    public Integer getIdIdentificador() {
        return idIdentificador;
    }

    public void setIdIdentificador(Integer idIdentificador) {
        this.idIdentificador = idIdentificador;
    }

    public List<SysAutomacao> getAutomacaoList() {
        return automacaoList;
    }

    public void setAutomacaoList(List<SysAutomacao> automacaoList) {
        this.automacaoList = automacaoList;
    }

    public List<SysRegraExtracao> getRegraExtracaoList() {
        return regraExtracaoList;
    }

    public void setRegraExtracaoList(List<SysRegraExtracao> regraExtracaoList) {
        this.regraExtracaoList = regraExtracaoList;
    }

}

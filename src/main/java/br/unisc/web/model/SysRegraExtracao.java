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

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "sys_regra_extracao")
@NamedQueries({
    @NamedQuery(name = "SysRegraExtracao.findAll", query = "SELECT s FROM SysRegraExtracao s"),
    @NamedQuery(name = "SysRegraExtracao.findById", query = "SELECT s FROM SysRegraExtracao s WHERE s.id = :id"),
    @NamedQuery(name = "SysRegraExtracao.findByNmView", query = "SELECT s FROM SysRegraExtracao s WHERE s.nmView = :nmView"),
    @NamedQuery(name = "SysRegraExtracao.findBySqlView", query = "SELECT s FROM SysRegraExtracao s WHERE s.sqlView = :sqlView"),
    @NamedQuery(name = "SysRegraExtracao.findByConfiguracao", query = "SELECT s FROM SysRegraExtracao s WHERE s.configuracao.id = :idConfiguracao ORDER BY s.nmView")
})
public class SysRegraExtracao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_view")
    private String nmView;
    @Basic(optional = false)
    @Column(name = "sql_view")
    private String sqlView;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;

    public SysRegraExtracao() {
    }

    public SysRegraExtracao(Integer id) {
        this.id = id;
    }

    public SysRegraExtracao(String nmView, SysConfiguracao configuracao) {
        this.nmView = nmView;
        this.configuracao = configuracao;
        this.sqlView = "";
    }

    public SysRegraExtracao(Integer id, String nmView, String sqlView) {
        this.id = id;
        this.nmView = nmView;
        this.sqlView = sqlView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmView() {
        return nmView;
    }

    public void setNmView(String nmView) {
        this.nmView = nmView;
    }

    public String getSqlView() {
        return sqlView;
    }

    public void setSqlView(String sqlView) {
        this.sqlView = sqlView;
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
        if (!(object instanceof SysRegraExtracao)) {
            return false;
        }
        SysRegraExtracao other = (SysRegraExtracao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysRegraExtracao[ id=" + id + " ]";
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
    }

    public String getDsInfo() {
        if ("vw_individuo_grupo".equals(nmView)) {
            return "CREATE OR REPLACE VIEW vw_individuo_grupo AS \n"
                    + " SELECT t.id,\n"
                    + "    t.id_individuo,\n"
                    + "    t.nm_individuo,\n"
                    + "    t.dt_nascimento,\n"
                    + "    t.id_grupo,\n"
                    + "    t.nm_grupo\n"
                    + "   FROM ( "
                    + " [SELECT PARA RETORNAR ESTES VALORES]"
                    + ") t;";
        } else if ("vw_individuo_atividade".equals(nmView)) {
            return "CREATE OR REPLACE VIEW vw_individuo_atividade AS \n"
                    + " SELECT t.id,\n"
                    + "    t.id_individuo,\n"
                    + "    t.nm_individuo,\n"
                    + "    t.dt_nascimento,\n"
                    + "    t.id_grupo,\n"
                    + "    t.nm_grupo\n"
                    + "   FROM ( "
                    + " [SELECT PARA RETORNAR ESTES VALORES]"
                    + ") t;";
        } else if ("vw_grupo_atividade".equals(nmView)) {
            return "CREATE OR REPLACE VIEW public.vw_grupo_atividade AS \n"
                    + " SELECT t.id,\n"
                    + "    t.id_desafio,\n"
                    + "    t.nm_desafio,\n"
                    + "    t.id_grupo,\n"
                    + "    t.vl_atingido,\n"
                    + "    t.sg_atingido,\n"
                    + "    t.dt_atingido\n"
                    + "   FROM (\n"
                    + " [SELECT PARA RETORNAR ESTES VALORES]"
                    + ") t;";
        }
        return "";
    }
}

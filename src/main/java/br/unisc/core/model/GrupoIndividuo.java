/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

import br.unisc.web.model.SysConfiguracao;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "grupo_individuo")
@NamedQueries({
    @NamedQuery(name = "GrupoIndividuo.findAll", query = "SELECT g FROM GrupoIndividuo g"),
    @NamedQuery(name = "GrupoIndividuo.findById", query = "SELECT g FROM GrupoIndividuo g WHERE g.id = :id"),
    @NamedQuery(name = "GrupoIndividuo.findByFgAtivo", query = "SELECT g FROM GrupoIndividuo g WHERE g.fgAtivo = :fgAtivo")})
public class GrupoIndividuo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fg_ativo")
    private boolean fgAtivo;
    @Basic(optional = false)
    @Column(name = "dt_ultima_sincronizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaSincronizacao;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;
    @JoinColumn(name = "id_individuo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Individuo individuo;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grupo grupo;

    public GrupoIndividuo() {
    }

    public GrupoIndividuo(Integer id) {
        this.id = id;
    }

    public GrupoIndividuo(Integer id, boolean fgAtivo, Date dtUltimaAtualizacao) {
        this.id = id;
        this.fgAtivo = fgAtivo;
        this.dtUltimaSincronizacao = dtUltimaAtualizacao;
    }

    public GrupoIndividuo(Grupo g, Individuo i, boolean fgAtivo) {
        this.grupo = g;
        this.individuo = i;
        this.fgAtivo = fgAtivo;
        this.dtUltimaSincronizacao = new Date();
    }

    public GrupoIndividuo(Grupo g, Individuo i) {
        this.grupo = g;
        this.individuo = i;
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

    public Date getDtUltimaSincronizacao() {
        return dtUltimaSincronizacao;
    }

    public void setDtUltimaSincronizacao(Date dtUltimaSincronizacao) {
        this.dtUltimaSincronizacao = dtUltimaSincronizacao;
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
    }

    public Individuo getIndividuo() {
        return individuo;
    }

    public void setIndividuo(Individuo individuo) {
        this.individuo = individuo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
        if (!(object instanceof GrupoIndividuo)) {
            return false;
        }
        GrupoIndividuo other = (GrupoIndividuo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.GrupoIndividuo[ id=" + id + " ]";
    }

}

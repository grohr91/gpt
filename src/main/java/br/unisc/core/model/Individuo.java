/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

import br.unisc.web.model.SysConfiguracao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "individuo")
@NamedQueries({
    @NamedQuery(name = "Individuo.findAll", query = "SELECT i FROM Individuo i"),
    @NamedQuery(name = "Individuo.findById", query = "SELECT i FROM Individuo i WHERE i.id = :id"),
    @NamedQuery(name = "Individuo.findByIdExterno", query = "SELECT i FROM Individuo i WHERE i.idExterno = :idExterno"),
    @NamedQuery(name = "Individuo.findByNmIndividuo", query = "SELECT i FROM Individuo i WHERE i.nmIndividuo = :nmIndividuo"),
    @NamedQuery(name = "Individuo.findByFgAtivo", query = "SELECT i FROM Individuo i WHERE i.fgAtivo = :fgAtivo"),
    @NamedQuery(name = "Individuo.findByDtUltimaAtualizacao", query = "SELECT i FROM Individuo i WHERE i.dtUltimaAtualizacao = :dtUltimaAtualizacao"),
    @NamedQuery(name = "Individuo.findByXpAtual", query = "SELECT i FROM Individuo i WHERE i.xpAtual = :xpAtual"),
    @NamedQuery(name = "Individuo.findByQtAtividadesConcluidas", query = "SELECT i FROM Individuo i WHERE i.qtAtividadesConcluidas = :qtAtividadesConcluidas"),
    @NamedQuery(name = "Individuo.findByQtDesafiosConcluidos", query = "SELECT i FROM Individuo i WHERE i.qtDesafiosConcluidos = :qtDesafiosConcluidos"),
    @NamedQuery(name = "Individuo.findByQtMetasConcluidas", query = "SELECT i FROM Individuo i WHERE i.qtMetasConcluidas = :qtMetasConcluidas"),
    @NamedQuery(name = "Individuo.findByQtEmblemas", query = "SELECT i FROM Individuo i WHERE i.qtEmblemas = :qtEmblemas"),
    @NamedQuery(name = "Individuo.findByQtItens", query = "SELECT i FROM Individuo i WHERE i.qtItens = :qtItens"),
    @NamedQuery(name = "Individuo.findByQtVida", query = "SELECT i FROM Individuo i WHERE i.qtVida = :qtVida"),
    @NamedQuery(name = "Individuo.findByVlDinehiro", query = "SELECT i FROM Individuo i WHERE i.vlDinehiro = :vlDinehiro")})
public class Individuo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_externo")
    private int idExterno;
    @Basic(optional = false)
    @Column(name = "nm_individuo")
    private String nmIndividuo;
    @Basic(optional = false)
    @Column(name = "fg_ativo")
    private boolean fgAtivo;
    @Basic(optional = false)
    @Column(name = "dt_ultima_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAtualizacao;
    @Column(name = "xp_atual")
    private Integer xpAtual;
    @Column(name = "qt_atividades_concluidas")
    private Integer qtAtividadesConcluidas;
    @Column(name = "qt_desafios_concluidos")
    private Integer qtDesafiosConcluidos;
    @Column(name = "qt_metas_concluidas")
    private Integer qtMetasConcluidas;
    @Column(name = "qt_emblemas")
    private Integer qtEmblemas;
    @Column(name = "qt_itens")
    private Integer qtItens;
    @Column(name = "qt_vida")
    private Integer qtVida;
    @Column(name = "vl_dinehiro")
    private Float vlDinehiro;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individuo")
    private List<GrupoIndividuo> grupoIndividuoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individuo")
    private List<IndividuoNivel> individuoNivelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "individuo")
    private List<IndividuoAtividade> individuoAtividadeList;

    @Transient
    private Grupo grupo;

    public Individuo() {
    }

    public Individuo(Integer idIndividuo) {
        this.id = idIndividuo;
    }

    public Individuo(Integer idIndividuo, Boolean fgAtivo) {
        this.id = idIndividuo;
        this.fgAtivo = fgAtivo;
    }

    public Individuo(Integer idIndividuo, String nmIndividuo) {
        this.id = idIndividuo;
        this.nmIndividuo = nmIndividuo;
    }

    public Individuo(Integer idIndividuo, String nmIndividuo, Boolean fgAtivo) {
        this.id = idIndividuo;
        this.nmIndividuo = nmIndividuo;
        this.fgAtivo = fgAtivo;
    }

    public Individuo(Integer id, int idExterno, String nmIndividuo, boolean fgAtivo, Date dtUltimaAtualizacao) {
        this.id = id;
        this.idExterno = idExterno;
        this.nmIndividuo = nmIndividuo;
        this.fgAtivo = fgAtivo;
        this.dtUltimaAtualizacao = dtUltimaAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(int idExterno) {
        this.idExterno = idExterno;
    }

    public String getNmIndividuo() {
        return nmIndividuo;
    }

    public void setNmIndividuo(String nmIndividuo) {
        this.nmIndividuo = nmIndividuo;
    }

    public boolean getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public Date getDtUltimaAtualizacao() {
        return dtUltimaAtualizacao;
    }

    public void setDtUltimaAtualizacao(Date dtUltimaAtualizacao) {
        this.dtUltimaAtualizacao = dtUltimaAtualizacao;
    }

    public Integer getXpAtual() {
        return xpAtual;
    }

    public void setXpAtual(Integer xpAtual) {
        this.xpAtual = xpAtual;
    }

    public Integer getQtAtividadesConcluidas() {
        return qtAtividadesConcluidas;
    }

    public void setQtAtividadesConcluidas(Integer qtAtividadesConcluidas) {
        this.qtAtividadesConcluidas = qtAtividadesConcluidas;
    }

    public Integer getQtDesafiosConcluidos() {
        return qtDesafiosConcluidos;
    }

    public void setQtDesafiosConcluidos(Integer qtDesafiosConcluidos) {
        this.qtDesafiosConcluidos = qtDesafiosConcluidos;
    }

    public Integer getQtMetasConcluidas() {
        return qtMetasConcluidas;
    }

    public void setQtMetasConcluidas(Integer qtMetasConcluidas) {
        this.qtMetasConcluidas = qtMetasConcluidas;
    }

    public Integer getQtEmblemas() {
        return qtEmblemas;
    }

    public void setQtEmblemas(Integer qtEmblemas) {
        this.qtEmblemas = qtEmblemas;
    }

    public Integer getQtItens() {
        return qtItens;
    }

    public void setQtItens(Integer qtItens) {
        this.qtItens = qtItens;
    }

    public Integer getQtVida() {
        return qtVida;
    }

    public void setQtVida(Integer qtVida) {
        this.qtVida = qtVida;
    }

    public Float getVlDinehiro() {
        return vlDinehiro;
    }

    public void setVlDinehiro(Float vlDinehiro) {
        this.vlDinehiro = vlDinehiro;
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
    }

    public List<GrupoIndividuo> getGrupoIndividuoList() {
        return grupoIndividuoList;
    }

    public void setGrupoIndividuoList(List<GrupoIndividuo> grupoIndividuoList) {
        this.grupoIndividuoList = grupoIndividuoList;
    }

    public List<IndividuoNivel> getIndividuoNivelList() {
        return individuoNivelList;
    }

    public void setIndividuoNivelList(List<IndividuoNivel> individuoNivelList) {
        this.individuoNivelList = individuoNivelList;
    }

    public List<IndividuoAtividade> getIndividuoAtividadeList() {
        return individuoAtividadeList;
    }

    public void setIndividuoAtividadeList(List<IndividuoAtividade> individuoAtividadeList) {
        this.individuoAtividadeList = individuoAtividadeList;
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
        if (!(object instanceof Individuo)) {
            return false;
        }
        Individuo other = (Individuo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Individuo[ id=" + id + " ]";
    }

}

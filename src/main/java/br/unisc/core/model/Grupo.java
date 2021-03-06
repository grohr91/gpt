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
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "grupo")
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findById", query = "SELECT g FROM Grupo g WHERE g.id = :id"),
    @NamedQuery(name = "Grupo.findByIdExterno", query = "SELECT g FROM Grupo g WHERE g.idExterno = :idExterno"),
    @NamedQuery(name = "Grupo.findByNmGrupo", query = "SELECT g FROM Grupo g WHERE g.nmGrupo = :nmGrupo"),
    @NamedQuery(name = "Grupo.findByFgAtivo", query = "SELECT g FROM Grupo g WHERE g.fgAtivo = :fgAtivo"),
    @NamedQuery(name = "Grupo.findByXpAtual", query = "SELECT g FROM Grupo g WHERE g.xpAtual = :xpAtual"),
    @NamedQuery(name = "Grupo.findByQtAtividadesConcluidas", query = "SELECT g FROM Grupo g WHERE g.qtAtividadesConcluidas = :qtAtividadesConcluidas"),
    @NamedQuery(name = "Grupo.findByQtDesafiosConcluidos", query = "SELECT g FROM Grupo g WHERE g.qtDesafiosConcluidos = :qtDesafiosConcluidos"),
    @NamedQuery(name = "Grupo.findByQtMetasConcluidas", query = "SELECT g FROM Grupo g WHERE g.qtMetasConcluidas = :qtMetasConcluidas"),
    @NamedQuery(name = "Grupo.findByQtEmblemas", query = "SELECT g FROM Grupo g WHERE g.qtEmblemas = :qtEmblemas"),
    @NamedQuery(name = "Grupo.findByQtItens", query = "SELECT g FROM Grupo g WHERE g.qtItens = :qtItens"),
    @NamedQuery(name = "Grupo.findByVlDinehiro", query = "SELECT g FROM Grupo g WHERE g.vlDinehiro = :vlDinehiro")})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_externo")
    private Integer idExterno;
    @Basic(optional = false)
    @Column(name = "nm_grupo")
    private String nmGrupo;
    @Basic(optional = false)
    @Column(name = "fg_ativo")
    private boolean fgAtivo;
    @Basic(optional = false)
    @Column(name = "dt_ultima_sincronizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaSincronizacao;
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
    @Column(name = "vl_dinehiro")
    private Float vlDinehiro;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private List<GrupoIndividuo> grupoIndividuoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private List<GrupoAtividade> grupoAtividadeList;

    public Grupo() {
    }

    public Grupo(Integer idGrupo) {
        this.id = idGrupo;
    }

    public Grupo(Integer idGrupo, Boolean fgAtivo) {
        this.id = idGrupo;
        this.fgAtivo = fgAtivo;
    }

    public Grupo(Integer idGrupo, String nmGrupo) {
        this.id = idGrupo;
        this.nmGrupo = nmGrupo;
    }

    public Grupo(Integer idExterno, String nmGrupo, Boolean fgAtivo) {
        this.idExterno = idExterno;
        this.nmGrupo = nmGrupo;
        this.fgAtivo = fgAtivo;
        this.dtUltimaSincronizacao = new Date();
    }

    public Grupo(Integer id, String nmGrupo, boolean fgAtivo, Date dtUltimaAtualizacao) {
        this.id = id;
        this.nmGrupo = nmGrupo;
        this.fgAtivo = fgAtivo;
        this.dtUltimaSincronizacao = dtUltimaAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Integer idExterno) {
        this.idExterno = idExterno;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
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

    public List<GrupoAtividade> getGrupoAtividadeList() {
        return grupoAtividadeList;
    }

    public void setGrupoAtividadeList(List<GrupoAtividade> grupoAtividadeList) {
        this.grupoAtividadeList = grupoAtividadeList;
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
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Grupo[ id=" + id + " ]";
    }

    public String getVal(String nmAtributo) {
        if (StringUtils.isBlank(nmAtributo)) {
            return "";
        } else if (nmAtributo.equals("nm_grupo")) {
            if (StringUtils.isBlank(this.nmGrupo)) {
                return "null";
            }
            return this.nmGrupo;
        } else if (nmAtributo.equals("xp_atual")) {
            if (this.xpAtual == null) {
                return "null";
            }
            return this.xpAtual.toString();
        } else if (nmAtributo.equals("qt_atividades_concluidas")) {
            if (this.qtAtividadesConcluidas == null) {
                return "null";
            }
            return this.qtAtividadesConcluidas.toString();
        } else if (nmAtributo.equals("qt_desafios_concluidos")) {
            if (this.qtDesafiosConcluidos == null) {
                return "null";
            }
            return this.qtDesafiosConcluidos.toString();
        } else if (nmAtributo.equals("qt_metas_concluidas")) {
            if (this.qtMetasConcluidas == null) {
                return "null";
            }
            return this.qtMetasConcluidas.toString();
        } else if (nmAtributo.equals("qt_emblemas")) {
            if (this.qtEmblemas == null) {
                return "null";
            }
            return this.qtEmblemas.toString();
        } else if (nmAtributo.equals("qt_itens")) {
            if (this.qtItens == null) {
                return "null";
            }
            return this.qtItens.toString();
        } else if (nmAtributo.equals("vl_dinheiro")) {
            if (this.vlDinehiro == null) {
                return "null";
            }
            return this.vlDinehiro.toString();
        }

        return "";
    }

    public void setVal(String nmAtributo, String novoVal) {
        try {
            if (StringUtils.isBlank(nmAtributo)) {
                return;
            } else if (nmAtributo.equals("nm_grupo")) {
                this.nmGrupo = novoVal;
                this.fgAtivo = true;
                this.idExterno = null;
                this.id = null;
            } else if (nmAtributo.equals("xp_atual")) {
                this.xpAtual = Integer.parseInt(novoVal);
            } else if (nmAtributo.equals("qt_atividades_concluidas")) {
                this.qtAtividadesConcluidas = Integer.parseInt(novoVal);
            } else if (nmAtributo.equals("qt_desafios_concluidos")) {
                this.qtDesafiosConcluidos = Integer.parseInt(novoVal);
            } else if (nmAtributo.equals("qt_metas_concluidas")) {
                this.qtMetasConcluidas = Integer.parseInt(novoVal);
            } else if (nmAtributo.equals("qt_emblemas")) {
                this.qtEmblemas = Integer.parseInt(novoVal);
            } else if (nmAtributo.equals("qt_itens")) {
                this.qtItens = Integer.parseInt(novoVal);
            } else if (nmAtributo.equals("vl_dinheiro")) {
                this.vlDinehiro = Float.parseFloat(novoVal);
            }
        } catch (Exception ex) {
        }
    }
}

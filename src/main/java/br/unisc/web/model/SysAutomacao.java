/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.web.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
@Table(name = "sys_automacao")
@NamedQueries({
    @NamedQuery(name = "SysAutomacao.findAll", query = "SELECT s FROM SysAutomacao s"),
    @NamedQuery(name = "SysAutomacao.findById", query = "SELECT s FROM SysAutomacao s WHERE s.id = :id"),
    @NamedQuery(name = "SysAutomacao.findByHrAutomacao", query = "SELECT s FROM SysAutomacao s WHERE s.hrAutomacao = :hrAutomacao"),
    @NamedQuery(name = "SysAutomacao.findByConfiguracao", query = "SELECT s FROM SysAutomacao s WHERE s.configuracao.id = :idConfiguracao")
})
public class SysAutomacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nr_dia_semana")
    private Integer nrDiaSemana;
    @Column(name = "hr_automacao")
    @Temporal(TemporalType.TIME)
    private Date hrAutomacao;
    @JoinColumn(name = "id_configuracao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SysConfiguracao configuracao;

    public SysAutomacao() {
    }

    public SysAutomacao(Integer id) {
        this.id = id;
    }

    public SysAutomacao(int nrDiaSemana, boolean fgAtivo, Date hrAutomacao) {
        this.nrDiaSemana = nrDiaSemana;
        this.hrAutomacao = hrAutomacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNrDiaSemana() {
        return nrDiaSemana;
    }

    public void setNrDiaSemana(Integer nrDiaSemana) {
        this.nrDiaSemana = nrDiaSemana;
    }

    public Date getHrAutomacao() {
        return hrAutomacao;
    }

    public void setHrAutomacao(Date hrAutomacao) {
        this.hrAutomacao = hrAutomacao;
    }

    public String getHrAutomacaoString() {
        if (hrAutomacao == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(hrAutomacao);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void setHrAutomacaoString(String hrAutomacao) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            this.hrAutomacao = sdf.parse(hrAutomacao);
        } catch (Exception ex) {
            this.hrAutomacao = null;
        }
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
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
        if (!(object instanceof SysAutomacao)) {
            return false;
        }
        SysAutomacao other = (SysAutomacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.web.model.SysAutomacao[ id=" + id + " ]";
    }

    public String getNmDiaSemana() {
        if (nrDiaSemana == null) {
            return "";
        }
        String diaSemana = "Segunda";
        if (GregorianCalendar.TUESDAY == nrDiaSemana) {
            return "Terça";
        } else if (GregorianCalendar.WEDNESDAY == nrDiaSemana) {
            return "Quarta";
        } else if (GregorianCalendar.THURSDAY == nrDiaSemana) {
            return "Quinta";
        } else if (GregorianCalendar.FRIDAY == nrDiaSemana) {
            return "Sexta";
        } else if (GregorianCalendar.SATURDAY == nrDiaSemana) {
            return "Sábado";
        } else if (GregorianCalendar.SUNDAY == nrDiaSemana) {
            return "Domingo";
        }
        return diaSemana;
    }
}

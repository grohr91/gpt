/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.core.model;

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

/**
 *
 * @author m68663 - Guilherme Rohr
 */
@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
    @NamedQuery(name = "Item.findByNmItem", query = "SELECT i FROM Item i WHERE i.nmItem = :nmItem"),
    @NamedQuery(name = "Item.findByVlItem", query = "SELECT i FROM Item i WHERE i.vlItem = :vlItem")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_item")
    private String nmItem;
    @Column(name = "vl_item")
    private Float vlItem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<ReconpensaItem> reconpensaItemList;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id, String nmItem) {
        this.id = id;
        this.nmItem = nmItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmItem() {
        return nmItem;
    }

    public void setNmItem(String nmItem) {
        this.nmItem = nmItem;
    }

    public Float getVlItem() {
        return vlItem;
    }

    public void setVlItem(Float vlItem) {
        this.vlItem = vlItem;
    }

    public List<ReconpensaItem> getReconpensaItemList() {
        return reconpensaItemList;
    }

    public void setReconpensaItemList(List<ReconpensaItem> reconpensaItemList) {
        this.reconpensaItemList = reconpensaItemList;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unisc.core.model.Item[ id=" + id + " ]";
    }
    
}

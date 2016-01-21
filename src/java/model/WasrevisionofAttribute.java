/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Humberto
 */
@Entity
@Table(name = "wasrevisionof_attribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasrevisionofAttribute.findAll", query = "SELECT w FROM WasrevisionofAttribute w"),
    @NamedQuery(name = "WasrevisionofAttribute.findByWasRevisionOfidWasDerivedFrom", query = "SELECT w FROM WasrevisionofAttribute w WHERE w.wasrevisionofAttributePK.wasRevisionOfidWasDerivedFrom = :wasRevisionOfidWasDerivedFrom"),
    @NamedQuery(name = "WasrevisionofAttribute.findByAttributeidAttribute", query = "SELECT w FROM WasrevisionofAttribute w WHERE w.wasrevisionofAttributePK.attributeidAttribute = :attributeidAttribute")})
public class WasrevisionofAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WasrevisionofAttributePK wasrevisionofAttributePK;
    @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Attribute attribute;

    public WasrevisionofAttribute() {
    }

    public WasrevisionofAttribute(WasrevisionofAttributePK wasrevisionofAttributePK) {
        this.wasrevisionofAttributePK = wasrevisionofAttributePK;
    }

    public WasrevisionofAttribute(int wasRevisionOfidWasDerivedFrom, int attributeidAttribute) {
        this.wasrevisionofAttributePK = new WasrevisionofAttributePK(wasRevisionOfidWasDerivedFrom, attributeidAttribute);
    }

    public WasrevisionofAttributePK getWasrevisionofAttributePK() {
        return wasrevisionofAttributePK;
    }

    public void setWasrevisionofAttributePK(WasrevisionofAttributePK wasrevisionofAttributePK) {
        this.wasrevisionofAttributePK = wasrevisionofAttributePK;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wasrevisionofAttributePK != null ? wasrevisionofAttributePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasrevisionofAttribute)) {
            return false;
        }
        WasrevisionofAttribute other = (WasrevisionofAttribute) object;
        if ((this.wasrevisionofAttributePK == null && other.wasrevisionofAttributePK != null) || (this.wasrevisionofAttributePK != null && !this.wasrevisionofAttributePK.equals(other.wasrevisionofAttributePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WasrevisionofAttribute[ wasrevisionofAttributePK=" + wasrevisionofAttributePK + " ]";
    }
    
}

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
@Table(name = "wasquotedfrom_attribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasquotedfromAttribute.findAll", query = "SELECT w FROM WasquotedfromAttribute w"),
    @NamedQuery(name = "WasquotedfromAttribute.findByWasQuotedFromidWasQuotedFrom", query = "SELECT w FROM WasquotedfromAttribute w WHERE w.wasquotedfromAttributePK.wasQuotedFromidWasQuotedFrom = :wasQuotedFromidWasQuotedFrom"),
    @NamedQuery(name = "WasquotedfromAttribute.findByAttributeidAttribute", query = "SELECT w FROM WasquotedfromAttribute w WHERE w.wasquotedfromAttributePK.attributeidAttribute = :attributeidAttribute")})
public class WasquotedfromAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WasquotedfromAttributePK wasquotedfromAttributePK;
    @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Attribute attribute;

    public WasquotedfromAttribute() {
    }

    public WasquotedfromAttribute(WasquotedfromAttributePK wasquotedfromAttributePK) {
        this.wasquotedfromAttributePK = wasquotedfromAttributePK;
    }

    public WasquotedfromAttribute(int wasQuotedFromidWasQuotedFrom, int attributeidAttribute) {
        this.wasquotedfromAttributePK = new WasquotedfromAttributePK(wasQuotedFromidWasQuotedFrom, attributeidAttribute);
    }

    public WasquotedfromAttributePK getWasquotedfromAttributePK() {
        return wasquotedfromAttributePK;
    }

    public void setWasquotedfromAttributePK(WasquotedfromAttributePK wasquotedfromAttributePK) {
        this.wasquotedfromAttributePK = wasquotedfromAttributePK;
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
        hash += (wasquotedfromAttributePK != null ? wasquotedfromAttributePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasquotedfromAttribute)) {
            return false;
        }
        WasquotedfromAttribute other = (WasquotedfromAttribute) object;
        if ((this.wasquotedfromAttributePK == null && other.wasquotedfromAttributePK != null) || (this.wasquotedfromAttributePK != null && !this.wasquotedfromAttributePK.equals(other.wasquotedfromAttributePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WasquotedfromAttribute[ wasquotedfromAttributePK=" + wasquotedfromAttributePK + " ]";
    }
    
}

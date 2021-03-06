/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Humberto
 */
@Embeddable
public class WasquotedfromAttributePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "WasQuotedFrom_idWasQuotedFrom")
    private int wasQuotedFromidWasQuotedFrom;
    @Basic(optional = false)
    @Column(name = "Attribute_idAttribute")
    private int attributeidAttribute;

    public WasquotedfromAttributePK() {
    }

    public WasquotedfromAttributePK(int wasQuotedFromidWasQuotedFrom, int attributeidAttribute) {
        this.wasQuotedFromidWasQuotedFrom = wasQuotedFromidWasQuotedFrom;
        this.attributeidAttribute = attributeidAttribute;
    }

    public int getWasQuotedFromidWasQuotedFrom() {
        return wasQuotedFromidWasQuotedFrom;
    }

    public void setWasQuotedFromidWasQuotedFrom(int wasQuotedFromidWasQuotedFrom) {
        this.wasQuotedFromidWasQuotedFrom = wasQuotedFromidWasQuotedFrom;
    }

    public int getAttributeidAttribute() {
        return attributeidAttribute;
    }

    public void setAttributeidAttribute(int attributeidAttribute) {
        this.attributeidAttribute = attributeidAttribute;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) wasQuotedFromidWasQuotedFrom;
        hash += (int) attributeidAttribute;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasquotedfromAttributePK)) {
            return false;
        }
        WasquotedfromAttributePK other = (WasquotedfromAttributePK) object;
        if (this.wasQuotedFromidWasQuotedFrom != other.wasQuotedFromidWasQuotedFrom) {
            return false;
        }
        if (this.attributeidAttribute != other.attributeidAttribute) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WasquotedfromAttributePK[ wasQuotedFromidWasQuotedFrom=" + wasQuotedFromidWasQuotedFrom + ", attributeidAttribute=" + attributeidAttribute + " ]";
    }
    
}

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
public class WasrevisionofAttributePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "WasRevisionOf_idWasDerivedFrom")
    private int wasRevisionOfidWasDerivedFrom;
    @Basic(optional = false)
    @Column(name = "Attribute_idAttribute")
    private int attributeidAttribute;

    public WasrevisionofAttributePK() {
    }

    public WasrevisionofAttributePK(int wasRevisionOfidWasDerivedFrom, int attributeidAttribute) {
        this.wasRevisionOfidWasDerivedFrom = wasRevisionOfidWasDerivedFrom;
        this.attributeidAttribute = attributeidAttribute;
    }

    public int getWasRevisionOfidWasDerivedFrom() {
        return wasRevisionOfidWasDerivedFrom;
    }

    public void setWasRevisionOfidWasDerivedFrom(int wasRevisionOfidWasDerivedFrom) {
        this.wasRevisionOfidWasDerivedFrom = wasRevisionOfidWasDerivedFrom;
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
        hash += (int) wasRevisionOfidWasDerivedFrom;
        hash += (int) attributeidAttribute;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasrevisionofAttributePK)) {
            return false;
        }
        WasrevisionofAttributePK other = (WasrevisionofAttributePK) object;
        if (this.wasRevisionOfidWasDerivedFrom != other.wasRevisionOfidWasDerivedFrom) {
            return false;
        }
        if (this.attributeidAttribute != other.attributeidAttribute) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WasrevisionofAttributePK[ wasRevisionOfidWasDerivedFrom=" + wasRevisionOfidWasDerivedFrom + ", attributeidAttribute=" + attributeidAttribute + " ]";
    }
    
}

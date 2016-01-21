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
public class HadprimarysourceAttributePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "HadPrimarySource_idHadPrimarySource")
    private int hadPrimarySourceidHadPrimarySource;
    @Basic(optional = false)
    @Column(name = "Attribute_idAttribute")
    private int attributeidAttribute;

    public HadprimarysourceAttributePK() {
    }

    public HadprimarysourceAttributePK(int hadPrimarySourceidHadPrimarySource, int attributeidAttribute) {
        this.hadPrimarySourceidHadPrimarySource = hadPrimarySourceidHadPrimarySource;
        this.attributeidAttribute = attributeidAttribute;
    }

    public int getHadPrimarySourceidHadPrimarySource() {
        return hadPrimarySourceidHadPrimarySource;
    }

    public void setHadPrimarySourceidHadPrimarySource(int hadPrimarySourceidHadPrimarySource) {
        this.hadPrimarySourceidHadPrimarySource = hadPrimarySourceidHadPrimarySource;
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
        hash += (int) hadPrimarySourceidHadPrimarySource;
        hash += (int) attributeidAttribute;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HadprimarysourceAttributePK)) {
            return false;
        }
        HadprimarysourceAttributePK other = (HadprimarysourceAttributePK) object;
        if (this.hadPrimarySourceidHadPrimarySource != other.hadPrimarySourceidHadPrimarySource) {
            return false;
        }
        if (this.attributeidAttribute != other.attributeidAttribute) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HadprimarysourceAttributePK[ hadPrimarySourceidHadPrimarySource=" + hadPrimarySourceidHadPrimarySource + ", attributeidAttribute=" + attributeidAttribute + " ]";
    }
    
}

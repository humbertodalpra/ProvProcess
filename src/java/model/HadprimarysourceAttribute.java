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
@Table(name = "hadprimarysource_attribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HadprimarysourceAttribute.findAll", query = "SELECT h FROM HadprimarysourceAttribute h"),
    @NamedQuery(name = "HadprimarysourceAttribute.findByHadPrimarySourceidHadPrimarySource", query = "SELECT h FROM HadprimarysourceAttribute h WHERE h.hadprimarysourceAttributePK.hadPrimarySourceidHadPrimarySource = :hadPrimarySourceidHadPrimarySource"),
    @NamedQuery(name = "HadprimarysourceAttribute.findByAttributeidAttribute", query = "SELECT h FROM HadprimarysourceAttribute h WHERE h.hadprimarysourceAttributePK.attributeidAttribute = :attributeidAttribute")})
public class HadprimarysourceAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HadprimarysourceAttributePK hadprimarysourceAttributePK;
    @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Attribute attribute;

    public HadprimarysourceAttribute() {
    }

    public HadprimarysourceAttribute(HadprimarysourceAttributePK hadprimarysourceAttributePK) {
        this.hadprimarysourceAttributePK = hadprimarysourceAttributePK;
    }

    public HadprimarysourceAttribute(int hadPrimarySourceidHadPrimarySource, int attributeidAttribute) {
        this.hadprimarysourceAttributePK = new HadprimarysourceAttributePK(hadPrimarySourceidHadPrimarySource, attributeidAttribute);
    }

    public HadprimarysourceAttributePK getHadprimarysourceAttributePK() {
        return hadprimarysourceAttributePK;
    }

    public void setHadprimarysourceAttributePK(HadprimarysourceAttributePK hadprimarysourceAttributePK) {
        this.hadprimarysourceAttributePK = hadprimarysourceAttributePK;
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
        hash += (hadprimarysourceAttributePK != null ? hadprimarysourceAttributePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HadprimarysourceAttribute)) {
            return false;
        }
        HadprimarysourceAttribute other = (HadprimarysourceAttribute) object;
        if ((this.hadprimarysourceAttributePK == null && other.hadprimarysourceAttributePK != null) || (this.hadprimarysourceAttributePK != null && !this.hadprimarysourceAttributePK.equals(other.hadprimarysourceAttributePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HadprimarysourceAttribute[ hadprimarysourceAttributePK=" + hadprimarysourceAttributePK + " ]";
    }
    
}

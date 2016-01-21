/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "hadmember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hadmember.findAll", query = "SELECT h FROM Hadmember h"),
    @NamedQuery(name = "Hadmember.findByIdCollection", query = "SELECT h FROM Hadmember h WHERE h.idCollection = :idCollection")})
public class Hadmember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdCollection")
    private String idCollection;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private model.Entity entityidEntity;

    public Hadmember() {
    }

    public Hadmember(String idCollection) {
        this.idCollection = idCollection;
    }

    public String getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(String idCollection) {
        this.idCollection = idCollection;
    }

    public model.Entity getEntityidEntity() {
        return entityidEntity;
    }

    public void setEntityidEntity(model.Entity entityidEntity) {
        this.entityidEntity = entityidEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCollection != null ? idCollection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hadmember)) {
            return false;
        }
        Hadmember other = (Hadmember) object;
        if ((this.idCollection == null && other.idCollection != null) || (this.idCollection != null && !this.idCollection.equals(other.idCollection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Hadmember[ idCollection=" + idCollection + " ]";
    }

}

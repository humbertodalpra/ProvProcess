/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Humberto
 */
@Entity
@Table(name = "wasderivedfrom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasderivedfrom.findAll", query = "SELECT w FROM Wasderivedfrom w"),
    @NamedQuery(name = "Wasderivedfrom.findByIdWasDerivedFrom", query = "SELECT w FROM Wasderivedfrom w WHERE w.idWasDerivedFrom = :idWasDerivedFrom"),
    @NamedQuery(name = "Wasderivedfrom.findByTypeDerived", query = "SELECT w FROM Wasderivedfrom w WHERE w.typeDerived = :typeDerived")})
public class Wasderivedfrom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasDerivedFrom")
    private Integer idWasDerivedFrom;
    @Column(name = "Type_Derived")
    private String typeDerived;
    @ManyToMany(mappedBy = "wasderivedfromList")
    private List<Attribute> attributeList;
    @JoinColumn(name = "Entity_idEntity_GeneratedEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private model.Entity entityidEntityGeneratedEntity;
    @JoinColumn(name = "Entity_idEntity_UsedEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private model.Entity entityidEntityUsedEntity;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivity;
    @JoinColumn(name = "Used_idUsed_Usage", referencedColumnName = "idUsed")
    @ManyToOne
    private Used usedidUsedUsage;
    @JoinColumn(name = "WasGeneratedBy_idWasGeneratedBy_Generation", referencedColumnName = "idWasGeneratedBy")
    @ManyToOne
    private Wasgeneratedby wasGeneratedByidWasGeneratedByGeneration;
    @OneToMany(mappedBy = "wasDerivedFromidWasDerivedFrom")
    private List<Wasinfluencedby> wasinfluencedbyList;

    public Wasderivedfrom() {
    }

    public Wasderivedfrom(Integer idWasDerivedFrom) {
        this.idWasDerivedFrom = idWasDerivedFrom;
    }

    public Integer getIdWasDerivedFrom() {
        return idWasDerivedFrom;
    }

    public void setIdWasDerivedFrom(Integer idWasDerivedFrom) {
        this.idWasDerivedFrom = idWasDerivedFrom;
    }

    public String getTypeDerived() {
        return typeDerived;
    }

    public void setTypeDerived(String typeDerived) {
        this.typeDerived = typeDerived;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public model.Entity getEntityidEntityGeneratedEntity() {
        return entityidEntityGeneratedEntity;
    }

    public void setEntityidEntityGeneratedEntity(model.Entity entityidEntityGeneratedEntity) {
        this.entityidEntityGeneratedEntity = entityidEntityGeneratedEntity;
    }

    public model.Entity getEntityidEntityUsedEntity() {
        return entityidEntityUsedEntity;
    }

    public void setEntityidEntityUsedEntity(model.Entity entityidEntityUsedEntity) {
        this.entityidEntityUsedEntity = entityidEntityUsedEntity;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
    }

    public Used getUsedidUsedUsage() {
        return usedidUsedUsage;
    }

    public void setUsedidUsedUsage(Used usedidUsedUsage) {
        this.usedidUsedUsage = usedidUsedUsage;
    }

    public Wasgeneratedby getWasGeneratedByidWasGeneratedByGeneration() {
        return wasGeneratedByidWasGeneratedByGeneration;
    }

    public void setWasGeneratedByidWasGeneratedByGeneration(Wasgeneratedby wasGeneratedByidWasGeneratedByGeneration) {
        this.wasGeneratedByidWasGeneratedByGeneration = wasGeneratedByidWasGeneratedByGeneration;
    }

    @XmlTransient
    public List<Wasinfluencedby> getWasinfluencedbyList() {
        return wasinfluencedbyList;
    }

    public void setWasinfluencedbyList(List<Wasinfluencedby> wasinfluencedbyList) {
        this.wasinfluencedbyList = wasinfluencedbyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasDerivedFrom != null ? idWasDerivedFrom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasderivedfrom)) {
            return false;
        }
        Wasderivedfrom other = (Wasderivedfrom) object;
        if ((this.idWasDerivedFrom == null && other.idWasDerivedFrom != null) || (this.idWasDerivedFrom != null && !this.idWasDerivedFrom.equals(other.idWasDerivedFrom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasderivedfrom[ idWasDerivedFrom=" + idWasDerivedFrom + " ]";
    }
    
}

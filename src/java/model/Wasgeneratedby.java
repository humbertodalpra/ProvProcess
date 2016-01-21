/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Humberto
 */
@Entity
@Table(name = "wasgeneratedby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasgeneratedby.findAll", query = "SELECT w FROM Wasgeneratedby w"),
    @NamedQuery(name = "Wasgeneratedby.findByIdWasGeneratedBy", query = "SELECT w FROM Wasgeneratedby w WHERE w.idWasGeneratedBy = :idWasGeneratedBy"),
    @NamedQuery(name = "Wasgeneratedby.findByTime", query = "SELECT w FROM Wasgeneratedby w WHERE w.time = :time")})
public class Wasgeneratedby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasGeneratedBy")
    private Integer idWasGeneratedBy;
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @ManyToMany(mappedBy = "wasgeneratedbyList")
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "wasGeneratedByidWasGeneratedByGeneration")
    private List<Wasderivedfrom> wasderivedfromList;
    @OneToMany(mappedBy = "wasGeneratedByidWasGeneratedBy")
    private List<Wasinfluencedby> wasinfluencedbyList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private model.Entity entityidEntity;

    public Wasgeneratedby() {
    }

    public Wasgeneratedby(Integer idWasGeneratedBy) {
        this.idWasGeneratedBy = idWasGeneratedBy;
    }

    public Integer getIdWasGeneratedBy() {
        return idWasGeneratedBy;
    }

    public void setIdWasGeneratedBy(Integer idWasGeneratedBy) {
        this.idWasGeneratedBy = idWasGeneratedBy;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    @XmlTransient
    public List<Wasderivedfrom> getWasderivedfromList() {
        return wasderivedfromList;
    }

    public void setWasderivedfromList(List<Wasderivedfrom> wasderivedfromList) {
        this.wasderivedfromList = wasderivedfromList;
    }

    @XmlTransient
    public List<Wasinfluencedby> getWasinfluencedbyList() {
        return wasinfluencedbyList;
    }

    public void setWasinfluencedbyList(List<Wasinfluencedby> wasinfluencedbyList) {
        this.wasinfluencedbyList = wasinfluencedbyList;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
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
        hash += (idWasGeneratedBy != null ? idWasGeneratedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasgeneratedby)) {
            return false;
        }
        Wasgeneratedby other = (Wasgeneratedby) object;
        if ((this.idWasGeneratedBy == null && other.idWasGeneratedBy != null) || (this.idWasGeneratedBy != null && !this.idWasGeneratedBy.equals(other.idWasGeneratedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasgeneratedby[ idWasGeneratedBy=" + idWasGeneratedBy + " ]";
    }
    
}

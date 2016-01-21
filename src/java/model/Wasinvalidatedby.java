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
@Table(name = "wasinvalidatedby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasinvalidatedby.findAll", query = "SELECT w FROM Wasinvalidatedby w"),
    @NamedQuery(name = "Wasinvalidatedby.findByIdWasInvalidatedBy", query = "SELECT w FROM Wasinvalidatedby w WHERE w.idWasInvalidatedBy = :idWasInvalidatedBy"),
    @NamedQuery(name = "Wasinvalidatedby.findByTime", query = "SELECT w FROM Wasinvalidatedby w WHERE w.time = :time")})
public class Wasinvalidatedby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasInvalidatedBy")
    private Integer idWasInvalidatedBy;
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @ManyToMany(mappedBy = "wasinvalidatedbyList")
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "wasInvalidatedByidWasInvalidatedBy")
    private List<Wasinfluencedby> wasinfluencedbyList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private model.Entity entityidEntity;

    public Wasinvalidatedby() {
    }

    public Wasinvalidatedby(Integer idWasInvalidatedBy) {
        this.idWasInvalidatedBy = idWasInvalidatedBy;
    }

    public Integer getIdWasInvalidatedBy() {
        return idWasInvalidatedBy;
    }

    public void setIdWasInvalidatedBy(Integer idWasInvalidatedBy) {
        this.idWasInvalidatedBy = idWasInvalidatedBy;
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
        hash += (idWasInvalidatedBy != null ? idWasInvalidatedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasinvalidatedby)) {
            return false;
        }
        Wasinvalidatedby other = (Wasinvalidatedby) object;
        if ((this.idWasInvalidatedBy == null && other.idWasInvalidatedBy != null) || (this.idWasInvalidatedBy != null && !this.idWasInvalidatedBy.equals(other.idWasInvalidatedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasinvalidatedby[ idWasInvalidatedBy=" + idWasInvalidatedBy + " ]";
    }
    
}

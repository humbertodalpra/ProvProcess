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
@Table(name = "wasendedby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasendedby.findAll", query = "SELECT w FROM Wasendedby w"),
    @NamedQuery(name = "Wasendedby.findByIdWasEndedBy", query = "SELECT w FROM Wasendedby w WHERE w.idWasEndedBy = :idWasEndedBy"),
    @NamedQuery(name = "Wasendedby.findByTime", query = "SELECT w FROM Wasendedby w WHERE w.time = :time")})
public class Wasendedby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasEndedBy")
    private Integer idWasEndedBy;
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @ManyToMany(mappedBy = "wasendedbyList")
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "wasEndedByidWasEndedBy")
    private List<Wasinfluencedby> wasinfluencedbyList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity_Trigger", referencedColumnName = "idEntity")
    @ManyToOne
    private model.Entity entityidEntityTrigger;
    @JoinColumn(name = "Activity_idActivity_Ended", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivityEnded;

    public Wasendedby() {
    }

    public Wasendedby(Integer idWasEndedBy) {
        this.idWasEndedBy = idWasEndedBy;
    }

    public Integer getIdWasEndedBy() {
        return idWasEndedBy;
    }

    public void setIdWasEndedBy(Integer idWasEndedBy) {
        this.idWasEndedBy = idWasEndedBy;
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

    public model.Entity getEntityidEntityTrigger() {
        return entityidEntityTrigger;
    }

    public void setEntityidEntityTrigger(model.Entity entityidEntityTrigger) {
        this.entityidEntityTrigger = entityidEntityTrigger;
    }

    public Activity getActivityidActivityEnded() {
        return activityidActivityEnded;
    }

    public void setActivityidActivityEnded(Activity activityidActivityEnded) {
        this.activityidActivityEnded = activityidActivityEnded;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasEndedBy != null ? idWasEndedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasendedby)) {
            return false;
        }
        Wasendedby other = (Wasendedby) object;
        if ((this.idWasEndedBy == null && other.idWasEndedBy != null) || (this.idWasEndedBy != null && !this.idWasEndedBy.equals(other.idWasEndedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasendedby[ idWasEndedBy=" + idWasEndedBy + " ]";
    }
    
}

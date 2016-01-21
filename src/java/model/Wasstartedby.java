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
@Table(name = "wasstartedby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasstartedby.findAll", query = "SELECT w FROM Wasstartedby w"),
    @NamedQuery(name = "Wasstartedby.findByIdWasStartedBy", query = "SELECT w FROM Wasstartedby w WHERE w.idWasStartedBy = :idWasStartedBy"),
    @NamedQuery(name = "Wasstartedby.findByTime", query = "SELECT w FROM Wasstartedby w WHERE w.time = :time")})
public class Wasstartedby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasStartedBy")
    private Integer idWasStartedBy;
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @ManyToMany(mappedBy = "wasstartedbyList")
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "wasStartedByidWasStartedBy")
    private List<Wasinfluencedby> wasinfluencedbyList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity_Trigger", referencedColumnName = "idEntity")
    @ManyToOne
    private model.Entity entityidEntityTrigger;
    @JoinColumn(name = "Activity_idActivity_Starter", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivityStarter;

    public Wasstartedby() {
    }

    public Wasstartedby(Integer idWasStartedBy) {
        this.idWasStartedBy = idWasStartedBy;
    }

    public Integer getIdWasStartedBy() {
        return idWasStartedBy;
    }

    public void setIdWasStartedBy(Integer idWasStartedBy) {
        this.idWasStartedBy = idWasStartedBy;
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

    public Activity getActivityidActivityStarter() {
        return activityidActivityStarter;
    }

    public void setActivityidActivityStarter(Activity activityidActivityStarter) {
        this.activityidActivityStarter = activityidActivityStarter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasStartedBy != null ? idWasStartedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasstartedby)) {
            return false;
        }
        Wasstartedby other = (Wasstartedby) object;
        if ((this.idWasStartedBy == null && other.idWasStartedBy != null) || (this.idWasStartedBy != null && !this.idWasStartedBy.equals(other.idWasStartedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasstartedby[ idWasStartedBy=" + idWasStartedBy + " ]";
    }
    
}

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
@Table(name = "used")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Used.findAll", query = "SELECT u FROM Used u"),
    @NamedQuery(name = "Used.findByIdUsed", query = "SELECT u FROM Used u WHERE u.idUsed = :idUsed"),
    @NamedQuery(name = "Used.findByTime", query = "SELECT u FROM Used u WHERE u.time = :time")})
public class Used implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsed")
    private Integer idUsed;
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @ManyToMany(mappedBy = "usedList")
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "usedidUsedUsage")
    private List<Wasderivedfrom> wasderivedfromList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne
    private model.Entity entityidEntity;
    @OneToMany(mappedBy = "usedidUsed")
    private List<Wasinfluencedby> wasinfluencedbyList;

    public Used() {
    }

    public Used(Integer idUsed) {
        this.idUsed = idUsed;
    }

    public Integer getIdUsed() {
        return idUsed;
    }

    public void setIdUsed(Integer idUsed) {
        this.idUsed = idUsed;
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
        hash += (idUsed != null ? idUsed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Used)) {
            return false;
        }
        Used other = (Used) object;
        if ((this.idUsed == null && other.idUsed != null) || (this.idUsed != null && !this.idUsed.equals(other.idUsed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Used[ idUsed=" + idUsed + " ]";
    }
    
}

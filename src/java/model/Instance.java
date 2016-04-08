/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author humbe
 */
@Entity
@Table(name = "instance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instance.findAll", query = "SELECT i FROM Instance i"),
    @NamedQuery(name = "Instance.findByIdProcessInstance", query = "SELECT i FROM Instance i WHERE i.idProcessInstance = :idProcessInstance"),
    @NamedQuery(name = "Instance.findByStartTime", query = "SELECT i FROM Instance i WHERE i.startTime = :startTime"),
    @NamedQuery(name = "Instance.findByEndTime", query = "SELECT i FROM Instance i WHERE i.endTime = :endTime")})
public class Instance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProcessInstance")
    private Integer idProcessInstance;
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Instance() {
    }

    public Instance(Integer idProcessInstance) {
        this.idProcessInstance = idProcessInstance;
    }

    public Integer getIdProcessInstance() {
        return idProcessInstance;
    }

    public void setIdProcessInstance(Integer idProcessInstance) {
        this.idProcessInstance = idProcessInstance;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcessInstance != null ? idProcessInstance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instance)) {
            return false;
        }
        Instance other = (Instance) object;
        if ((this.idProcessInstance == null && other.idProcessInstance != null) || (this.idProcessInstance != null && !this.idProcessInstance.equals(other.idProcessInstance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Instance[ idProcessInstance=" + idProcessInstance + " ]";
    }

    @Transient
    public String getDuration() {
        if (endTime != null) {
            long duration = endTime.getTime() - startTime.getTime();
            return duration <= 0 ? "00" : String.format("%03d:%02d", duration / 3600000, (duration / 60000) % 60);
        } else {
            return "";
        }
    }

}

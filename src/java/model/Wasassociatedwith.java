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
@Table(name = "wasassociatedwith")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasassociatedwith.findAll", query = "SELECT w FROM Wasassociatedwith w"),
    @NamedQuery(name = "Wasassociatedwith.findByIdWasAssociatedWith", query = "SELECT w FROM Wasassociatedwith w WHERE w.idWasAssociatedWith = :idWasAssociatedWith")})
public class Wasassociatedwith implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasAssociatedWith")
    private Integer idWasAssociatedWith;
    @ManyToMany(mappedBy = "wasassociatedwithList")
    private List<Attribute> attributeList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentidAgent;
    @JoinColumn(name = "Entity_idEntity_Plan", referencedColumnName = "idEntity")
    @ManyToOne
    private model.Entity entityidEntityPlan;
    @OneToMany(mappedBy = "wasAssociatedWithidWasAssociatedWith")
    private List<Wasinfluencedby> wasinfluencedbyList;

    public Wasassociatedwith() {
    }

    public Wasassociatedwith(Integer idWasAssociatedWith) {
        this.idWasAssociatedWith = idWasAssociatedWith;
    }

    public Integer getIdWasAssociatedWith() {
        return idWasAssociatedWith;
    }

    public void setIdWasAssociatedWith(Integer idWasAssociatedWith) {
        this.idWasAssociatedWith = idWasAssociatedWith;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
    }

    public Agent getAgentidAgent() {
        return agentidAgent;
    }

    public void setAgentidAgent(Agent agentidAgent) {
        this.agentidAgent = agentidAgent;
    }

    public model.Entity getEntityidEntityPlan() {
        return entityidEntityPlan;
    }

    public void setEntityidEntityPlan(model.Entity entityidEntityPlan) {
        this.entityidEntityPlan = entityidEntityPlan;
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
        hash += (idWasAssociatedWith != null ? idWasAssociatedWith.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasassociatedwith)) {
            return false;
        }
        Wasassociatedwith other = (Wasassociatedwith) object;
        if ((this.idWasAssociatedWith == null && other.idWasAssociatedWith != null) || (this.idWasAssociatedWith != null && !this.idWasAssociatedWith.equals(other.idWasAssociatedWith))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasassociatedwith[ idWasAssociatedWith=" + idWasAssociatedWith + " ]";
    }
    
}

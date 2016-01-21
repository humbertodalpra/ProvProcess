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
import javax.persistence.JoinTable;
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
@Table(name = "actedonbehalfof")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actedonbehalfof.findAll", query = "SELECT a FROM Actedonbehalfof a"),
    @NamedQuery(name = "Actedonbehalfof.findByIdActedOnBehalfOfl", query = "SELECT a FROM Actedonbehalfof a WHERE a.idActedOnBehalfOfl = :idActedOnBehalfOfl")})
public class Actedonbehalfof implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActedOnBehalfOfl")
    private Integer idActedOnBehalfOfl;
    @JoinTable(name = "actedonbehalfof_attribute", joinColumns = {
        @JoinColumn(name = "ActedOnBehalfOf_idActedOnBehalfOfl", referencedColumnName = "idActedOnBehalfOfl")}, inverseJoinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")})
    @ManyToMany
    private List<Attribute> attributeList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivity;
    @JoinColumn(name = "Agent_idAgent_Delegate", referencedColumnName = "idAgent")
    @ManyToOne(optional = false)
    private Agent agentidAgentDelegate;
    @JoinColumn(name = "Agent_idAgent_Responsible", referencedColumnName = "idAgent")
    @ManyToOne(optional = false)
    private Agent agentidAgentResponsible;
    @OneToMany(mappedBy = "actedOnBehalfOfidActedOnBehalfOfl")
    private List<Wasinfluencedby> wasinfluencedbyList;

    public Actedonbehalfof() {
    }

    public Actedonbehalfof(Integer idActedOnBehalfOfl) {
        this.idActedOnBehalfOfl = idActedOnBehalfOfl;
    }

    public Integer getIdActedOnBehalfOfl() {
        return idActedOnBehalfOfl;
    }

    public void setIdActedOnBehalfOfl(Integer idActedOnBehalfOfl) {
        this.idActedOnBehalfOfl = idActedOnBehalfOfl;
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

    public Agent getAgentidAgentDelegate() {
        return agentidAgentDelegate;
    }

    public void setAgentidAgentDelegate(Agent agentidAgentDelegate) {
        this.agentidAgentDelegate = agentidAgentDelegate;
    }

    public Agent getAgentidAgentResponsible() {
        return agentidAgentResponsible;
    }

    public void setAgentidAgentResponsible(Agent agentidAgentResponsible) {
        this.agentidAgentResponsible = agentidAgentResponsible;
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
        hash += (idActedOnBehalfOfl != null ? idActedOnBehalfOfl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actedonbehalfof)) {
            return false;
        }
        Actedonbehalfof other = (Actedonbehalfof) object;
        if ((this.idActedOnBehalfOfl == null && other.idActedOnBehalfOfl != null) || (this.idActedOnBehalfOfl != null && !this.idActedOnBehalfOfl.equals(other.idActedOnBehalfOfl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Actedonbehalfof[ idActedOnBehalfOfl=" + idActedOnBehalfOfl + " ]";
    }
    
}

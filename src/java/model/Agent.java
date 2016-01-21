/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "agent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a"),
    @NamedQuery(name = "Agent.findByIdAgent", query = "SELECT a FROM Agent a WHERE a.idAgent = :idAgent"),
    @NamedQuery(name = "Agent.findByName", query = "SELECT a FROM Agent a WHERE a.name = :name"),
    @NamedQuery(name = "Agent.findByTypeAgent", query = "SELECT a FROM Agent a WHERE a.typeAgent = :typeAgent")})
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgent")
    private Integer idAgent;
    @Column(name = "Name")
    private String name;
    @Column(name = "Type_Agent")
    private String typeAgent;
    @JoinTable(name = "agent_attribute", joinColumns = {
        @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")}, inverseJoinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")})
    @ManyToMany
    private List<Attribute> attributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentidAgentDelegate")
    private List<Actedonbehalfof> actedonbehalfofList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentidAgentResponsible")
    private List<Actedonbehalfof> actedonbehalfofList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentidAgent")
    private List<Wasattributedto> wasattributedtoList;
    @OneToMany(mappedBy = "agentidAgent")
    private List<Wasassociatedwith> wasassociatedwithList;

    public Agent() {
    }

    public Agent(Integer idAgent) {
        this.idAgent = idAgent;
    }

    public Integer getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Integer idAgent) {
        this.idAgent = idAgent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(String typeAgent) {
        this.typeAgent = typeAgent;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    @XmlTransient
    public List<Actedonbehalfof> getActedonbehalfofList() {
        return actedonbehalfofList;
    }

    public void setActedonbehalfofList(List<Actedonbehalfof> actedonbehalfofList) {
        this.actedonbehalfofList = actedonbehalfofList;
    }

    @XmlTransient
    public List<Actedonbehalfof> getActedonbehalfofList1() {
        return actedonbehalfofList1;
    }

    public void setActedonbehalfofList1(List<Actedonbehalfof> actedonbehalfofList1) {
        this.actedonbehalfofList1 = actedonbehalfofList1;
    }

    @XmlTransient
    public List<Wasattributedto> getWasattributedtoList() {
        return wasattributedtoList;
    }

    public void setWasattributedtoList(List<Wasattributedto> wasattributedtoList) {
        this.wasattributedtoList = wasattributedtoList;
    }

    @XmlTransient
    public List<Wasassociatedwith> getWasassociatedwithList() {
        return wasassociatedwithList;
    }

    public void setWasassociatedwithList(List<Wasassociatedwith> wasassociatedwithList) {
        this.wasassociatedwithList = wasassociatedwithList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgent != null ? idAgent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.idAgent == null && other.idAgent != null) || (this.idAgent != null && !this.idAgent.equals(other.idAgent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Agent[ idAgent=" + idAgent + " ]";
    }
    
}

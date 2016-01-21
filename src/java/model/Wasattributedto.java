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
@Table(name = "wasattributedto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasattributedto.findAll", query = "SELECT w FROM Wasattributedto w"),
    @NamedQuery(name = "Wasattributedto.findByIdWasAttributedTo", query = "SELECT w FROM Wasattributedto w WHERE w.idWasAttributedTo = :idWasAttributedTo")})
public class Wasattributedto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasAttributedTo")
    private Integer idWasAttributedTo;
    @ManyToMany(mappedBy = "wasattributedtoList")
    private List<Attribute> attributeList;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne(optional = false)
    private Agent agentidAgent;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private model.Entity entityidEntity;
    @OneToMany(mappedBy = "wasAttributedToidWasAttributedTo")
    private List<Wasinfluencedby> wasinfluencedbyList;

    public Wasattributedto() {
    }

    public Wasattributedto(Integer idWasAttributedTo) {
        this.idWasAttributedTo = idWasAttributedTo;
    }

    public Integer getIdWasAttributedTo() {
        return idWasAttributedTo;
    }

    public void setIdWasAttributedTo(Integer idWasAttributedTo) {
        this.idWasAttributedTo = idWasAttributedTo;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public Agent getAgentidAgent() {
        return agentidAgent;
    }

    public void setAgentidAgent(Agent agentidAgent) {
        this.agentidAgent = agentidAgent;
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
        hash += (idWasAttributedTo != null ? idWasAttributedTo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasattributedto)) {
            return false;
        }
        Wasattributedto other = (Wasattributedto) object;
        if ((this.idWasAttributedTo == null && other.idWasAttributedTo != null) || (this.idWasAttributedTo != null && !this.idWasAttributedTo.equals(other.idWasAttributedTo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasattributedto[ idWasAttributedTo=" + idWasAttributedTo + " ]";
    }
    
}

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
@Table(name = "attribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attribute.findAll", query = "SELECT a FROM Attribute a"),
    @NamedQuery(name = "Attribute.findByIdAttribute", query = "SELECT a FROM Attribute a WHERE a.idAttribute = :idAttribute"),
    @NamedQuery(name = "Attribute.findByName", query = "SELECT a FROM Attribute a WHERE a.name = :name"),
    @NamedQuery(name = "Attribute.findByValue", query = "SELECT a FROM Attribute a WHERE a.value = :value")})
public class Attribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAttribute")
    private Integer idAttribute;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Value")
    private String value;
    @JoinTable(name = "wasderivedfrom_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasDerivedFrom_idWasDerivedFrom", referencedColumnName = "idWasDerivedFrom")})
    @ManyToMany
    private List<Wasderivedfrom> wasderivedfromList;
    @ManyToMany(mappedBy = "attributeList")
    private List<Actedonbehalfof> actedonbehalfofList;
    @ManyToMany(mappedBy = "attributeList")
    private List<Activity> activityList;
    @JoinTable(name = "wasinvalidatedby_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasInvalidatedBy_idWasInvalidatedBy", referencedColumnName = "idWasInvalidatedBy")})
    @ManyToMany
    private List<Wasinvalidatedby> wasinvalidatedbyList;
    @JoinTable(name = "wasattributedto_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasAttributedTo_idWasAttributedTo", referencedColumnName = "idWasAttributedTo")})
    @ManyToMany
    private List<Wasattributedto> wasattributedtoList;
    @JoinTable(name = "wasgeneratedby_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasGeneratedBy_idWasGeneratedBy", referencedColumnName = "idWasGeneratedBy")})
    @ManyToMany
    private List<Wasgeneratedby> wasgeneratedbyList;
    @JoinTable(name = "wasinfluencedby_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasInfluencedBy_IdWasInfluencedBy", referencedColumnName = "IdWasInfluencedBy")})
    @ManyToMany
    private List<Wasinfluencedby> wasinfluencedbyList;
    @JoinTable(name = "wasinformedby_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasInformedBy_idWasInformedBy", referencedColumnName = "idWasInformedBy")})
    @ManyToMany
    private List<Wasinformedby> wasinformedbyList;
    @JoinTable(name = "wasstartedby_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasStartedBy_idWasStartedBy", referencedColumnName = "idWasStartedBy")})
    @ManyToMany
    private List<Wasstartedby> wasstartedbyList;
    @JoinTable(name = "wasassociatedwith_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasAssociatedWith_idWasAssociatedWith", referencedColumnName = "idWasAssociatedWith")})
    @ManyToMany
    private List<Wasassociatedwith> wasassociatedwithList;
    @ManyToMany(mappedBy = "attributeList")
    private List<Agent> agentList;
    @JoinTable(name = "wasendedby_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "WasEndedBy_idWasEndedBy", referencedColumnName = "idWasEndedBy")})
    @ManyToMany
    private List<Wasendedby> wasendedbyList;
    @JoinTable(name = "entity_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")})
    @ManyToMany
    private List<model.Entity> entityList;
    @JoinTable(name = "used_attribute", joinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")}, inverseJoinColumns = {
        @JoinColumn(name = "Used_idUsed", referencedColumnName = "idUsed")})
    @ManyToMany
    private List<Used> usedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attribute")
    private List<HadprimarysourceAttribute> hadprimarysourceAttributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attribute")
    private List<WasrevisionofAttribute> wasrevisionofAttributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attribute")
    private List<WasquotedfromAttribute> wasquotedfromAttributeList;

    public Attribute() {
    }

    public Attribute(Integer idAttribute) {
        this.idAttribute = idAttribute;
    }

    public Attribute(Integer idAttribute, String name, String value) {
        this.idAttribute = idAttribute;
        this.name = name;
        this.value = value;
    }

    public Integer getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Integer idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public List<Wasderivedfrom> getWasderivedfromList() {
        return wasderivedfromList;
    }

    public void setWasderivedfromList(List<Wasderivedfrom> wasderivedfromList) {
        this.wasderivedfromList = wasderivedfromList;
    }

    @XmlTransient
    public List<Actedonbehalfof> getActedonbehalfofList() {
        return actedonbehalfofList;
    }

    public void setActedonbehalfofList(List<Actedonbehalfof> actedonbehalfofList) {
        this.actedonbehalfofList = actedonbehalfofList;
    }

    @XmlTransient
    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @XmlTransient
    public List<Wasinvalidatedby> getWasinvalidatedbyList() {
        return wasinvalidatedbyList;
    }

    public void setWasinvalidatedbyList(List<Wasinvalidatedby> wasinvalidatedbyList) {
        this.wasinvalidatedbyList = wasinvalidatedbyList;
    }

    @XmlTransient
    public List<Wasattributedto> getWasattributedtoList() {
        return wasattributedtoList;
    }

    public void setWasattributedtoList(List<Wasattributedto> wasattributedtoList) {
        this.wasattributedtoList = wasattributedtoList;
    }

    @XmlTransient
    public List<Wasgeneratedby> getWasgeneratedbyList() {
        return wasgeneratedbyList;
    }

    public void setWasgeneratedbyList(List<Wasgeneratedby> wasgeneratedbyList) {
        this.wasgeneratedbyList = wasgeneratedbyList;
    }

    @XmlTransient
    public List<Wasinfluencedby> getWasinfluencedbyList() {
        return wasinfluencedbyList;
    }

    public void setWasinfluencedbyList(List<Wasinfluencedby> wasinfluencedbyList) {
        this.wasinfluencedbyList = wasinfluencedbyList;
    }

    @XmlTransient
    public List<Wasinformedby> getWasinformedbyList() {
        return wasinformedbyList;
    }

    public void setWasinformedbyList(List<Wasinformedby> wasinformedbyList) {
        this.wasinformedbyList = wasinformedbyList;
    }

    @XmlTransient
    public List<Wasstartedby> getWasstartedbyList() {
        return wasstartedbyList;
    }

    public void setWasstartedbyList(List<Wasstartedby> wasstartedbyList) {
        this.wasstartedbyList = wasstartedbyList;
    }

    @XmlTransient
    public List<Wasassociatedwith> getWasassociatedwithList() {
        return wasassociatedwithList;
    }

    public void setWasassociatedwithList(List<Wasassociatedwith> wasassociatedwithList) {
        this.wasassociatedwithList = wasassociatedwithList;
    }

    @XmlTransient
    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    @XmlTransient
    public List<Wasendedby> getWasendedbyList() {
        return wasendedbyList;
    }

    public void setWasendedbyList(List<Wasendedby> wasendedbyList) {
        this.wasendedbyList = wasendedbyList;
    }

    @XmlTransient
    public List<model.Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<model.Entity> entityList) {
        this.entityList = entityList;
    }

    @XmlTransient
    public List<Used> getUsedList() {
        return usedList;
    }

    public void setUsedList(List<Used> usedList) {
        this.usedList = usedList;
    }

    @XmlTransient
    public List<HadprimarysourceAttribute> getHadprimarysourceAttributeList() {
        return hadprimarysourceAttributeList;
    }

    public void setHadprimarysourceAttributeList(List<HadprimarysourceAttribute> hadprimarysourceAttributeList) {
        this.hadprimarysourceAttributeList = hadprimarysourceAttributeList;
    }

    @XmlTransient
    public List<WasrevisionofAttribute> getWasrevisionofAttributeList() {
        return wasrevisionofAttributeList;
    }

    public void setWasrevisionofAttributeList(List<WasrevisionofAttribute> wasrevisionofAttributeList) {
        this.wasrevisionofAttributeList = wasrevisionofAttributeList;
    }

    @XmlTransient
    public List<WasquotedfromAttribute> getWasquotedfromAttributeList() {
        return wasquotedfromAttributeList;
    }

    public void setWasquotedfromAttributeList(List<WasquotedfromAttribute> wasquotedfromAttributeList) {
        this.wasquotedfromAttributeList = wasquotedfromAttributeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttribute != null ? idAttribute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attribute)) {
            return false;
        }
        Attribute other = (Attribute) object;
        if ((this.idAttribute == null && other.idAttribute != null) || (this.idAttribute != null && !this.idAttribute.equals(other.idAttribute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Attribute[ idAttribute=" + idAttribute + " ]";
    }
    
}

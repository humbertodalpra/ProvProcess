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
@javax.persistence.Entity
@Table(name = "entity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entity.findAll", query = "SELECT e FROM Entity e"),
    @NamedQuery(name = "Entity.findByIdEntity", query = "SELECT e FROM Entity e WHERE e.idEntity = :idEntity"),
    @NamedQuery(name = "Entity.findByName", query = "SELECT e FROM Entity e WHERE e.name = :name"),
    @NamedQuery(name = "Entity.findByTypeEntity", query = "SELECT e FROM Entity e WHERE e.typeEntity = :typeEntity")})
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEntity")
    private Integer idEntity;
    @Column(name = "Name")
    private String name;
    @Column(name = "Type_Entity")
    private String typeEntity;
    @JoinTable(name = "alternateof", joinColumns = {
        @JoinColumn(name = "Entity_idEntity_Alternate1", referencedColumnName = "idEntity")}, inverseJoinColumns = {
        @JoinColumn(name = "Entity_idEntity_Alternate2", referencedColumnName = "idEntity")})
    @ManyToMany
    private List<Entity> entityList;
    @ManyToMany(mappedBy = "entityList")
    private List<Entity> entityList1;
    @JoinTable(name = "specializationof", joinColumns = {
        @JoinColumn(name = "Entity_idEntity_SpecificEntity", referencedColumnName = "idEntity")}, inverseJoinColumns = {
        @JoinColumn(name = "Entity_idEntit_GeneralEntity", referencedColumnName = "idEntity")})
    @ManyToMany
    private List<Entity> entityList2;
    @ManyToMany(mappedBy = "entityList2")
    private List<Entity> entityList3;
    @ManyToMany(mappedBy = "entityList")
    private List<Attribute> attributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntity")
    private List<Wasattributedto> wasattributedtoList;
    @OneToMany(mappedBy = "entityidEntityPlan")
    private List<Wasassociatedwith> wasassociatedwithList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntityGeneratedEntity")
    private List<Wasderivedfrom> wasderivedfromList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntityUsedEntity")
    private List<Wasderivedfrom> wasderivedfromList1;
    @OneToMany(mappedBy = "entityidEntity")
    private List<Used> usedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntity")
    private List<Wasinvalidatedby> wasinvalidatedbyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntity")
    private List<Hadmember> hadmemberList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntity")
    private List<Wasgeneratedby> wasgeneratedbyList;
    @OneToMany(mappedBy = "entityidEntityTrigger")
    private List<Wasstartedby> wasstartedbyList;
    @OneToMany(mappedBy = "entityidEntityTrigger")
    private List<Wasendedby> wasendedbyList;

    public Entity() {
    }

    public Entity(Integer idEntity) {
        this.idEntity = idEntity;
    }

    public Integer getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Integer idEntity) {
        this.idEntity = idEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(String typeEntity) {
        this.typeEntity = typeEntity;
    }

    @XmlTransient
    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    @XmlTransient
    public List<Entity> getEntityList1() {
        return entityList1;
    }

    public void setEntityList1(List<Entity> entityList1) {
        this.entityList1 = entityList1;
    }

    @XmlTransient
    public List<Entity> getEntityList2() {
        return entityList2;
    }

    public void setEntityList2(List<Entity> entityList2) {
        this.entityList2 = entityList2;
    }

    @XmlTransient
    public List<Entity> getEntityList3() {
        return entityList3;
    }

    public void setEntityList3(List<Entity> entityList3) {
        this.entityList3 = entityList3;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
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

    @XmlTransient
    public List<Wasderivedfrom> getWasderivedfromList() {
        return wasderivedfromList;
    }

    public void setWasderivedfromList(List<Wasderivedfrom> wasderivedfromList) {
        this.wasderivedfromList = wasderivedfromList;
    }

    @XmlTransient
    public List<Wasderivedfrom> getWasderivedfromList1() {
        return wasderivedfromList1;
    }

    public void setWasderivedfromList1(List<Wasderivedfrom> wasderivedfromList1) {
        this.wasderivedfromList1 = wasderivedfromList1;
    }

    @XmlTransient
    public List<Used> getUsedList() {
        return usedList;
    }

    public void setUsedList(List<Used> usedList) {
        this.usedList = usedList;
    }

    @XmlTransient
    public List<Wasinvalidatedby> getWasinvalidatedbyList() {
        return wasinvalidatedbyList;
    }

    public void setWasinvalidatedbyList(List<Wasinvalidatedby> wasinvalidatedbyList) {
        this.wasinvalidatedbyList = wasinvalidatedbyList;
    }

    @XmlTransient
    public List<Hadmember> getHadmemberList() {
        return hadmemberList;
    }

    public void setHadmemberList(List<Hadmember> hadmemberList) {
        this.hadmemberList = hadmemberList;
    }

    @XmlTransient
    public List<Wasgeneratedby> getWasgeneratedbyList() {
        return wasgeneratedbyList;
    }

    public void setWasgeneratedbyList(List<Wasgeneratedby> wasgeneratedbyList) {
        this.wasgeneratedbyList = wasgeneratedbyList;
    }

    @XmlTransient
    public List<Wasstartedby> getWasstartedbyList() {
        return wasstartedbyList;
    }

    public void setWasstartedbyList(List<Wasstartedby> wasstartedbyList) {
        this.wasstartedbyList = wasstartedbyList;
    }

    @XmlTransient
    public List<Wasendedby> getWasendedbyList() {
        return wasendedbyList;
    }

    public void setWasendedbyList(List<Wasendedby> wasendedbyList) {
        this.wasendedbyList = wasendedbyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntity != null ? idEntity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) object;
        if ((this.idEntity == null && other.idEntity != null) || (this.idEntity != null && !this.idEntity.equals(other.idEntity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Entity[ idEntity=" + idEntity + " ]";
    }
    
}

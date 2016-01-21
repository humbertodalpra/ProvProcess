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
@Table(name = "wasinformedby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasinformedby.findAll", query = "SELECT w FROM Wasinformedby w"),
    @NamedQuery(name = "Wasinformedby.findByIdWasInformedBy", query = "SELECT w FROM Wasinformedby w WHERE w.idWasInformedBy = :idWasInformedBy")})
public class Wasinformedby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasInformedBy")
    private Integer idWasInformedBy;
    @ManyToMany(mappedBy = "wasinformedbyList")
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "wasInformedByidWasInformedBy")
    private List<Wasinfluencedby> wasinfluencedbyList;
    @JoinColumn(name = "Activity_idActivity_Informed", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivityInformed;
    @JoinColumn(name = "Activity_idActivity_Informant", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivityInformant;

    public Wasinformedby() {
    }

    public Wasinformedby(Integer idWasInformedBy) {
        this.idWasInformedBy = idWasInformedBy;
    }

    public Integer getIdWasInformedBy() {
        return idWasInformedBy;
    }

    public void setIdWasInformedBy(Integer idWasInformedBy) {
        this.idWasInformedBy = idWasInformedBy;
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

    public Activity getActivityidActivityInformed() {
        return activityidActivityInformed;
    }

    public void setActivityidActivityInformed(Activity activityidActivityInformed) {
        this.activityidActivityInformed = activityidActivityInformed;
    }

    public Activity getActivityidActivityInformant() {
        return activityidActivityInformant;
    }

    public void setActivityidActivityInformant(Activity activityidActivityInformant) {
        this.activityidActivityInformant = activityidActivityInformant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasInformedBy != null ? idWasInformedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasinformedby)) {
            return false;
        }
        Wasinformedby other = (Wasinformedby) object;
        if ((this.idWasInformedBy == null && other.idWasInformedBy != null) || (this.idWasInformedBy != null && !this.idWasInformedBy.equals(other.idWasInformedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasinformedby[ idWasInformedBy=" + idWasInformedBy + " ]";
    }
    
}

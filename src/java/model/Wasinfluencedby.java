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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Humberto
 */
@Entity
@Table(name = "wasinfluencedby")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wasinfluencedby.findAll", query = "SELECT w FROM Wasinfluencedby w"),
    @NamedQuery(name = "Wasinfluencedby.findByIdWasInfluencedBy", query = "SELECT w FROM Wasinfluencedby w WHERE w.idWasInfluencedBy = :idWasInfluencedBy"),
    @NamedQuery(name = "Wasinfluencedby.findByWasRevisionOfidWasDerivedFrom", query = "SELECT w FROM Wasinfluencedby w WHERE w.wasRevisionOfidWasDerivedFrom = :wasRevisionOfidWasDerivedFrom"),
    @NamedQuery(name = "Wasinfluencedby.findByWasQuotedFromidWasQuotedFrom", query = "SELECT w FROM Wasinfluencedby w WHERE w.wasQuotedFromidWasQuotedFrom = :wasQuotedFromidWasQuotedFrom"),
    @NamedQuery(name = "Wasinfluencedby.findByHadPrimarySourceidHadPrimarySource", query = "SELECT w FROM Wasinfluencedby w WHERE w.hadPrimarySourceidHadPrimarySource = :hadPrimarySourceidHadPrimarySource")})
public class Wasinfluencedby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdWasInfluencedBy")
    private String idWasInfluencedBy;
    @Column(name = "WasRevisionOf_idWasDerivedFrom")
    private Integer wasRevisionOfidWasDerivedFrom;
    @Column(name = "WasQuotedFrom_idWasQuotedFrom")
    private Integer wasQuotedFromidWasQuotedFrom;
    @Column(name = "HadPrimarySource_idHadPrimarySource")
    private Integer hadPrimarySourceidHadPrimarySource;
    @ManyToMany(mappedBy = "wasinfluencedbyList")
    private List<Attribute> attributeList;
    @JoinColumn(name = "ActedOnBehalfOf_idActedOnBehalfOfl", referencedColumnName = "idActedOnBehalfOfl")
    @ManyToOne
    private Actedonbehalfof actedOnBehalfOfidActedOnBehalfOfl;
    @JoinColumn(name = "Used_idUsed", referencedColumnName = "idUsed")
    @ManyToOne
    private Used usedidUsed;
    @JoinColumn(name = "WasAssociatedWith_idWasAssociatedWith", referencedColumnName = "idWasAssociatedWith")
    @ManyToOne
    private Wasassociatedwith wasAssociatedWithidWasAssociatedWith;
    @JoinColumn(name = "WasAttributedTo_idWasAttributedTo", referencedColumnName = "idWasAttributedTo")
    @ManyToOne
    private Wasattributedto wasAttributedToidWasAttributedTo;
    @JoinColumn(name = "WasDerivedFrom_idWasDerivedFrom", referencedColumnName = "idWasDerivedFrom")
    @ManyToOne
    private Wasderivedfrom wasDerivedFromidWasDerivedFrom;
    @JoinColumn(name = "WasEndedBy_idWasEndedBy", referencedColumnName = "idWasEndedBy")
    @ManyToOne
    private Wasendedby wasEndedByidWasEndedBy;
    @JoinColumn(name = "WasGeneratedBy_idWasGeneratedBy", referencedColumnName = "idWasGeneratedBy")
    @ManyToOne
    private Wasgeneratedby wasGeneratedByidWasGeneratedBy;
    @JoinColumn(name = "WasInformedBy_idWasInformedBy", referencedColumnName = "idWasInformedBy")
    @ManyToOne
    private Wasinformedby wasInformedByidWasInformedBy;
    @JoinColumn(name = "WasInvalidatedBy_idWasInvalidatedBy", referencedColumnName = "idWasInvalidatedBy")
    @ManyToOne
    private Wasinvalidatedby wasInvalidatedByidWasInvalidatedBy;
    @JoinColumn(name = "WasStartedBy_idWasStartedBy", referencedColumnName = "idWasStartedBy")
    @ManyToOne
    private Wasstartedby wasStartedByidWasStartedBy;

    public Wasinfluencedby() {
    }

    public Wasinfluencedby(String idWasInfluencedBy) {
        this.idWasInfluencedBy = idWasInfluencedBy;
    }

    public String getIdWasInfluencedBy() {
        return idWasInfluencedBy;
    }

    public void setIdWasInfluencedBy(String idWasInfluencedBy) {
        this.idWasInfluencedBy = idWasInfluencedBy;
    }

    public Integer getWasRevisionOfidWasDerivedFrom() {
        return wasRevisionOfidWasDerivedFrom;
    }

    public void setWasRevisionOfidWasDerivedFrom(Integer wasRevisionOfidWasDerivedFrom) {
        this.wasRevisionOfidWasDerivedFrom = wasRevisionOfidWasDerivedFrom;
    }

    public Integer getWasQuotedFromidWasQuotedFrom() {
        return wasQuotedFromidWasQuotedFrom;
    }

    public void setWasQuotedFromidWasQuotedFrom(Integer wasQuotedFromidWasQuotedFrom) {
        this.wasQuotedFromidWasQuotedFrom = wasQuotedFromidWasQuotedFrom;
    }

    public Integer getHadPrimarySourceidHadPrimarySource() {
        return hadPrimarySourceidHadPrimarySource;
    }

    public void setHadPrimarySourceidHadPrimarySource(Integer hadPrimarySourceidHadPrimarySource) {
        this.hadPrimarySourceidHadPrimarySource = hadPrimarySourceidHadPrimarySource;
    }

    @XmlTransient
    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public Actedonbehalfof getActedOnBehalfOfidActedOnBehalfOfl() {
        return actedOnBehalfOfidActedOnBehalfOfl;
    }

    public void setActedOnBehalfOfidActedOnBehalfOfl(Actedonbehalfof actedOnBehalfOfidActedOnBehalfOfl) {
        this.actedOnBehalfOfidActedOnBehalfOfl = actedOnBehalfOfidActedOnBehalfOfl;
    }

    public Used getUsedidUsed() {
        return usedidUsed;
    }

    public void setUsedidUsed(Used usedidUsed) {
        this.usedidUsed = usedidUsed;
    }

    public Wasassociatedwith getWasAssociatedWithidWasAssociatedWith() {
        return wasAssociatedWithidWasAssociatedWith;
    }

    public void setWasAssociatedWithidWasAssociatedWith(Wasassociatedwith wasAssociatedWithidWasAssociatedWith) {
        this.wasAssociatedWithidWasAssociatedWith = wasAssociatedWithidWasAssociatedWith;
    }

    public Wasattributedto getWasAttributedToidWasAttributedTo() {
        return wasAttributedToidWasAttributedTo;
    }

    public void setWasAttributedToidWasAttributedTo(Wasattributedto wasAttributedToidWasAttributedTo) {
        this.wasAttributedToidWasAttributedTo = wasAttributedToidWasAttributedTo;
    }

    public Wasderivedfrom getWasDerivedFromidWasDerivedFrom() {
        return wasDerivedFromidWasDerivedFrom;
    }

    public void setWasDerivedFromidWasDerivedFrom(Wasderivedfrom wasDerivedFromidWasDerivedFrom) {
        this.wasDerivedFromidWasDerivedFrom = wasDerivedFromidWasDerivedFrom;
    }

    public Wasendedby getWasEndedByidWasEndedBy() {
        return wasEndedByidWasEndedBy;
    }

    public void setWasEndedByidWasEndedBy(Wasendedby wasEndedByidWasEndedBy) {
        this.wasEndedByidWasEndedBy = wasEndedByidWasEndedBy;
    }

    public Wasgeneratedby getWasGeneratedByidWasGeneratedBy() {
        return wasGeneratedByidWasGeneratedBy;
    }

    public void setWasGeneratedByidWasGeneratedBy(Wasgeneratedby wasGeneratedByidWasGeneratedBy) {
        this.wasGeneratedByidWasGeneratedBy = wasGeneratedByidWasGeneratedBy;
    }

    public Wasinformedby getWasInformedByidWasInformedBy() {
        return wasInformedByidWasInformedBy;
    }

    public void setWasInformedByidWasInformedBy(Wasinformedby wasInformedByidWasInformedBy) {
        this.wasInformedByidWasInformedBy = wasInformedByidWasInformedBy;
    }

    public Wasinvalidatedby getWasInvalidatedByidWasInvalidatedBy() {
        return wasInvalidatedByidWasInvalidatedBy;
    }

    public void setWasInvalidatedByidWasInvalidatedBy(Wasinvalidatedby wasInvalidatedByidWasInvalidatedBy) {
        this.wasInvalidatedByidWasInvalidatedBy = wasInvalidatedByidWasInvalidatedBy;
    }

    public Wasstartedby getWasStartedByidWasStartedBy() {
        return wasStartedByidWasStartedBy;
    }

    public void setWasStartedByidWasStartedBy(Wasstartedby wasStartedByidWasStartedBy) {
        this.wasStartedByidWasStartedBy = wasStartedByidWasStartedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasInfluencedBy != null ? idWasInfluencedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wasinfluencedby)) {
            return false;
        }
        Wasinfluencedby other = (Wasinfluencedby) object;
        if ((this.idWasInfluencedBy == null && other.idWasInfluencedBy != null) || (this.idWasInfluencedBy != null && !this.idWasInfluencedBy.equals(other.idWasInfluencedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wasinfluencedby[ idWasInfluencedBy=" + idWasInfluencedBy + " ]";
    }
    
}

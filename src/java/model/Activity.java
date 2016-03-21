/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Humberto
 */
@Entity
@Table(name = "activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByIdActivity", query = "SELECT a FROM Activity a WHERE a.idActivity = :idActivity"),
    @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a WHERE a.name = :name"),
    @NamedQuery(name = "Activity.findByStartTime", query = "SELECT a FROM Activity a WHERE a.startTime = :startTime"),
    @NamedQuery(name = "Activity.findByEndTime", query = "SELECT a FROM Activity a WHERE a.endTime = :endTime"),
    @NamedQuery(name = "Activity.findByIdProcessInstance", query = "SELECT a FROM Activity a WHERE a.idProcessInstance = :idProcessInstance"),
    @NamedQuery(name = "Activity.findByTypeActivity", query = "SELECT a FROM Activity a WHERE a.typeActivity = :typeActivity"),
    @NamedQuery(name = "Activity.findByPriority", query = "SELECT a FROM Activity a WHERE a.priority = :priority")})
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActivity")
    private Integer idActivity;
    @Column(name = "Name")
    private String name;
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "idProcessInstance")
    private int idProcessInstance;
    @Basic(optional = false)
    @Column(name = "Type_Activity")
    private String typeActivity;
    @Column(name = "Priority")
    private String priority;
    @JoinTable(name = "activity_attribute", joinColumns = {
        @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")}, inverseJoinColumns = {
        @JoinColumn(name = "Attribute_idAttribute", referencedColumnName = "idAttribute")})
    @ManyToMany
    private List<Attribute> attributeList;
    @OneToMany(mappedBy = "activityidActivity")
    private List<Actedonbehalfof> actedonbehalfofList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<Wasassociatedwith> wasassociatedwithList;
    @OneToMany(mappedBy = "activityidActivity")
    private List<Wasderivedfrom> wasderivedfromList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<Used> usedList;
    @OneToMany(mappedBy = "activityidActivity")
    private List<Wasinvalidatedby> wasinvalidatedbyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivityInformed")
    private List<Wasinformedby> wasinformedbyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivityInformant")
    private List<Wasinformedby> wasinformedbyList1;
    @OneToMany(mappedBy = "activityidActivity")
    private List<Wasgeneratedby> wasgeneratedbyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<Wasstartedby> wasstartedbyList;
    @OneToMany(mappedBy = "activityidActivityStarter")
    private List<Wasstartedby> wasstartedbyList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<Wasendedby> wasendedbyList;
    @OneToMany(mappedBy = "activityidActivityEnded")
    private List<Wasendedby> wasendedbyList1;

    public Activity() {
    }

    public Activity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Activity(Integer idActivity, int idProcessInstance, String typeActivity) {
        this.idActivity = idActivity;
        this.idProcessInstance = idProcessInstance;
        this.typeActivity = typeActivity;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdProcessInstance() {
        return idProcessInstance;
    }

    public void setIdProcessInstance(int idProcessInstance) {
        this.idProcessInstance = idProcessInstance;
    }

    public String getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(String typeActivity) {
        this.typeActivity = typeActivity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
    public List<Wasinformedby> getWasinformedbyList() {
        return wasinformedbyList;
    }

    public void setWasinformedbyList(List<Wasinformedby> wasinformedbyList) {
        this.wasinformedbyList = wasinformedbyList;
    }

    @XmlTransient
    public List<Wasinformedby> getWasinformedbyList1() {
        return wasinformedbyList1;
    }

    public void setWasinformedbyList1(List<Wasinformedby> wasinformedbyList1) {
        this.wasinformedbyList1 = wasinformedbyList1;
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
    public List<Wasstartedby> getWasstartedbyList1() {
        return wasstartedbyList1;
    }

    public void setWasstartedbyList1(List<Wasstartedby> wasstartedbyList1) {
        this.wasstartedbyList1 = wasstartedbyList1;
    }

    @XmlTransient
    public List<Wasendedby> getWasendedbyList() {
        return wasendedbyList;
    }

    public void setWasendedbyList(List<Wasendedby> wasendedbyList) {
        this.wasendedbyList = wasendedbyList;
    }

    @XmlTransient
    public List<Wasendedby> getWasendedbyList1() {
        return wasendedbyList1;
    }

    public void setWasendedbyList1(List<Wasendedby> wasendedbyList1) {
        this.wasendedbyList1 = wasendedbyList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivity != null ? idActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.idActivity == null && other.idActivity != null) || (this.idActivity != null && !this.idActivity.equals(other.idActivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Activity[ idActivity=" + idActivity + " ]";
    }
    
    @Transient 
    public String getDuration(){
        long duration =  endTime.getTime() - startTime.getTime();
        return  duration <= 0 ? "00" : String.format("%03d:%02d", TimeUnit.MILLISECONDS.toHours(duration), TimeUnit.MILLISECONDS.toMinutes(duration % 60)  );
    }
}

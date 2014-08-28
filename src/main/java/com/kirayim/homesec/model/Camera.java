/*----------------------------------------------------------------------------+
-| File name............Camera.java
| Created by...........shalom
| Creation date........Aug 27, 2014
+-----------------------------------------------------------------------------+
|  IMPORTANT NOTE: This file contains  proprietary  information  which  is    |
|  private  to  Transspot Ltd. You  are  forbidden  to allow  any  of  the    |
|  information, code, algorithms, methods etc. contained herein to come to    |
|  the notice of parties  not specifically authorized for  that purpose by    |
|  Transspot Ltd. or use any of the intellectual property contained herein    |
|  except as authorized by Transspot Ltd . Copyright (C) Transspot Ltd, 2009  |
+-----------------------------------------------------------------------------+
| Update     Date       By           Note                                     |
| ------     ----       --           ----                                     |
|                                                                             |
+-----------------------------------------------------------------------------+
|$Id$
+-----------------------------------------------------------------------------+
| Purpose..............                                                       |
| Headers..............                                                       |
| External procedures..                                                       |
| External data........                                                       |
| Public procedures....                                                       |
| Public data..........                                                       |
| Documentation refs...                                                       |
| Key points...........                                                       |
|                                                                             |
+-----------------------------------------------------------------------------*/

package com.kirayim.homesec.model;



/*--------------------------------------------------------------------+
 |                            Imports                                  |
 +--------------------------------------------------------------------*/

//===== Java main libraries =====

//===== Java extension libraries =====
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.appfuse.model.BaseObject;
import org.appfuse.model.User;
import org.hibernate.annotations.Index;


//===== Third party =====

//===== Transspot =====

//===== Local project =====

/**
 * @author shalom
 *
 */
@XmlRootElement
@Entity(name="cameras")
public class Camera extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Index(name = "cameras_name_index")
    Long cameraId;

    @Column
    String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Date creationTime = new Date();


    @ManyToOne
    User owner;


    public Camera() {}


    /**
     * @return the cameraId
     */
    public Long getCameraId() {
        return cameraId;
    }

    /**
     * @param cameraId the cameraId to set
     */
    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (cameraId ^ (cameraId >>> 32));
        result = prime * result
                + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        return result;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Camera other = (Camera) obj;
        if (cameraId != other.cameraId)
            return false;
        if (creationTime == null) {
            if (other.creationTime != null)
                return false;
        } else if (!creationTime.equals(other.creationTime))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        return true;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Camera [cameraId=" + cameraId + ", name=" + name
                + ", creationTime=" + creationTime + "]";
    }


    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }


    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }






}

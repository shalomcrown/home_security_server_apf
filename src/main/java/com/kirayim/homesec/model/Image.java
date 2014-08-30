/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shalom
 */
@XmlRootElement
@Entity(name = "image")
public class Image
   {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Long id;

   @ManyToOne
   Camera camera;

   @Column
   String filename;

   @Column
   @Temporal(TemporalType.TIMESTAMP)
   Date uploadTime;

   public Image() {}

   public Image(Camera camera, String filename, Date uploadTime) {
      this.camera = camera;
      this.filename = filename;
      this.uploadTime = uploadTime;
   }


   @Override
   public String toString()
      {
      return "Image{" + "id=" + id + ", filename=" + filename + ", uploadTime=" + uploadTime + '}';
      }

   @Override
   public int hashCode()
      {
      int hash = 3;
      hash = 31 * hash + Objects.hashCode(this.id);
      hash = 31 * hash + Objects.hashCode(this.filename);
      hash = 31 * hash + Objects.hashCode(this.uploadTime);
      return hash;
      }

   @Override
   public boolean equals(Object obj)
      {
      if (obj == null)
         {
         return false;
         }
      if (getClass() != obj.getClass())
         {
         return false;
         }
      final Image other = (Image) obj;
      if (!Objects.equals(this.id, other.id))
         {
         return false;
         }
      if (!Objects.equals(this.camera, other.camera))
         {
         return false;
         }
      if (!Objects.equals(this.filename, other.filename))
         {
         return false;
         }
      if (!Objects.equals(this.uploadTime, other.uploadTime))
         {
         return false;
         }
      return true;
      }

   public Long getId()
      {
      return id;
      }

   public void setId(Long id)
      {
      this.id = id;
      }

   public Camera getCamera()
      {
      return camera;
      }

   public void setCamera(Camera camera)
      {
      this.camera = camera;
      }

   public String getFilename()
      {
      return filename;
      }

   public void setFilename(String filename)
      {
      this.filename = filename;
      }

   public Date getUploadTime()
      {
      return uploadTime;
      }

   public void setUploadTime(Date uploadTime)
      {
      this.uploadTime = uploadTime;
      }

   }

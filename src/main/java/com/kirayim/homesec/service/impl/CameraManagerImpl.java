/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service.impl;

import com.kirayim.homesec.model.Camera;
import java.util.Date;
import org.appfuse.dao.GenericDao;
import org.appfuse.model.User;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author shalom
 */
@Service("cameraManager")
public class CameraManagerImpl extends GenericManagerImpl<Camera, Long> {


    public CameraManagerImpl(GenericDao<Camera, Long> genericDao) {
        this.dao = genericDao;
    }

   @Override
   public Camera save(Camera object)
      {

      // New object
      if (object.getCameraId() == null) {
         object.setCreationTime(new Date());

         User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

         object.setOwner(user);
      }

      return super.save(object); //To change body of generated methods, choose Tools | Templates.
      }

   }

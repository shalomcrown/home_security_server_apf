/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service;

import com.kirayim.homesec.model.Camera;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.appfuse.service.GenericManager;

/**
 *
 * @author shalom
 */
@Path("/cameras")
public interface CameraManager extends GenericManager<Camera, Long> {

   @GET
   @Override
   public List<Camera> getAll();

   @POST
   @Override
   public Camera save(Camera object);
   }

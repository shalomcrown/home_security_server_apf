/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service;

import com.kirayim.homesec.model.Image;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.appfuse.service.GenericManager;

/**
 *
 * @author shalom
 */
@Path("/images")
public interface ImageManager extends GenericManager<Image, Long>
   {
   @GET
   List<Image> getAll();


   @POST
   @Path("{cameraId}")
   @Consumes(MediaType.MULTIPART_FORM_DATA)
   Image handleFileUpload(@PathParam("cameraId") long cameraId,  @Context HttpServletRequest request);

   }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service;

import com.kirayim.homesec.model.Image;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
   }

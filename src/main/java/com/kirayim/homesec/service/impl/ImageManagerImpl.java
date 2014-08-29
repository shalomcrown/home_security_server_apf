/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service.impl;

import com.kirayim.homesec.model.Image;
import com.kirayim.homesec.service.ImageManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.core.Context;
import org.apache.commons.io.IOUtils;
import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author shalom
 */
@Service("imageManager")
public class ImageManagerImpl extends GenericManagerImpl<Image, Long>  implements ImageManager {
   public static Logger logger = LoggerFactory.getLogger(ImageManagerImpl.class.getName());
   static File uploadDir = new File(System.getProperty("user.home"), ".homesec_images");


   public ImageManagerImpl(GenericDao<Image, Long> genericDao) {
        this.dao = genericDao;
   }

   @Override
   public List<Image> getAll() {
       return dao.getAll();
   }


   @Override
    public String handleFileUpload(HttpServletRequest request){
      String name = "fred";

      String encoding = request.getCharacterEncoding();

      if (encoding == null) {
          try {
              request.setCharacterEncoding(encoding = "UTF-8");
          } catch (UnsupportedEncodingException ex) {
              logger.warn("Bad encoding", ex);
          }
      }

      try {
         if (request.getParts().size() > 1) {
            return "Only one upload per request!";
         }

         for (Part part : request.getParts()) {
            if (! uploadDir.exists()) {
               uploadDir.mkdirs();
            }

            File outputFile = new File(uploadDir, name);

            OutputStream out = new FileOutputStream(outputFile);

            InputStream in = part.getInputStream();

            IOUtils.copy(in, out);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
//            part.write(new File(uploadDir, name).getAbsolutePath());
         }

      } catch (IOException | ServletException e ) {
         logger.warn("Bad upload", e);
      }

       return "What?";
    }

}

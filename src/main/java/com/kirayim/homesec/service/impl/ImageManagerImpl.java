/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service.impl;

import com.kirayim.homesec.model.Camera;
import com.kirayim.homesec.model.Image;
import com.kirayim.homesec.service.CameraManager;
import com.kirayim.homesec.service.ImageManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javassist.tools.web.BadHttpRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import org.apache.commons.io.IOUtils;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.appfuse.dao.GenericDao;
import org.appfuse.model.User;
import org.appfuse.service.impl.GenericManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author shalom
 */
@Service("imageManager")
public class ImageManagerImpl extends GenericManagerImpl<Image, Long>  implements ImageManager {
   public static Logger logger = LoggerFactory.getLogger(ImageManagerImpl.class.getName());
   static File uploadDir = new File(System.getProperty("user.home"), ".homesec_images");

   @Autowired
   CameraManager cameraManager;

   public ImageManagerImpl(GenericDao<Image, Long> genericDao) {
        this.dao = genericDao;
   }

   @Override
   public List<Image> getAll() {
       return dao.getAll();
   }


   @Override
    public Image handleFileUpload(long cameraId, HttpServletRequest request){
      String extension = null;
      Date uploadTime = new Date();

      String encoding = request.getCharacterEncoding();

      if (encoding == null) {
          try {
              request.setCharacterEncoding(encoding = "UTF-8");
          } catch (UnsupportedEncodingException ex) {
              logger.warn("Bad encoding", ex);
          }
      }

      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      Camera camera = cameraManager.get(cameraId);

      if (camera == null) {
         throw new NotAllowedException("Imvalid camera ID");
      }

      if (! camera.getOwner().equals(user)) {
         throw new NotAllowedException("Camera doesn't belong to current user");
      }

      /* -------------------------------------------------
       * Calculate output file name
       * ------------------------------------------------- */

      String name = String.format("homesec_image_%1$08d_%2$TY%2$Tm%2$Td_%2$TH%2$TM%2$TS%2$TL", camera.getCameraId(), uploadTime);

      /* -------------------------------------------------
       * Get uploaded file and write to destination
       * ------------------------------------------------- */

      try {
         if (request.getParts().size() > 1) {
            throw new NotAllowedException("Only one image upload per transaction");
         }

         for (Part part : request.getParts()) {
            if (! uploadDir.exists()) {
               uploadDir.mkdirs();
            }

            String contentType = part.getContentType();

            if (contentType != null) {
               try {
                  MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
                  MimeType mimeType = allTypes.forName(contentType);

                  extension = mimeType.getExtension();

                  name += extension;

               } catch (MimeTypeException mimeTypeException){
                  logger.warn("Mime? ", mimeTypeException);
               }
            }

            File outputFile = new File(uploadDir, name);

            OutputStream out = new FileOutputStream(outputFile);

            InputStream in = part.getInputStream();

            IOUtils.copy(in, out);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
         }
      } catch (IOException | ServletException e ) {
         throw new BadRequestException(e);
      }

      /* -------------------------------------------------
       * If extension is unknown, re-read file to try
       * and get extension
       * ------------------------------------------------- */

      if (extension == null) {
      // TODO:
      }

      /* -------------------------------------------------
       * Save image record in DB
       * ------------------------------------------------- */

      Image image = new Image(camera, name, uploadTime);

      dao.save(image);

       return image;
    }

}

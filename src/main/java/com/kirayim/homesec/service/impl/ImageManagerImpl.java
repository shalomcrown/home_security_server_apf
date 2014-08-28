/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kirayim.homesec.service.impl;

import com.kirayim.homesec.model.Image;
import com.kirayim.homesec.service.ImageManager;
import javax.jws.WebService;
import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author shalom
 */
@Service("imageManager")
public class ImageManagerImpl extends GenericManagerImpl<Image, Long>  implements ImageManager {
   public ImageManagerImpl(GenericDao<Image, Long> genericDao) {
        this.dao = genericDao;
   }

}

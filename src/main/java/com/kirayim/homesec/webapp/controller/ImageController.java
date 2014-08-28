package com.kirayim.homesec.webapp.controller;

import org.appfuse.dao.SearchException;
import org.appfuse.service.GenericManager;
import com.kirayim.homesec.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/images*")
public class ImageController {
    private GenericManager<Image, Long> imageManager;

    @Autowired
    public void setImageManager(@Qualifier("imageManager") GenericManager<Image, Long> imageManager) {
        this.imageManager = imageManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(imageManager.search(query, Image.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(imageManager.getAll());
        }
        return model;
    }
}

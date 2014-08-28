package com.kirayim.homesec.webapp.controller;

import org.appfuse.service.GenericManager;
import com.kirayim.homesec.model.Image;

import com.kirayim.homesec.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImageControllerTest extends BaseControllerTestCase {
    @Autowired
    private ImageController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("imageList"));
        assertTrue(((List) m.get("imageList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        GenericManager<Image, Long> imageManager = (GenericManager<Image, Long>) applicationContext.getBean("imageManager");
        imageManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("imageList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
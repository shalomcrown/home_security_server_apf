package com.kirayim.homesec.webapp.controller;

import com.kirayim.homesec.model.Camera;
import com.kirayim.homesec.webapp.controller.BaseControllerTestCase;

import java.util.List;
import java.util.Map;
import org.appfuse.service.GenericManager;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class CameraControllerTest extends BaseControllerTestCase {
    @Autowired
    private CameraController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("cameraList"));
        assertTrue(((List) m.get("cameraList")).size() > 0);
    }

    @Test
    @Ignore
    public void testSearch() throws Exception {
        // regenerate indexes
        @SuppressWarnings("unchecked")
        GenericManager<Camera, Long> cameraManager = (GenericManager<Camera, Long>) applicationContext.getBean("cameraManager");
        cameraManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("cameraList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
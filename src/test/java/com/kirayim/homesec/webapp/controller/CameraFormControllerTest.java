package com.kirayim.homesec.webapp.controller;

import com.kirayim.homesec.webapp.controller.BaseControllerTestCase;
import com.kirayim.homesec.model.Camera;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CameraFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private CameraFormController form;
    private Camera camera;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/cameraform");
        request.addParameter("cameraId", "-1");

        camera = form.showForm(request);
        assertNotNull(camera);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/cameraform");
        request.addParameter("cameraId", "-1");

        camera = form.showForm(request);
        assertNotNull(camera);

        request = newPost("/cameraform");

        camera = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(camera).getBindingResult();
        form.onSubmit(camera, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/cameraform");
        request.addParameter("delete", "");
        camera = new Camera();
        camera.setCameraId(-2L);

        BindingResult errors = new DataBinder(camera).getBindingResult();
        form.onSubmit(camera, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}

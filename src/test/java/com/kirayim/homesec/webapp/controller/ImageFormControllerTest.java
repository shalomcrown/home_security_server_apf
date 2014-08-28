package com.kirayim.homesec.webapp.controller;

import com.kirayim.homesec.webapp.controller.BaseControllerTestCase;
import com.kirayim.homesec.model.Image;
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

public class ImageFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private ImageFormController form;
    private Image image;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/imageform");
        request.addParameter("id", "-1");

        image = form.showForm(request);
        assertNotNull(image);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/imageform");
        request.addParameter("id", "-1");

        image = form.showForm(request);
        assertNotNull(image);

        request = newPost("/imageform");

        image = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(image).getBindingResult();
        form.onSubmit(image, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/imageform");
        request.addParameter("delete", "");
        image = new Image();
        image.setId(-2L);

        BindingResult errors = new DataBinder(image).getBindingResult();
        form.onSubmit(image, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}

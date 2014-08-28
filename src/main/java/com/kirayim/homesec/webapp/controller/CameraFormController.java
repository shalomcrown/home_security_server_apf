package com.kirayim.homesec.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.kirayim.homesec.model.Camera;
import com.kirayim.homesec.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/cameraform*")
public class CameraFormController extends BaseFormController {
    private GenericManager<Camera, Long> cameraManager = null;

    @Autowired
    public void setCameraManager(@Qualifier("cameraManager") GenericManager<Camera, Long> cameraManager) {
        this.cameraManager = cameraManager;
    }

    public CameraFormController() {
        setCancelView("redirect:cameras");
        setSuccessView("redirect:cameras");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Camera showForm(HttpServletRequest request)
    throws Exception {
        String cameraId = request.getParameter("cameraId");

        if (!StringUtils.isBlank(cameraId)) {
            return cameraManager.get(new Long(cameraId));
        }

        return new Camera();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Camera camera, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(camera, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "cameraform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (camera.getCameraId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            cameraManager.remove(camera.getCameraId());
            saveMessage(request, getText("camera.deleted", locale));
        } else {
            cameraManager.save(camera);
            String key = (isNew) ? "camera.added" : "camera.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:cameraform?cameraId=" + camera.getCameraId();
            }
        }

        return success;
    }
}

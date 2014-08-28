package com.kirayim.homesec.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.kirayim.homesec.model.Image;
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
@RequestMapping("/imageform*")
public class ImageFormController extends BaseFormController {
    private GenericManager<Image, Long> imageManager = null;

    @Autowired
    public void setImageManager(@Qualifier("imageManager") GenericManager<Image, Long> imageManager) {
        this.imageManager = imageManager;
    }

    public ImageFormController() {
        setCancelView("redirect:images");
        setSuccessView("redirect:images");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Image showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return imageManager.get(new Long(id));
        }

        return new Image();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Image image, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(image, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "imageform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (image.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            imageManager.remove(image.getId());
            saveMessage(request, getText("image.deleted", locale));
        } else {
            imageManager.save(image);
            String key = (isNew) ? "image.added" : "image.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:imageform?id=" + image.getId();
            }
        }

        return success;
    }
}

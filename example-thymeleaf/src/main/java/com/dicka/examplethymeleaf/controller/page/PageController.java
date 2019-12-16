package com.dicka.examplethymeleaf.controller.page;

import com.dicka.examplethymeleaf.dto.RequestVendor;
import com.dicka.examplethymeleaf.model.Vendor;
import com.dicka.examplethymeleaf.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Controller
public class PageController {

    @Autowired
    private VendorService vendorService;

    @GetMapping(value = "/vendor-form")
    public ModelAndView getVendorForm(){
        RequestVendor requestVendor = new RequestVendor();
        ModelAndView modelAndView = new ModelAndView();
        /** handling for requestVendor **/
        modelAndView.addObject("requestVendor", requestVendor);
        modelAndView.setViewName("content/vendor-content-form");
        modelAndView.addObject("title", "Vendor Form");
        return modelAndView;
    }

    @GetMapping(value = "/vendor-table")
    public ModelAndView getVendorTable(){
        ModelAndView view = new ModelAndView();
        ArrayList listVendors = vendorService.listVendor();
        view.addObject("title", "Vendor Table");
        view.addObject("vendors", listVendors);
        view.setViewName("content/vendor-content-table");
        return view;
    }

    /** handling image cover bank letter .jpa, .png **/
    @GetMapping(value = "/vendor-image-coverbank")
    public void handlingImageCoverBank(HttpServletRequest servletRequest,
                                       HttpServletResponse servletResponse,
                                       Model model, @RequestParam("id")Long id) throws IOException{

        Vendor vendor = null;
        if (id!=null){
            vendor = this.vendorService.findVendorById(id);
        }

        if (vendor != null && vendor.getCoverBankAcc()!=null){
            servletResponse.setContentType("image/jpeg, image/jpeg ,image/png, image/gif");
            servletResponse.getOutputStream().write(vendor.getCoverBankAcc());
        }
        servletResponse.getOutputStream().close();
    }

    /** handling image NPWP format .jpg, .png**/
    @GetMapping(value = "/vendor-image-npwp")
    public void handlingImageNPWP(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                              Model model, @RequestParam("id")Long id) throws IOException {

        Vendor vendor = null;
        if (id!=null){
            vendor = this.vendorService.findVendorById(id);
        }

        if (vendor != null && vendor.getNpwpFile()!=null){
            servletResponse.setContentType("image/jpeg, image/jpeg ,image/png, image/gif");
            servletResponse.getOutputStream().write(vendor.getNpwpFile());
        }
        servletResponse.getOutputStream().close();
    }

    @PostMapping(value = "/register-vendor")
    public String registerVendor(@ModelAttribute @Valid RequestVendor requestVendor){
        log.info("TEST INSERT VENDOR : "+requestVendor.toString());
        vendorService.createVendor(requestVendor);
        return "redirect:/vendor";
    }
}

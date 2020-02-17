package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by EMINCAKICI on Feb Tue,2020
 */
@Controller
public class PetClinicController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value="/login.html")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = {"/", "/index.html"})
    public ModelAndView index() {
        ModelAndView mev = new ModelAndView();
        mev.setViewName("index");
        return mev;
    }

    @RequestMapping("/owners")
    public ModelAndView getOwners() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("owners", petClinicService.findOwners());
        modelAndView.setViewName("owners");
        return modelAndView;
    }

    @RequestMapping("/pcs")
    @ResponseBody
    public String welcome() {
        return "Welcome to pet.";
    }
}

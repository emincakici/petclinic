package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by EMINCAKICI on Feb Fri 21,2020
 */
@Controller
public class PetClinicNewOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value = "/owners/new", method = RequestMethod.GET)
    private String newOwner() {
        return "newOwner";
    }

    @ModelAttribute
    public Owner initModel() {
        return new Owner();
    }

    @RequestMapping(value = "/owners/new", method = RequestMethod.POST)
    public String handleFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "newOwner";
        }
        petClinicService.createOwner(owner);
        redirectAttributes.addFlashAttribute("message", "owner created with id: " + owner.getId());
        return "redirect:/owners";
    }
}

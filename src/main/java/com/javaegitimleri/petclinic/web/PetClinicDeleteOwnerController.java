package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by EMINCAKICI on Feb Fri 21,2020
 */
@Controller
public class PetClinicDeleteOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    //ModelMap: Controllerdan view a veri aktarılmasını sağlayan sınıftır.
    @RequestMapping(value = "/owners/delete/{id}", method = RequestMethod.GET)
    public String loadOwner(@PathVariable Long id, ModelMap modelMap) {
        Owner owner = petClinicService.findOwner(id);
        modelMap.put("owner", owner);
        return "deleteOwner";
    }

    @RequestMapping(value = "/owners/delete/{id}", method = RequestMethod.POST)
    public String handleFormSubmit(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        petClinicService.deleteOwner(id);
        redirectAttributes.addFlashAttribute("message", "owner deleted with id: " + id);
        return "redirect:/owners";
    }
}

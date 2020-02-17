package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.exception.InternalServerException;
import com.javaegitimleri.petclinic.exception.OwnerNotFoundExceptiıon;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by EMINCAKICI on Feb Sun 09,2020
 */
@RestController
@RequestMapping("/rest")
public class PetClinicRestController {
    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        try {
            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundExceptiıon one) {
            throw one;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable Long id, @RequestBody Owner ownerRequest) {
        try {
            Owner owner = petClinicService.findOwner(id);
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            petClinicService.updateOwner(owner);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundExceptiıon e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            petClinicService.createOwner(owner);
            Long id = owner.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return (ResponseEntity<URI>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petClinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        try {
            Owner owner = petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundExceptiıon ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName) {
        List<Owner> owners = petClinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}",produces = "application/json")
    public ResponseEntity<?> getOwnerAsHateoas(@PathVariable("id") Long id) {
        try {
            Owner owner = petClinicService.findOwner(id);
            Link self = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withSelfRel();
            Link create = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withRel("create");
            Link update = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withRel("update");
            Link delete = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withRel("delete");
            Resource<Owner> resource = new Resource<>(owner,self,create,update,delete);
            return ResponseEntity.ok(resource);
        } catch (OwnerNotFoundExceptiıon ex) {
            return ResponseEntity.noContent().build();
        }
    }

}

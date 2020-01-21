package com.example.petclinic.controller;

import com.example.petclinic.model.Owner;
import com.example.petclinic.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("owner")
public class OwnerController implements BasicController<Owner> {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {

        this.ownerService = ownerService;
    }

    @Override
    @PostMapping(value = "addOwner", produces = "application/json")
    public Owner add(@RequestBody Owner owner) {

        return this.ownerService.add(owner);
    }

    @Override
    @GetMapping(value = "getById/{id}", produces = "application/json")
    public Owner get(@PathVariable("id") Long id) {

        // Demonstrates exception handling with ResponseStatusException exception
        Owner owner = null;
        try {
            owner = this.ownerService.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Owner [" + id + "] Not Found", exc);
        }
        return owner;
    }

    @Override
    @PutMapping(value = "updateOwner", produces = "application/json")
    public Owner modify(@RequestBody Owner owner) {

        return this.ownerService.modify(owner);
    }

    @Override
    @RequestMapping(value = "deleteOwner", method = {RequestMethod.DELETE}, produces = "application/json")
    public boolean delete(@RequestBody Owner owner) {

        return this.ownerService.delete(owner);
    }

    @Override
    @GetMapping(value = "getAllOwners", produces = "application/json")
    public List<Owner> getAll() {

        List<Owner> all = this.ownerService.getAll();
        return all;
    }

    @GetMapping(value = "getOwnerByName/{name}", produces = "application/json")
    public List<Owner> getOwnerByName(@PathVariable("name")String name) {

        return this.ownerService.getOwnerByName(name);
    }

}

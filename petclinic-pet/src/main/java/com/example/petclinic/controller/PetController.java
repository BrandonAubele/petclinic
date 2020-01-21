package com.example.petclinic.controller;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController implements BasicController<Pet> {

    private PetService petService;

    public PetController(PetService petService) {

        this.petService = petService;
    }

    @Override
    @PostMapping(value = "addPet", produces = "application/json")
    public Pet add(@RequestBody Pet pet) {

        return this.petService.add(pet);
    }

    @Override
    @GetMapping(value = "getPetById/{id}", produces = "application/json")
    public Pet get(@PathVariable("id") Long id) {

        return this.petService.get(id);
    }

    @Override
    @PutMapping(value = "updatePet", produces = "application/json")
    public Pet modify(@RequestBody Pet pet) {

        return this.petService.modify(pet);
    }

    @Override
    @DeleteMapping(value = "deletePet", produces = "application/json")
    public boolean delete(@RequestBody Pet pet) {

        return this.petService.delete(pet);
    }

    @Override
    @GetMapping(value = "getAllPets", produces = "application/json")
    public List<Pet> getAll() {

        return this.petService.getAll();

    }

    @GetMapping(value = "getAllPetsByOwner", produces = "application/json")
    public List<Pet> getAllPetsForOwner(@RequestBody Owner owner) {

        return this.petService.getAllPetsForOwner(owner);
    }

    @GetMapping(value = "getPetByName", produces = "application/json")
    public List<Pet> getPetByName(@RequestBody String name) {

        return this.petService.getPetByName(name);
    }
}

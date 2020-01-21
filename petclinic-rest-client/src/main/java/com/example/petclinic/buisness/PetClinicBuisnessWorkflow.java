package com.example.petclinic.buisness;

import com.example.petclinic.models.Owner;
import com.example.petclinic.models.Pet;
import com.example.petclinic.models.PetType;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PetClinicBuisnessWorkflow {

    OwnerService ownerService;
    PetService petService;

    public PetClinicBuisnessWorkflow(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    

    public void runBuisness(){
            String gameChanger = "Homer_Simpson";
            String simpsonAddress = "742 Evergreen Terrace";
            String simpsonCity = "Springfield";
            String simpsonHomePhone = "9395550113";
            // Create Owners
            Owner owner1 = Owner.builder().withName(gameChanger).withAddress(simpsonAddress).withCity(simpsonCity).withPhoneNumber(simpsonHomePhone).build();
            Owner owner2 = Owner.builder().withName("Marge_Simpson").withAddress(simpsonAddress).withCity(simpsonCity).withPhoneNumber(simpsonHomePhone).build();
            Owner owner3 = Owner.builder().withName("Bart_Simpson").withAddress(simpsonAddress).withCity(simpsonCity).withPhoneNumber(simpsonHomePhone).build();
            Owner owner4 = Owner.builder().withName("Lisa_Simpson").withAddress(simpsonAddress).withCity(simpsonCity).withPhoneNumber(simpsonHomePhone).build();

            // Pets for Homer
            Pet pet1 = Pet.builder().withName("Strangles").withBirthDate(new Date()).withPetType(PetType.SNAKE).build();
            Pet pet2 = Pet.builder().withName("Mojo").withBirthDate(new Date()).withPetType(PetType.MONKEY).build();
            Pet pet3 = Pet.builder().withName("Pinchy").withBirthDate(new Date()).withPetType(PetType.LOBSTER).build();
            Pet pet4 = Pet.builder().withName("Plopper").withBirthDate(new Date()).withPetType(PetType.PIG).build();

            // Pets for Marge
            Pet pet5 = Pet.builder().withName("Greyhound").withBirthDate(new Date()).withPetType(PetType.DOG).build();

            // Pets for Bart
            Pet pet6 = Pet.builder().withName("Laddie").withBirthDate(new Date()).withPetType(PetType.DOG).build();
            Pet pet7 = Pet.builder().withName("Santa's Little Helper").withBirthDate(new Date()).withPetType(PetType.DOG).build();
            Pet pet8 = Pet.builder().withName("Stampy").withBirthDate(new Date()).withPetType(PetType.ELEPHANT).build();
            Pet pet9 = Pet.builder().withName("Duncan").withBirthDate(new Date()).withPetType(PetType.HORSE).build();


            // Pets for Lisa
            Pet pet10 = Pet.builder().withName("Nibbles").withBirthDate(new Date()).withPetType(PetType.HAMPSTER).build();
            Pet pet11 = Pet.builder().withName("Chirpy Boy").withBirthDate(new Date()).withPetType(PetType.LIZARD).build();
            Pet pet12 = Pet.builder().withName("Bart Junior").withBirthDate(new Date()).withPetType(PetType.LIZARD).build();
            Pet pet13 = Pet.builder().withName("Snowball IV").withBirthDate(new Date()).withPetType(PetType.CAT).build();
            Pet pet14 = Pet.builder().withName("Princess").withBirthDate(new Date()).withPetType(PetType.HORSE).build();
            // Add Owners
            ownerService.saveOwner(owner1);
            ownerService.saveOwner(owner2);
            ownerService.saveOwner(owner3);
            ownerService.saveOwner(owner4);
            // Add Pets
            petService.savePet(pet1);
            petService.savePet(pet2);
            petService.savePet(pet3);
            petService.savePet(pet4);
            petService.savePet(pet5);
            petService.savePet(pet6);
            petService.savePet(pet7);
            petService.savePet(pet8);
            petService.savePet(pet9);
            petService.savePet(pet10);
            petService.savePet(pet11);
            petService.savePet(pet12);
            petService.savePet(pet13);
            petService.savePet(pet14);

            ownerService.getAllOwners();
            petService.getAllPets();


            ownerService.getOwnerById(1l);
            ownerService.deleteOwner(owner2);
            ownerService.getAllOwners();
            ownerService.getOwnerByName(gameChanger);
            ownerService.updateOwner(owner3, gameChanger);
            ownerService.getAllOwners();
        }


    }



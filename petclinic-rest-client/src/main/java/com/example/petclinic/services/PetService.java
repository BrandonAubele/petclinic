package com.example.petclinic.services;

import com.example.petclinic.models.Pet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    RestTemplate restTemplate;



    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public Pet savePet(Pet pet) {
        URI uri = URI.create("http://localhost:9092/help/pet/addPet/");
        Pet response = restTemplate.postForObject(uri,pet, Pet.class);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Pet getPetById(Long id) {
        Pet response = restTemplate.getForObject("http://localhost:9092/help/pet/getById/{id}",Pet.class, id);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Pet getPetByName(String name){
        HttpEntity<Pet> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Pet>> responseType = new ParameterizedTypeReference<List<Pet>>() {};
        ResponseEntity<List<Pet>> resp = restTemplate.exchange("http://localhost:9092/help/pet/getPetByName/"+name,
                HttpMethod.GET, entity,  responseType);
        List<Pet> list = resp.getBody();
        Pet newPet =list.get(0);
        String message = newPet.toString();
        log.info(message);
        return newPet;
    }
    public List<Pet> getAllPets(){
        HttpEntity<Pet> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Pet>> responseType = new ParameterizedTypeReference<List<Pet>>(){};
        ResponseEntity<List<Pet>> resp = restTemplate.exchange("http://localhost:9092/help/pet/getAllPets",
                HttpMethod.GET, entity,  responseType);
        List<Pet> list = resp.getBody();
        String message = String.valueOf(list);
        log.info(message);
        return list;
        /*TODO This is an alternative method for doing this
        URI uri = URI.create("http://localhost:9092/help/pet/getAllPets");
        List<Pet> response = restTemplate.getForObject(uri,List.class);
        String message = response.toString();
        log.info(message);
        return response;*/
    }
    public void deletePet(Pet pet) {
        URI uri = URI.create("http://localhost:9092/help/pet/deletePet/");
        HttpEntity<Pet> entity = new HttpEntity<>(pet);
        restTemplate.exchange(uri,
                HttpMethod.DELETE,entity,String.class);

    }
    //Delete the record ten call this to populate the null space
    public Pet updatePet(Pet pet, String name){
        HttpEntity<Pet> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Pet>> responseType = new ParameterizedTypeReference<List<Pet>>(){};
        ResponseEntity<List<Pet>> resp = restTemplate.exchange("http://localhost:9092/help/pet/getPetByName/"+ name,
                HttpMethod.GET, entity,  responseType);
        List<Pet> list = resp.getBody();
        Pet responsePet = list.get(0);
        responsePet.setName(pet.getName());
        responsePet.setBirthDate(pet.getBirthDate());
        responsePet.setPetType(pet.getPetType());
        responsePet.setOwner(pet.getOwner());
        HttpEntity<Pet> entity2= new HttpEntity<>(responsePet);
        restTemplate.exchange("http://localhost:9092/help/pet/updatePet",HttpMethod.PUT,entity2,Pet.class);
        return responsePet;

    }





}

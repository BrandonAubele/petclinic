package com.example.petclinic.services;

import com.example.petclinic.models.Vet;

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
public class VetService {

    private static final Logger log = LoggerFactory.getLogger(VetService.class);

    RestTemplate restTemplate;



    public VetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public Vet saveVet(Vet vet) {
        URI uri = URI.create("http://localhost:9094/help/vet/addVet/");
        Vet response = restTemplate.postForObject(uri,vet, Vet.class);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Vet getVetById(Long id) {
        Vet response = restTemplate.getForObject("http://localhost:9094/help/vet/getById/{id}",Vet.class, id);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Vet getVetByName(String name){
        HttpEntity<Vet> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Vet>> responseType = new ParameterizedTypeReference<List<Vet>>() {};
        ResponseEntity<List<Vet>> resp = restTemplate.exchange("http://localhost:9094/help/vet/getVetByName/"+name,
                HttpMethod.GET, entity,  responseType);
        List<Vet> list = resp.getBody();
        Vet newVet =list.get(0);
        String message = newVet.toString();
        log.info(message);
        return newVet;
    }
    public List<Vet> getAllVets(){
        HttpEntity<Vet> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Vet>> responseType = new ParameterizedTypeReference<List<Vet>>(){};
        ResponseEntity<List<Vet>> resp = restTemplate.exchange("http://localhost:9094/help/vet/getAllVets",
                HttpMethod.GET, entity,  responseType);
        List<Vet> list = resp.getBody();
        String message = String.valueOf(list);
        log.info(message);
        return list;
        /*TODO This is an alternative method for doing this
        URI uri = URI.create("http://localhost:9094/help/vet/getAllVets");
        List<Vet> response = restTemplate.getForObject(uri,List.class);
        String message = response.toString();
        log.info(message);
        return response;*/
    }
    public void deleteVet(Vet vet) {
        URI uri = URI.create("http://localhost:9094/help/vet/deleteVet/");
        HttpEntity<Vet> entity = new HttpEntity<>(vet);
        restTemplate.exchange(uri,
                HttpMethod.DELETE,entity,String.class);

    }
    //Delete the record ten call this to populate the null space
    public Vet updateVet(Vet vet, String name){
        HttpEntity<Vet> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Vet>> responseType = new ParameterizedTypeReference<List<Vet>>(){};
        ResponseEntity<List<Vet>> resp = restTemplate.exchange("http://localhost:9094/help/vet/getVetByName/"+ name,
                HttpMethod.GET, entity,  responseType);
        List<Vet> list = resp.getBody();
        Vet responseVet = list.get(0);
        responseVet.setName(vet.getName());
        HttpEntity<Vet> entity2= new HttpEntity<>(responseVet);
        restTemplate.exchange("http://localhost:9094/help/vet/updateVet",HttpMethod.PUT,entity2,Vet.class);
        return responseVet;

    }





}

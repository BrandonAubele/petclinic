package com.example.petclinic.services;

import com.example.petclinic.models.Owner;

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
public class OwnerService {

    private static final Logger log = LoggerFactory.getLogger(OwnerService.class);

    RestTemplate restTemplate;



    public OwnerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public Owner saveOwner(Owner owner) {
        URI uri = URI.create("http://localhost:9091/help/owner/addOwner/");
        Owner response = restTemplate.postForObject(uri,owner, Owner.class);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Owner getOwnerById(Long id) {
        Owner response = restTemplate.getForObject("http://localhost:9091/help/owner/getById/{id}",Owner.class, id);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Owner getOwnerByName(String name){
        HttpEntity<Owner> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Owner>> responseType = new ParameterizedTypeReference<List<Owner>>() {};
        ResponseEntity<List<Owner>> resp = restTemplate.exchange("http://localhost:9091/help/owner/getOwnerByName/"+name,
                HttpMethod.GET, entity,  responseType);
        List<Owner> list = resp.getBody();
        Owner newOwner =list.get(0);
        String message = newOwner.toString();
        log.info(message);
        return newOwner;
    }
    public List<Owner> getAllOwners(){
        HttpEntity<Owner> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Owner>> responseType = new ParameterizedTypeReference<List<Owner>>(){};
        ResponseEntity<List<Owner>> resp = restTemplate.exchange("http://localhost:9091/help/owner/getAllOwners",
                HttpMethod.GET, entity,  responseType);
        List<Owner> list = resp.getBody();
        String message = String.valueOf(list);
        log.info(message);
        return list;
        /*TODO This is an alternative method for doing this
        URI uri = URI.create("http://localhost:9091/help/owner/getAllOwners");
        List<Owner> response = restTemplate.getForObject(uri,List.class);
        String message = response.toString();
        log.info(message);
        return response;*/
    }
    public void deleteOwner(Owner owner) {
        URI uri = URI.create("http://localhost:9091/help/owner/deleteOwner/");
        HttpEntity<Owner> entity = new HttpEntity<>(owner);
        restTemplate.exchange(uri,
                HttpMethod.DELETE,entity,String.class);

    }
    //Delete the record ten call this to populate the null space
    public Owner updateOwner(Owner owner, String name){
        HttpEntity<Owner> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Owner>> responseType = new ParameterizedTypeReference<List<Owner>>(){};
        ResponseEntity<List<Owner>> resp = restTemplate.exchange("http://localhost:9091/help/owner/getOwnerByName/"+ name,
                HttpMethod.GET, entity,  responseType);
        List<Owner> list = resp.getBody();
        Owner responseOwner = list.get(0);
        responseOwner.setName(owner.getName());
        responseOwner.setPhoneNumber(owner.getPhoneNumber());
        responseOwner.setAddress(owner.getAddress());
        responseOwner.setCity(owner.getCity());
        HttpEntity<Owner> entity2= new HttpEntity<>(responseOwner);
        restTemplate.exchange("http://localhost:9091/help/owner/updateOwner",HttpMethod.PUT,entity2,Owner.class);
        return responseOwner;

    }





}

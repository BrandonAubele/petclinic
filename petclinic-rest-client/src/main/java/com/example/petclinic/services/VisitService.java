package com.example.petclinic.services;

import com.example.petclinic.models.Visit;

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
public class VisitService {

    private static final Logger log = LoggerFactory.getLogger(VisitService.class);

    RestTemplate restTemplate;



    public VisitService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public Visit saveVisit(Visit visit) {
        URI uri = URI.create("http://localhost:9093/help/visit/addVisit/");
        Visit response = restTemplate.postForObject(uri,visit, Visit.class);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Visit getVisitById(Long id) {
        Visit response = restTemplate.getForObject("http://localhost:9093/help/visit/getById/{id}",Visit.class, id);
        String message = response.toString();
        log.info(message);
        return response;
    }
    public Visit getVisitByName(String name){
        HttpEntity<Visit> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Visit>> responseType = new ParameterizedTypeReference<List<Visit>>() {};
        ResponseEntity<List<Visit>> resp = restTemplate.exchange("http://localhost:9093/help/visit/getVisitByName/"+name,
                HttpMethod.GET, entity,  responseType);
        List<Visit> list = resp.getBody();
        Visit newVisit =list.get(0);
        String message = newVisit.toString();
        log.info(message);
        return newVisit;
    }
    public List<Visit> getAllVisits(){
        HttpEntity<Visit> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Visit>> responseType = new ParameterizedTypeReference<List<Visit>>(){};
        ResponseEntity<List<Visit>> resp = restTemplate.exchange("http://localhost:9093/help/visit/getAllVisits",
                HttpMethod.GET, entity,  responseType);
        List<Visit> list = resp.getBody();
        String message = String.valueOf(list);
        log.info(message);
        return list;
        /*TODO This is an alternative method for doing this
        URI uri = URI.create("http://localhost:9093/help/visit/getAllVisits");
        List<Visit> response = restTemplate.getForObject(uri,List.class);
        String message = response.toString();
        log.info(message);
        return response;*/
    }
    public void deleteVisit(Visit visit) {
        URI uri = URI.create("http://localhost:9093/help/visit/deleteVisit/");
        HttpEntity<Visit> entity = new HttpEntity<>(visit);
        restTemplate.exchange(uri,
                HttpMethod.DELETE,entity,String.class);

    }
    //Delete the record ten call this to populate the null space
    public Visit updateVisit(Visit visit, String name){
        HttpEntity<Visit> entity = new HttpEntity<>(null);
        ParameterizedTypeReference<List<Visit>> responseType = new ParameterizedTypeReference<List<Visit>>(){};
        ResponseEntity<List<Visit>> resp = restTemplate.exchange("http://localhost:9093/help/visit/getVisitByName/"+ name,
                HttpMethod.GET, entity,  responseType);
        List<Visit> list = resp.getBody();
        Visit responseVisit = list.get(0);
        responseVisit.setDateOfVisit(visit.getDateOfVisit());
        responseVisit.setPet(visit.getPet());
        responseVisit.setDescription(visit.getDescription());
        HttpEntity<Visit> entity2= new HttpEntity<>(responseVisit);
        restTemplate.exchange("http://localhost:9093/help/visit/updateVisit",HttpMethod.PUT,entity2,Visit.class);
        return responseVisit;

    }





}

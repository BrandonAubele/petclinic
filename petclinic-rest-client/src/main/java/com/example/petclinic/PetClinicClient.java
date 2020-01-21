package com.example.petclinic;

import com.example.petclinic.buisness.PetClinicBuisnessWorkflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PetClinicClient {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(PetClinicClient.class, args);

        PetClinicBuisnessWorkflow business = (PetClinicBuisnessWorkflow) context.getBean("petClinicBuisnessWorkflow");
        business.runBuisness();
    }
}

package com.reznikov.smartenergycustomer.services.client;

import com.reznikov.smartenergycustomer.domains.Customer;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRestTemplateClient {
    
    @Autowired
    private KeycloakRestTemplate restTemplate;

    public Customer getCustomer(String organizationId){
        ResponseEntity<Customer> restExchange =
                restTemplate.exchange(
                   "http://gateway:8072/smartenergycustomer/v1/customer/{supplierId}",
                   HttpMethod.GET,
                   null, Customer.class, organizationId);

        return restExchange.getBody();
    }

    
}

package com.reznikov.smartenergy.services.client;


import com.reznikov.smartenergy.dto.CustomerFullDto;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierRestTemplateClient {

    @Autowired
    private KeycloakRestTemplate restTemplate;

    public List<CustomerFullDto> getCustomers(Long organizationId) {
        ResponseEntity<List<CustomerFullDto>> restExchange =
                restTemplate.exchange(
                        "http://gateway:8072/smartenergycustomer/api/customer/supplier/{id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<CustomerFullDto>>() {},
                        organizationId);

        return restExchange.getBody();
    }

}
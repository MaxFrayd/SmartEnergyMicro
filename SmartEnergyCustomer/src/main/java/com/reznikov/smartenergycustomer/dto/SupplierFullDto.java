package com.reznikov.smartenergycustomer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierFullDto {

    @Id
    private Long id;

    private String name;

    private String email;

    private AddressDto address;

    private double energyAmount;

    private String status;

    private LocalDateTime created;

    private  LocalDateTime updated;

}


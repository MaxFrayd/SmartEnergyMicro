package com.reznikov.smartenergycustomer.utils;

import com.github.javafaker.Faker;
import com.reznikov.smartenergycustomer.domains.Address;
import com.reznikov.smartenergycustomer.domains.Supplier;
import com.reznikov.smartenergycustomer.enums.SupplierStatus;
import com.reznikov.smartenergycustomer.repositories.AddressRepository;
import com.reznikov.smartenergycustomer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SupplierDataInitializer implements CommandLineRunner {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final Faker faker = new Faker();

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 20; i++) {
            Address address = new Address();
            address.setLatitude(Double.parseDouble(faker.address()
                    .latitude()
                    .replace("-", "")
                    .replace(",", ".")
            ));
            address.setLongitude(Double.parseDouble(faker.address()
                    .longitude()
                    .replace("-","")
                    .replace(",", ".")
            ));
            address.setCity(faker.address().city());
            addressRepository.save(address);

            Supplier supplier = new Supplier();
            supplier.setName(faker.company().name());
            supplier.setEmail(faker.internet().emailAddress());
            supplier.setAddress(address);
            supplier.setEnergyAmount(faker.number().randomDouble(2, 1000, 5000));
            supplier.setStatus(SupplierStatus.ACTIVE);
            supplier.setPricePrKwt(faker.number().randomDouble(2, 1000, 5000));
            supplier.setCurrentEnergyAmount(supplier.getEnergyAmount());
            supplierRepository.save(supplier);
        }
    }
}
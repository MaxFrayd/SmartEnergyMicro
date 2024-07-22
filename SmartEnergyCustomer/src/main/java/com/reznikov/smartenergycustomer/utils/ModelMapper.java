package com.reznikov.smartenergycustomer.utils;

import com.reznikov.smartenergycustomer.domains.Address;
import com.reznikov.smartenergycustomer.domains.Supplier;
import com.reznikov.smartenergycustomer.dto.AddressDto;
import com.reznikov.smartenergycustomer.dto.SupplierRegDto;
import com.reznikov.smartenergycustomer.enums.SupplierStatus;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public Supplier fromFormDto(SupplierRegDto dto) {
//        return new Supplier(dto.getName(),
//                dto.getEmail(),
//                fromAddressDto(dto.getAddress()),
//                dto.getEnergyAmount(),
//                SupplierStatus.NEW);
//                dto.getEnergyAmount(),
//                dto.getPricePrKwt()

        return Supplier.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .pricePrKwt(dto.getPricePrKwt())
                .energyAmount(dto.getEnergyAmount())
                .address(fromAddressDto(dto.getAddress()))
                .status(SupplierStatus.NEW)
                .currentEnergyAmount(dto.getEnergyAmount())



                .build();
    }
    public SupplierRegDto toFormDto(Supplier entity) {
        return SupplierRegDto.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .address(toAddressDto(entity.getAddress()))
                .energyAmount(entity.getEnergyAmount())
//                entity.getName(),
//                entity.getEmail(),
//                toAddressDto(entity.getAddress()),
//                entity.getEnergyAmount()
//                );
                .build();
    }

    public Address fromAddressDto(AddressDto dto) {
        return new Address(dto.getLatitude(), dto.getLongitude(), dto.getCity());
    }
    public AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getLatitude(), address.getLongitude(), address.getCity());
    }
}

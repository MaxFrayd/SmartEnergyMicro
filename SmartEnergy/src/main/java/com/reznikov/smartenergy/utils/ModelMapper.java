package com.reznikov.smartenergy.utils;

import com.reznikov.smartenergy.domains.Address;
import com.reznikov.smartenergy.domains.Supplier;
import com.reznikov.smartenergy.dto.AddressDto;
import com.reznikov.smartenergy.dto.SupplierRegDto;
import com.reznikov.smartenergy.enums.SupplierStatus;
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
        return new Address(dto.getId(), dto.getLatitude(), dto.getLongitude(), dto.getCity());
    }
    public AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getId(), address.getLatitude(), address.getLongitude(), address.getCity());
    }
}

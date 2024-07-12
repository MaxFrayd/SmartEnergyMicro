package com.reznikov.smartenergy.utils;

import com.reznikov.smartenergy.domains.Address;
import com.reznikov.smartenergy.domains.Supplier;
import com.reznikov.smartenergy.dto.AddressDto;
import com.reznikov.smartenergy.dto.SupplierFormDto;
import com.reznikov.smartenergy.enums.SupplierStatus;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public Supplier fromFormDto(SupplierFormDto dto) {
        return new Supplier(dto.getName(),
                dto.getEmail(),
                fromAddressDto(dto.getAddress()),
                dto.getEnergyAmount(),
                SupplierStatus.NEW);
    }
    public SupplierFormDto toFormDto(Supplier entity) {
        return new SupplierFormDto(
                entity.getName(),
                entity.getEmail(),
                toAddressDto(entity.getAddress()),
                entity.getEnergyAmount()
                );
    }

    public Address fromAddressDto(AddressDto dto) {
        return new Address(dto.getLatitude(), dto.getLongitude(), dto.getCity());
    }
    public AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getLatitude(), address.getLongitude(), address.getCity());
    }
}

package com.reznikov.smartenergycustomer.services;

import com.reznikov.smartenergycustomer.domains.Address;
import com.reznikov.smartenergycustomer.domains.Supplier;
import com.reznikov.smartenergycustomer.dto.SupplierRegDto;
import com.reznikov.smartenergycustomer.enums.SupplierStatus;
import com.reznikov.smartenergycustomer.repositories.AddressRepository;
import com.reznikov.smartenergycustomer.repositories.SupplierRepository;
import com.reznikov.smartenergycustomer.utils.DuplicateEntityException;
import com.reznikov.smartenergycustomer.utils.InvalidFormatException;
import com.reznikov.smartenergycustomer.utils.ModelMapper;
import com.reznikov.smartenergycustomer.utils.SupplierNotFoundException;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierRepository supplierRepository;
    @Resource
    private AddressRepository addressRepository;

    @Resource
    private ModelMapper modelMapper;

    @Override
    public List<SupplierRegDto> findSuppliersByCriteria(String name, String email) {
        List<Supplier> suppliers = supplierRepository.findAllByNameAndEmail(name, email);
        return suppliers.stream()
                .map(modelMapper::toFormDto)
                .collect(Collectors.toList());
    }

    public String addSupplier(@Valid SupplierRegDto supplierDto) {

        if (supplierRepository.findByEmail(supplierDto.getEmail()) != null){
            throw new DuplicateEntityException("Supplier with such email is already exists");
        }

        // Check if longitude and latitude strings are valid numbers
        if (!isValidLatitude(supplierDto.getAddress().getLatitude()) || !isValidLongitude(supplierDto.getAddress().getLongitude())) {
            throw new InvalidFormatException("Longitude and Latitude must be valid numbers");
        }
        Supplier supplier =  modelMapper.fromFormDto(supplierDto);
        Address address =  addressRepository.save(supplier.getAddress());
        supplier.setAddress(address);
        supplierRepository.save(supplier);
        return "Supplier added";
    }
    private boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }

    private boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= -180 && longitude <= 180;
    }

    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }


    @Override
    public String activateSupplier(Long id) {
        Supplier supplier =  this.getSupplierById(id).orElseThrow(()->new SupplierNotFoundException("Supplier is not found "));
        supplier.setStatus(SupplierStatus.ACTIVE);
        this.updateSupplier(supplier);
        return "Activated";
    }

    @Override
    public Page<Supplier> findBySearchCriteria(Specification<Supplier> spec, Pageable page) {

        return supplierRepository.findAll(spec, page);


    }
}
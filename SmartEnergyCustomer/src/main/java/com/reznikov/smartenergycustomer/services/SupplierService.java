package com.reznikov.smartenergycustomer.services;

import com.reznikov.smartenergycustomer.domains.Supplier;
import com.reznikov.smartenergycustomer.dto.SupplierRegDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;
import java.util.Optional;

public interface SupplierService {
     List<SupplierRegDto> findSuppliersByCriteria(String name, String email);
     String activateSupplier(Long id);
     String addSupplier(SupplierRegDto supplier);

     Supplier updateSupplier(Supplier supplier);

     List<Supplier> getAllSuppliers();

     Optional<Supplier> getSupplierById(Long id);

     void deleteSupplier(Long id);

     Page<Supplier> findBySearchCriteria(Specification<Supplier> spec, Pageable page);
}

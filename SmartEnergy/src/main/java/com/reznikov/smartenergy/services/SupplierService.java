package com.reznikov.smartenergy.services;

import com.reznikov.smartenergy.domains.Supplier;
import com.reznikov.smartenergy.dto.SupplierFormDto;
import com.reznikov.smartenergy.dto.SupplierFullDto;
import com.reznikov.smartenergy.specifications.SupplierSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;
import java.util.Optional;

public interface SupplierService {
     List<SupplierFormDto> findSuppliersByCriteria(String name, String email);
     String activateSupplier(Long id);
     String addSupplier(SupplierFormDto supplier);

     Supplier updateSupplier(Supplier supplier);

     List<Supplier> getAllSuppliers();

     Optional<Supplier> getSupplierById(Long id);

     void deleteSupplier(Long id);

     Page<Supplier> findBySearchCriteria(Specification<Supplier> spec, Pageable page);
}

package com.reznikov.smartenergycustomer.repositories;

import com.reznikov.smartenergycustomer.domains.Supplier;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
     Supplier findByEmail(String email);
     List<Supplier> findAllByNameAndEmail(String name, String email);
}
package com.reznikov.smartenergycustomer.api;

import com.reznikov.smartenergycustomer.domains.Supplier;
import com.reznikov.smartenergycustomer.dto.SupplierRegDto;
import com.reznikov.smartenergycustomer.dto.SupplierSearchDto;
import com.reznikov.smartenergycustomer.services.SupplierService;
import com.reznikov.smartenergycustomer.specifications.SupplierSpecificationBuilder;
import com.reznikov.smartenergycustomer.specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
@Validated
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/search")
    public ResponseEntity<List<SupplierRegDto>> findSupplierByComplexCriterias(
            @RequestParam String name,
            @RequestParam String email) {
        List<SupplierRegDto> supplierDtos = supplierService.findSuppliersByCriteria(name, email);
        return ResponseEntity.ok(supplierDtos);
    }


    @PostMapping
    public ResponseEntity<String> addSupplier(@RequestBody @Validated SupplierRegDto supplier) {
        return ResponseEntity.ok(supplierService.addSupplier(supplier));
    }

    @GetMapping("{id}/activate")
    public ResponseEntity<String> activateSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.activateSupplier(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        return ResponseEntity.ok(supplierService.updateSupplier(supplier));
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search/byCriteria")
    ResponseEntity<Page<Supplier>> searchByCriteria(
            @RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize",
                    defaultValue = "10") int pageSize,
            @RequestBody SupplierSearchDto
                    supplierSearchDto) {

        SupplierSpecificationBuilder builder = new SupplierSpecificationBuilder();

        List<SearchCriteria<?>> criteriaList = supplierSearchDto.getSearchCriteriaList();

        if(criteriaList != null){
            for (SearchCriteria<?> searchCriteria : criteriaList) {
                searchCriteria.setDataOption(supplierSearchDto.getDataOption());
                builder.with(searchCriteria);
            }
        }
        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("energyAmount")
                        .ascending());


        Page<Supplier> suppliers = supplierService.findBySearchCriteria(builder.build(), page);

        return ResponseEntity.ok(suppliers);
    }
}
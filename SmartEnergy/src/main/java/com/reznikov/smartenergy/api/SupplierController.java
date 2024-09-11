package com.reznikov.smartenergy.api;

import com.reznikov.smartenergy.domains.Supplier;
import com.reznikov.smartenergy.dto.CustomerFullDto;
import com.reznikov.smartenergy.dto.SupplierRegDto;
import com.reznikov.smartenergy.dto.SupplierSearchDto;
import com.reznikov.smartenergy.services.SupplierService;
import com.reznikov.smartenergy.services.client.SupplierRestTemplateClient;
import com.reznikov.smartenergy.specifications.SupplierSpecificationBuilder;
import com.reznikov.smartenergy.specifications.SearchCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
@Validated
@Api(value = "smartenergy-application")

public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierRestTemplateClient supplierRestTemplateClient;

    @RolesAllowed({"ADMIN"})
    @GetMapping("/{id}/customers")
    public ResponseEntity<List<CustomerFullDto>> getCustomersBySupplierId(@PathVariable("id") Long supplierId) {
        List<CustomerFullDto> customers = supplierRestTemplateClient.getCustomers(supplierId);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SupplierRegDto>> findSupplierByComplexCriterias(
            @RequestParam String name,
            @RequestParam String email) {
        List<SupplierRegDto> supplierDtos = supplierService.findSuppliersByCriteria(name, email);
        return ResponseEntity.ok(supplierDtos);
    }

    @RolesAllowed({"ADMIN"})
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
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @RolesAllowed({"ADMIN"})
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
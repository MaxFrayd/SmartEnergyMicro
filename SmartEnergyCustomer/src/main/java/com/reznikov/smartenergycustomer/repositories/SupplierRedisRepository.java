package com.reznikov.smartenergycustomer.repositories;

import com.reznikov.smartenergycustomer.dto.SupplierFullDto;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRedisRepository extends CrudRepository<SupplierFullDto, Long> {
}

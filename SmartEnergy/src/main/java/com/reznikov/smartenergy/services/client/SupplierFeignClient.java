//package com.reznikov.smartenergy.services.client;
//
//import com.reznikov.smartenergy.dto.CustomerFullDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//@FeignClient("smartenergycustomer")
//public interface SupplierFeignClient {
//    @RequestMapping(
//            method= RequestMethod.GET,
//            value="smartenergycustomer/api/customer/supplier/{id}",
//            consumes="application/json")
//    List<CustomerFullDto> getCustomers(@PathVariable("id") Long id);
//
//}
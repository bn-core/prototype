package com.bn.controller;

import com.bn.controller.request.CreateCustomerRequest;
import com.bn.domain.Customer;
import com.bn.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("v1/customers")
@RestController
@Slf4j
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("{id}")
    public Customer getCustomer(@NotNull @PathVariable Long id) {
        log.info("Get customer - {}", id);
        return customerService.get(id);
    }

    @PostMapping
    public Long createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        log.info("Create customer - {}", request.getMobilePhone());
        Customer customer = Customer.builder()
            .mobilePhone(request.getMobilePhone())
            .password(request.getPassword())
            .build();
        return customerService.create(customer);
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}

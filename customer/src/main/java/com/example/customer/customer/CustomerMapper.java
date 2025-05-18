package com.example.customer.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest request) {
    if (request == null) {
      return null;
    }
    return Customer.builder()
        .id(request.id() != null ? Integer.parseInt(request.id()) : null)
        .firstname(request.firstname())
        .lastname(request.lastname())
        .email(request.email())
        .password(request.password())
        .address(request.address())
        .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    if (customer == null) {
      return null;
    }
    return new CustomerResponse(
        customer.getId().toString(),
        customer.getFirstname(),
        customer.getLastname(),
        customer.getEmail(),
        null,
        customer.getAddress()
    );
  }
}

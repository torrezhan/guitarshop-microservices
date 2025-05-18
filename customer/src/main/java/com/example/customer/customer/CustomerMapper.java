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
        .firstName(request.firstname())
        .lastName(request.lastname())
        .email(request.email())
        .password(request.password())
        .address(request.address())
        .admin(request.admin() != null ? request.admin() : false)
        .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    if (customer == null) {
      return null;
    }
    return new CustomerResponse(
        customer.getId().toString(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmail(),
        null,
        customer.getAddress(),
        customer.isAdmin()
    );
  }
}

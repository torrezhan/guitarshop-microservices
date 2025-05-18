package com.example.customer.customer;

import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.exception.EmailAlreadyExistsException;
import com.example.customer.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;
  private final CustomerMapper mapper;
  private final PasswordEncoder passwordEncoder;

  public String createCustomer(CustomerRequest request) {
    // Check if email already exists
    if (repository.findByEmail(request.email()).isPresent()) {
      throw new EmailAlreadyExistsException("Email " + request.email() + " is already registered");
    }
    
    var customer = mapper.toCustomer(request);
    customer.setPassword(passwordEncoder.encode(request.password()));
    customer = this.repository.save(customer);
    return customer.getId().toString();
  }

  public void updateCustomer(CustomerRequest request) {
    var customer = this.repository.findById(Integer.parseInt(request.id()))
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())
        ));
    
    // Verify ownership
    String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    if (!customer.getEmail().equals(authenticatedEmail)) {
      throw new UnauthorizedAccessException("You are not authorized to update this customer's data");
    }
    
    mergeCustomer(customer, request);
    this.repository.save(customer);
  }

  private void mergeCustomer(Customer customer, CustomerRequest request) {
    if (StringUtils.isNotBlank(request.firstname())) {
      customer.setFirstName(request.firstname());
    }
    if (StringUtils.isNotBlank(request.email())) {
      customer.setEmail(request.email());
    }
    if (StringUtils.isNotBlank(request.password())) {
      customer.setPassword(passwordEncoder.encode(request.password()));
    }
    if (request.address() != null) {
      customer.setAddress(request.address());
    }
  }

  public List<CustomerResponse> findAllCustomers() {
    return this.repository.findAll()
        .stream()
        .map(this.mapper::fromCustomer)
        .collect(Collectors.toList());
  }

  public CustomerResponse findById(String id) {
    return this.repository.findById(Integer.parseInt(id))
        .map(mapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
  }

  public CustomerResponse findByEmail(String email) {
    return this.repository.findByEmail(email)
        .map(mapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided email: %s", email)));
  }

  public boolean existsById(String id) {
    return this.repository.findById(Integer.parseInt(id))
        .isPresent();
  }

  public void deleteCustomer(String id) {
    var customer = this.repository.findById(Integer.parseInt(id))
        .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    
    // Get authenticated email from security context
    String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    
    // Check if authenticated user exists in our database
    var authenticatedCustomer = repository.findByEmail(authenticatedEmail)
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("Authenticated user with email %s not found in the database", authenticatedEmail)
        ));
    
    // Verify ownership
    if (!customer.getEmail().equals(authenticatedEmail)) {
      throw new UnauthorizedAccessException("You are not authorized to delete this customer's data");
    }
    
    this.repository.deleteById(Integer.parseInt(id));
  }
}

package com.example.customer.customer;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("/register")
  public ResponseEntity<String> createCustomer(
      @RequestBody @Valid CustomerRequest request
  ) {
    return ResponseEntity.ok(this.customerService.createCustomer(request));
  }

  @PutMapping("/{customer-id}")
  public ResponseEntity<Void> updateCustomer(
      @PathVariable("customer-id") String customerId,
      @RequestBody @Valid CustomerRequest request
  ) {
    if (!customerId.equals(request.id())) {
      throw new IllegalArgumentException("Customer ID in path does not match ID in request body");
    }
    this.customerService.updateCustomer(request);
    return ResponseEntity.accepted().build();
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {
    return ResponseEntity.ok(this.customerService.findAllCustomers());
  }

  @GetMapping("/exists/{customer-id}")
  public ResponseEntity<Boolean> existsById(
      @PathVariable("customer-id") String customerId
  ) {
    return ResponseEntity.ok(this.customerService.existsById(customerId));
  }

  @GetMapping("/{customer-id}")
  public ResponseEntity<CustomerResponse> getCustomerById(
      @PathVariable("customer-id") String customerId
  ) {
    return ResponseEntity.ok(this.customerService.findById(customerId));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<CustomerResponse> findByEmail(
      @PathVariable("email") String email
  ) {
    return ResponseEntity.ok(this.customerService.findByEmail(email));
  }

  @DeleteMapping("/{customer-id}")
  public ResponseEntity<Void> deleteCustomer(
      @PathVariable("customer-id") String customerId
  ) {
    this.customerService.deleteCustomer(customerId);
    return ResponseEntity.accepted().build();
  }
}

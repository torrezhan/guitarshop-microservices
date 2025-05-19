package com.example.customer.customer;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
  @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
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

  @GetMapping("/exists/{customer-id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
  public ResponseEntity<Boolean> existsById(
      @PathVariable("customer-id") String customerId
  ) {
    return ResponseEntity.ok(this.customerService.existsById(customerId));
  }

  @GetMapping("/{customer-id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
  public ResponseEntity<CustomerResponse> getCustomerById(
      @PathVariable("customer-id") String customerId
  ) {
    return ResponseEntity.ok(this.customerService.findById(customerId));
  }

  @GetMapping("/email/{email}")
  @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
  public ResponseEntity<CustomerResponse> findByEmail(
      @PathVariable("email") String email
  ) {
    return ResponseEntity.ok(this.customerService.findByEmail(email));
  }

  @DeleteMapping("/{customer-id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
  public ResponseEntity<Void> deleteCustomer(
      @PathVariable("customer-id") String customerId
  ) {
    this.customerService.deleteCustomer(customerId);
    return ResponseEntity.accepted().build();
  }
}

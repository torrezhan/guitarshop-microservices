package com.example.order.customer;

import com.example.order.config.FeignConfig;
import com.example.order.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
    name = "customer-service",
    url = "${application.config.customer-url}",
    configuration = FeignConfig.class
)
public interface CustomerClient {

  @GetMapping("/{customer-id}")
  Optional<CustomerDTO> findCustomerById(@PathVariable("customer-id") String customerId);

  @GetMapping("/email/{email}")
  Optional<CustomerDTO> findCustomerByEmail(@PathVariable("email") String email);
}

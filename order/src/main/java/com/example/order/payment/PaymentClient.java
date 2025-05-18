package com.example.order.payment;

import com.example.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "payment-service",
    url = "${application.config.payment-url}",
    configuration = FeignConfig.class
)
public interface PaymentClient {

  @PostMapping
  Integer requestOrderPayment(@RequestBody PaymentRequest request);
}

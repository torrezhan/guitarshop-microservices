package com.example.order.payment;

import com.example.order.dto.CustomerDTO;
import com.example.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerDTO customer
) {
}

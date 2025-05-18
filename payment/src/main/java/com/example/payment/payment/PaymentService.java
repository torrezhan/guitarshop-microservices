package com.example.payment.payment;

import com.example.payment.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository repository;
  private final PaymentMapper mapper;

  public Integer createPayment(PaymentRequest request) {
    // Verify ownership
    String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    if (!request.customer().email().equals(authenticatedEmail)) {
      throw new UnauthorizedAccessException("You are not authorized to create payments for this customer");
    }
    
    var payment = this.repository.save(this.mapper.toPayment(request));
    return payment.getId();
  }
}

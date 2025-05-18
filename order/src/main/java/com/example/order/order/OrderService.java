package com.example.order.order;

import com.example.order.customer.CustomerClient;
import com.example.order.exception.BusinessException;
import com.example.order.exception.UnauthorizedAccessException;
import com.example.order.orderline.OrderLineRequest;
import com.example.order.orderline.OrderLineService;
import com.example.order.payment.PaymentClient;
import com.example.order.payment.PaymentRequest;
import com.example.order.product.ProductClient;
import com.example.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        BigDecimal calculatedTotal = purchasedProducts.stream()
                .map(product -> product.price().multiply(BigDecimal.valueOf(product.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var order = Order.builder()
                .reference(request.reference())
                .totalAmount(calculatedTotal)
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();

        order = this.repository.save(order);

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                calculatedTotal,
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var customer = customerClient.findCustomerByEmail(authenticatedEmail)
                .orElseThrow(() -> new BusinessException("Customer not found"));
        
        return this.repository.findByCustomerId(customer.id())
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        var order = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
        
        // Verify ownership
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var customer = customerClient.findCustomerByEmail(authenticatedEmail)
                .orElseThrow(() -> new BusinessException("Customer not found"));
        
        if (!order.getCustomerId().equals(customer.id())) {
            throw new UnauthorizedAccessException("You are not authorized to access this order");
        }
        
        return this.mapper.fromOrder(order);
    }
}

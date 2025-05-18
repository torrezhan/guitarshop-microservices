package com.example.order.orderline;

import com.example.order.customer.CustomerClient;
import com.example.order.exception.BusinessException;
import com.example.order.exception.UnauthorizedAccessException;
import com.example.order.order.Order;
import com.example.order.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new BusinessException("Order not found"));
        
        // Verify ownership
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var customer = customerClient.findCustomerByEmail(authenticatedEmail)
                .orElseThrow(() -> new BusinessException("Customer not found"));
        
        if (!order.getCustomerId().equals(customer.id())) {
            throw new UnauthorizedAccessException("You are not authorized to modify this order");
        }
        
        var orderLine = mapper.toOrderLine(request);
        return repository.save(orderLine).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException("Order not found"));
        
        // Verify ownership
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var customer = customerClient.findCustomerByEmail(authenticatedEmail)
                .orElseThrow(() -> new BusinessException("Customer not found"));
        
        if (!order.getCustomerId().equals(customer.id())) {
            throw new UnauthorizedAccessException("You are not authorized to view this order's details");
        }
        
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}

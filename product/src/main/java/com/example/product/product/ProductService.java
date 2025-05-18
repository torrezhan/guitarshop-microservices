package com.example.product.product;

import com.example.product.exception.ProductPurchaseException;
import com.example.product.exception.UnauthorizedAccessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(
            ProductRequest request
    ) {
        // Verify admin role
        if (!hasAdminRole()) {
            throw new UnauthorizedAccessException("Only administrators can create products");
        }
        
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public String getProductPhotoUrl(Integer id) {
        return repository.findById(id)
                .map(Product::getPhoto)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    @Transactional
    public ProductResponse updateProduct(Integer id, ProductRequest request) {
        // Verify admin role
        if (!hasAdminRole()) {
            throw new UnauthorizedAccessException("Only administrators can update products");
        }
        
        var product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
        
        mapper.updateProductFromRequest(request, product);
        return mapper.toProductResponse(repository.save(product));
    }

    @Transactional
    public void deleteProduct(Integer id) {
        // Verify admin role
        if (!hasAdminRole()) {
            throw new UnauthorizedAccessException("Only administrators can delete products");
        }
        
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with ID:: " + id);
        }
        repository.deleteById(id);
    }

    public List<ProductResponse> findByCategory(Integer categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
    
    private boolean hasAdminRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

}

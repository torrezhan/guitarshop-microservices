package com.example.product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{product-id}/photo")
    public ResponseEntity<Void> getProductPhoto(
            @PathVariable("product-id") Integer productId
    ) {
        String photoUrl = service.getProductPhotoUrl(productId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(photoUrl))
                .build();
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("product-id") Integer productId,
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(service.updateProduct(productId, request));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("product-id") Integer productId
    ) {
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category-id}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(
            @PathVariable("category-id") Integer categoryId
    ) {
        return ResponseEntity.ok(service.findByCategory(categoryId));
    }
}

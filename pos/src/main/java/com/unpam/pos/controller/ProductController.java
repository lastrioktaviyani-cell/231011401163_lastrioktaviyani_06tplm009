package com.unpam.pos.controller;

import com.unpam.pos.model.Product;
import com.unpam.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return productService.search(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        return productService.getByCategory(categoryId);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product details) {
        return productService.getById(id)
                .map(p -> {
                    p.setName(details.getName());
                    p.setDescription(details.getDescription());
                    p.setPrice(details.getPrice());
                    p.setStock(details.getStock());
                    p.setCategory(details.getCategory());
                    return ResponseEntity.ok(productService.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return productService.getById(id)
                .map(p -> {
                    productService.delete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
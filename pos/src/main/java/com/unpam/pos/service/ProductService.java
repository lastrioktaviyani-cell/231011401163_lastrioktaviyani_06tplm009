package com.unpam.pos.service;

import com.unpam.pos.model.Product;
import com.unpam.pos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> search(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
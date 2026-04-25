package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productMapper.findById(id);
    }

    @PostMapping
    public int createProduct(@RequestBody Product product) {
        return productMapper.insert(product);
    }

    @PutMapping("/{id}")
    public int updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        return productMapper.update(product);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Integer id) {
        return productMapper.delete(id);
    }
}

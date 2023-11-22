package com.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.Product;
import com.abm.exception.ProductException;
import com.abm.request.CreateProductRequest;
import com.abm.service.ProductService;

@RestController
@RequestMapping("/api/retailer")
@CrossOrigin
public class RetailerProductController {
	
	@Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Object createProduct(@RequestBody CreateProductRequest req) {
        try {
            Product createdProduct = productService.createProduct(req);
            return "Products details added successfully!";
        } catch (ProductException e) {
            return "Bad Request: " + e.getMessage();
        }
    }
    
    @PutMapping("/update")
    public Object updateProduct( @RequestBody Product req) {
        try {
            Product updatedProduct = productService.updateProduct( req);
            return updatedProduct;
        } catch (ProductException e) {
            return "Not Found: " + e.getMessage();
        }
    }
    
    @GetMapping("product/{productId}")
    public Object getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.findProductById(productId);
            return product;
        } catch (ProductException e) {
            return "Not Found: " + e.getMessage();
        }
    }

}

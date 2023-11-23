package com.abm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.abm.response.ApiResponse;
import com.abm.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        try {
            String message = productService.deleteProduct(productId);
            return message;
        } catch (ProductException e) {
            return "Not Found: " + e.getMessage();
        }
    }
	
	
	@GetMapping("/all")
	public List<Product> findAllProduct(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/recent")
	public List<Product> recentlyAddedProduct(){
		return productService.recentlyAddedProduct();
	}
	
//	@PutMapping("/{productId}/update")
//	public Product updateProductHandler(@RequestBody Product req, @PathVariable Long productId) throws ProductException{
//		return productService.updateProduct(productId, req);
//	}
	
	@PostMapping("/creates")
	public ApiResponse createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException {
		for (CreateProductRequest product : reqs) {
			productService.createProduct(product);
		}
		return new ApiResponse("Products created successfully", true);
	}
}

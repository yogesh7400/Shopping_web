package com.abm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.Product;
import com.abm.exception.ProductException;
import com.abm.request.CreateProductRequest;
import com.abm.response.ApiResponse;
import com.abm.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Object createProduct(@RequestBody CreateProductRequest req) {
        try {
            Product createdProduct = productService.createProduct(req);
            return createdProduct;
        } catch (ProductException e) {
            return "Bad Request: " + e.getMessage();
        }
    }


    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        try {
            String message = productService.deleteProduct(productId);
            return message;
        } catch (ProductException e) {
            return "Not Found: " + e.getMessage();
        }
    }

    @PutMapping("/update/{productId}")
    public Object updateProduct(@PathVariable Long productId, @RequestBody Product req) {
        try {
            Product updatedProduct = productService.updateProduct(productId, req);
            return updatedProduct;
        } catch (ProductException e) {
            return "Not Found: " + e.getMessage();
        }
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Object getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.findProductById(productId);
            return product;
        } catch (ProductException e) {
            return "Not Found: " + e.getMessage();
        }
    }

    @GetMapping("/category/{category}")
    public List<Product> findProductsByCategory(@PathVariable String category) {
        return productService.findProductByCategory(category);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String query) {
        return productService.searchProduct(query);
    }

    @GetMapping("/recently-added")
    public List<Product> getRecentlyAddedProducts() {
        return productService.recentlyAddedProduct();
    }
    
    @PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException{
		
		for(CreateProductRequest product:reqs) {
			productService.createProduct(product);
		}
		
		ApiResponse res=new ApiResponse("products created successfully",true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}

    @GetMapping("/filtered")
    public Page<Product> filterProducts(@RequestParam(required = false) String category,
                                        @RequestParam(required = false) List<String> colors,
                                        @RequestParam(required = false) List<String> sizes,
                                        @RequestParam(required = false) Integer minPrice,
                                        @RequestParam(required = false) Integer maxPrice,
                                        @RequestParam(required = false) Integer minDiscount,
                                        @RequestParam(required = false) String sort,
                                        @RequestParam(required = false) String stock,
                                        @RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        return productService.getAllProduct(category, colors, sizes, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
    }
}

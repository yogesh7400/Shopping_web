package com.abm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.abm.entity.Product;
import com.abm.exception.ProductException;
import com.abm.request.CreateProductRequest;

public interface ProductService {
//retailer
	public Product createProduct(CreateProductRequest req) throws ProductException;

	public String deleteProduct(Long productId) throws ProductException;

	public Product updateProduct(Product product) throws ProductException;

	public List<Product> getAllProducts();
	
	// for user

	public Product findProductById(Long id) throws ProductException;

	public List<Product> findProductByCategory(String category);

	public List<Product> searchProduct(String query);

//	public List<Product> getAllProduct(List<String>colors,List<String>sizes,int minPrice, int maxPrice,int minDiscount, String category, String sort,int pageNumber, int pageSize);
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

	public List<Product> recentlyAddedProduct();

}

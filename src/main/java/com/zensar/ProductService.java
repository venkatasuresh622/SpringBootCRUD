package com.zensar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	

	    public List<Product> getAllProducts(){
	        List<Product> allProductsList = productRepository.findByOrderByProductIdAsc();
	        return allProductsList;
	    }
	    
	    public void deleteProduct(int productId) {
	    	productRepository.deleteById(productId);
	    }


		public Product getProduct(int productId) {
			// TODO Auto-generated method stub
			Product productsList = productRepository.getById(productId);
	        return productsList;
		}


		public void updateProduct(int productId, Product product) {
			productRepository.save(product);
		}
}

package com.zensar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/addProductForm")
	public ModelAndView openProductForm() {
		ModelAndView modelAndView= new ModelAndView("addProductForm.jsp");
		return modelAndView;
	}
	
  
    
    @RequestMapping("/addProduct")
    public ModelAndView addProduct(Product product) {
        productService.addProduct(product);
        ModelAndView modelAndView = new ModelAndView("success.jsp");
        return modelAndView;
    }

    
    

    
    @RequestMapping("/getAllProducts")
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView("viewAllProducts.jsp");
        //connect to service to get all products list
        List<Product> allProductsList = productService.getAllProducts();
        modelAndView.addObject("allProductsList", allProductsList);
        return modelAndView;
    }
    
    @RequestMapping("/delete")
    public ModelAndView deleteProduct(@RequestParam("productId") int productId) {
        // call service to delete product with the given productId
        productService.deleteProduct(productId);
        return getAllProducts();
    }
    
    @RequestMapping("/updateProductForm")
    public ModelAndView updateProductForm(@RequestParam("productId") int productId) {
        ModelAndView modelAndView = new ModelAndView("updateProductForm.jsp");
        //connect to service to get all products list
        Product product = productService.getProduct(productId);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    
    
    @RequestMapping("/updateProduct")
    public ModelAndView updateProduct( Product product, int productId) {
        // call service to delete product with the given productId
        productService.updateProduct(productId,product);
        return getAllProducts();
    }
    
    
}
 
 


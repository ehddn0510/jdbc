package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSelect {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        List<Product> products = null;
        try {
            products = productService.get();
            System.out.println(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

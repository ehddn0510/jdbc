package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductSelectOne {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        String id = "1";  // 조회할 제품 ID
        Product product = null;
        try {
            product = productService.get(id);
            System.out.println(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package edu.sm.product;

import edu.sm.service.ProductService;

public class ProductDelete {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        String id = "1";  // 삭제할 제품 ID

        try {
            productService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.sql.Date;

public class ProductUpdate {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .id("2")
                .name("Product3")
                .price(500)
                .regdate(Date.valueOf("2024-09-12"))  // 예시 날짜로 등록일 설정
                .build();

        try {
            productService.modify(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

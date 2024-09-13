package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.ProductService;

import java.sql.Date;

public class ProductInsert {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .id("2")
                .name("Product2")
                .price(100)  // price를 정수값으로 설정
                .regdate(Date.valueOf("2024-09-12"))  // 예시 날짜로 등록일 설정
                .build();

        try {
            productService.add(product);
        } catch (DuplicatedIdException e) {
            System.out.println("ID가 중복 되어 입력이 안됩니다.");
        } catch (Exception e) {
            System.out.println("시스템 장애");
        }
    }
}

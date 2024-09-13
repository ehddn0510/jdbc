package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.ProductService;

import java.util.Date;

public class ProductInsertTest {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();

            // 5개의 상품을 추가, id를 0으로 설정하여 자동 증가되도록 처리
            Product[] products = {
                    new Product(0, "Laptop", 1500, "15 inch", "Silver", new Date()),
                    new Product(0, "Smartphone", 800, "6 inch", "Black", new Date()),
                    new Product(0, "Tablet", 600, "10 inch", "White", new Date()),
                    new Product(0, "Monitor", 300, "24 inch", "Black", new Date()),
                    new Product(0, "Laptop", 50, "Full Size", "White", new Date())  // 중복된 이름
            };

            for (Product product : products) {
                try {
                    productService.insert(product);  // add 메서드를 insert로 변경
                    System.out.println(product.getName() + "이(가) 성공적으로 추가되었습니다.");
                } catch (DuplicatedIdException e) {
                    // 중복된 이름일 경우 예외 메시지 출력
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

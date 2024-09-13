package edu.sm.product;

import edu.sm.service.ProductService;

public class ProductDeleteTest {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();
            productService.delete(1);  // 메서드 이름을 delete로 변경
            System.out.println("상품이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

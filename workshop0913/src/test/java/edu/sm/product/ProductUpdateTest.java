package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductUpdateTest {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();
            Product product = productService.selectOne(1);  // getProductById를 selectOne으로 변경
            if (product != null) {
                product.setName("Updated Laptop");
                product.setPrice(9000);
                productService.update(product);  // updateProduct를 update로 변경
                System.out.println("상품이 성공적으로 수정되었습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

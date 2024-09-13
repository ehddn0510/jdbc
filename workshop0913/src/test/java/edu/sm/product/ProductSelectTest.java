package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSelectTest {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();
            List<Product> products = productService.select();  // getAllProducts를 select로 변경

            // name, price, size, color 모두 출력
            for (Product product : products) {
                System.out.println(product.getName() + " - " + product.getPrice() + " - " +
                        product.getSize() + " - " + product.getColor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

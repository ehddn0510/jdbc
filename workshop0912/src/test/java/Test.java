import edu.sm.dto.Product;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
        Product p = Product.builder()
                .id("id01")
                .name("Product01")
                .price(100)
                .regdate(Date.valueOf("2024-09-12"))  // 등록일 설정
                .build();

        System.out.println(p);
    }
}

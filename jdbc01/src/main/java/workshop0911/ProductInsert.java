package workshop0911;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductInsert {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // 2. Connection 설정
        String url = "jdbc:mysql://localhost:3306/smdb";  // 데이터베이스 URL
        String sqlid = "smuser";  // 사용자 ID
        String sqlpwd = "111111";  // 비밀번호

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);
            System.out.println("Connected to database");

            // **SQL for product 테이블 삽입 작업**
            String insertProductSql = "INSERT INTO product (user_id, product_name, price, photo_name, registrant) VALUES(?,?,?,?,?)";  // product 테이블에 삽입
            PreparedStatement psProduct = null;
            try {
                psProduct = conn.prepareStatement(insertProductSql);
                psProduct.setString(1, "김준");  // user_id
                psProduct.setString(2, "핸드");  // product_name
                psProduct.setFloat(3, 1300000);  // price (FLOAT 타입)
                psProduct.setString(4, "smartphone.jpg");  // photo_name
                psProduct.setString(5, "김창대");  // registrant (등록자)

                int resultProduct = psProduct.executeUpdate();
                System.out.println(resultProduct + " row(s) inserted into product.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (psProduct != null) {
                    try {
                        psProduct.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        } finally {
            // Connection 닫기
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

package workshop0911;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSelect {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
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
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }

        // 3. SQL
        String selectOneSql = "SELECT * FROM product WHERE product_id = ?";  // product_id로 조회
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(selectOneSql);
            ps.setInt(1, 2);  // 조회할 product_id를 설정

            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("product_id"));
                System.out.println("User ID: " + rs.getString("user_id"));
                System.out.println("Product Name: " + rs.getString("product_name"));
                System.out.println("Price: " + rs.getFloat("price"));
                System.out.println("Photo Name: " + rs.getString("photo_name"));
                System.out.println("Registrant: " + rs.getString("registrant"));
            } else {
                System.out.println("No product found with product_id = 1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 4. Close
    }
}

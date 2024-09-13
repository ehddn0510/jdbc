package workshop0911;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductUpdate {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        }

        // 2. Connection
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
        String updateSql = "UPDATE product SET product_name = ?, price = ?, photo_name = ?, registrant = ? WHERE product_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(updateSql);
            ps.setString(1, "에어컨");  // 수정할 상품명
            ps.setFloat(2, 1500000);  // 수정할 가격 (FLOAT 타입)
            ps.setString(3, "washing_machine.jpg");  // 수정할 사진 이름
            ps.setString(4, "김춘배");  // 수정할 등록자
            ps.setInt(5, 1);  // 수정할 상품의 product_id (INT 타입)

            int result = ps.executeUpdate();
            System.out.println(result + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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


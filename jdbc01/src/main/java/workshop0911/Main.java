package workshop0911;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
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
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);
            System.out.println("Connected to database");

            // 3. SQL
            String selectSql = "SELECT * FROM product";
            ps = conn.prepareStatement(selectSql);
            rs = ps.executeQuery();

            // 데이터 출력
            while (rs.next()) {
                System.out.println("User ID: " + rs.getString("user_id"));
                System.out.println("Product Name: " + rs.getString("product_name"));
                System.out.println("Price: " + rs.getFloat("price"));
                System.out.println("Photo Name: " + rs.getString("photo_name"));
                System.out.println("Registrant: " + rs.getString("registrant"));
                System.out.println("-------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ResultSet, PreparedStatement, Connection 닫기
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
    }
}

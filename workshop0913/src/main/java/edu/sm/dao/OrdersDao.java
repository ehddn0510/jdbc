package edu.sm.dao;

import edu.sm.dto.Orders;
import edu.sm.frame.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    private Connection connection;

    public OrdersDao() throws SQLException {
        ConnectionPool pool = ConnectionPool.create();
        connection = pool.getConnection();
    }

    // 주문 추가
    public void addOrder(Orders order) throws SQLException {
        String sql = "INSERT INTO Orders (cart_id, order_date) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCartId());
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        }
    }

    // 주문 수정
    public void updateOrder(Orders order) throws SQLException {
        String sql = "UPDATE Orders SET cart_id = ?, order_date = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCartId());
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(3, order.getId());
            pstmt.executeUpdate();
        }
    }

    // 모든 주문 조회
    public List<Orders> getAllOrders() throws SQLException {
        String sql = "SELECT * FROM Orders";
        List<Orders> orders = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders(
                        rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getTimestamp("order_date")
                );
                orders.add(order);
            }
        }
        return orders;
    }

    // 특정 주문 조회
    public Orders getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE id = ?";
        Orders order = null;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Orders(
                        rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getTimestamp("order_date")
                );
            }
        }
        return order;
    }

    // 주문 삭제
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM Orders WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

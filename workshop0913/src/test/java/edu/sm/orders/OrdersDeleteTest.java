package edu.sm.orders;

import edu.sm.service.OrdersService;

import java.sql.SQLException;

public class OrdersDeleteTest {
    public static void main(String[] args) {
        try {
            OrdersService ordersService = new OrdersService();

            // 주문 삭제
            ordersService.deleteOrder(1);  // 삭제할 주문의 ID
            System.out.println("주문이 성공적으로 삭제되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

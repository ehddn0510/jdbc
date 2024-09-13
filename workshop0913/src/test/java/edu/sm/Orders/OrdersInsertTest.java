package edu.sm.Orders;

import edu.sm.dto.Orders;
import edu.sm.service.OrdersService;

import java.sql.SQLException;

public class OrdersInsertTest {
    public static void main(String[] args) {
        try {
            OrdersService ordersService = new OrdersService();

            // 주문 추가
            Orders order = new Orders();
            order.setCartId(1);  // 실제 Cart 테이블에 있는 id로 설정해야 함

            ordersService.addOrder(order);
            System.out.println("주문이 성공적으로 추가되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

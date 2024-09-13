package edu.sm.Orders;

import edu.sm.dto.Orders;
import edu.sm.service.OrdersService;

import java.sql.SQLException;

public class OrdersUpdateTest {
    public static void main(String[] args) {
        try {
            OrdersService ordersService = new OrdersService();

            // 기존 주문을 수정하기 위한 객체 생성
            Orders order = new Orders();
            order.setId(1);  // 수정할 주문 ID
            order.setCartId(2);  // 새로운 Cart ID로 변경
            ordersService.updateOrder(order);
            System.out.println("주문이 성공적으로 수정되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

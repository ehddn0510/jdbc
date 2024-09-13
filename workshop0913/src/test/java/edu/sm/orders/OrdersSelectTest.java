package edu.sm.orders;

import edu.sm.dto.Orders;
import edu.sm.service.OrdersService;

import java.sql.SQLException;
import java.util.List;

public class OrdersSelectTest {
    public static void main(String[] args) {
        try {
            OrdersService ordersService = new OrdersService();
            List<Orders> ordersList = ordersService.getAllOrders();

            for (Orders order : ordersList) {
                System.out.println("Order ID: " + order.getId() +
                        ", Cart ID: " + order.getCartId() +
                        ", Order Date: " + order.getOrderDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

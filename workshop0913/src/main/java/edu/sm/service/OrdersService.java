package edu.sm.service;

import edu.sm.dao.OrdersDao;
import edu.sm.dto.Orders;

import java.sql.SQLException;
import java.util.List;

public class OrdersService {
    private OrdersDao ordersDao;

    public OrdersService() throws SQLException {
        this.ordersDao = new OrdersDao();
    }

    // 주문 추가
    public void addOrder(Orders order) throws SQLException {
        ordersDao.addOrder(order);
    }

    // 주문 수정
    public void updateOrder(Orders order) throws SQLException {
        ordersDao.updateOrder(order);
    }

    // 모든 주문 조회
    public List<Orders> getAllOrders() throws SQLException {
        return ordersDao.getAllOrders();
    }

    // 특정 주문 조회
    public Orders getOrderById(int id) throws SQLException {
        return ordersDao.getOrderById(id);
    }

    // 주문 삭제
    public void deleteOrder(int id) throws SQLException {
        ordersDao.deleteOrder(id);
    }
}

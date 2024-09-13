package edu.sm.dto;

import java.util.Date;

public class Orders {
    private int id;
    private int cartId;
    private Date orderDate;

    // 생성자
    public Orders(int id, int cartId, Date orderDate) {
        this.id = id;
        this.cartId = cartId;
        this.orderDate = orderDate;
    }

    // 기본 생성자
    public Orders() {}

    // Getter와 Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}

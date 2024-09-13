package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

// 장바구니 내의 특정 상품 조회
public class CartSelectOne {

    public static void main(String[] args) {
        CartService cartService = new CartService();
        int cartId = 2;  // 장바구니에서 특정 상품 ID 조회
        Cart cart = null;

        try {
            cart = cartService.selectOne(cartId);  // selectOne 메서드 호출
            System.out.println(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.vls.cart.model;

public class CartModel {
    private int cartId;
    private int courseId;
    private int loginId;

    public CartModel(int cartId, int courseId, int loginId) {
        this.cartId = cartId;
        this.courseId = courseId;
        this.loginId = loginId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }
}

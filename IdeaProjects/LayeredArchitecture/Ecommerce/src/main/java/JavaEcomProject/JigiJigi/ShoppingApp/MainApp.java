package JavaEcomProject.JigiJigi.ShoppingApp;

import JavaEcomProject.JigiJigi.Products.Profile;
import JavaEcomProject.JigiJigi.Products.ShoppingCart;

public class MainApp {
    public static void main(String[] args) {
        Profile profile = new Profile();
        if (profile.loginOrSignup()) {
            ShoppingCart cart = new ShoppingCart(profile);
            cart.startShopping();
        }
    }
}

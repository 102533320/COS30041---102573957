/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edsfsb;

import dto.CartItem;
import ed.sfsb.ShopCartBeanRemote;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Matt
 */
public class ShopCartAppClient {

    @javax.ejb.EJB
    private static ShopCartBeanRemote shopCart;

    public ShopCartAppClient() {
    }

    public static void main(String[] args) {
        ShopCartAppClient appClient = new ShopCartAppClient();
        // show that the shopCart is empty
        appClient.displayCart();
        // assuming they are selected by the user
        // 1. testing adding of new items
        CartItem item1 = new CartItem("000001", "Intel Core i7 CPU", 349.99, 2);
        CartItem item2 = new CartItem("000002", "Intel SSD 512GB", 299.99, 3);
        appClient.addCart(item1);
        appClient.displayCart();
        appClient.addCart(item2);
        appClient.displayCart();
        
        // 2. test updating the quantity (new quantity should be 6)
        appClient.addCart(item2);
        appClient.displayCart();
        
        // 3. test deleting an item from the cart
        appClient.deleteCartItem(item1.getItemId());
        appClient.displayCart();
        
        // 3.1 test deleting an item that doesn't exist
        appClient.deleteCartItem("000003");
        appClient.displayCart();
        
        // 4. test updating an item that doesn't exist in the cart
        CartItem item3 = new CartItem("000003", "Fake Item", 1, 1);
        appClient.updateCartItem(item3);
        appClient.displayCart();
    }
    
    public void addCart(CartItem item) {
        System.out.println("Adding item " + item.getDescription() + " to cart");
        if (addCartItem(item)) {
            System.out.println("Your order of " + item.getQuantity()
                    + " " + item.getDescription() + " has been added.");
        } else {
            System.out.println("Sorry, your order of " + item.getQuantity() + " "
                    + item.getDescription() + " cannot be added due to low stock.");
        }
    }

    public boolean addCartItem(CartItem cartItem) {
        CartItem existingItem = shopCart.getItem(cartItem.getItemId());
        boolean result = false;
        if (existingItem == null) {
            // item doesn't already exist, add a new item in
            result = shopCart.add(cartItem);
        } else {
            // item already exists, update the quantity of existing item
            existingItem.setQuantity(
                existingItem.getQuantity() + cartItem.getQuantity()
            );
            
            result = updateCartItem(existingItem);
        }
        return result;
    }
    
    public boolean deleteCartItem(String itemId){
        return shopCart.delete(itemId);
    }
    
    public boolean updateCartItem(CartItem cartItem){
        return shopCart.update(cartItem);
    }

    public void displayCart() {
        ArrayList<CartItem> ciList = shopCart.getCart();
        if (ciList.isEmpty()) {
            System.out.println("The shopping cart is empty!");
            return;
        }
        System.out.println("Your shopping cart has the following items:");
        double total = 0.0;
        for (CartItem ci : ciList) {
            double unitPrice = ci.getUnitPrice();
            double quantity = ci.getQuantity();
            double subTotal = unitPrice * quantity;
            System.out.println("Item: " + ci.getDescription()
                    + "\tUnit Price: " + ci.getUnitPrice()
                    + "\tQuantity: " + ci.getQuantity()
                    + "\tSub-Total: " + subTotal);
            total = total + subTotal;
        }
        System.out.println("---");
        System.out.println("Total price: " + total);
        System.out.println("----End of Shopping Cart---");
    }
}

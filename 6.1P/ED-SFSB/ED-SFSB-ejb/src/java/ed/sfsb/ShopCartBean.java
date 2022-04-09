/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Matt
 */
@Stateful
public class ShopCartBean implements ShopCartBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private ArrayList<CartItem> cart;

    public ShopCartBean() {
        cart = new ArrayList<CartItem>();
    }

    @Override
    public CartItem getItem(String itemId) {
        for (int i = 0; i < cart.size(); i++){
            CartItem ci = cart.get(i);
            if (ci.getItemId().equals(itemId)){
                return ci;
            }
        }
        return null;
    }
    
    @Override
    public int getIndex(String itemId) {
        for (int i = 0; i < cart.size(); i++){
            CartItem ci = cart.get(i);
            if (ci.getItemId().equals(itemId)){
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public boolean add(CartItem cartItem) {
        boolean result = false;
        try {
            result = cart.add(cartItem);
        } catch (Exception ex) {
            
        }
        return result;
    }
    
    @Override
    public boolean delete(String itemId) {
        boolean result = false;
        try {
            int index = getIndex(itemId);
            if (index == -1) return false;
            cart.remove(index);
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }
    
    @Override
    public boolean update(CartItem cartItem) {
        boolean result = false;
        int existingItemIndex = getIndex(cartItem.getItemId());
        if (existingItemIndex == -1) return false;
        
        try {
            cart.set(existingItemIndex, cartItem);
            result = true;
        } catch(Exception ex){
        }
        return result;
    }
    
    @Override
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    @Remove
    public void remove() {
        cart = null;
    }
}

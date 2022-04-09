/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Matt
 */
@Remote
public interface ShopCartBeanRemote {

    boolean add(CartItem cartItem);

    ArrayList<CartItem> getCart();

    boolean delete(String itemId);

    CartItem getItem(String itemId);

    boolean update(CartItem cartItem);

    int getIndex(String itemId);
    
}

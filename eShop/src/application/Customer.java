package application;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */

public class Customer extends User {
	/**
	 * Orderlist and Cart from the Customer atrinutes
	 */
	private List<Product> orderList = new ArrayList<Product>();
	private List<Product> cart = new ArrayList<Product>();
	/**
	 * Customer constructor
	 */
	public Customer(String username, String password, boolean isAdmin) {
		super(username, password, isAdmin);
	}
	/**
	 * Customer getters and setters
	 */

	public List<Product> getOrderList() {
		return orderList;
	}

	public List<Product> getCart() {
		return cart;
	}

	public void setProductOrder(Product order) {
		orderList.add(order);
	}

	public void setProductCart(Product cartP) {
		cart.add(cartP);
	}

	public void removeOrder(Product order) {
		orderList.remove(order);
	}

	public void removeCart(Product cart) {
		orderList.remove(cart);
	}

	public void removeCartList() {
		cart.clear();
	}

	public void removeOrderList() {
		orderList.clear();
	}

}

package application;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */
public class Seller extends User {
	/**
	 * 
	 * ProducctList of a Seller
	 * Rating of a Seller
	 *
	 */
	private List<Product> productList = new ArrayList<Product>();
	private double rating;
	/**
	 * 
	 * Constructor of a Seller
	 *
	 */
	public Seller(String username, String password, boolean isAdmin, double rating) {
		super(username, password, isAdmin);
		this.rating = rating;
	}
	/**
	 * 
	 * Getters and Setters
	 *
	 */
	public List<Product> getProductList() {
		return productList;
	}

	public void addProductList(Product product) {
		productList.add(product);
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}

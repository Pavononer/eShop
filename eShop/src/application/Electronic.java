package application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.Scene;

/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */

public class Electronic extends Product {
	/**
	 * Specifications and brand for the electronic product
	 */
	private String specifications;
	private String brand;

	/**
	 * Electronic constructor
	 */
	
	public Electronic(String name, String description, String image, double price, double rate, int stockCounter, String purchaseDate, String specifications, String brand) {
		super(name, description, image, price, rate, stockCounter, purchaseDate);
		this.specifications = specifications;
		this.brand = brand;
	}
	/**
	 * Getters and setters for Electronic
	 */
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyRating(int rating) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyAmount(double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addReview(String review) {
		reviews.add(review);	
	}

}

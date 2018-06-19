package application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */

public abstract class Product {
	/**
	 * Name of product
	 * Description of product
	 * Url from Image
	 * Price 
	 * Rate
	 * List of ratings
	 * List of reviews
	 * PurchaseDate
	 * StockCounter
	 *
	 */
	protected String name;
	protected String description;
	protected String image;
	protected double price;
	protected double rate;
	protected List<String> reviews;
	protected List<Integer> ratings;
	protected int stockCounter;
	protected String purchaseDate;
	/**
	 * 
	 * Constructor from Product
	 *
	 */
	public Product(String name, String description, String image, double price, double rate, int stockCounter, String purchaseDate) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.rate = rate;
		this.stockCounter = stockCounter;
		this.purchaseDate = purchaseDate;
	}
	/**
	 * 
	 * Getters and setters for Product
	 * Methods to be implemented for products
	 *
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<String> getReviews() {
		return reviews;
	}

	public void setReviews(List<String> reviews) {
		this.reviews = reviews;
	}

	public int getStockCounter() {
		return stockCounter;
	}

	public void setStockCounter(int stockCounter) {
		this.stockCounter = stockCounter;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}



	public abstract void showView();

	public abstract void addReview(String review);
	
	public abstract void modifyRating(int rating);

	public abstract void modifyAmount(double amount);

}

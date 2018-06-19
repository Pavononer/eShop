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

public class Music extends Product {
	/**
	 * 
	 * Author and albmuname for Music
	 *
	 */
	private String author;
	private String albumName;
	/**
	 * 
	 * Music Constructors
	 *
	 */
	public Music(String name, String description, String image, double price, double rate, int stockCounter, String purchaseDate, String author, String albumName) {
		super(name, description, image, price, rate, stockCounter, purchaseDate);
		this.author = author;
		this.albumName = albumName;
	}
	/**
	 * 
	 * Getters and Setters for Music
	 *
	 */
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifyRating(int rating) {
		int acRate = 0;
		int totRate = 0;
		ratings.add(rating);
		for (int rate : ratings) {
			acRate += rate;
			totRate++;
		}

		this.rate = acRate / totRate;
	}

	@Override
	public void modifyAmount(double amount) {
		this.stockCounter -= amount;
	}

	@Override
	public void addReview(String review) {
		reviews.add(review);
		
	}

}

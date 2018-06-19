package Controller;

import application.Electronic;
import application.Movie;
import application.Music;
import application.Product;
import application.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */

public class ProductViewController {
	/**
	 * 
	 *Controller of the view of one single product
	 *
	 */
	@FXML
	private Button logoutButton;
	@FXML
	private Button buyButton;
	@FXML
	private Button mainViewButton;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label desLabel;
	@FXML
	private Label stockLabel;
	@FXML
	private Label specific1Label;
	@FXML
	private Label specific2Label;
	@FXML
	private Label rateLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private ImageView iproduct;
	@FXML
	private ListView reviewsList;

	/**
	 * 
	 * Method to set the values of the view based on the product which was clicked from before
	 * Listeners to know if buttons were pressed
	 *
	 */

	public void userInteraction(final Model.LoginManager loginManager, String username, Product p, User session) {

		
		System.out.println(p.getName());
		//Setting values in the view from the database
		usernameLabel.setText("Welcome back "+ username +"!");
		nameLabel.setText(p.getName());
		desLabel.setText("Description: " +p.getDescription());
		iproduct.setImage(new Image(p.getImage()));
		stockLabel.setText("Left in stock: "+Integer.toString(p.getStockCounter()));
		//rateLabel.setText(Double.toString(p.getRate()));
		priceLabel.setText("Price: "+Double.toString(p.getPrice())+ "$");
		
		if (p instanceof Music){
			specific1Label.setText("Album Name: "+((Music) p).getAlbumName());
			specific2Label.setText("Album Author: "+((Music) p).getAuthor());
		}else if(p instanceof Electronic){
			specific1Label.setText("Specifications: "+((Electronic) p).getSpecifications());
			specific2Label.setText("Brand: "+((Electronic) p).getBrand());
		}else if(p instanceof Movie){
			specific1Label.setText("Duration: "+((Movie) p).getDuration());
			specific2Label.setText("Trailer link: "+((Movie) p).getTrailer());
		}
				
			
			//Actions triggered in the view
			mainViewButton.setOnAction(e -> loginManager.showMainView(username,session));
			logoutButton.setOnAction(e -> loginManager.logout());
			
	}
}
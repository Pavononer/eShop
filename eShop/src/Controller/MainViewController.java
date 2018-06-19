package Controller;

import javafx.event.Event;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Model.LoginManager;
import Model.daoModel;
import Model.daoModelImpl;
import application.Electronic;
import application.Movie;
import application.Music;
import application.Product;
import application.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/** Controls the main application screen */
public class MainViewController {

	@FXML
	private Button logoutButton;
	@FXML
	private Button moviesButton;
	@FXML
	private Button musicButton;
	@FXML
	private Button electronicButton;
	@FXML
	private Button cartButton;
	@FXML
	private Label usernameLabel;
	@FXML
	private ImageView imovie1;
	@FXML
	private ImageView imovie2;
	@FXML
	private ImageView imovie3;
	@FXML
	private ImageView imusic1;
	@FXML
	private ImageView imusic2;
	@FXML
	private ImageView imusic3;
	@FXML
	private ImageView ielec1;
	@FXML
	private ImageView ielec2;
	@FXML
	private ImageView ielec3;
	@FXML
	private Label lmovie1;
	@FXML
	private Label lmovie2;
	@FXML
	private Label lmovie3;
	@FXML
	private Label lmusic1;
	@FXML
	private Label lmusic2;
	@FXML
	private Label lmusic3;
	@FXML
	private Label lelec1;
	@FXML
	private Label lelec2;
	@FXML
	private Label lelec3;
	@FXML
	private TextField searchBar;
	
	/**
	 * 
	 * Method to see the user cart if he had one and set the overall view
	 * It also allows to click on the different categories to see all the products from that category
	 * It allows to see your Cart and logout
	 * 
	 */
	public void userInteraction(final Model.LoginManager loginManager, String username, User session) throws Exception {
		daoModel dao = new daoModelImpl();
		List<Product> products = new ArrayList<Product>(dao.getProducts());
		List<Product> myProducts = new ArrayList<Product>(dao.getCart(session));
		List<Product> moviesProducts = new ArrayList<Product>();
		List<Product> musicProducts = new ArrayList<Product>();
		List<Product> electronicProducts = new ArrayList<Product>();
		
		//Divide in categories for the productListView
		for (Product p: products){
			if (p instanceof Movie){
				moviesProducts.add(p);
				
			}else if (p instanceof Music){
				musicProducts.add(p);
				
			}else if (p instanceof Electronic){
				electronicProducts.add(p);
				
			}
		}
		//Setting values in the view from the database
		usernameLabel.setText("Welcome back "+ username +"!");
		
		//Method to show 3 samples of the different products in the main view
		
		Music[] arr = new Music[3];
		Electronic[] arr1 = new Electronic[3];
		Movie[] arr2 = new Movie[3];
		int cont = 0; int cont1= 0; int cont2=0;
		for(Product p: products){
			if ((p instanceof Music)&&cont<3){
				arr[cont]=(Music) p;
				cont++;
			}else if ((p instanceof Electronic)&&cont1<3){
				arr1[cont1] = (Electronic)p;
				cont1++;
			}else if((p instanceof Movie)&&cont2<3){
				arr2[cont2] = (Movie)p;
				cont2++;
			}
			if(cont == 3 && cont1 == 3 && cont2 == 3){
				break;
			}
		}
//		Path[] path = new Path[8];
		
//		for(int i = 0; i<path.length; i++){
//			if(i<3 && i>=0){
//				path[i] = Paths.get(arr[i].getImage());
//			}else if(i>2 && i<6){
//				path[i] = Paths.get(arr1[i-3].getImage());
//			}else{
//				path[i] = Paths.get(arr2[i-6].getImage());
//			}		
//		}
		
		
		lmusic1.setText(arr[0].getName());
		lmusic2.setText(arr[1].getName());
		lmusic3.setText(arr[2].getName());
		
		lelec1.setText(arr1[0].getName());
		lelec2.setText(arr1[1].getName());
		lelec3.setText(arr1[2].getName());
		
		lmovie1.setText(arr2[0].getName());
		lmovie2.setText(arr2[1].getName());
		lmovie3.setText(arr2[2].getName());
		
		imovie1.setImage(new Image(arr2[0].getImage()));
		imovie2.setImage(new Image(arr2[1].getImage()));
		imovie3.setImage(new Image(arr2[2].getImage()));
		
		imusic1.setImage(new Image(arr[0].getImage()));
		imusic2.setImage(new Image(arr[1].getImage()));
		imusic3.setImage(new Image(arr[2].getImage()));
		
		ielec1.setImage(new Image(arr1[0].getImage()));
		ielec2.setImage(new Image(arr1[1].getImage()));
		ielec3.setImage(new Image(arr1[2].getImage()));
		
		
		//Actions triggered in the view
		//Search bar redirect to productListView
		searchBar.setOnInputMethodTextChanged(e -> {
			try {
				searchProductName(loginManager, searchBar.getText(), username, session);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		//Listeners to change between the different views depending if we click images or buttons
		
		cartButton.setOnAction(e -> loginManager.showProductListView(myProducts,username,session));
		moviesButton.setOnAction(e -> loginManager.showProductListView(moviesProducts,username,session));
		musicButton.setOnAction(e -> loginManager.showProductListView(musicProducts,username,session));
		electronicButton.setOnAction(e -> loginManager.showProductListView(electronicProducts,username,session));
		logoutButton.setOnAction(e -> loginManager.logout());
		imovie1.setOnMouseClicked(e -> loginManager.showProduct(username,arr2[0], session));
		
		imovie2.setOnMouseClicked(e -> loginManager.showProduct(username,arr2[1], session));
		
		imovie3.setOnMouseClicked(e -> loginManager.showProduct(username,arr2[2], session));
		
		imusic1.setOnMouseClicked(e -> loginManager.showProduct(username, arr[0], session));
		
		imusic2.setOnMouseClicked(e -> loginManager.showProduct(username, arr[1], session));
		
		imusic3.setOnMouseClicked(e -> loginManager.showProduct(username, arr[2], session));
		
		ielec1.setOnMouseClicked(e -> loginManager.showProduct(username, arr1[0], session));
		
		ielec2.setOnMouseClicked(e -> loginManager.showProduct(username, arr1[1], session));
		
		ielec3.setOnMouseClicked(e -> loginManager.showProduct(username, arr1[2], session));
		
		
	}
	/**
	 * 
	 * Searching method based on what is been written
	 *
	 */
	
	public void searchProductName (final Model.LoginManager loginManager, String search, String username, User session) throws Exception{
		//Filter by name
		daoModel dao = new daoModelImpl();
		List<Product> products = new ArrayList<Product>(dao.searchProductbyName(search));
		//Return that ArrayList to the view
		loginManager.showProductListView (products,username, session);
	}
}
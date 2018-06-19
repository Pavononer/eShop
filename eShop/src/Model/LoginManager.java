package Model;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import Controller.LoginController;
import Controller.MainViewController;
import Controller.ProductListController;
import Controller.ProductViewController;
import application.Product;
import application.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */
/** Manages control flow for the whole application */
public class LoginManager {
	private Scene scene;
	private User session;

	public LoginManager(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Callback method invoked to notify that a user has been authenticated.
	 * Will show the main application screen.
	 * @throws Exception 
	 */
	public void authenticated(User session,String username) throws Exception {
		showMainView(username,session);
		System.out.println(session.getPassword());
	}

	/**
	 * Callback method invoked to notify that a user has logged out of the main
	 * application. Will show the login application screen.
	 */
	public void logout() {
		showLoginScreen();
	}
	/**
	 * Callback method invoked to  show the login application screen.
	 */
	public void showLoginScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
			scene.setRoot((Parent) loader.load());
			LoginController controller = loader.<LoginController>getController();
			controller.initManager(this);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	/**
	 * Callback method invoked to  show the Main View screen.
	 */
	public void showMainView(String username,User session) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			scene.setRoot((Parent) loader.load());
			MainViewController controller = loader.<MainViewController>getController();
			controller.userInteraction(this, username, session);
		} catch (Exception ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	/**
	 * Callback method invoked to  show the Product View screen.
	 */
	public void showProduct(String username, Product p, User session) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("productView.fxml"));
			scene.setRoot((Parent) loader.load());
			ProductViewController controller = loader.<ProductViewController>getController();
			controller.userInteraction(this,username, p, session);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Callback method invoked to  show the ProductListView application screen.
	 */
	public void showProductListView(List<Product> productsList, String username, User session){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("productList.fxml"));
			scene.setRoot((Parent) loader.load());
			ProductListController controller = loader.<ProductListController>getController();
			controller.userInteraction(this,username, productsList,session);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

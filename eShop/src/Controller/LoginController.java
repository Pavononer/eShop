package Controller;

//import javafx.event.*;
import javafx.fxml.FXML;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.daoModel;
import application.Customer;
import application.Electronic;
import application.Movie;
import application.Music;
import application.Product;
import application.Seller;
import application.User;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */
/** Controls the login screen */
public class LoginController {
	/**
	 * 
	 * Different FXML labels that are present in the LogInView
	 *
	 */
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private CheckBox checkBox;
	@FXML
	private ImageView logo;

	private boolean isSeller = false;
	String sessionID = null;
	String username = null;
	User session;

	public void initManager(final Model.LoginManager loginManager) {
		/**
		 * 
		 * Method to see if the user has been registered or not
		 *
		 */
		logo.setImage(new Image("file:Images/logo2.jpg"));
		loginButton.setOnAction((event) -> {
			try {
				session = authorize();
				username = session.getUsername();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (username != null) {
				try {
					loginManager.authenticated(session, session.getUsername());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		/**
		 * 
		 * Listener to see if the User is a seller or not
		 *
		 */
		checkBox.setOnAction((event) -> {
			isSeller = checkBox.isSelected();
		});
		/**
		 * 
		 *Button to register if you were not
		 *
		 */
		registerButton.setOnAction((event) -> {
			try {
				register();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	/**
	 * 
	 * Method to register a user and include him in the db
	 *
	 */
	private void register() throws Exception {

		daoModel dao = new Model.daoModelImpl();
		User u;
		if (isSeller == false) {
			u = new Customer(user.getText(), password.getText(), false);
		} else {
			u = new Seller(user.getText(), password.getText(), false, 0);
		}
		dao.insertUser(u);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("This user has been registered!");
		alert.showAndWait();
		System.out.println("The user: " + u.getUsername() + " has been registered into the DB");
	}

	/**
	 * Check authorization credentials.
	 * 
	 * If accepted, return a sessionID for the authorized session otherwise,
	 * return null.
	 * 
	 * @throws Exception
	 * 
	 */
	private User authorize() throws Exception {
		int id = 0;
		User u;
		// Call method that returns ask in DB if username and pass exists
		daoModel dao = new Model.daoModelImpl();
		//dao.showTable("sellers_ar");
		if (isSeller == false) {
			u = new Customer(user.getText(), password.getText(), false);
		} else {
			u = new Seller(user.getText(), password.getText(), false, 0);
		}
		try {
			id = dao.returnUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (id == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("This user is not in the DB");
			alert.showAndWait();
			System.out.println("This user is not in the DB");
		} else {
			//System.out.println("Yey! this user does exist in the DB");
			//sessionID = generateSessionID();
			return u;
		}
		return null;
	}

	private static int sessionIDcounter = 0;

	private String generateSessionID() {
		sessionIDcounter++;
		return "Login - session " + sessionIDcounter;
	}
}

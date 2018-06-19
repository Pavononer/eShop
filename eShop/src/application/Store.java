package application;


import java.io.IOException;

import Model.LoginManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;



/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */

public class Store extends Application {
	/**
	 * 
	 * Class to Launch the application
	 *
	 */

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * 
	 * Method to jump into the loginView and start the application
	 *
	 */
	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(new StackPane());

		LoginManager loginManager = new LoginManager(scene);
		loginManager.showLoginScreen();

		stage.setScene(scene);
		stage.show();
	}

}

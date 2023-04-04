package com.coachomatic;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
* <h1>LoginController</h1>
* LoginController class prompts user for username and password and upon login, brings them to the user menu.
* User can also create a new account. 
* 
* @author  Grace Pearcey
* @version 1.0
* @since   2023-03-29 
*/
public class LoginController {

		@FXML Button createAccountButton;		
		@FXML TextField usernameTextField;		
		@FXML PasswordField passwordField;
		
		private Stage stage;
		private Scene scene;
		private Parent root;


		/**
	    * Brings user to UserMenuScene if user credentials are created and set
	    * 
		* @param event
		* @throws IOException
		* @return void
		*/
		public void login(ActionEvent event) throws IOException {
			
			
			String username = usernameTextField.getText();
			String password = passwordField.getText();
			
			//Check if user credentials missing or no user created
			if (username.isBlank() == true || password.isBlank() == true || Main.user == null) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Login");
				alert.setHeaderText("Invalid User Credentials");
				alert.setContentText("Please try again.");
				
				if (alert.showAndWait().get() == ButtonType.OK) {
				}
			}
			else {
				//Check if user credentials are correct
				if (Main.user.getUsername().equals(username) && Main.user.getPassword().equals(password)) {
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenuScene.fxml"));
					root = loader.load();
					
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();		
				}
				else {
					
					//Alert User of invalid credentials
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Login");
					alert.setHeaderText("Invalid User Credentials");
					alert.setContentText("Please try again.");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
					}					
				}				
			}
		}
		
		/**
	    * Brings user to CreateAccount Scene.
	    * 
		* @param event
		* @throws IOException
		* @return void
		*/
		public void createAccount(ActionEvent event) throws IOException {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccountScene.fxml"));
			root = loader.load();
					
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
}

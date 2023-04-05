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

import com.coach_o_matic_be.*;

/**
 * <h1>CreateAccountController</h1>
 * Creates new user accounts.
 * User must input a username and password to create the account.
 *
 * @version 1.0
 * @since 2023-03-31
 */

 public class CreateAccountController {
	
	
	private static final SoccerPositions GK = null;
	private static final SoccerPositions LD = null;
	private static final SoccerPositions RD = null;
	private static final SoccerPositions LM = null;
	private static final SoccerPositions CM = null;
	private static final SoccerPositions RM = null;
	private static final SoccerPositions ST = null;
	private SoccerPositions[] positions = {GK, LD, RD, LM, CM, RM, ST};
	
	@FXML private Button saveAccountButton;
	@FXML private Button returnButton;
	
	@FXML private TextField usernameTextField;	
	@FXML private PasswordField passwordField;
	
	private String username;
	private String password;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	/**
	 * Creates a user and returns to login page if user enters something for both username and password fields.
	 * Gives user an alert if either of the fields are empty.
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void createAccountAndExit(ActionEvent event) throws IOException {
		boolean usernameEmpty = usernameTextField.getText().isBlank();
		boolean passwordEmpty = passwordField.getText().isBlank();
		
		if (usernameEmpty == true || passwordEmpty == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Create User Account");
			alert.setHeaderText("Invalid Input");
			alert.setContentText("Please create a username and password.");
			
			if (alert.showAndWait().get() == ButtonType.OK) {
			}
		}
		else {
			
			//Creates a  User object with username and password
		    username = usernameTextField.getText();
		    password = passwordField.getText();
		    Main.user = new User(username, password);
			addExampleTeam();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
			root = loader.load();
					
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	private void addExampleTeam() {
		SoccerTeam example_team = new SoccerTeam("Example Team");
		
		SoccerPlayer p1 = example_team.createPlayer("Andrew", positions);
		example_team.addPlayer(p1);
		
		SoccerPlayer p2 = example_team.createPlayer("Reza", positions);
		example_team.addPlayer(p2);
		
		SoccerPlayer p3 = example_team.createPlayer("Thumeera", positions);
		example_team.addPlayer(p3);
		
		SoccerPlayer p4 = example_team.createPlayer("Ebrahim", positions);
		example_team.addPlayer(p4);
		
		SoccerPlayer p5 = example_team.createPlayer("Siu", positions);
		example_team.addPlayer(p5);
		
		SoccerPlayer p6 = example_team.createPlayer("Glyn", positions);
		example_team.addPlayer(p6);
		
		SoccerPlayer p7 = example_team.createPlayer("Misha", positions);
		example_team.addPlayer(p7);
		
		SoccerPlayer p8 = example_team.createPlayer("Rajeevan", positions);
		example_team.addPlayer(p8);
		
		Main.user.addTeam(example_team);
		
	}
	/**
	 * Returns user to login menu without creating a new user.
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void returnToLogin(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	}


}

package com.coachomatic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.coach_o_matic_be.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
* <h1>LineupController</h1>
* LineupController class is used to display generated lineups to the user.
* TODO - BE Connection.
*
* @author  Grace Pearcey, David Davilla
* @version 1.0
* @since   2023-03-29 
*/
public class LineupController {
	
	@FXML Label formationLabel;
	
	@FXML private Button returnButton;
	@FXML private AnchorPane LineupScenePane;
	
	@FXML private Button logoutButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private SoccerTeam team;
	
	/**
	 * LineupController constructor
	 * TODO - may need to update for BE connection
	 * 
	 * @param team_name
	 */
	public LineupController(String team_name) {
		team = Main.user.getTeam(team_name);
	}
	
	/**
	* A GUI Class
	* Logs out user, brings user to LoginScene. Doesn't update or save anything. 
	* 
	* @param event
	* @throws IOException
	* @return void
	*/
	public void logout(ActionEvent event) throws IOException

	{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	/**
	 * A GUI Class 
	 * Returns to TeamMenuScene
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void returnToPreviousScene(ActionEvent event) throws IOException
	{		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamMenuScene.fxml"));
		loader.setControllerFactory(controllerClass -> new TeamMenuController(team.getName()));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	

}

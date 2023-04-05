package com.coachomatic;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.coach_o_matic_be.*;

/**
* <h1>UserMenuController</h1>
* UserMenuController class allows user to create a new team or select an existing.
* 
* @version 1.0
* @since   2023-03-29 
*/
public class UserMenuController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML Label usernameLabel;
	
	@FXML private Button logoutButton;
	@FXML private Button newTeamButton;
	@FXML private Button submitButton;
	
	@FXML private ChoiceBox<String> selectTeamChoiceBox;	
	
	@FXML private AnchorPane userMenuScenePane;
	
	private ObservableList<String> teamsListString;

	
	public String teamname;
	
	/**
	 * Returns a string array of teams that can be used in the gui for displaying a team list
	 * 
	 * @param ArrayList<Team>
	 * @return ArrayList<String>
	 */
	public ObservableList<String> getStringTeamList(ArrayList<SoccerTeam> teamList){
		ObservableList<String> list = FXCollections.observableArrayList();
		for(SoccerTeam t:teamList) {
			list.add(t.getName());
		}
		return list;
	}

	/**
	 * A GUI Class 
	 * Initializes selectTeamChoiceBox
	 * 
	 * @return void
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Display username
		displayName(Main.user.getUsername());
		
		//initialze Team ChoiceBox
		teamsListString = getStringTeamList(Main.user.getTeams());
		selectTeamChoiceBox.getItems().addAll(teamsListString);
	}
	
	
	/**
	 * A GUI Class
	 * Displays username to the UserMenuPage.
	 * 
	 * @param username
	 * @return void
	 */
	public void displayName(String username) {
		usernameLabel.setText("Hi " + username);
	}

	
	
	/**
	 * A GUI Class
	 * Logs out user, brings user to LoginScene
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
	 * Create a new team and brings user to EditTeamScene
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void addTeam(ActionEvent event)throws IOException
	{		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTeamScene.fxml"));

		// Creates a controller factory that allows for calling EditTeamController constructor before loading
		loader.setControllerFactory(controllerClass -> new EditTeamController());

		root = loader.load();

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	

	/**
	 * A GUI Class
	 * Brings user to selected Team Menu. 
	 * Displays an alert if no team is selected. 
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void visitTeamMenu(ActionEvent event) throws IOException {
			
			if (selectTeamChoiceBox.getValue() == null) {
				
				//No teams created				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Select Team");
				alert.setHeaderText("No Teams to Select From");
				alert.setContentText("Please create a team.");
				
				if (alert.showAndWait().get() == ButtonType.OK) {
				}
			}
			else {
				teamname = selectTeamChoiceBox.getValue();
				boolean teamname_empty = teamname.isBlank();
				if (teamname_empty) {
					
					//No team selected					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Select Team");
					alert.setHeaderText("Invalid Input");
					alert.setContentText("Please select one of the teams from the the drop down list.");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
					}
				}
				
				else {
					
					//Team selected -> Display the selected team's menu scene
					FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamMenuScene.fxml"));		
					loader.setControllerFactory(controllerClass -> new TeamMenuController(teamname)); //sets the teamname of team to load
					root = loader.load();
					
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			}
			
	}



	
}
	
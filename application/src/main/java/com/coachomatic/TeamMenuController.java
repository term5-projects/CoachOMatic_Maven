package com.coachomatic;


import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckListView;

import com.coach_o_matic_be.*;
import javafx.collections.ListChangeListener;
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
* <h1>TeamMenuController</h1>
* TeamMenuController class allows user to edit their team and
* select available players to create lineups.
* TODO - BE Connection for lineup generator, fix formations so min players is not hardcoded
* 
* @author  Grace Pearcey
* @version 1.0
* @since   2023-03-29 
*/
public class TeamMenuController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML private Button logoutButton;
	@FXML private Button returnButton;
	@FXML private Button createLineupButton;
	@FXML private Button editTeamButton;
	
	@FXML private Label teamnamelabel;
	
	@FXML private AnchorPane teamMenuScenePane;
	
	@FXML private CheckListView<String> availablePlayersCheckListView;
	
	private ArrayList<String> stringPlayersList = new ArrayList<>();
	
	private ArrayList<String> selectedPlayers =new ArrayList<>();
	
	private SoccerTeam team;

	int num_players_in_formation = 2; //TODO -> fix formations
	
	/**
	 * TeamMenuConroller constructor
	 * Sets current team.
	 * 
	 * @param teamname
	 */
	public TeamMenuController(String teamname) {
		team = Main.user.getTeam(teamname);
	}
	
	/**
	 * Returns a string array of the team's players that can be used in the gui for displaying a player list
	 * 
	 * @param ArrayList<Player>
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getStringPlayerList(ArrayList<SoccerPlayer> playerList){
		ArrayList<String> stringPlayerList = new ArrayList<>();
		for(Player p:playerList) {
			stringPlayerList.add(p.getName());
		}
		return stringPlayerList;
	}
	
	/**
	 * A GUI Class
	 * Initializes the available players checklist. 
	 * Clears then updates selectedPlayers every time a box is ticked.
	 * 
	 * @return void
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Display Team Name
		displayTeamName(team.getName());
		
		//Initialize PlayerList
		stringPlayersList = getStringPlayerList(team.getPlayers());
		availablePlayersCheckListView.getItems().addAll(stringPlayersList);
		availablePlayersCheckListView.getCheckModel().getCheckedItems().addListener((ListChangeListener<? super String>) new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		         selectedPlayers.clear();
		    	 selectedPlayers.addAll(availablePlayersCheckListView.getCheckModel().getCheckedItems());
		     }
		 });
	}
	
	/**
	 * A GUI Class
	 * Displays team name in a label
	 * 
	 * @param TeamName
	 * @return void
	 */
	public void displayTeamName(String TeamName) {
		teamnamelabel.setText( TeamName + " Menu");
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
	 * Brings user to User Menu
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void returnToUserMenu(ActionEvent event) throws IOException
	{		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenuScene.fxml"));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
	/**
	 * A GUI Class
	 * Brings user to generated lineup scene
	 * TODO - BE connection - might need to make a constuctor and pass in info
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void switchToLineupScene(ActionEvent event)throws IOException
	{	
		//TODO BE Connection - num_players_in_formation = formation.getPlayers();
		if (selectedPlayers.size() < num_players_in_formation) {
			//Not enough players selected to generate lineup
			String num_players_in_formation_str = String.valueOf(num_players_in_formation);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Lineup Generation");
			alert.setHeaderText("Invalid Player Selection");
			alert.setContentText("Please select at least " +  num_players_in_formation_str + " players for the team formation.");
			
			if (alert.showAndWait().get() == ButtonType.OK) {
			}
		}else {
		
			//Load the Lineup Scene
			System.out.println("The dynamic array is: " + selectedPlayers);			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LineupScene.fxml"));	
			loader.setControllerFactory(controllerClass -> new LineupController(team.getName(), selectedPlayers)); //TODO BE may need to add more params into constructor
			root = loader.load();
	
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
			scene = new Scene(root);	
			stage.setScene(scene);	
			stage.show();
		}
	}
	
	/**
	 * A GUI Class
	 * Brings user to edit team scene	 *
	 * 
	 * @param event
	 * @throws IOException
	 * @return void
	 */
	public void switchToEditTeamScene(ActionEvent event)throws IOException

	{		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTeamScene.fxml"));
		loader.setControllerFactory(controllerClass -> new EditTeamController(team.getName()));
		root = loader.load();	
					
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}


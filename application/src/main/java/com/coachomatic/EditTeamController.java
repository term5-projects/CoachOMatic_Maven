package com.coachomatic;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.coach_o_matic_be.*;

/**
* <h1>EditTeamController</h1>
* EditTeamController class is used to edit or create a new team.
* Prompts user for Team name, number of shifts per game, formation, and players on the team.  
* TODO - fix formations
*
* @author  Grace Pearcey
* @version 1.0
* @since   2023-03-29 
*/

public class EditTeamController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML Label usernameLabel;
	@FXML private Label formationLabel;
	
	@FXML private Button returnButton;
	@FXML private Button logoutButton;
	@FXML private Button addPlayerButton;
	@FXML private Button saveTeamButton;
	@FXML private Button editPlayerButton;
	@FXML private Button deletePlayerButton;
	
	@FXML private TextField teamNameTextField;
	
	@FXML private ChoiceBox<String> formationChoiceBox;	
	@FXML private ChoiceBox<String> test;	
	
	@FXML private Spinner<Integer> shiftsSpinner;
	
	@FXML private ListView<String> playerListView;	
	
	@FXML private AnchorPane editTeamPane;

	private ArrayList<String> stringPlayersList = new ArrayList<>();
	
	public SoccerTeam team;
	
	private String defaultTeamName = "";
	
	private String[] formations = {"2-3-1"}; //TODO - fix formations
	private String selectedPlayer;


	public EditTeamController() {
		team = new SoccerTeam(defaultTeamName); 
		Main.user.addTeam(team);
		System.out.println("EditTeamController() called");		
		
	}
	public EditTeamController(String team_name) {
		team = Main.user.getTeam(team_name);
		System.out.println("EditTeamController(String team_name) called");
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
	 * Returns a string array of all soccer formations that can a coach can select
	 * TODO - fix formations
	 * 
	 * @param ArrayList<Formation>
	 * @return ArrayList<String>
	 */
//	public ArrayList<String> getStringFormationList(ArrayList<Formation> formationList){
//		ArrayList<String> stringFormationList = new ArrayList<>();
//		for(Formation f:formationList) {
//			stringFormationList.add(f.getFormationName());
//		}
//		return stringPlayerList;
//	}
	
	

	/**
	 * A GUI Class
	 * Initializes ChoiceBox, Spinner, and ListView
	 * TODO fix formations
	 * 
	 * @return void
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.print("EditTeamController initialize() called");
		
		//Fill Text Field
		teamNameTextField.setText(team.getName());
		
		//formation ChoiceBox
		formationChoiceBox.getItems().addAll(formations); //TODO fix formations

		
		//shifts Spinner 
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);		
		valueFactory.setValue(team.getGameShifts());	
	
		shiftsSpinner.setValueFactory(valueFactory);		
		
		//Player List View		
		stringPlayersList = getStringPlayerList(team.getPlayers());
		playerListView.getItems().addAll(stringPlayersList);
		playerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {//TODO -> <Player>

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
				
			}
		});
		
	}
	
	/**
	* A GUI Class
	* Logs out user, brings user to LoginScene. Doesn't save anything. Deletes the temporary  team if exists 
	* 
	* @param event
	* @throws IOException
	* @return void
	*/
	public void logout(ActionEvent event)throws IOException
	{
		if (team.getName().equals(defaultTeamName)) {
			Main.user.removeTeam(defaultTeamName);
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		
	}
	
	/**
	 * A GUI Class
	 * Brings user to Edit Player Page
	 * 
	 * @param player
	 * @return void
	 * @throws IOException 
	 */
	public void editPlayer(ActionEvent event) throws IOException
	{			
		//Display alert if no player selected
		if (selectedPlayer == null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Select Player");
			alert.setHeaderText("No Player Selected");
			alert.setContentText("Please select a player from the list.");
			
			if (alert.showAndWait().get() == ButtonType.OK) {
			}
		}else{
			
			//load EditPlayerScene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPlayerScene.fxml"));
			loader.setControllerFactory(controllerClass -> new EditPlayerController(team.getName(), selectedPlayer));
			root = loader.load();
					
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
						
		}
		return;
		
		

	}
	
	/**
	 * A GUI Class
	 * Permanently deletes a player from the team
	 * 
	 * @param event
	 * @return void
	 */
	public void deletePlayer(ActionEvent event) {
		selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
		
		//Display alert if no player selected
		if (selectedPlayer == null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Select Player");
			alert.setHeaderText("No Player Selected");
			alert.setContentText("Please select a player from the list.");
			
			if (alert.showAndWait().get() == ButtonType.OK) {
			}
		}else{
			//Delete Player		
			boolean player_deleted = team.removePlayer(selectedPlayer);
			stringPlayersList = getStringPlayerList(team.getPlayers()); //get updated player list
			playerListView.getItems().clear(); // clear the ListView
		    playerListView.getItems().addAll(stringPlayersList); // add updated stringPlayersList to the ListView
			System.out.println("players after " + selectedPlayer +  " removed: " + stringPlayersList);
			
			if (player_deleted == false) {
				System.out.println("Error: " + selectedPlayer + " could not be deleted.");
			}
			
		}
		return;
	}
	
	/**
	 * A GUI Class
	 * Brings user to add Player Scene
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void addPlayer(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPlayerScene.fxml"));
		loader.setControllerFactory(controllerClass -> new EditPlayerController(team.getName())); 
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * A GUI Class
	 * Saves team name, formation, and shifts, and exits to the TeamMenu of team edited or created
	 * TODO - add a check for formation
	 * 
	 * @param event
	 * @return void
	 * @throws IOException 
	 */
	public void saveTeam(ActionEvent event) throws IOException {

		boolean teamEmpty = teamNameTextField.getText().isBlank();
		String formation = formationChoiceBox.getValue();
		
		//Display alert if no team name or formation selected
		if (teamEmpty == true) {//TODO add check for formation
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Team Settings");
			alert.setHeaderText("Missing Team Information");
			alert.setContentText("Please enter Team Name and Formation.");
			
			if (alert.showAndWait().get() == ButtonType.OK) {
			}
		} else {
			
			//Update the team
			Main.user.updateTeam(team, teamNameTextField.getText(), team.getFormation(), shiftsSpinner.getValue());
			
			//Exit the edit team scene to the TeamMenuScene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamMenuScene.fxml"));		
			loader.setControllerFactory(controllerClass -> new TeamMenuController(team.getName()));
			root = loader.load();
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}			
		}

		
		
	
	
	/**
	 * A GUI Class 
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void returnToPreviousScene(ActionEvent event) throws IOException
	{		
		//Delete new_team if not updated and saved, exit to UserMenu as no team was created
		if (team.getName().equals(defaultTeamName)) {
			Main.user.removeTeam(defaultTeamName);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenuScene.fxml"));
			root = loader.load();
					
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else {
			
		//Return to User menu if new team was created, or return to Team Menu if existing team was edited.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamMenuScene.fxml"));
		loader.setControllerFactory(controllerClass -> new TeamMenuController(team.getName()));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}

	}
		
}
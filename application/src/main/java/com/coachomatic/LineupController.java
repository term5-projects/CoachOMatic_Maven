package com.coachomatic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import com.coach_o_matic_be.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




/**
* <h1>LineupController</h1>
* LineupController class is used to display generated lineups to the user.
* @version 1.0
* @since   2023-03-29 
*/
public class LineupController implements Initializable{

	
	@FXML Label formationLabel;
	
	@FXML private Button returnButton;
	@FXML private AnchorPane LineupScenePane;
	@FXML private Button logoutButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private SoccerTeam team;
	
	private ArrayList<String> selectedPlayers =new ArrayList<>();

	/**
	 * LineupController constructor
	 * 
	 * @param team_name
	 */
	public LineupController(String team_name, ArrayList<String> selectedPlayers_user) {
		team = Main.user.getTeam(team_name);
		selectedPlayers = selectedPlayers_user;
	}
	

	
  @FXML private TableView<String> lineupTable;

  @FXML private TableColumn<String, String> shiftColumn;

  @FXML private TableColumn<String, String> gkColumn;

  @FXML private TableColumn<String, String> ldColumn;

  @FXML private TableColumn<String, String> rdColumn;

  @FXML private TableColumn<String, String> lmColumn;

  @FXML private TableColumn<String, String> cmColumn;

  @FXML private TableColumn<String, String> rmColumn;

  @FXML private TableColumn<String, String> stColumn;

  @FXML private TableColumn<String, String> subColumn;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    	  shiftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[0]));
    	  gkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[1]));
    	  ldColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[2]));
    	  rdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[3]));
    	  lmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[4]));
    	  cmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[5]));
    	  rmColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[6]));
    	  stColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[7]));
    	  subColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().split(",")[8]));
    	  
    	  
    	 
    	  
    	  ArrayList<String> lineuprows = new ArrayList<>();
    	  
    	  
    	  ArrayList<ArrayList<String>> lineupgenerationoutput = SoccerLineupGenerator7v7.generateLineup(selectedPlayers, team.getFormation() ,team.getGameShifts());
    	  
    	  for (int i =0; i < lineupgenerationoutput.size(); i++) {
        	  String str = String.join(",", lineupgenerationoutput.get(i));
        	  lineuprows.add(str);
    	  }
    	  lineupTable.getItems().addAll(lineuprows);
    	  team.getPlayers();  
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



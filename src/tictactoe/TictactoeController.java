/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TictactoeController implements Initializable {

    @FXML
    private Button locbtn;
    @FXML
    private Button combtn;
    @FXML
    private Button onbtn; 

   /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void goLevel(MouseEvent event) throws IOException {
        Stage stage = (Stage) combtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/level/Level.fxml"));
        stage.setScene(new Scene(root));
        
    }

    @FXML
    private void gonline(MouseEvent event) throws IOException {
        Stage stage = (Stage) onbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
        stage.setScene(new Scene(root));
        
    }

    @FXML
    private void golocalgame(MouseEvent event) throws IOException {
        Stage stage = (Stage) locbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/playersName/PlayersName.fxml"));
        stage.setScene(new Scene(root));
    }
    
    
    
}

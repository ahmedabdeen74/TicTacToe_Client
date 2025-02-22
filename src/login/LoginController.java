/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import Player.DTOPlayer;
import Player.PlayerSocket;
import Popups.PopUps;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usertxt;
    @FXML
    private TextField passtxt;
    @FXML
    private Button signbtn;
    @FXML
    private Button regbtn;
    private Stage stage;
    private PlayerSocket playerSocket;
    @FXML
    private Button backhomebtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }

    @FXML
    private void onSignIn(ActionEvent event) {
        playerSocket = PlayerSocket.getInstance();
        Platform.runLater(() -> {
            stage = (Stage) signbtn.getScene().getWindow();
            playerSocket.setStage(stage);
        });
        String username = usertxt.getText().trim();
        String password = passtxt.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            //showAlert(Alert.AlertType.WARNING, "Login Error", "Both fields are required.");
            PopUps.showErrorAlert(stage, "Login Error", "Both fields are required.");
            return;
        }

        // Prepare the JSON message
        Map<String, String> map = new HashMap<>();
        map.put("type", "login");
        map.put("username", username);
        map.put("password", password);
        map.put("status", "online");


        // Send JSON to the server
        try {
            playerSocket.sendJSON(map);
            int score=playerSocket.getPlayerScore();
            DTOPlayer player = new DTOPlayer(username, score, playerSocket.socket);
            playerSocket.setLoggedInPlayer(player);
            //showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "An error occurred during login.");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @FXML
    private void goReg(ActionEvent event) {
        try {
            Stage stage = (Stage) regbtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/register/Register.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Unable to navigate to the registration screen.");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void goHome(ActionEvent event) {
        try {
            Stage stage = (Stage) backhomebtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/tictactoe/tictactoe.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Unable to navigate to the registration screen.");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

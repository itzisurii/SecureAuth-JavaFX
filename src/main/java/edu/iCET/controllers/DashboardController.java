package edu.iCET.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    void logOutOnAction(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();
        currentStage.close();

        // Open login window
        Stage loginStage = new Stage();
        try {
            loginStage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginStage.setTitle("Login");
        loginStage.show();
    }

}

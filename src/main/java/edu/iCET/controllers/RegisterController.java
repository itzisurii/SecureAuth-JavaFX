package edu.iCET.controllers;

import edu.iCET.service.impl.RegisterServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    RegisterServiceImpl registerService = new RegisterServiceImpl();

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    Stage stage = new Stage();

    @FXML
    void signUpOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (!validateEmail(email) || !validPassword(password) || !emailExists(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Only @gmail.com allowed or Password must be 8+ chars, with upper/lower/symbol or already existing account from that email");
            alert.show();
        }

            boolean success = registerService.registerUser(email, password);

            if(success){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully registered! Please login.");
                alert.show();

                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                txtEmail.clear();
                txtPassword.clear();

            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Failed");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong while registering. Try again.");
                alert.show();
            }
        }

    private boolean validateEmail(String email) {
        return registerService.validateEmail(email);
    }

    private boolean validPassword(String password){
        return registerService.validatePassword(password);
    }

    public boolean emailExists(String email) {
        return registerService.emailExists(email);
    }

}

package edu.iCET.controllers;

import edu.iCET.database.PasswordHasher;
import edu.iCET.database.UserDatabase;
import edu.iCET.service.impl.LoginServiceimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    LoginServiceimpl loginServiceimpl= new LoginServiceimpl();
    private PasswordHasher passwordHasher = new PasswordHasher();

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    Stage stage = new Stage();
    @FXML
    void onRegisterClick(MouseEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void signUpOnAction(ActionEvent event) {

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();

        if (!loginServiceimpl.validateEmail(email) && (!loginServiceimpl.validatePassword(password))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Staff Login Failed");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Only @gmail.com allowed and Password must be 8+ chars, with upper/lower/symbol");
            alert.show();
            return;
        } else {
            loginServiceimpl.searchUser(email, password);
        }

        boolean loginSuccess = loginServiceimpl.searchUser(email, password);

        if (loginSuccess) {
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clearFeilds();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("User Not Found");
            alert.setHeaderText("You are not registered yet");
            alert.setContentText("Do you want to register?");

            alert.showAndWait().ifPresent(response -> {
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_form.fxml"))));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            clearFeilds();
        }


        if (loginServiceimpl.needRegistration(email, password)) {

            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
    }

    private boolean validateEmail(String email) {
        return loginServiceimpl.validateEmail(email);
    }

    private boolean validPassword(String password){
        return loginServiceimpl.validatePassword(password);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return loginServiceimpl.emailExists(email);
    }

    // Check email + password
    public boolean searchUser(String email, String password) {
        String hashed = passwordHasher.hashPassword(password);
        return loginServiceimpl.searchUser(email, hashed);
    }

    // Decide if user should register
    public boolean needsRegistration(String email, String password) {
        return  loginServiceimpl.needRegistration(email, password);
    }



}

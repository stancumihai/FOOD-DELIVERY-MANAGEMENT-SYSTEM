package org.stancu.presentation.controller.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.stancu.App;
import org.stancu.model.userModel.User;
import org.stancu.service.LoginService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private LoginService loginService;

    @FXML
    public TextField userTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Label errorLabel;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    public Button registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setLoginService(LoginService.getInstance());
        errorLabel.setVisible(false);
    }

    public int login() throws IOException {
        int idGrasper = 0;
        if (checkIfFieldIsEmpty()) {
            errorLabel.setText("          Some fields are let empty");
            errorLabel.setVisible(true);
        } else if (!confirmPasswordTextField.getText().equals(passwordTextField.getText())) {
            errorLabel.setText("           Password is not same");
            errorLabel.setVisible(true);
        } else {
            User formUser = new User(userTextField.getText(), passwordTextField.getText(), emailTextField.getText());
            String option = loginService.logIn(formUser);
            if (option != null) {
                idGrasper = loginService.getIdGrasper();
                if (option.equals("client")) {
                    App.setRoot("client", 806, 550);
                } else if (option.equals("admin")) {
                    App.setRoot("administrator", 725, 520);
                } else App.setRoot("employee", 725, 550);
            } else {
                errorLabel.setText("            User does not exist");
                errorLabel.setVisible(true);
            }
        }
        return idGrasper;
    }

    public boolean checkIfFieldIsEmpty() {
        return passwordTextField.getText().isBlank() || userTextField.getText().isBlank() || emailTextField.getText().isBlank();
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }


    public void register() throws IOException {
        App.setRoot("register", 360, 650);
    }
}

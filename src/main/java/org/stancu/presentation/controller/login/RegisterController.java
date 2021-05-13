package org.stancu.presentation.controller.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.stancu.App;
import org.stancu.model.userModel.Administrator;
import org.stancu.model.userModel.Client;
import org.stancu.model.userModel.Employee;
import org.stancu.model.userModel.User;
import org.stancu.service.user.AdministratorService;
import org.stancu.service.user.ClientService;
import org.stancu.service.user.EmployeeService;
import org.stancu.service.user.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField confirmPasswordTextField;
    public Label errorLabel;
    @FXML
    public TextField userTextField;
    @FXML
    public PasswordField passwordTextField;

    private final ObservableList<String> comboBoxTopics = FXCollections.observableArrayList();

    private final UserService userService = UserService.getInstance();
    private final AdministratorService administratorService = AdministratorService.getInstance();
    private final ClientService clientService = ClientService.getInstance();
    private final EmployeeService employeeService = EmployeeService.getInstance();
    public ComboBox<String> typeComboBox;

    private final File userFile = new File("users.txt");
    private final File clientFile = new File("clients.txt");
    private final File adminFile = new File("admins.txt");
    private final File employeeFile = new File("employees.txt");

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxTopics.addAll("CLIENT", "ADMIN", "EMPLOYEE");
        typeComboBox.setItems(comboBoxTopics);
        errorLabel.setVisible(false);
    }

    public void register() throws IOException {

        if (checkIfFieldIsEmpty()) {
            errorLabel.setText("          Some fields are let empty");
            errorLabel.setVisible(true);
        } else if (!confirmPasswordTextField.getText().equals(passwordTextField.getText())) {
            errorLabel.setText("           Password is not same");
            errorLabel.setVisible(true);
        } else {
            User myUser = new User(userTextField.getText(), passwordTextField.getText(), emailTextField.getText());
            if (userService.selectAll().size() == 0) {
                myUser.setId(1);
            } else {
                myUser.setId(userService.getLastId() + 1);
            }
            String option = typeComboBox.getValue();
            switch (option) {
                case "CLIENT": {
                    userService.insert(myUser);
                    Client client;
                    if (clientService.selectAll().size() == 0) {
                        client = new Client(1, userService.getLastId());
                    } else {
                        client = new Client(clientService.getLastId() + 1, userService.getLastId());
                    }
                    clientService.insert(client);
                    FileOutputStream fileOutputStream = new FileOutputStream(clientFile);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    for (Client client1 : clientService.selectAll()) {
                        objectOutputStream.writeObject(client1);
                    }
                    fileOutputStream.close();
                    break;
                }
                case "ADMIN": {
                    userService.insert(myUser);
                    Administrator administrator;
                    if (administratorService.selectAll().size() == 0) {
                        administrator = new Administrator(1, userService.getLastId());
                    } else {
                        administrator = new Administrator(administratorService.getLastId() + 1, userService.getLastId());
                    }
                    administratorService.insert(administrator);
                    FileOutputStream fileOutputStream = new FileOutputStream(adminFile);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    for (Administrator administrator1 : administratorService.selectAll()) {
                        objectOutputStream.writeObject(administrator1);
                    }
                    fileOutputStream.close();
                    break;
                }

                case "EMPLOYEE": {
                    userService.insert(myUser);
                    Employee employee;
                    if (employeeService.selectAll().size() == 0) {
                        employee = new Employee(1, userService.getLastId());
                    } else {
                        employee = new Employee(employeeService.getLastId() + 1, userService.getLastId());
                    }
                    employeeService.insert(employee);
                    FileOutputStream fileOutputStream = new FileOutputStream(employeeFile);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    for (Employee employee1 : employeeService.selectAll()) {
                        objectOutputStream.writeObject(employee1);
                    }
                    fileOutputStream.close();
                    break;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(userFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (User user : userService.selectAll()) {
                objectOutputStream.writeObject(user);
            }
            fileOutputStream.close();
            App.setRoot("login", 360, 650);
        }
    }

    public boolean checkIfFieldIsEmpty() {
        return passwordTextField.getText().isBlank() || userTextField.getText().isBlank() || emailTextField.getText().isBlank();
    }

    public void goBackToLoginPage() throws IOException {
        App.setRoot("login", 360, 650);
    }
}

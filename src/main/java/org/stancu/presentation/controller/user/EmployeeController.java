package org.stancu.presentation.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.stancu.App;
import org.stancu.service.user.EmployeeService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {


    private EmployeeService employeeService;

    @FXML
    public TextArea notificationArea;

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
        notificationArea.setEditable(false);
        setEmployeeService(EmployeeService.getInstance());
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void showNotifications() {
        for (String notification : employeeService.getNotifications()) {
            notificationArea.appendText(employeeService.getNotifications() + "\n");
        }
    }

    public void logout() throws IOException {
        App.setRoot("login",360, 650);
    }
}

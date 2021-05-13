package org.stancu.service;

import org.stancu.model.userModel.Administrator;
import org.stancu.model.userModel.Client;
import org.stancu.model.userModel.Employee;
import org.stancu.model.userModel.User;
import org.stancu.service.user.AdministratorService;
import org.stancu.service.user.ClientService;
import org.stancu.service.user.EmployeeService;
import org.stancu.service.user.UserService;

import java.util.List;

public class LoginService {

    public static LoginService loginService = null;
    private static final UserService userService = UserService.getInstance();
    private static final AdministratorService administratorService = AdministratorService.getInstance();
    private static final ClientService clientService = ClientService.getInstance();
    private static final EmployeeService employeeService = EmployeeService.getInstance();
    private int idGrasper;

    public static LoginService getInstance() {
        if (loginService == null) {
            loginService = new LoginService();
        }
        return loginService;
    }

    public String logIn(User formUser) {
        List<User> users = userService.selectAll();
        List<Administrator> admins = administratorService.selectAll();
        List<Client> clients = clientService.selectAll();
        List<Employee> employees = employeeService.selectAll();
        User myUser = userService.findByEmail(formUser.getEmail());
        if (myUser == null) {
            return null;
        }
        formUser.setId(myUser.getId());
        setIdGrasper(myUser.getId());
        for (Administrator admin : admins) {
            if (checkCondition(userService.findById(admin.getUserId()), formUser)) {
                return "admin";
            }
        }
        for (Employee employee : employees) {
            if (checkCondition(userService.findById(employee.getUserId()), formUser)) {
                return "employee";
            }
        }
        for (Client client : clients) {
            if (checkCondition(userService.findById(client.getUserId()), formUser)) {
                return "client";
            }
        }
        return null;
    }

    private static boolean checkCondition(User original, User toEvaluate) {
        return original.getId().equals(toEvaluate.getId()) && original.getUserName().equals(toEvaluate.getUserName()) &&
                original.getPassword().equals(toEvaluate.getPassword()) && original.getEmail().equals(toEvaluate.getEmail());
    }

    public void setIdGrasper(int idGrasper) {
        this.idGrasper = idGrasper;
    }

    public int getIdGrasper() {
        return idGrasper;
    }
}

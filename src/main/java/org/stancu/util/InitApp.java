package org.stancu.util;

import org.stancu.model.MenuItem;
import org.stancu.model.Order;
import org.stancu.model.userModel.Administrator;
import org.stancu.model.userModel.Client;
import org.stancu.model.userModel.Employee;
import org.stancu.model.userModel.User;
import org.stancu.service.DeliveryService;
import org.stancu.service.OrderService;
import org.stancu.service.ProductService;
import org.stancu.service.user.AdministratorService;
import org.stancu.service.user.ClientService;
import org.stancu.service.user.EmployeeService;
import org.stancu.service.user.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InitApp {

    public static void deserializeInitialUsers(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<User> users = new ArrayList<>();
        UserService userService = UserService.getInstance();
        while (true) {
            try {
                users.add((User) objectInputStream.readObject());
            } catch (Exception e) {
                break;
            }
        }
        objectInputStream.close();
        fileInputStream.close();
        for (User user : users) {
            userService.insert(user);
        }
        users.forEach(System.out::println);
    }


    public static void deserializeInitialClients(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<Client> clients = new ArrayList<>();
        ClientService clientService = ClientService.getInstance();
        while (true) {
            try {
                clients.add((Client) objectInputStream.readObject());
            } catch (Exception e) {
                break;
            }
        }
        objectInputStream.close();
        fileInputStream.close();
        for (Client client : clients) {
            clientService.insert(client);
        }
    }


    public static void deserializeInitialEmployees(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<Employee> employees = new ArrayList<>();
        EmployeeService employeeService = EmployeeService.getInstance();
        while (true) {
            try {
                employees.add((Employee) objectInputStream.readObject());
            } catch (Exception e) {
                break;
            }
        }
        objectInputStream.close();
        fileInputStream.close();
        for (Employee employee : employees) {
            employeeService.insert(employee);
        }
    }

    public static void deserializeInitialAdmins(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<Administrator> administrators = new ArrayList<>();
        AdministratorService administratorService = AdministratorService.getInstance();
        while (true) {
            try {
                administrators.add((Administrator) objectInputStream.readObject());
            } catch (Exception e) {
                break;
            }
        }
        objectInputStream.close();
        fileInputStream.close();
        for (Administrator administrator : administrators) {
            administratorService.insert(administrator);
        }
    }

    public static void initProductIds() {

        ProductService productService = ProductService.getInstance();
        List<MenuItem> products = DeliveryService.getItems();
        for (MenuItem baseProduct : products) {
            productService.insert(baseProduct);
        }

        for (int i = 0; i < productService.selectAll().size(); i++) {
            productService.selectAll().get(i).setId(i + 1);
        }
    }

    public static void serializeProductsAfterSomeOperations() throws IOException {
        ProductService productService = ProductService.getInstance();
        List<MenuItem> products = productService.selectAll();
        File f = new File("products.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (MenuItem baseProduct : products) {
            objectOutputStream.writeObject(baseProduct);
        }
        fileOutputStream.close();
    }

    public static void serializeInitialProducts() throws IOException {

        DeliveryService.setInitialItemsFromFile();
        List<MenuItem> products = DeliveryService.getItems();
        File f = new File("products.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (MenuItem baseProduct : products) {
            objectOutputStream.writeObject(baseProduct);
        }
        fileOutputStream.close();
    }

    public static void deserializeInitialOrders(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<Order> orders = new ArrayList<>();
        OrderService orderService = OrderService.getInstance();
        while (true) {
            try {
                orders.add((Order) objectInputStream.readObject());
            } catch (Exception e) {
                break;
            }
        }
        objectInputStream.close();
        fileInputStream.close();
        for (Order order : orders) {
            orderService.insert(order);
        }
        orders.forEach(System.out::println);
    }
}

package org.stancu.presentation.controller.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.stancu.App;
import org.stancu.model.BaseProduct;
import org.stancu.model.Dummy;
import org.stancu.model.MenuItem;
import org.stancu.model.Order;
import org.stancu.model.userModel.Employee;
import org.stancu.service.DeliveryService;
import org.stancu.service.LoginService;
import org.stancu.service.OrderService;
import org.stancu.service.ProductService;
import org.stancu.service.user.EmployeeService;
import org.stancu.util.Formatter;
import org.stancu.util.InitApp;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ClientController implements Initializable {


    private OrderService orderService;
    private EmployeeService employeeService;
    private LoginService loginService;
    private ProductService productService;

    @FXML
    public TableView<MenuItem> tableview;
    @FXML
    public TableColumn<BaseProduct, String> title;
    @FXML
    public TableColumn<BaseProduct, Double> rating;
    @FXML
    public TableColumn<BaseProduct, Integer> calories;
    @FXML
    public TableColumn<BaseProduct, Integer> protein;
    @FXML
    public TableColumn<BaseProduct, Integer> fat;
    @FXML
    public TableColumn<BaseProduct, Integer> sodium;
    @FXML
    public TableColumn<BaseProduct, Integer> price;
    @FXML
    public TextField filterField;
    @FXML
    public ComboBox<String> comboBox;
    @FXML
    public Button finishOrderButton;
    @FXML
    public TextArea orderTextArea;
    @FXML
    public Button billButton;
    private Order dummyOrder = new Order();
    private final ObservableList<MenuItem> dataList = FXCollections.observableArrayList();
    private final ObservableList<String> comboBoxTopics = FXCollections.observableArrayList();
    private final File orderFile = new File("orders.txt");
    private static int isOk = 0;

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

        setOrderService(OrderService.getInstance());
        setLoginService(LoginService.getInstance());
        setEmployeeService(EmployeeService.getInstance());
        setProductService(ProductService.getInstance());
        try {
            constructSearchEngine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructSearchEngine() throws IOException {
        FilteredList<MenuItem> filteredData = new FilteredList<>(dataList, b -> true);
        initTable();
        tableview.setItems(filteredData);
        comboBoxTopics.addAll("title", "rating", "calories", "protein", "fat", "sodium", "price");
        comboBox.setItems(comboBoxTopics);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(product -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String comboBoxValue = comboBox.getValue();
            String lowerCaseFilter = newValue.toLowerCase();
            switch (comboBoxValue) {
                case "title": {
                    return product.getTitle().toLowerCase().contains(lowerCaseFilter);
                }
                case "rating": {
                    return String.valueOf(product.getRating()).toLowerCase().contains(lowerCaseFilter);
                }
                case "calories": {
                    return String.valueOf(product.getCalories()).toLowerCase().contains(lowerCaseFilter);
                }
                case "protein": {
                    return String.valueOf(product.getProtein()).toLowerCase().contains(lowerCaseFilter);
                }
                case "fat": {
                    return String.valueOf(product.getFat()).toLowerCase().contains(lowerCaseFilter);
                }
                case "sodium": {
                    return String.valueOf(product.getSodium()).toLowerCase().contains(lowerCaseFilter);
                }
                case "price": {
                    return String.valueOf(product.getPrice()).toLowerCase().contains(lowerCaseFilter);
                }
                default: {
                    break;
                }
            }
            return false;
        }));
        SortedList<MenuItem> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
        isOk = 1;
        System.out.println(sortedData.size());
    }

    public void initTable() throws IOException {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        protein.setCellValueFactory(new PropertyValueFactory<>("protein"));
        fat.setCellValueFactory(new PropertyValueFactory<>("fat"));
        sodium.setCellValueFactory(new PropertyValueFactory<>("sodium"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        if (isOk == 0) {
            dataList.addAll(DeliveryService.getItems());
            System.out.println(1);
        } else {
            InitApp.serializeProductsAfterSomeOperations();
            dataList.addAll(productService.selectAll());
            System.out.println(2);
        }
    }

    public void addProductToFinalOrder() {
        int id;
        if (orderService.selectAll().size() == 0) {
            id = 0;
        } else id = orderService.getLastId();

        MenuItem baseProduct = tableview.getSelectionModel().getSelectedItem();
        dummyOrder.setId(id + 1);
        dummyOrder.getMenuItems().add(baseProduct);
        Dummy dummyProd = new Dummy(baseProduct.getTitle(), baseProduct.getPrice());
        orderTextArea.appendText("\n");
        orderTextArea.appendText(dummyProd.toString());
    }

    public void calculateOrder() throws IOException {
        int finalSum = 0;
        for (MenuItem menuItem : dummyOrder.getMenuItems()) {
            finalSum += menuItem.getPrice();
        }
        int orderId = dummyOrder.getId();
        orderTextArea.appendText("\n The total price is : " +
                finalSum + "\n" + Formatter.formatDate() + "    " + Formatter.formatTime());
        dummyOrder.setOrderDate(LocalDate.parse(Formatter.formatDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dummyOrder.setOrderTime(LocalTime.parse(Formatter.formatTime(), DateTimeFormatter.ofPattern("HH-mm-ss")));
        dummyOrder.setTotalPrice(finalSum);
        notifyEmployees(orderId);
        sendDataToAdministrator(LocalDate.parse(Formatter.formatDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(Formatter.formatTime(), DateTimeFormatter.ofPattern("HH-mm-ss")), finalSum);
    }

    public void notifyEmployees(int orderId) {
        for (Employee employee : employeeService.selectAll()) {
            employeeService.notifyMe(employee, orderId);
        }
    }

    public void generateBill() throws IOException {
        FileWriter fileWriter = new FileWriter("bill.txt");
        fileWriter.write(orderTextArea.getText());
        orderTextArea.clear();
        fileWriter.close();
    }

    public void sendDataToAdministrator(LocalDate date, LocalTime time, int finalSum) throws IOException {
        int clientId = loginService.getIdGrasper();
        dummyOrder.setClientId(clientId);
        orderService.insert(dummyOrder);
        dummyOrder = new Order();
        FileOutputStream fileOutputStream = new FileOutputStream(orderFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Order order1 : orderService.selectAll().values()) {
            objectOutputStream.writeObject(order1);
        }
        fileOutputStream.close();
    }

    public void logout() throws IOException {
        App.setRoot("login", 360, 650);
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}

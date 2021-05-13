package org.stancu.presentation.controller.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.stancu.App;
import org.stancu.model.BaseProduct;
import org.stancu.model.CompositeProduct;
import org.stancu.model.MenuItem;
import org.stancu.model.Order;
import org.stancu.model.userModel.User;
import org.stancu.service.ProductService;
import org.stancu.service.user.AdministratorService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;

public class AdministratorController implements Initializable {

    @FXML
    public ChoiceBox<String> comboBox;
    @FXML
    public Button addButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button modifyButton;
    @FXML
    public TextField titleField;
    @FXML
    public TextField ratingField;
    @FXML
    public TextField caloriesField;
    @FXML
    public TextField proteinField;
    @FXML
    public TextField sodiumField;
    @FXML
    public TextField fatField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField valueArea;
    @FXML
    public Button generateReportButton;
    @FXML
    public TextArea contentArea;
    @FXML
    public Label errorLabel;
    @FXML
    public Spinner<Integer> idSpinner;
    @FXML
    public CheckBox compositeCheckBoxStart;
    @FXML
    public CheckBox compositeCheckBoxEnd;
    private MenuItem dummyMenuItem;
    private final List<MenuItem> dummyMenuListItem = new ArrayList<>();
    private AdministratorService adminService;
    private ProductService productService;
    private final ObservableList<String> comboBoxTopics = FXCollections.observableArrayList();
    private final File productFile = new File("products.txt");

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

        setAdminService(AdministratorService.getInstance());
        setProductService(ProductService.getInstance());
        idSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000));
        idSpinner.setEditable(true);
        errorLabel.setVisible(false);
        comboBoxTopics.addAll("Time interval of orders",
                "Product ordered more than a specific time",
                "Value of order more than a specific sum",
                "Clients that ordered more than a specific number",
                "Products in a day and number of times ordered");
        comboBox.setItems(comboBoxTopics);
    }

    public void logout() throws IOException {
        App.setRoot("login", 360, 650);
    }

    public void addProduct() throws IOException {
        if (titleField.getText().isBlank() || ratingField.getText().isBlank() || caloriesField.getText().isBlank()
                || proteinField.getText().isBlank() || proteinField.getText().isBlank() || sodiumField.getText().isBlank() ||
                fatField.getText().isBlank() || priceField.getText().isBlank()) {
            errorLabel.setText("Please check for empty fields");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);

            if (compositeCheckBoxStart.isSelected()) {
                dummyMenuItem = new CompositeProduct();
                MenuItem dummy=new BaseProduct(productService.getLastId() + 1, titleField.getText(), Double.valueOf(ratingField.getText()),
                        Integer.valueOf(caloriesField.getText()), Integer.valueOf(proteinField.getText()), Integer.valueOf(sodiumField.getText()),
                        Integer.valueOf(fatField.getText()), Integer.valueOf(priceField.getText()));
                productService.insert(dummy);

                dummyMenuListItem.add(new BaseProduct(productService.getLastId() + 1, titleField.getText(), Double.valueOf(ratingField.getText()),
                        Integer.valueOf(caloriesField.getText()), Integer.valueOf(proteinField.getText()), Integer.valueOf(sodiumField.getText()),
                        Integer.valueOf(fatField.getText()), Integer.valueOf(priceField.getText())));
                dummyMenuItem.getMenuItems().addAll(dummyMenuListItem);
                   System.out.println(dummy);

            } else if (compositeCheckBoxStart.isDisabled() && compositeCheckBoxEnd.isSelected()) {
                productService.insert(dummyMenuItem);
            } else {
                dummyMenuItem = new BaseProduct(titleField.getText(), Double.valueOf(ratingField.getText()),
                        Integer.valueOf(caloriesField.getText()), Integer.valueOf(proteinField.getText()), Integer.valueOf(sodiumField.getText()),
                        Integer.valueOf(fatField.getText()), Integer.valueOf(priceField.getText()));
                productService.insert(dummyMenuItem);
            }

            dummyMenuListItem.clear();

            /*---------------HERE IS SERIALIZATION -----------**/
            FileOutputStream fileOutputStream = new FileOutputStream(productFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (MenuItem product : productService.selectAll()) {
                objectOutputStream.writeObject(product);
            }
            fileOutputStream.close();
        }
    }

    public void deleteProduct() {
        int id = idSpinner.getValue();
        productService.delete(id);
    }

    public void modifyProduct() {
        int id = idSpinner.getValue();
        BaseProduct baseProduct = new BaseProduct(titleField.getText(), Double.valueOf(ratingField.getText()),
                Integer.valueOf(caloriesField.getText()), Integer.valueOf(proteinField.getText()), Integer.valueOf(sodiumField.getText()),
                Integer.valueOf(fatField.getText()), Integer.valueOf(priceField.getText()));
        productService.update(id, baseProduct);
    }

    public void setAdminService(AdministratorService adminService) {
        this.adminService = adminService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void generateReport() {
        String option = comboBox.getValue();
        contentArea.clear();
        switch (option) {
            case "Time interval of orders": {
                String[] period = fetchTimeStamps();
                if (period != null) {
                    LocalTime start = LocalTime.parse(period[0]);
                    LocalTime end = LocalTime.parse(period[1]);
                    List<Order> orders = adminService.reportOfTimeInterval(start, end);
                    for (Order order : orders) {
                        contentArea.appendText(order.toString());
                    }
                }
                break;
            }
            case "Product ordered more than a specific time": {
                int timeLimit = Integer.parseInt(valueArea.getText());
                Map<MenuItem, Integer> frequency = new HashMap<>();
                String dummy = "";
                frequency = adminService.countForProductsInADay(dummy, frequency);
                for (Map.Entry<MenuItem, Integer> entry : frequency.entrySet()) {
                    if (entry.getValue() >= timeLimit) {
                        contentArea.appendText(entry.getKey() + "  " + entry.getValue() + "\n");
                    }
                }
            }
            case "Value of order more than a specific sum": {
                String sum = valueArea.getText();
                if (sum != null) {
                    List<Order> orders = adminService.countForSumGreaterThanNr(Integer.parseInt(sum));
                    for (Order order : orders) {
                        contentArea.appendText("Order  " + order.getId() + " : " + order.getTotalPrice() + "\n");
                    }
                }
                break;
            }
            case "Clients that ordered more than a specific number": {
                String text = valueArea.getText();
                if (text != null) {
                    List<User> clients = adminService.clientsOrderedMoreThanNrAndMoreThanSum(Integer.parseInt(valueArea.getText()));
                    for (User client : clients) {
                        contentArea.appendText(client.toString());
                    }
                }
                break;
            }
            case "Products in a day and number of times ordered": {
                String time = valueArea.getText();
                Map<MenuItem, Integer> frequency = new HashMap<>();
                frequency = adminService.countForProductsInADay(time, frequency);
                if (time != null) {
                    for (Map.Entry<MenuItem, Integer> pair : frequency.entrySet()) {
                        contentArea.appendText(pair.getKey() + "  " + pair.getValue() + "\n");
                    }
                }
                break;
            }
        }
    }

    public String[] fetchTimeStamps() {
        String text = valueArea.getText();
        if (!text.isBlank()) {
            return text.split("-", 2);
        } else return null;
    }
}


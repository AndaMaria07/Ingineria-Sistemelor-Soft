package com.example.shoporderingsystem.controller;
import com.example.shoporderingsystem.HelloApplication;
import com.example.shoporderingsystem.domain.Company;
import com.example.shoporderingsystem.domain.Product;
import com.example.shoporderingsystem.domain.User;
import com.example.shoporderingsystem.utils.UtilMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerViewController {
    double xOffset = 0;
    double yOffset = 0;
    private Controller controller;
    private Stage primaryStage;
    public TextField userTextField, passwordTextField;
    private User logged;
    public TextField name;
    public TextField price;
    public TextField quantity;
    @FXML
    public ChoiceBox<String> picker;
    @FXML
    public TableView<Product> productsTable;
    @FXML
    public TableColumn<Product,String> nameColumn;
    @FXML
    public TableColumn<Product,Double> priceColumn;
    @FXML
    public TableColumn<Product,Integer> quantityColumn;
    private ObservableList<Product> productsModels = FXCollections.observableArrayList();


    public void setController(Controller controller) {
        this.controller = controller;
        initModel();
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productsTable.setItems(productsModels);
    }

    public void initModel(){
        ObservableList<String> listPicker = FXCollections.observableArrayList();
        listPicker.add("Avon");
        listPicker.add("Abc");
        listPicker.add("Tranvis");
        listPicker.add("Dedeman");
        picker.setItems(listPicker);
        List<Product> products = controller.getProducts();
        productsModels.setAll(products);
    }

    public void onAddButtonClick() throws IOException {
        String nameStr = name.getText();
        String priceStr = price.getText();
        String quantityStr = quantity.getText();
        String companyStr = picker.getValue();
        try {
            HelloApplication.getController().addProduct(nameStr, priceStr, quantityStr, companyStr);
            initModel();

        } catch (Exception exc) {
            UtilMethods.showErrorDialog(exc.getMessage());
        }
    }
}

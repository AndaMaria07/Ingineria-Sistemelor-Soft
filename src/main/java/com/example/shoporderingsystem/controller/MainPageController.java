package com.example.shoporderingsystem.controller;

import com.example.shoporderingsystem.HelloApplication;
import com.example.shoporderingsystem.domain.Order;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController  {
    double xOffset = 0;
    double yOffset = 0;
    private Controller controller;
    private Stage primaryStage;
    public TextField userTextField, passwordTextField;
    private User logged;
    public TableView<Product> productsTable;
    public TableColumn<Product,String> nameColumn;
    public TableColumn<Product,String> priceColumn;
    public TableColumn<Product,String> quantityColumn;
    public TableColumn<Product,String> companyColumn;
    private ObservableList<Product> productsModels = FXCollections.observableArrayList();

    @FXML
    private Spinner<Integer> quantity;

    public void setController(Controller controller) {
        this.controller = controller;
        initModel();
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setUser(User logged) {
        this.logged = logged;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productsTable.setItems(productsModels);
    }

    public void initModel(){
        List<Product> products = controller.getProducts();
        productsModels.setAll(products);
    }

    @FXML
    protected void onManageProductsButtonClick() throws Exception {

        if (!logged.getRole().equals("admin"))
            UtilMethods.showErrorDialog("You have no permission!");
        else {
                FXMLLoader profileWindowLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/shoporderingsystem/views/managerView.fxml"));
                Scene profileScene = new Scene(profileWindowLoader.load());
                primaryStage.setScene(profileScene);
                ManagerViewController managerViewController = profileWindowLoader.getController();
                managerViewController.setController(controller);
                managerViewController.setStage(primaryStage);
                managerViewController.setUser(logged);
        }
    }

    @FXML
    protected void addToShoppingCart(){
        if (logged.getRole().equals("admin"))
            UtilMethods.showErrorDialog("You have no permission!");
        else {
            try {
            Product selected =  productsTable.getSelectionModel().getSelectedItem();
            int q = quantity.getValue();
            Product p = new Product(selected.getName(),selected.getPrice(), q);
            controller.addInCart(p);
            controller.updateProduct(selected.getId(),selected.getName(),String.valueOf(selected.getPrice()),String.valueOf(selected.getQuantity() - q));
            initModel();
            } catch (Exception ex) {
                UtilMethods.showErrorDialog(ex.getMessage());
            }
        }
    }

    @FXML
    public void onCartButtonClicked() throws IOException {
        FXMLLoader cartWindowLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/shoporderingsystem/views/cartView.fxml"));
        Scene cartScene = new Scene(cartWindowLoader.load());
        primaryStage.setTitle("Shopping Cart");
        primaryStage.setScene(cartScene);
        CartController cartController = cartWindowLoader.getController();
        cartController.setController(controller);
        cartController.setStage(primaryStage);
        cartController.setUser(logged);
    }


}


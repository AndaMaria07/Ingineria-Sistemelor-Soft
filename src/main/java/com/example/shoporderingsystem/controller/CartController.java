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
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    private Controller controller;
    private Stage primaryStage;
    private User logged;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    public TableView<Product> productsTable;
    public TableColumn<Product,String> nameColumn;
    public TableColumn<Product,String> priceColumn;
    public TableColumn<Product,String> quantityColumn;
    private List<Product> productListForCart = new ArrayList<>();
    @FXML
    Spinner quantity = new Spinner();
    @FXML
    private TextField quantityText;

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
        productsTable.setItems(products);
    }

    public void initModel(){
        products.setAll(controller.getProductsForCart());
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
    public void onHomeButtonClicked() throws IOException {
        FXMLLoader homeWindowLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/shoporderingsystem/views/mainPage.fxml"));
        Scene homeScene = new Scene(homeWindowLoader.load());
        primaryStage.setTitle("Shopping Cart");
        primaryStage.setScene(homeScene);
        MainPageController mainPageController = homeWindowLoader.getController();
        mainPageController.setController(controller);
        mainPageController.setStage(primaryStage);
        mainPageController.setUser(logged);
    }

    @FXML
    public void onViewProductsButtonClick() throws IOException {
        FXMLLoader ordersWindowLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/shoporderingsystem/views/ordersView.fxml"));
        Scene homeScene = new Scene(ordersWindowLoader.load());
        primaryStage.setTitle("Shopping Cart");
        primaryStage.setScene(homeScene);
        OrdersController ordersController = ordersWindowLoader.getController();
        ordersController.setController(controller);
        ordersController.setStage(primaryStage);
        ordersController.setUser(logged);
    }

    @FXML
    public void onPlaceOrderButtonClicked() {
        Order order = new Order(controller.getProductsForCart());
        controller.addOrder(order);
        controller.setProductsForCart(new ArrayList<>());
        initModel();
    }

    @FXML
    public void onUpdateButtonClicked(){
        Product selected =  productsTable.getSelectionModel().getSelectedItem();
        String q = quantityText.getText();
        controller.modifyCart(selected,q);
        initModel();
    }

    @FXML
    public void onDeleteButtonClicked(){
        Product selected = productsTable.getSelectionModel().getSelectedItem();
        controller.deleteFromCart(selected);
        initModel();
    }
}

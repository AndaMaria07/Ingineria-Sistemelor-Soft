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

public class OrdersController {

    private Controller controller;
    private Stage primaryStage;
    private User logged;
    private ObservableList<Order> orders = FXCollections.observableArrayList();
    public TableView<Order> ordersTable;
    public TableColumn<Order,Integer> idColumn;
    public TableColumn<Order,String> productsColumn;

    private List<Order> orderArrayList = new ArrayList<>();

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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("infoOrder"));
        ordersTable.setItems(orders);
    }

    public void initModel(){
        List<Order> orderList = controller.getOrders();
        for(Order order : orderList){
            List<Product> productList = order.getProductList();
            String info = "";
            for(Product p : productList){
                info = info.concat(p.getName() + " x " + p.getQuantity() + "\n");
            }
            order.setInfoOrder(info);
        }
        orders.setAll(orderList);
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

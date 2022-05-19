package com.example.shoporderingsystem.controller;

import com.example.shoporderingsystem.HelloApplication;
import com.example.shoporderingsystem.domain.Product;
import com.example.shoporderingsystem.domain.User;
import com.example.shoporderingsystem.utils.UtilMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
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
    private ObservableList<Product> productsModels;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setUser(User logged) {
        this.logged = logged;
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
        }
    }

    @FXML
    protected void onBuyButtonClicked(){
        if (logged.getRole().equals("admin"))
            UtilMethods.showErrorDialog("You have no permission!");
        else {
            try {

            } catch (Exception ex) {
                UtilMethods.showErrorDialog(ex.getMessage());
            }
        }
    }

}


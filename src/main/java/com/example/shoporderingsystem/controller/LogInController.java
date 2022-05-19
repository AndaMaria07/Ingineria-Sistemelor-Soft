package com.example.shoporderingsystem.controller;

import com.example.shoporderingsystem.domain.User;
import com.example.shoporderingsystem.repository.UserRepositoryInterface;
import com.example.shoporderingsystem.utils.UtilMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    double xOffset = 0;
    double yOffset = 0;

    private Controller controller;
    private Stage primaryStage;
    public TextField userTextField, passwordTextField;
    private User logged;


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    protected void onLogInButtonClick() throws Exception {

        String userName = userTextField.getText();
        String password = passwordTextField.getText();
        try{
            User user = controller.findOneByNameAndPassword(userName,password);
            FXMLLoader profileWindowLoader = new FXMLLoader(getClass().getResource("/com/example/shoporderingsystem/views/mainPage.fxml"));
            Scene profileScene = new Scene(profileWindowLoader.load());
            profileScene.setFill(Color.TRANSPARENT);
            profileScene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            profileScene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            primaryStage.setTitle("Trips");
            primaryStage.setScene(profileScene);
            MainPageController mainPageController = profileWindowLoader.getController();
            mainPageController.setUser(user);
            mainPageController.setController(controller);
            mainPageController.setStage(primaryStage);
        }
        catch (Exception ex){
            UtilMethods.showErrorDialog(ex.getMessage());
        }

    }
}

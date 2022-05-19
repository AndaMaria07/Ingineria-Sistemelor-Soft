package com.example.shoporderingsystem.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UtilMethods {
    public static void showErrorDialog(String errorMessage){
        Stage errorStage=new Stage();
        Label errorLabel = new Label(errorMessage);
        VBox layout = new VBox(errorLabel);
        layout.setAlignment(Pos.CENTER);
        Scene invalidInformationErrorScene = new Scene(layout, 400, 50);
        errorStage.setTitle("Error");
        errorStage.setScene(invalidInformationErrorScene);
        errorStage.show();
    }
}


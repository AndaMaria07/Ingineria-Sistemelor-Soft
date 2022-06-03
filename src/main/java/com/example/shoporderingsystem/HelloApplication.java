package com.example.shoporderingsystem;
import com.example.shoporderingsystem.controller.Controller;
import com.example.shoporderingsystem.controller.LogInController;
import com.example.shoporderingsystem.repository.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {

    private static SessionFactory sessionFactory;
    private static Controller controller;
    Scene logInScene;
    private double xOffset = 0;
    private double yOffset = 0;

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        HelloApplication.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        initialize();
        Properties properties = new Properties();
        try {
            properties.load(HelloApplication.class.getResourceAsStream("/app.properties"));
            System.out.println("properties set. ");
            properties.list(System.out);
        } catch (IOException var10) {
            System.err.println("Cannot find properties " + var10);
            return;
        }

        UserRepositoryInterface employeeRepositoryInterface = new UserRepository(sessionFactory);
        ProductRepositoryInterface productRepositoryInterface = new ProductRepository(sessionFactory);
        OrderRepositoryInterface orderRepositoryInterface = new OrderRepository("jdbc:sqlite:D:/Facultate/Anul 2/Sem2/ISS/lab/identifier.sqlite");
        setController(new Controller(employeeRepositoryInterface,productRepositoryInterface,orderRepositoryInterface));
        FXMLLoader logInWindowLoader = new FXMLLoader(getClass().getResource("/com/example/shoporderingsystem/views/logIn.fxml"));
        logInScene = new Scene(logInWindowLoader.load(), 612, 341);
        logInScene.setFill(Color.TRANSPARENT);
        logInScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        logInScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        primaryStage.setTitle("LogIn");
        primaryStage.setScene(logInScene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        LogInController logInController = logInWindowLoader.getController();
        logInController.setController(controller);
        logInController.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
        close();
    }

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
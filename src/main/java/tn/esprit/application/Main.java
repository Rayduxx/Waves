package tn.esprit.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import tn.esprit.controllers.MainController;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main_layout.fxml"));
        Parent root = fxmlLoader.load();
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/waveslogo.png")));
        MainController mainController = fxmlLoader.getController();
        mainController.setLightTheme();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Waves Studio");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(root.minWidth(-1));
        primaryStage.setMinHeight(root.minHeight(-1) + 50);
        primaryStage.setHeight(root.prefHeight(-1));
        primaryStage.setWidth(root.prefWidth(-1));
        primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 4);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent ->    {
            mainController.exit();
            System.exit(0);
        });

        scene.setOnKeyPressed(ke -> mainController.setKeyPressed(ke.getCode()) );
    }



    public static Stage getPrimaryStage(){
        return Main.primaryStage;
    }

    private void setPrimaryStage(Stage p){
        primaryStage = p;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

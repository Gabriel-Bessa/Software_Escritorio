package Application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Program extends Application {
    
    public static Scene mainScene;
    public static Stage stage;

    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            ScrollPane scrollPane = loader.load();

            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            mainScene = new Scene(scrollPane);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sistema do Escritorio!");
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Scene getMainScene(){
        return mainScene;
    }
    public static Stage getMainStage(){
        return stage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}

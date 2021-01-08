package Application;

import java.awt.AlphaComposite;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Program extends Application {
    // Objeto statico da scene que está sendo exibida na tela no momento
    public static Scene mainScene;
    // Objeto referente a janela principal
    public static Stage stage;

    public void start(Stage primaryStage) { 
        // população do objeto com a janela inicial
        stage = primaryStage;
        try {
            // carrega o FXML contido no aquivo MainView.fxml para o objeto loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            // faz o downcasting do carregamento do conteudo do objeto loader
            ScrollPane scrollPane = loader.load();

            // Atributos para fazer a tela ser to tamanho da view
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            // Atualização da scene principal com a criação de uma nova com base no objeto scrollpane
            mainScene = new Scene(scrollPane);
            // Coloca a scene na janela principal
            primaryStage.setScene(mainScene);
            // Ao executar ela começa maximizada
            primaryStage.setMaximized(true);
            // Gera um titulo da janela
            primaryStage.setTitle("Sistema do Escritorio!");
            // mostra a janela para o usuario 
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

package Application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Program extends Application {
    // Objeto statico da scene que está sendo exibida na tela no momento
    public static Scene mainScene;
    // Objeto referente a janela principal
    public static Stage stage;
    // Imagem de icone global
    public static Image icon;
    

    public void start(Stage primaryStage) { 
        // população do objeto com a janela inicial
        stage = primaryStage;
        icon = new Image("file:///" +System.getProperty("user.dir")+"\\img\\logo.png");
        
        try {
            // carrega o FXML contido no aquivo MainView.fxml para o objeto loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            // faz o downcasting do carregamento do conteudo do objeto loader
            ScrollPane scrollPane = loader.load();

            // Atributos para fazer a tela ser to tamanho da view
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            // Adicona icone
            stage.getIcons().add(icon);
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

package gui;

import static Application.Program.getMainScene;
import static Application.Program.stage;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import gui.util.Limitante;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    private ClienteService service = new ClienteService();

    private ObservableList<Cliente> obsList;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnLimpar;

    @FXML
    private TableView<Cliente> tableViewPesquisa;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, List<Processo>> tableColumnProcessos;

    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemProcessos;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("onMenuItemSellerAction");
    }

    @FXML
    public void onBtnPesquisarAction() {
        if (txtPesquisa.getText().trim().equals("")) {
            Alert.showAlert("Pesquisa", "Valor inválido!", "Tente com nome válidos!!!", AlertType.ERROR);
        } else {
            iniciarNodos();
            updateTableView();
        }
    }

    @FXML
    public void onMenuItemProcessos() {
        loadView("/gui/ProcessosLista.fxml", (ProcessosListaController controller) -> {
            controller.setProcessoService(new ProcessoService());
            controller.updateTableView();
        }, "");
    }

    @FXML
    public void onMenuItemPesquisaRapidaAction() {
        loadView("/gui/MainView.fxml", x -> {
        }, "Main");
    }

    @FXML
    public void teste() {
        Alert.showAlert("Teste", "Teste", "Teste", AlertType.CONFIRMATION);

    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml", x -> {
        }, "");
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service está Nulo!");
        }
        List<Cliente> list = service.findByPseudo(txtPesquisa.getText());
        if (list.size() == 0) {
            Alert.showAlert("Pesquisa", "Cliente não encontrado", txtPesquisa.getText() + " não encontrado!", AlertType.ERROR);
        } else {
            obsList = FXCollections.observableArrayList(list);
            tableViewPesquisa.setItems(obsList);
        }

    }

    public void limparTableview() {
        List<Cliente> s = tableViewPesquisa.getItems();
        if (s.size() > 0) {
            obsList.removeAll(s);
            txtPesquisa.setText("");
        } else {
            txtPesquisa.setText("");
        }
    }

    private void iniciarNodos() {

        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnProcessos.setCellValueFactory(new PropertyValueFactory<>("processos"));

        Stage stage = (Stage) getMainScene().getWindow();
        tableViewPesquisa.prefHeightProperty().bind(stage.heightProperty());
    }

    /* private synchronized <T> void loadHome(String path, Consumer<T> acaoDeInicializacao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            ScrollPane scrollPane = loader.load();

            Scene mainScene = getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            mainVBox.getChildren().clear();
            mainVBox.getChildren().addAll(scrollPane.getContent());

            T controller = loader.getController();
            acaoDeInicializacao.accept(controller);
        } catch (IOException ex) {
            ex.printStackTrace();
            Alert.showAlert("IO Exception", "Erro para carregar a Página", "ERRO TA AKI", AlertType.ERROR);
        }
    }*/
    private synchronized <T> void loadView(String path, Consumer<T> acaoDeInicializacao, String s) {
        if (s == "Main") {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                ScrollPane scrollPane = loader.load();
                
                VBox main = (VBox) scrollPane.getContent();
                main.getChildren().remove(0);
                
                Scene mainScene = getMainScene();
                VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

                
                Node mainMenu = mainVBox.getChildren().get(0);
                mainVBox.getChildren().clear();
                mainVBox.getChildren().add(mainMenu);
                mainVBox.getChildren().addAll((VBox) (main));

                T controller = loader.getController();
                acaoDeInicializacao.accept(controller);
            } catch (IOException ex) {
                ex.printStackTrace();
                Alert.showAlert("IO Exception", "Erro para carregar a Página", "ERRO TA AKI", AlertType.ERROR);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                VBox newVBox = loader.load();

                Scene mainScene = getMainScene();
                VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

                Node mainMenu = mainVBox.getChildren().get(0);

                mainVBox.getChildren().clear();
                mainVBox.getChildren().add(mainMenu);
                mainVBox.getChildren().addAll(newVBox.getChildren());

                T controller = loader.getController();
                acaoDeInicializacao.accept(controller);

            } catch (IOException ex) {

                ex.printStackTrace();
                Alert.showAlert("IO Exception", "Erro para carregar a Página", ex.getMessage(), AlertType.ERROR);

            }
        }
    }

}

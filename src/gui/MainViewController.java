package gui;

import static Application.Program.getMainScene;
import static Application.Program.getMainStage;
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
        iniciarNodos();
        updateTableView();
    }

    @FXML
    public void onMenuItemProcessos() {
        loadView("/gui/ProcessosLista.fxml", (ProcessosListaController controller) -> {
            controller.setProcessoService(new ProcessoService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemPesquisaRapidaAction() {
        Alert.showAlert("TESTE", "TESTE", "TESTE", AlertType.CONFIRMATION);
    }

    @FXML
    public void teste() {
        Alert.showAlert("Teste", "Teste", "Teste", AlertType.CONFIRMATION);
        
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml", x -> {
        });
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service está Nulo!");
        }
        List<Cliente> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPesquisa.setItems(obsList);
    }

    private void iniciarNodos() {
        Limitante.setTextFieldInteger(txtPesquisa);

        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnProcessos.setCellValueFactory(new PropertyValueFactory<>("processos"));

        Stage stage = (Stage) getMainScene().getWindow();
        tableViewPesquisa.prefHeightProperty().bind(stage.heightProperty());
    }

    private synchronized <T> void loadView(String path, Consumer<T> acaoDeInicializacao) {
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
            ex.getMessage();
            ex.printStackTrace();

            Alert.showAlert("IO Exception", "Erro para carregar a Página", "ERRO TA AKI", AlertType.ERROR);
        }
    }

}
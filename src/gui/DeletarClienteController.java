package gui;

import Application.Program;
import static Application.Program.icon;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import static gui.util.Utils.stageAtual;
import static gui.MainViewController.serviceCliente;
import static gui.MainViewController.serviceProcesso;
import gui.util.Utils;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeletarClienteController implements Initializable {

    private ProcessoService service = serviceProcesso;

    private ClienteService serviceC = serviceCliente;

    @FXML
    private Button btnPesquisaCliente;

    @FXML
    private Button btnNewCliente;
    
    @FXML
    private Button btnAtualizarTabela;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private TableView<Cliente> tableviewCliente;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnId;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, String> tableColumnObservacoes;
    
    @FXML
    private TableColumn<Cliente, Cliente> tableColumnEDIT;

    @FXML
    private TableColumn<Cliente, List<Processo>> tableColumnProcesso;

    private Cliente clientePesquisa;

    private ObservableList<Cliente> obsList;

    @FXML
    public void onbtnIrParaOPjeTRTAction() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://pje.trt3.jus.br/primeirograu/login.seam"));
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onbtnIrParaOPjeTJMGAction() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://pje.tjmg.jus.br/pje/login.seam"));
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onBtnPesquisarCliente() {
        String nome = txtPesquisa.getText();
        List<Cliente> listac = serviceC.findByPseudo(nome);
        if (listac.size() == 0) {
            Alert.showAlert("Cliente", "Pesquisa", "Nenhum cliente encontrado com o nome de: " + nome, javafx.scene.control.Alert.AlertType.ERROR);
        } else {
            obsList = FXCollections.observableArrayList(listac);
            tableviewCliente.setItems(obsList);
        }
    }

    @FXML
    public void onBtnPesquisarAction() {
        List<Cliente> list = serviceC.findByPseudo(clientePesquisa.getNome());
        if (list.size() == 0) {
            Alert.showAlert("Consulta", "Consulta", "Erro na cunsulta!", javafx.scene.control.Alert.AlertType.ERROR);
        } else {
            Stage novo = new Stage();
            novo.getIcons().add(icon);
            novo.setTitle("Cliente");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CarregarCliente.fxml"));
                loader.setController(new CarregarClienteController(clientePesquisa));
                VBox vbox = loader.load();

                novo.setScene(new Scene(vbox));
                novo.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onBtnAtualizarTableAction(){
        updateTableView();
        Alert.showAlert("Tabela Atualizada!", "Atualização feita!", "Agora todos os conteudos estão atualizados!", javafx.scene.control.Alert.AlertType.CONFIRMATION);
    }
    
    @FXML
    public void onSelecaoDeLinha() {
        clientePesquisa = tableviewCliente.getSelectionModel().getSelectedItem();
        btnPesquisaCliente.setText("Visualizar: " + clientePesquisa.getNome());
    }

    @FXML
    public void onBtnNewAction(ActionEvent event) {
        Stage parentStage = stageAtual(event);
        createDialogForm("/gui/FormCliente.fxml", parentStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarNodos();
    }

    public void setProcessoService(ProcessoService service) {
        this.service = service;
    }

    public void setServiceC(ClienteService serviceC) {
        this.serviceC = serviceC;
    }

    private void iniciarNodos() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));
        tableColumnProcesso.setCellValueFactory(new PropertyValueFactory<>("processos"));
        Stage stage = (Stage) Program.getMainScene().getWindow();
        tableviewCliente.prefHeightProperty().bind(stage.heightProperty());
        updateTableView();
    }

    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Cliente, Cliente>() {
            private final Button button = new Button("Deletar");

            @Override
            protected void updateItem(Cliente obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> createDialogForm(
                                obj, "/gui/DeletarClienteForm.fxml", Utils.stageAtual(event)));
            }
        });
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service está Nulo!");
        }
        List<Cliente> list = serviceC.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableviewCliente.setItems(obsList);
        
        initEditButtons();
    }

    private void createDialogForm(Cliente obj, String path, Stage parentStage) {
        DeletarClienteFormController controller = new DeletarClienteFormController(obj);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));            
            loader.setController(controller);
            Pane pane = loader.load();

            Stage modalStage = new Stage();
            modalStage.getIcons().add(icon);
            modalStage.setTitle("Entre com os dados do departamento!");
            modalStage.setScene(new Scene(pane));
            modalStage.setResizable(true);
            modalStage.initOwner(parentStage);
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert.showAlert("IO Exception", "ERROR", e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
    }
    private void createDialogForm(String path, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();

            Stage modalStage = new Stage();
            modalStage.getIcons().add(icon);
            modalStage.setTitle("Entre com os dados do departamento!");
            modalStage.setScene(new Scene(pane));
            modalStage.setResizable(true);
            modalStage.initOwner(parentStage);
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert.showAlert("IO Exception", "ERROR", e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
    }
}

package gui;

import Application.Program;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.AreaService;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import static gui.util.Utils.stageAtual;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProcessosListaController implements Initializable {

    private ProcessoService serviceProcesso;
    private ClienteService serviceC;

    public Processo p;

    private static Cliente pesquisa;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnPesquisa;

    @FXML
    private Button btnTeste;

    @FXML
    private Button btnIrParaOPje;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private TableView<Processo> tableviewProcessos;

    @FXML
    private TableColumn<Processo, Integer> tableColumnId;

    @FXML
    private TableColumn<Processo, Integer> tableColumnNum;

    @FXML
    private TableColumn<Processo, String> tableColumnCausa;

    @FXML
    private TableColumn<Processo, String> tableColumnNomeCliente;

    @FXML
    private Button btnNew;

    private ObservableList<Processo> obsList;

    @FXML
    public void onbtnIrParaOPjeAction() {

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
    public void visualizarProcesso() {
        this.p = tableviewProcessos.getSelectionModel().getSelectedItem();
        this.pesquisa = serviceC.findByClienteById(p.getId_cliente());
        btnPesquisa.setText("Pesquisar: " + p.getNomeCliente());
    }

    @FXML
    public void onBtnPesquisarAction(ActionEvent event) {
        Stage novo = new Stage();
        novo.setTitle("Cliente");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CarregarCliente.fxml"));
            loader.setController(new CarregarClienteController(pesquisa));
            VBox vbox = loader.load();

            novo.setScene(new Scene(vbox));
            novo.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void teste() {
        Processo p = tableviewProcessos.getSelectionModel().getSelectedItem();
        this.pesquisa = serviceC.findByClienteById(p.getId_cliente());
    }

    @FXML
    public void onBtnNewAction(ActionEvent event) {
        Stage parentStage = stageAtual(event);
        createDialogForm("/gui/FormDepartamento.fxml", parentStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarNodos();
    }

    public void setProcessoService(ProcessoService service) {
        this.serviceProcesso = service;
    }

    public void setServiceC(ClienteService serviceC) {
        this.serviceC = serviceC;
    }

    private void iniciarNodos() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        tableColumnCausa.setCellValueFactory(new PropertyValueFactory<>("causa"));
        tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        Stage stage = (Stage) Program.getMainScene().getWindow();
        tableviewProcessos.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView() {
        if (serviceProcesso == null) {
            throw new IllegalStateException("Service está Nulo!");
        }
        serviceProcesso.setService(serviceC);
        List<Processo> list = serviceProcesso.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableviewProcessos.setItems(obsList);
    }

    private void createDialogForm(String path, Stage parentStage) {
        FormDepartamentoController controller = new FormDepartamentoController();
        controller.setService(new AreaService());
        controller.setServiceCliente(new ClienteService());
        controller.setServiceProcesso(new ProcessoService());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            loader.setController(controller);
            Pane pane = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Entre com os dados do requisitados!");
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

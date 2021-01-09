package gui;

import Application.Program;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import gui.util.Limitante;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteListController implements Initializable{

    private Cliente c;

    public ClienteListController() {
    }
    
    public void setC(Cliente c) {
        this.c = c;
    }
    
    private ProcessoService service;
    
    private ClienteService serviceC;
    
    @FXML
    private Button btnPesquisaProcessos;
    
    @FXML
    private Button btnNewCliente;
    
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
    private TableColumn<Cliente, List<Processo>> tableColumnProcesso;
    
    
    
    private ObservableList<Cliente> obsList; 
    
    @FXML
    public void onBtnNewAction(ActionEvent event){
        Stage parentStage = stageAtual(event);
        createDialogForm("/gui/FormCliente.fxml", parentStage);        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarNodos();
    }

    public void setProcessoService(ProcessoService service){
        this.service = service;
    }

    public void setServiceC(ClienteService serviceC) {
        this.serviceC = serviceC;
    }
    
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
        
    private void iniciarNodos() {
        Limitante.setTextFieldInteger(txtPesquisa);
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));
        tableColumnProcesso.setCellValueFactory(new PropertyValueFactory<>("processos"));
        Stage stage = (Stage) Program.getMainScene().getWindow();
        tableviewCliente.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(){
        if(service == null){
            throw new IllegalStateException("Service está Nulo!");
        }
        List<Cliente> list = serviceC.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableviewCliente.setItems(obsList);
    }
    
    private void createDialogForm(String path, Stage parentStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();
            
            Stage modalStage = new Stage();
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

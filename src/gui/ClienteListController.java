package gui;

import Application.Program;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import gui.util.Limitante;
import static gui.util.Utils.stageAtual;
import java.io.IOException;
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
    private TableView<Processo> tableviewCliente;
    
    @FXML
    private TableColumn<Processo, Integer> tableColumnId;
        
    @FXML 
    private TableColumn<Processo, String> tableColumnNome;
    
    @FXML
    private TableColumn<Processo, String> tableColumnTelefone;
    
    @FXML
    private TableColumn<Processo, String> tableColumnEndereco;
    @FXML
    private TableColumn<Processo, List<Processo>> tableColumnProcessos;
    
    
    
    private ObservableList<Processo> obsList; 
    
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
    
    
    
        
    private void iniciarNodos() {
        Limitante.setTextFieldInteger(txtPesquisa);
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnProcessos.setCellValueFactory(new PropertyValueFactory<>(""));
        Stage stage = (Stage) Program.getMainScene().getWindow();
        tableviewCliente.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(){
        if(service == null){
            throw new IllegalStateException("Service está Nulo!");
        }
        service.setService(serviceC);
        List<Processo> list = service.findAll();
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

package gui;

import Application.Program;
import Model.entities.Processo;
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

public class ProcessosListaController implements Initializable{

    private ProcessoService service;
    
    @FXML
    private Button btnPesquisa;
    
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
    private TableColumn<Processo, String> tableColumnCliente;
    
    @FXML
    private Button btnNew;
    
    private ObservableList<Processo> obsList; 
    
    @FXML
    public void onBtnNewAction(ActionEvent event){
        Stage parentStage = stageAtual(event);
        createDialogForm("/gui/FormDepartamento.fxml", parentStage);        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarNodos();
    }

    public void setProcessoService(ProcessoService service){
        this.service = service;
    }
    
        
    private void iniciarNodos() {
        Limitante.setTextFieldInteger(txtPesquisa);
        
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomecliente"));
        tableColumnCausa.setCellValueFactory(new PropertyValueFactory<>("causa"));
        Stage stage = (Stage) Program.getMainScene().getWindow();
        tableviewProcessos.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(){
        if(service == null){
            throw new IllegalStateException("Service está Nulo!");
        }
        List<Processo> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableviewProcessos.setItems(obsList);
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
            System.out.println(e.getMessage());
            Alert.showAlert("IO Exception", "ERROR", e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
    }
}

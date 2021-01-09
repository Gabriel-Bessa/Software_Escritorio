package gui;

import Model.entities.Area;
import Model.service.AreaService;
import Model.service.ProcessoService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormClienteController implements Initializable {

    private ObservableList<Area> obsList;
    private AreaService service;
    private ProcessoService serviceProcesso;
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextArea txtObservacoes;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Label labelErrorNome;

    public void setService(AreaService service) {
        this.service = service;
    }

    public void setServiceProcesso(ProcessoService serviceProcesso) {
        this.serviceProcesso = serviceProcesso;
    }
       
    @FXML
    public void btnSalvarOnAction() {
        System.out.println("btnSalvarOnAction");
    }

    @FXML
    public void btnCancelarOnAcation() {
        System.out.println("btnCancelarOnAcation");
    }

    @Override
    public void initialize(URL utl, ResourceBundle rb) {
        iniciarNodes();
    }
    
    public void iniciarNodes() {
        setService(new AreaService());      
        setServiceProcesso(new ProcessoService());
    }

}

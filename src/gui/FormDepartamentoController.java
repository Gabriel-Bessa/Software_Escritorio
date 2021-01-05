package gui;

import Model.entities.Area;
import Model.service.AreaService;
import gui.util.Limitante;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormDepartamentoController implements Initializable {

    private ObservableList<Area> obsList;
    private AreaService service;
    
    @FXML
    private ComboBox<Area> comboBoxAreas;
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtNomeCliente;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Label labelErrorNome;

    public void setService(AreaService service) {
        this.service = service;
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
        
        obsList = FXCollections.observableArrayList(service.findAll());
        comboBoxAreas.setItems(obsList);
        
        Limitante.setTextFieldMaxLength(txtNome, 30);
        
    }
    /*public List<Area> getAreas(){
        
    }*/

}

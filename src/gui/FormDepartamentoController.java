package gui;

import Model.entities.Area;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.AreaService;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import gui.util.Limitante;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormDepartamentoController implements Initializable {

    private ObservableList<Area> obsList;
    private AreaService service;
    private ProcessoService serviceProcesso;
    private ClienteService serviceCliente;

    @FXML
    private ComboBox<Area> comboBoxAreas;

    @FXML
    private TextField txtNum;

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

    public void setServiceProcesso(ProcessoService serviceProcesso) {
        this.serviceProcesso = serviceProcesso;
    }

    public void setServiceCliente(ClienteService serviceCliente) {
        this.serviceCliente = serviceCliente;
    }

    public void onBtnCancelarAction() {
        Stage window = (Stage) btnCancelar.getScene().getWindow();
        window.close();
    }

    @FXML
    public void btnSalvarOnAction() {
        if (txtNum.getText().trim().equals("") || txtNomeCliente.getText().trim().equals("")) {
            Alert.showAlert("Registro", "Erro no registro!", "Preencha os campos para registrar-lo!", javafx.scene.control.Alert.AlertType.ERROR);
        } else {
            Cliente id = serviceCliente.findByNome(txtNomeCliente.getText());
            if (id == null) {
                Alert.showAlert("Cliente Não encontrado", "O Cliente: " + txtNomeCliente.getText() + " não consta na base de dados!", "Tente novamente!", javafx.scene.control.Alert.AlertType.ERROR);
            } else {
                Processo p = new Processo(txtNum.getText(), comboBoxAreas.getSelectionModel().getSelectedItem().getNome(), txtNomeCliente.getText(), serviceCliente.findByNome(txtNomeCliente.getText()).getId());
                serviceProcesso.insert(p);
                Alert.showAlert("Regitrado!", "Cliente Adicionado Com sucesso!", "Adicionado na base de dados!", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                onBtnCancelarAction();
            }
        }
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
        obsList = FXCollections.observableArrayList(service.findAll());
        comboBoxAreas.setItems(obsList);
        List<Area> list = service.findAll();
        comboBoxAreas.getSelectionModel().select(list.get(0));

        Limitante.setTextFieldMaxLength(txtNum, 30);

    }

}

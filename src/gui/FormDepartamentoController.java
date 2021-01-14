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
    private ObservableList<Cliente> obsListCliente;
    private AreaService service;
    private ProcessoService serviceProcesso;
    private ClienteService serviceCliente;

    @FXML
    private ComboBox<Area> comboBoxAreas;

    @FXML
    private ComboBox<Cliente> comboBoxClientes;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labelErrorNomeCliente;

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
    public void teste() {

        txtNomeCliente.focusedProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal == true) {

            } else {
                verificaCliente();
            }

        });

        comboBoxClientes.getSelectionModel().selectedItemProperty().addListener(
                (valorObservado, valorAntigo, valorNovo) -> {
                    txtNomeCliente.setText(comboBoxClientes.getSelectionModel().getSelectedItem().getNome());
                });
    }

    @FXML
    public void atualizar() {
        /*List<Cliente> list = serviceCliente.findByPseudo(txtNomeCliente.getText());
        if (list.size() > 1) {
            txtNomeCliente.setText(comboBoxClientes.getSelectionModel().getSelectedItem().getNome());
        }*/
    }

    private void verificaCliente() {
        if (!txtNomeCliente.getText().equals("")) {
            List<Cliente> list = serviceCliente.findByPseudo(txtNomeCliente.getText());
            if (list.size() > 1) {
                obsListCliente = FXCollections.observableArrayList(list);
                comboBoxClientes.setItems(obsListCliente);
                comboBoxClientes.getSelectionModel().select(list.get(0));
                comboBoxClientes.visibleProperty().set(true);
                txtNomeCliente.setText(comboBoxClientes.getSelectionModel().getSelectedItem().getNome());
            } else if (list.size() == 1) {
                comboBoxClientes.visibleProperty().set(false);
            }
        } else {
            comboBoxClientes.visibleProperty().set(false);
        }
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
                Cliente c = comboBoxClientes.getSelectionModel().getSelectedItem();
                if (c != null) {
                    System.out.println(c.getId());
                    Processo p = new Processo(txtNum.getText(), comboBoxAreas.getSelectionModel().getSelectedItem().getNome(), c.getNome(), c.getId());
                    Processo teste = serviceProcesso.findByNum(txtNum.getText());
                    if (teste == null) {
                        System.out.println(p.toString(""));
                        serviceProcesso.insert(p);
                        Alert.showAlert("Registro de Processo!", "Cliente Adicionado Com sucesso!", "Adicionado na base de dados!", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                        onBtnCancelarAction();
                    } else {
                        Alert.showAlert("Registro de Processo!", "Error", "Numero de processo já cadastrado na base de dados!", javafx.scene.control.Alert.AlertType.ERROR);
                    }
                } else {
                    Processo p = new Processo(txtNum.getText(), comboBoxAreas.getSelectionModel().getSelectedItem().getNome(), txtNomeCliente.getText(), serviceCliente.findByNome(txtNomeCliente.getText()).getId());
                    Processo teste = serviceProcesso.findByNum(txtNum.getText());
                    if (teste == null) {
                        serviceProcesso.insert(p);
                        Alert.showAlert("Registro de Processo!", "Cliente Adicionado Com sucesso!", "Adicionado na base de dados!", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                        onBtnCancelarAction();
                    } else {
                        Alert.showAlert("Registro de Processo!", "Error", "Numero de processo já cadastrado na base de dados!", javafx.scene.control.Alert.AlertType.ERROR);
                    }
                }
            }
        }
    }

    @FXML
    public void btnCancelarOnAcation() {
        System.out.println("btnCancelarOnAcation");
    }

    @Override
    public void initialize(URL utl, ResourceBundle rb
    ) {
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

package gui;

import static gui.MainViewController.serviceCliente;
import gui.util.Alert;
import static gui.MainViewController.serviceProcesso;
import Model.entities.Area;
import Model.entities.Cliente;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormClienteController implements Initializable {

    private ObservableList<Area> obsList;
    private ProcessoService serviceP = serviceProcesso;
    private ClienteService serviceC = serviceCliente;

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

    @FXML
    public void btnSalvarOnAction() {
        Stage view = (Stage) txtTelefone.getScene().getWindow();

        if (txtTelefone.getText().equals("") || txtNome.getText().equals("") || txtEndereco.getText().equals("")) {
            Alert.showAlert("Criar Cliente!", "Error", "Preencha os campos antes de enviar!", javafx.scene.control.Alert.AlertType.ERROR);
        } else {
            String telefone = txtTelefone.getText();
            String nome = txtNome.getText();
            String endereco = txtEndereco.getText();
            String observacoes = txtObservacoes.getText();
            List<Cliente> c = serviceC.findByPseudo(txtNome.getText());

            if (c.size() == 0) {
                Cliente cliente = new Cliente(nome, telefone, endereco, observacoes);
                Alert.showAlert("Criar Cliente!", "Sucesso!", "Cliente inserido na base de dados", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                serviceC.insert(cliente);
                view.close();
            } else {
                for (Cliente cliente : c) {
                    if (!txtNome.getText().equals(cliente.getNome())) {
                        Cliente clienteT = new Cliente(nome, telefone, endereco, observacoes);
                        serviceC.insert(cliente);
                        Alert.showAlert("Criar Cliente!", "Sucesso!", "Cliente inserido na base de dados", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                        view.close();
                    } else {
                        if (!telefone.equals(cliente.getTelefone())) {
                            Cliente clienteT = new Cliente(nome, telefone, endereco, observacoes);
                            serviceC.insert(cliente);
                            Alert.showAlert("Criar Cliente!", "Sucesso!", "Cliente inserido na base de dados", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                            view.close();
                        } else {
                            if (!endereco.equals(cliente.getEndereco())) {
                                Cliente clienteE = new Cliente(nome, telefone, endereco, observacoes);
                                serviceC.insert(cliente);
                                Alert.showAlert("Criar Cliente!", "Sucesso!", "Cliente inserido na base de dados", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                                view.close();
                            } else {
                                Alert.showAlert("Criar Cliente", "Error", "Cliente já está no Banco de Dados", javafx.scene.control.Alert.AlertType.ERROR);
                            }
                        }
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
    public void initialize(URL utl, ResourceBundle rb) {
        iniciarNodes();
    }

    public void iniciarNodes() {
    }

}

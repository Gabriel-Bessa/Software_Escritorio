package gui;

import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import static gui.MainViewController.serviceCliente;
import static gui.MainViewController.serviceProcesso;
import gui.util.Alert;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeletarClienteFormController implements Initializable {

    private ClienteService serviceC;
    private ProcessoService serviceP;

    public DeletarClienteFormController(Cliente c) {
        this.c = c;
        this.serviceC = serviceCliente;
        this.serviceP = serviceProcesso;
    }

    @FXML
    private Text txtTitulo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextArea txtObservacoes;

    @FXML
    private TextArea txtProcessos;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnCancelar;

    private Cliente c;

    private Processo p;

    @FXML
    public void onBtnExluirAction() {
        if (!c.getProcessos().isEmpty()) {
            Alert.showAlert("Deletar Clientes", "Error", "Exclua os Processos antes de excluir os clientes", javafx.scene.control.Alert.AlertType.ERROR);
        } else {
            serviceC.deletar(c);
            Alert.showAlert("Deletar Cliente", "Sucesso!", "Dados deletados com sucesso!", javafx.scene.control.Alert.AlertType.CONFIRMATION);
            Stage now = (Stage) txtEndereco.getScene().getWindow();
            now.close();
        }
    }

    @FXML
    public void onBtnCancelarAction() {
        Stage now = (Stage) btnCancelar.getScene().getWindow();
        now.close();
    }

    public void setP(Processo p) {
        this.p = p;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public Cliente getC() {
        return c;
    }

    public Processo getP() {
        return p;
    }

    public void preencherDados() {
        txtTitulo.setText("DADOS DO CLIENTE: " + c.getNome());
        txtNome.setText(c.getNome());
        txtEndereco.setText(c.getEndereco());
        txtObservacoes.setText(c.getObservacoes());
        txtTelefone.setText(c.getTelefone());
        List<Processo> list = serviceProcesso.findByClientId(c.getId());
        String numeros = "";
        for (Processo processo : list) {
            numeros = "" + processo.getNum() + "  |  ";
        }
        txtProcessos.setText(numeros);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherDados();
    }
}

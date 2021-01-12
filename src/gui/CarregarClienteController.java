package gui;

import Model.entities.Cliente;
import Model.entities.Processo;
import static gui.MainViewController.serviceProcesso;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CarregarClienteController implements Initializable {

    public CarregarClienteController(Cliente c) {
        this.c = c;
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
    private Button btnIrPjeTRT;

    @FXML
    private Button btnIrPjeTJMG;

    @FXML
    private void onBtnIrPjeTRTAction() {
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
    private void onBtnIrPjeTJMGAction() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://pje.tjmg.jus.br/pje/login.seam"));
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    private Cliente c;

    private Processo p;

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

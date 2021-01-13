/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.MainViewController.serviceCliente;
import static gui.MainViewController.serviceProcesso;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class DeletarProcessoFormController implements Initializable {

    public DeletarProcessoFormController(Processo p) {
        this.p = p;
        this.serviceC = serviceCliente;
        this.serviceP = serviceProcesso;
    }

    private Processo p;
    private ProcessoService serviceP;
    private ClienteService serviceC;
    
    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private TextField txtNum;
    
    @FXML
    private TextField txtNomeCliente;
    
    @FXML
    private TextField txtArea;
    
    @FXML
    private TextField txtConfirm;
    
    @FXML
    private Label txtConfirmLabel;
    
    @FXML
    private void onBtnDeletarAction(){
        if(txtConfirm.getText().equals("")){
            txtConfirm.visibleProperty().set(true);
            txtConfirmLabel.visibleProperty().set(true);
        }else {
            if(txtConfirm.getText().toUpperCase().equals("SIM")){
                serviceP.Deletar(p);
                Alert.showAlert("Deletar Processo!", "Sucesso!", "Processo excluido com sucesso!", javafx.scene.control.Alert.AlertType.CONFIRMATION);
                obBtnCancelarAction();
            }else {
                Alert.showAlert("Deletar Processo!", "Error", "Digite o valor SIM para confirmar a exclusão!", javafx.scene.control.Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void obBtnCancelarAction(){
        Stage now = (Stage) btnCancelar.getScene().getWindow();
        now.close();
    }
    
    private void iniciarNodos(){
        txtNum.setText(p.getNum());
        txtNomeCliente.setText(p.getNomeCliente());
        txtArea.setText(p.getCausa());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarNodos();
    }    
    
}

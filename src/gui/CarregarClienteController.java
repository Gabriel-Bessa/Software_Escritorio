/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Model.entities.Cliente;
import Model.entities.Processo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    public void preencherDados(){
        txtTitulo.setText("DADOS DO CLIENTE: " + c.getNome());
        txtNome.setText(c.getNome());
        txtEndereco.setText(c.getEndereco());
        txtObservacoes.setText(c.getObservacoes());
        txtTelefone.setText(c.getTelefone());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherDados();
        
    }    
    
}

package gui;

import static Application.Program.getMainScene;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import Model.service.ProcessoService;
import gui.util.Alert;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    public static ClienteService serviceCliente = new ClienteService();
    public static ProcessoService serviceProcesso = new ProcessoService();

    private Cliente c;
    private ObservableList<Cliente> obsList;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnPesquisarCliente;

    @FXML
    private Button btnLimpar;

    @FXML
    private TableView<Cliente> tableViewPesquisa;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, List<Processo>> tableColumnProcessos;

    @FXML
    private TableColumn<Cliente, List<Processo>> tableColumnObservacoes;

    @FXML
    private MenuItem menuItemRegistroCliente;

    @FXML
    private MenuItem menuItemRegistroProcessos;

    @FXML
    private MenuItem menuItemAtualizarCliente;

    @FXML
    private MenuItem menuItemDeletarCliente;

    @FXML
    private MenuItem menuItemDeletarProcessos;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private Button btnArquivoAction;

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml", x -> {
        }, "");
    }

    @FXML
    public void onMenuItemRegistrarCliente() {
        loadView("/gui/ClienteLista.fxml", (ClienteListaController controller) -> {
            controller.updateTableView();
        }, "");
    }

    @FXML
    public void onMenuItemProcessos() {
        loadView("/gui/ProcessosLista.fxml", (ProcessosListaController controller) -> {
            controller.setProcessoService(new ProcessoService());
            controller.setServiceC(new ClienteService());
            controller.updateTableView();
        }, "");
    }

    @FXML
    public void onMenuItemAtualizarClienteAction() {
        loadView("/gui/AtualizarCliente.fxml", x -> {
        }, "");
    }

    @FXML
    public void onbtnArquivoAction() {
        if (serviceProcesso == null) {
            Alert.showAlert("Serviço está nulo!", "Serviço está nulo!", "Serviço está nulo!", AlertType.ERROR);
        }
        List<Processo> listaProcesso = serviceProcesso.findAll();
        String pathProcessos = System.getProperty("user.dir") + "\\src\\drive\\processos.txt";
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(pathProcessos))) {
            for (Processo p : listaProcesso) {
                bw.write(p.toString(""));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Cliente> listaCliente = serviceCliente.findAll();
        String pathCleintes = System.getProperty("user.dir") + "\\src\\drive\\clientes.txt";
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(pathCleintes))) {
            for (Cliente c : listaCliente) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert.showAlert("Arquivos!", "Sucesso!", "Arquivos gerados e upados para a nuvem!", AlertType.CONFIRMATION);
    }

    @FXML
    public void onBtnPesquisarClienteAction() {
        if (c == null) {
            Alert.showAlert("Pesquisa Inválida!", "Cliente Inválido!", "Selecione alguem para ser pesquisado!", AlertType.ERROR);
        } else {
            Stage novo = new Stage();
            novo.setTitle("Cliente");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CarregarCliente.fxml"));
                loader.setController(new CarregarClienteController(c));
                VBox vbox = loader.load();

                novo.setScene(new Scene(vbox));
                novo.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onMenuItemPesquisaRapidaAction() {
        loadView("/gui/MainView.fxml", x -> {
        }, "Main");
    }

    @FXML
    public void onMenuItemClienteAction() {
        System.out.println("onMenuItemSellerAction");
    }

    
    @FXML
    public void onBtnPesquisarAction() {
        if (txtPesquisa.getText().trim().equals("")) {
            Alert.showAlert("Pesquisa", "Valor inválido!", "Tente com nome válidos!!!", AlertType.ERROR);
        } else {
            iniciarNodos();
            updateTableView();
        }
    }
    
    @FXML
    public void visualizarCliente() {
        if (c == null) {
            Alert.showAlert("Pesquisa rápida!", "Error", "Nenhum cliente selecionado!", AlertType.ERROR);
        } else {
            this.c = tableViewPesquisa.getSelectionModel().getSelectedItem();
            btnPesquisarCliente.setText("Visualizar: " + c.getNome());
        }
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        Date data = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        btnArquivoAction.setText("Gerar arquivo:  " + sdf1.format(data));
    }

    public void updateTableView() {
        if (serviceCliente == null) {
            throw new IllegalStateException("Service está Nulo!");
        }
        List<Cliente> list = serviceCliente.findByPseudo(txtPesquisa.getText());
        if (list.size() == 0) {
            Alert.showAlert("Pesquisa", "Cliente não encontrado", txtPesquisa.getText() + " não encontrado!", AlertType.ERROR);
        } else {
            obsList = FXCollections.observableArrayList(list);
            tableViewPesquisa.setItems(obsList);
        }

    }

    public void limparTableview() {
        List<Cliente> s = tableViewPesquisa.getItems();
        if (s.size() > 0) {
            obsList.removeAll(s);
            txtPesquisa.setText("");
        } else {
            txtPesquisa.setText("");
        }
    }

    private void iniciarNodos() {

        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnProcessos.setCellValueFactory(new PropertyValueFactory<>("processos"));
        tableColumnObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));

        Stage stage = (Stage) getMainScene().getWindow();
        tableViewPesquisa.prefHeightProperty().bind(stage.heightProperty());
    }

    private synchronized <T> void loadView(String path, Consumer<T> acaoDeInicializacao, String s) {
        if (s == "Main") {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                ScrollPane scrollPane = loader.load();

                VBox main = (VBox) scrollPane.getContent();
                main.getChildren().remove(0);

                Scene mainScene = getMainScene();
                VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

                Node mainMenu = mainVBox.getChildren().get(0);
                mainVBox.getChildren().clear();
                mainVBox.getChildren().add(mainMenu);
                mainVBox.getChildren().addAll((VBox) (main));

                T controller = loader.getController();
                acaoDeInicializacao.accept(controller);
            } catch (IOException ex) {
                ex.printStackTrace();
                Alert.showAlert("IO Exception", "Erro para carregar a Página", "ERRO TA AKI", AlertType.ERROR);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                VBox newVBox = loader.load();

                Scene mainScene = getMainScene();
                VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

                Node mainMenu = mainVBox.getChildren().get(0);

                mainVBox.getChildren().clear();
                mainVBox.getChildren().add(mainMenu);
                mainVBox.getChildren().addAll(newVBox.getChildren());

                T controller = loader.getController();
                acaoDeInicializacao.accept(controller);

            } catch (IOException ex) {

                ex.printStackTrace();
                Alert.showAlert("IO Exception", "Erro para carregar a Página", ex.getMessage(), AlertType.ERROR);

            }
        }
    }

}

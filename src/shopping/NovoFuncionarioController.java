package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import shopping.modelos.Funcionario;
import shopping.modelos.Loja;
import shopping.modelos.SetorComercial;
import shopping.modelos.SetorFisico;

public class NovoFuncionarioController {

    Connection connection;
    JFXAlert alert;
    final ToggleGroup toggleG = new ToggleGroup();
    @FXML
    private Label erro;
    @FXML
    private JFXTextField nome;
    @FXML
    private JFXTextField cpf;
    @FXML
    private JFXTextField telefone;
    @FXML
    private JFXTextField endereco;
    @FXML
    private JFXTextField salario;
    @FXML
    private JFXRadioButton gerenteRB;
    @FXML
    private JFXRadioButton vendedorRB;
    @FXML
    private JFXRadioButton estoquistaRB;
    @FXML
    private JFXRadioButton atendenteRB;
    @FXML
    private Spinner<Loja> loja_spinner;
    @FXML
    private FlowPane content_pane;
    String loja_cnpj = null; 
    
    public void setConnection(Connection conn, JFXAlert alert) {
        this.connection = conn;
        this.alert = alert;

        atendenteRB.setToggleGroup(toggleG);
        gerenteRB.setToggleGroup(toggleG);
        estoquistaRB.setToggleGroup(toggleG);
        vendedorRB.setToggleGroup(toggleG);

    }
    
    public void setLoja(String cnpj){
        this.loja_cnpj = cnpj;
        loja_spinner.setVisible(false);
        content_pane.getChildren().remove(loja_spinner);
    }
    public void carregarSpinner() {
        ObservableList<Loja> lojas = FXCollections.observableArrayList(
                new Loja.ListarLojas().getLojas(connection));
        SpinnerValueFactory<Loja> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(lojas);
        loja_spinner.setValueFactory(valueFactory);
    }

    @FXML
    private void cadastrar(ActionEvent event) {
        
        Funcionario f = new Funcionario();
        f.setNome(nome.getText());
        f.setCpf(cpf.getText());
        f.setFuncao(((JFXRadioButton)toggleG.getSelectedToggle()).getText());
        f.setSalario(Float.parseFloat(salario.getText()));
        if(loja_cnpj == null)f.setLoja(loja_spinner.getValue());
        else{
            Loja j = new Loja();
            j.setCnpj(loja_cnpj);
            f.setLoja(j);
        }
        f.setEndereço(endereco.getText());
        f.setTelefone(telefone.getText());
        boolean inserida = f.inserir(connection);
        if (inserida) {
            alert.hide();
        } else {
            new Thread() {
                @Override
                public void run() {
                    erro.setVisible(true);
                    try {
                        this.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NovaLojaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    erro.setVisible(false);
                }
            }.start();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        alert.hide();
    }
}

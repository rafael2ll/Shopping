package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class MainController {

    Connection connection;

    @FXML
    private JFXButton nova_loja;
    @FXML
    private JFXButton novo_gerente;
    @FXML
    private JFXButton listar_gerentes;
    @FXML
    private JFXButton listar_lojas;
    @FXML
    private JFXButton gerar_relatorios;
    @FXML
    private JFXButton sair;

    public void setConnection(Connection conn) {
        this.connection = conn;
    }

    @FXML
    private void novaLoja(ActionEvent event) {
        try{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("fxml/nova_loja.fxml")));
        BorderPane root = (BorderPane) loader.load();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(false);
        alert.setWidth(700);
        alert.setHeight(800);
        alert.setContent(root);
        alert.setHideOnEscape(false);
        alert.initModality(Modality.WINDOW_MODAL);
        ((NovaLojaController)loader.getController()).setConnection(connection, alert);
        ((NovaLojaController)loader.getController()).carregarSpinner();
        alert.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void novoGerente(ActionEvent event) {
    }

    @FXML
    private void listarGerentes(ActionEvent event) {
    }

    @FXML
    private void listarLojas(ActionEvent event) {
    }

    @FXML
    private void gerarRelatorios(ActionEvent event) {
    }

    @FXML
    private void Sair(ActionEvent event) {
    }

}

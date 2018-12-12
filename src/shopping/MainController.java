package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import shopping.helpers.GerenteItemCell;
import shopping.helpers.LojaItemCell;
import shopping.modelos.Funcionario;
import shopping.modelos.Loja;

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
    @FXML
    private JFXListView<Loja> lojasList;
    @FXML
    private JFXListView<Funcionario> gerentesList;
    @FXML
    private VBox contentLayout;

    public void setConnection(Connection conn) {
        this.connection = conn;
    }

    @FXML
    private void novaLoja(ActionEvent event) {
        try {
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
            ((NovaLojaController) loader.getController()).setConnection(connection, alert);
            ((NovaLojaController) loader.getController()).carregarSpinner();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    @FXML
    private void novoFuncionario(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader((getClass().getResource("fxml/novo_funcionario.fxml")));
            BorderPane root = (BorderPane) loader.load();
            JFXAlert<Void> alert = new JFXAlert<>(stage);
            alert.setOverlayClose(false);
            alert.setWidth(700);
            alert.setHeight(900);
            alert.setContent(root);
            alert.setHideOnEscape(false);
            alert.initModality(Modality.WINDOW_MODAL);
            ((NovoFuncionarioController) loader.getController()).setConnection(connection, alert);
            ((NovoFuncionarioController) loader.getController()).carregarSpinner();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void listarGerentes(ActionEvent event) {
        if (!gerentesList.isVisible()) {
            gerentesList.setVisible(true);
            contentLayout.getChildren().add(gerentesList);
        }
        if (lojasList.isVisible()) {
            lojasList.setVisible(false);
            contentLayout.getChildren().remove(lojasList);
        }
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Lista de Gerentes");
        ObservableList<Funcionario> observableList = FXCollections.observableArrayList();
        observableList.addAll(new Funcionario.Query(connection).getAllFuncionariosPorFuncao("Gerente"));
        gerentesList.setCellFactory((ListView<Funcionario> listView) -> new GerenteItemCell(connection));
        gerentesList.setItems(observableList);
        gerentesList.refresh();
    }

    @FXML
    private void listarLojas(ActionEvent event) {
        if (!lojasList.isVisible()) {
            lojasList.setVisible(true);
            contentLayout.getChildren().add(lojasList);
        }

        if (gerentesList.isVisible()) {
            gerentesList.setVisible(false);
            contentLayout.getChildren().remove(gerentesList);
        }
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Lista de Lojas");
        ObservableList<Loja> observableList = FXCollections.observableArrayList();
        observableList.addAll(new Loja.ListarLojas().getLojas(connection));
        lojasList.setCellFactory((ListView<Loja> listView) -> new LojaItemCell(connection));
        lojasList.setItems(observableList);
        lojasList.setShowTooltip(false);
        lojasList.refresh();
    }

    @FXML
    private void gerarRelatorios(ActionEvent event) {
    }

    @FXML
    private void Sair(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("fxml/login.fxml")));
        Parent root;

        try {
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setWidth(600);
            stage.setResizable(false);
            stage.setHeight(400);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

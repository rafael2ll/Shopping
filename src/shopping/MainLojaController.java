/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shopping.helpers.FuncionarioItemCell;
import shopping.helpers.InventarioItemCell;
import shopping.helpers.RelatorioVendaItemCell;
import shopping.modelos.Funcionario;
import shopping.modelos.Inventario;
import shopping.modelos.Loja;
import shopping.modelos.RelatorioVenda;

public class MainLojaController {

    Connection connection;
    String loja_cnpj;
    Loja loja;

    @FXML
    private JFXButton inicio;
    @FXML
    private ImageView relatorio;
    @FXML
    private JFXButton sair;

    private JFXListView<List<RelatorioVenda>> listViewRV;
    private JFXListView<Funcionario> listViewFuncionario;
    private JFXListView<Inventario> listViewInventario;
    @FXML
    private VBox topFuncionariosLayout;
    @FXML
    private VBox ultimasVendasLayout;
    @FXML
    private HBox content;
    @FXML
    private Label loja_nome;
    @FXML
    private Label nome;
    @FXML
    private Label localizacao;
    @FXML
    private Label setor_comercial;
    @FXML
    private Label preco_setorfisico;

    public void setConnection(Connection conn, String cnpj) {
        this.loja_cnpj = cnpj;
        this.connection = conn;
        
        loja = new Loja.Query(conn).getLojaByCnpj(cnpj);
        loja_nome.setText(loja.getNome());
        nome.setText("Nome: "+loja.getNome());
        localizacao.setText("Localização: "+ loja.getSetorFisico().formatSetor());
        setor_comercial.setText(loja.getSetorComercial());
        preco_setorfisico.setText("Preço Aluguel: R$"+loja.getSetorFisico().getCusto());
        listViewRV = new JFXListView<>();
        listViewRV.setStyle("-fx-background-color: #f5f5f5;-fx-accent: #EEEEEE;");
        listViewRV.setPrefWidth(1000);

        listViewFuncionario = new JFXListView<>();
        listViewFuncionario.setStyle("-fx-background-color: #f5f5f5;-fx-accent: #EEEEEE;");
        listViewFuncionario.setPrefWidth(1000);

        listViewInventario = new JFXListView<>();
        listViewInventario.setStyle("-fx-background-color: #f5f5f5;-fx-accent: #EEEEEE;");
        listViewInventario.setPrefWidth(1000);
    }

    @FXML
    private void inicio(ActionEvent event) {
        content.getChildren().clear();
        content.getChildren().add(topFuncionariosLayout);
        content.getChildren().add(ultimasVendasLayout);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Inicio");
        ObservableList<Funcionario> observableList = FXCollections.observableArrayList();
        observableList.addAll(new Funcionario.Query(connection).getFuncionariosPorLoja(loja_cnpj));

//        listFuncionarios.setCellFactory((ListView<Loja> listView) -> new LojaItemCell(connection));
//        listFuncionarios.setItems(observableList);
//        listFuncionarios.setShowTooltip(false);
//        lisFuncionarios.refresh();
    }

    @FXML
    private void vender(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader((getClass().getResource("fxml/venda_dialog.fxml")));
            VBox root = (VBox) loader.load();
            JFXAlert<Void> alert = new JFXAlert<>(stage);
            alert.setOverlayClose(false);
            alert.setWidth(350);
            alert.setHeight(350);
            alert.setContent(root);
            alert.setHideOnEscape(false);
            alert.initModality(Modality.WINDOW_MODAL);
            ((VendaDialogContoller) loader.getController()).setConnection(connection, alert, loja_cnpj);
            ((VendaDialogContoller) loader.getController()).carregarAutoCompletes();
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
            ((NovoFuncionarioController) loader.getController()).setLoja(loja_cnpj);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void listarFuncionarios(ActionEvent event) {
        content.getChildren().clear();
        content.getChildren().add(listViewFuncionario);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Lista de Funcionários");
        ObservableList<Funcionario> observableList = FXCollections.observableArrayList();
        observableList.addAll(new Funcionario.Query(connection).getFuncionariosPorLoja(loja_cnpj));

        listViewFuncionario.setCellFactory((ListView<Funcionario> listView) -> new FuncionarioItemCell(connection));
        listViewFuncionario.setItems(observableList);
        listViewFuncionario.setShowTooltip(false);
        listViewFuncionario.refresh();
    }

    @FXML
    private void inventario(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Inventário");
        content.getChildren().clear();
        content.getChildren().add(listViewInventario);
        ObservableList<Inventario> observableList = FXCollections.observableArrayList();
        observableList.addAll(new Inventario.Query(connection).getInventarios_loja(loja_cnpj));

        listViewInventario.setCellFactory((ListView<Inventario> listView) -> new InventarioItemCell(connection));
        listViewInventario.setItems(observableList);
        listViewInventario.setShowTooltip(false);
        listViewInventario.refresh();
    }

    @FXML
    private void novoProduto(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader((getClass().getResource("fxml/requisitar_produto.fxml")));
            VBox root = (VBox) loader.load();
            JFXAlert<Void> alert = new JFXAlert<>(stage);
            alert.setOverlayClose(false);
            alert.setWidth(350);
            alert.setHeight(220);
            alert.setContent(root);
            alert.setHideOnEscape(false);
            alert.initModality(Modality.WINDOW_MODAL);
            ((RequisitarProdutoController) loader.getController()).setConnection(connection, alert, loja_cnpj);

            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gerarRelatorios(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setTitle("Relatório de Vendas Recentes");
        content.getChildren().clear();
        content.getChildren().add(listViewRV);
        ObservableList<List<RelatorioVenda>> observableList = FXCollections.observableArrayList();
        ArrayList<RelatorioVenda> vendas = new RelatorioVenda.Query(connection).getRelarioVendaLoja(loja_cnpj);
        Map<java.sql.Date, List<RelatorioVenda>> vendas_mapeadas = vendas.stream().collect(Collectors.groupingBy(v -> v.getData()));

        observableList.addAll(vendas_mapeadas.values());
        System.out.println(vendas_mapeadas);
        listViewRV.setCellFactory((ListView<List<RelatorioVenda>> listView) -> new RelatorioVendaItemCell(connection));
        listViewRV.setItems(observableList);
        listViewRV.setShowTooltip(false);
        listViewRV.refresh();
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

    void setConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

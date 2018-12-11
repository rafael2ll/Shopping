/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shopping.modelos.RelatorioVenda;

public class RelatorioVendaItemController {

    @FXML
    private VBox root;
    @FXML
    private Label data;
    @FXML
    private VBox contentVBox;
    @FXML
    private Label total;
    
    public RelatorioVendaItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/relatorio_venda_item.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(List<RelatorioVenda> vendas, Connection conn) {
        Float f = vendas.stream().map(v-> v.getQuantidade()*v.getProduto().getPreco()).reduce(0f, (v1,v2)-> v1+v2);
        data.setText(new SimpleDateFormat("dd/MM/yyyy").format(vendas.get(0).getData()));
        vendas.stream().map((v) -> {
            VendaItemController controller = new VendaItemController();
            controller.setInfo(v);
            return controller;
        }).forEachOrdered((controller) -> {
            contentVBox.getChildren().add(controller.getView());
        });
        total.setText("R$"+f);
    }

    public Node getView() {
        return root;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import shopping.modelos.RelatorioVenda;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class VendaItemController{

    @FXML
    private HBox root;
    @FXML
    private Label cliente;
    @FXML
    private Label vendedor;
    @FXML
    private Label produto;
    @FXML
    private Label preco;
    
     public VendaItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/rv_item.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setInfo(RelatorioVenda vend){
        cliente.setText(vend.getCliente().getNome());
        vendedor.setText(vend.getFuncionario().getNome());
        produto.setText(vend.getProduto().getNome()+" - "+ vend.getQuantidade()+"u");
        preco.setText("R$"+vend.getProduto().getPreco()*vend.getQuantidade());
    }

    public Node getView() {
        return root;
    }
}

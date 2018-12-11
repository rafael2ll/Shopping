/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import shopping.modelos.Inventario;
import shopping.modelos.Promocao;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class InventarioItemController {

    
    @FXML
    private HBox root;
    @FXML
    private Label nome;
    @FXML
    private Label quantidade;
    @FXML
    private Label preco;
    @FXML
    private HBox desconto_layout;
    @FXML
    private Label desconto;
    private Connection conn;
    
    public InventarioItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/inventario_item.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InventarioItemController setInfo(Connection c, Inventario inv) {
        this.conn = c;
        try {
            nome.setText(inv.getProduto().getNome());
            preco.setText("R$"+inv.getProduto().getPreco());
            quantidade.setText(inv.getQuantidade()+" em estoque");
            
            new Thread(){
                @Override
                public void run(){
                    Promocao p = Promocao.isInPromocao(c, inv.getLoja().getCnpj(), inv.getProduto().getId());
                    if(p != null){
                        desconto_layout.setVisible(true);
                        desconto.setText("Deconto de R$"+p.getDesconto());
                    }
                }
            }.run();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HBox getView() {
        return root;
    }

}

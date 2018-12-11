/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.textfield.TextFields;
import shopping.modelos.Inventario;
import shopping.modelos.Loja;
import shopping.modelos.Produto;

public class RequisitarProdutoController  {

    @FXML
    private JFXTextField item;
    @FXML
    private JFXTextField quantidade;
    
    String loja_cnpj;
    Connection conn;
    List<Produto> produtoList;
    private JFXAlert alert;
    @FXML
    private Label erro;
    
    public void setConnection(Connection c, JFXAlert alert, String loja_cnpj){
        this.conn = c;
        this.alert = alert;
        this.loja_cnpj = loja_cnpj;
        produtoList = new Produto.Query(conn).getProdutos();
        TextFields.bindAutoCompletion(item, produtoList);
       
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        alert.hide();
    }

    @FXML
    private void pedir(ActionEvent event) {
        Produto p = produtoList.stream().filter(c_ -> c_.toString().equals(item.getText())).findFirst().orElse(null);
        Short quant =  Short.valueOf(quantidade.getText());
        
        if (p == null) {
            showErrorMessage("Selecione um produto valido");
            return;
        }
        
        Loja l = new Loja();
        l.setCnpj(loja_cnpj);
        boolean inserido = new Inventario(quant,l, p).inserir(conn);
        if(inserido){
            alert.hide();
        }else{
            showErrorMessage("A loja ja possui esse produto");
        }
    }
     private void showErrorMessage(String erro_) {
        new Thread() {
            @Override
            public void run() {
                erro.setText(erro_);
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

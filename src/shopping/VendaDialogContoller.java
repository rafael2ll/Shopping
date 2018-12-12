/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.textfield.TextFields;
import shopping.modelos.Cliente;
import shopping.modelos.Funcionario;
import shopping.modelos.Loja;
import shopping.modelos.Produto;
import shopping.modelos.RelatorioVenda;

public class VendaDialogContoller {
    
    @FXML
    private JFXTextField cliente;
    @FXML
    private JFXTextField vendedor;
    @FXML
    private JFXTextField item;
    @FXML
    private JFXTextField quantidade;
    @FXML
    private JFXTextField data;
    
    Connection connection;
    JFXAlert alert;
    List<Funcionario> funcionarioList;
    List<Cliente> clienteList;
    List<Produto> produtoList;
    private String loja_cnpj;
    @FXML
    private Label erro;
    
    public void setConnection(Connection conn, JFXAlert alert, String loja_cnpj) {
        this.connection = conn;
        this.alert = alert;
        this.loja_cnpj = loja_cnpj;
    }
    
    public void carregarAutoCompletes() {
        clienteList = new Cliente.Query(connection).getClientes();
        funcionarioList = new Funcionario.Query(connection).getFuncionariosPorFuncao(loja_cnpj, "Vendedor");
        produtoList = new Produto.Query(connection).getProdutosFromLoja(loja_cnpj);
        
        TextFields.bindAutoCompletion(cliente, clienteList);
        TextFields.bindAutoCompletion(vendedor, funcionarioList);
        TextFields.bindAutoCompletion(item, produtoList);
    }
    
    @FXML
    private void vender(ActionEvent event) {
        Loja j = new Loja();
        j.setCnpj(loja_cnpj);
        java.sql.Date data_;
        String data_text = data.getText();
        Cliente c = clienteList.stream().filter(c_ -> c_.toString().equals(cliente.getText())).findFirst().orElse(null);
        Funcionario f = funcionarioList.stream().filter(c_ -> c_.toString().equals(vendedor.getText())).findFirst().orElse(null);
        Produto p = produtoList.stream().filter(c_ -> c_.toString().equals(item.getText())).findFirst().orElse(null);
        Short quant =  Short.valueOf(quantidade.getText());
        
        if (c == null || f == null || p == null) {
            showErrorMessage("Selecione um cliente, vendedor ou produto valido");
            return;
        }
       try {
            data_ =new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(data_text).getTime());
        } catch (ParseException ex) {
            data_ = java.sql.Date.valueOf(LocalDate.now());
            ex.printStackTrace();
        }
        RelatorioVenda v = new RelatorioVenda(data_, c, f, j,quant, p);
        boolean inserida = v.criar_relatorio(connection);
        if (inserida) {
            alert.hide();
        } else {
            showErrorMessage("Não foi possivel efetuar a venda");
        }
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        alert.hide();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import shopping.modelos.Loja;
import shopping.modelos.SetorComercial;
import shopping.modelos.SetorFisico;

/**
 *
 * @author rafael
 */
public class NovaLojaController {
    Connection connection;
    JFXAlert alert;
    @FXML
    private JFXTextField nome;
    @FXML
    private JFXTextField cnpj;
    @FXML
    private JFXTextField razao_social;
    @FXML
    private Spinner<SetorFisico> spinner_area_fisica;
    @FXML
    private Spinner<SetorComercial> spinner_area_comercial;
    @FXML
    private JFXTextField id_acesso;
    @FXML
    private JFXPasswordField senha_acesso;
    @FXML
    private Label erro;

    
    public void setConnection(Connection conn, JFXAlert alert){
        this.connection = conn;
        this.alert = alert;
    }
    
    public void carregarSpinner(){
        ObservableList<SetorComercial> setores_comerciais = FXCollections.observableArrayList(
                new SetorComercial.Query().getSetoresComerciais(connection));
        
         SpinnerValueFactory<SetorComercial> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(setores_comerciais);
         spinner_area_comercial.setValueFactory(valueFactory);   
         
         ObservableList<SetorFisico> setores_fisicos = FXCollections.observableArrayList(
            new SetorFisico.Query().getSetoresFisicos(connection));
        
         SpinnerValueFactory<SetorFisico> valueFactory2 = new SpinnerValueFactory.ListSpinnerValueFactory<>(setores_fisicos);
         spinner_area_fisica.setValueFactory(valueFactory2);
         
    }
    @FXML
    private void cadastrar(ActionEvent event) {
        String cnpj_, nome_, id_acesso_, acesso_pwd;
        cnpj_ = cnpj.getText();
        nome_ = nome.getText();
        id_acesso_ = id_acesso.getText();
        acesso_pwd = senha_acesso.getText();
        SetorFisico setor_fisico = spinner_area_fisica.getValue();
        SetorComercial setor_comercial = spinner_area_comercial.getValue();
        Loja j = new Loja(cnpj_, nome_, setor_comercial.getNome(), setor_fisico.getId());
        boolean inserida = j.inserir(connection, id_acesso_, acesso_pwd);
        if(inserida)
            alert.hide();
        else{
            new Thread(){
              @Override
              public void run(){
                   erro.setVisible (true);
                  try {
                      this.wait(5000);
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

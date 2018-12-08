/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import shopping.modelos.Funcionario;
import shopping.modelos.Loja;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class GerenteItemController{
    Funcionario f;
    Connection conn;
    @FXML
    private HBox root;
    @FXML
    private Label loja;
    @FXML
    private Label nome;
    @FXML
    private Label cpf;
    
    public GerenteItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/gerente_item.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GerenteItemController setInfo(Connection c, Funcionario f) {
        this.f =f;
        this.conn = c;
        try {
            nome.setText(f.getNome());
            cpf.setText(f.getCpf());
            loja.setText(f.getLoja().getNome());
          
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HBox getView() {
        return root;
    }

}

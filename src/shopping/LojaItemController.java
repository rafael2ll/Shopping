/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import shopping.modelos.Loja;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class LojaItemController {

    @FXML
    private HBox root;
    @FXML
    private Label nome;
    @FXML
    private Label cnpj;
    
    private Loja loja;

    public LojaItemController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loja_item.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Loja l)
    {
        this.loja = l;
        nome.setText(l.getNome());
        cnpj.setText(l.getCnpj());
    }

    public HBox getView(){
        return root;
    }    
    
    @FXML
    private void deleteLoja(ActionEvent event) {
    }

}

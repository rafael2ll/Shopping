/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.helpers;

import java.sql.Connection;
import javafx.scene.control.ListCell;
import shopping.GerenteItemController;
import shopping.modelos.Funcionario;
import shopping.modelos.Loja;

public class GerenteItemCell extends ListCell<Funcionario> {
    Connection c;
    
    public GerenteItemCell(Connection c){
        this.c = c;
    }
    @Override
    public void updateItem(Funcionario f, boolean empty) {
        super.updateItem(f, empty);
        if (f != null) {
            GerenteItemController data = new GerenteItemController();
            data.setInfo(c, f);
            setGraphic(data.getView());
        }
    }
}
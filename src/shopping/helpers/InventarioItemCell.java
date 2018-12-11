/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.helpers;

import java.sql.Connection;
import javafx.scene.control.ListCell;
import shopping.InventarioItemController;
import shopping.LojaItemController;
import shopping.modelos.Inventario;
import shopping.modelos.Loja;

/**
 *
 * @author rafael
 */
public class InventarioItemCell extends ListCell<Inventario> {
    Connection c;
    
    public InventarioItemCell(Connection c){
        this.c = c;
    }
    
    @Override
    public void updateItem(Inventario j, boolean empty) {
        super.updateItem(j, empty);
        if (j != null) {
            InventarioItemController data = new InventarioItemController();
            data.setInfo(c, j);
            setGraphic(data.getView());
        }
    }
}

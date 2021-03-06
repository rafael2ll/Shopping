/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.helpers;

import java.sql.Connection;
import javafx.scene.control.ListCell;
import shopping.LojaItemController;
import shopping.modelos.Loja;

public class LojaItemCell extends ListCell<Loja> {
    Connection c;
    
    public LojaItemCell(Connection c){
        this.c = c;
    }
    @Override
    public void updateItem(Loja j, boolean empty) {
        super.updateItem(j, empty);
        if (j != null) {
            LojaItemController data = new LojaItemController();
            data.setInfo(c, j);
            System.out.println("Att:"+j.getNome());
            setGraphic(data.getView());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.helpers;

import javafx.scene.control.ListCell;
import shopping.LojaItemController;
import shopping.modelos.Loja;

public class LojaItemCell extends ListCell<Loja>
{
    @Override
    public void updateItem(Loja j, boolean empty)
    {
        super.updateItem(j,empty);
        if(j != null)
        {
            LojaItemController data = new LojaItemController();
            data.setInfo(j);
            setGraphic(data.getView());
        }
    }
}

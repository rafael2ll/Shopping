/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.helpers;

import java.sql.Connection;
import java.util.List;
import javafx.scene.control.ListCell;
import shopping.RelatorioVendaItemController;
import shopping.VendaItemController;
import shopping.modelos.RelatorioVenda;

/**
 *
 * @author rafael
 */
public class RelatorioVendaItemCell extends ListCell<List<RelatorioVenda>> {

    Connection c;

    public RelatorioVendaItemCell(Connection c) {
        this.c = c;
    }

    @Override
    public void updateItem(List<RelatorioVenda> j, boolean empty) {
        super.updateItem(j, empty);
        if (j != null) {
            RelatorioVendaItemController data = new RelatorioVendaItemController();
            data.setData(j, c);
            setGraphic(data.getView());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.helpers;

import java.sql.Connection;
import javafx.scene.control.ListCell;
import shopping.FuncionarioItemController;
import shopping.GerenteItemController;
import shopping.modelos.Funcionario;

/**
 *
 * @author rafael
 */
public class FuncionarioItemCell extends ListCell<Funcionario> {
    Connection c;
    
    public FuncionarioItemCell(Connection c){
        this.c = c;
    }
    @Override
    public void updateItem(Funcionario f, boolean empty) {
        super.updateItem(f, empty);
        if (f != null) {
            FuncionarioItemController data = new FuncionarioItemController();
            data.setInfo(c, f);
            setGraphic(data.getView());
        }
    }
}

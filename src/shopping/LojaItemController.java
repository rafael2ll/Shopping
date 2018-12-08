/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shopping.modelos.Loja;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class LojaItemController {

    Connection conn;
    @FXML
    private HBox root;
    @FXML
    private Label nome;
    @FXML
    private Label cnpj;

    private Loja loja;

    public LojaItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/loja_item.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LojaItemController setInfo(Connection c, Loja l) {
        this.loja = l;
        this.conn = c;
        try {
            nome.setText(l.getNome());
            cnpj.setText(l.getCnpj());
            System.out.println(l);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HBox getView() {
        return root;
    }

    @FXML
    private void deleteLoja(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        JFXAlert<Void> alert = new JFXAlert<>(stage);
        alert.setOverlayClose(true);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setBody(new Label("Deseja realmente apagar a loja " + loja.getNome() + "?"));

        JFXButton deletar = new JFXButton("DELETAR");
        deletar.setCancelButton(true);
        deletar.setButtonType(JFXButton.ButtonType.RAISED);
        deletar.setOnAction(e -> {
            loja.remover(conn, loja.getCnpj(), loja.getCnpj());
            Parent p = ((Node) event.getSource()).getParent();
            while (!(p instanceof JFXListView)) {
                p = p.getParent();
            }
            ((JFXListView) p).getItems().remove(loja);
            alert.hide();
        });

        JFXButton cancelar = new JFXButton("CANCELAR");
        cancelar.setButtonType(JFXButton.ButtonType.RAISED);
        cancelar.setDefaultButton(true);
        cancelar.setButtonType(JFXButton.ButtonType.RAISED);
        cancelar.setOnAction(e -> {
            alert.hide();
        });
        layout.setActions(cancelar, deletar);

        alert.setContent(layout);
        alert.initModality(Modality.NONE);
        alert.show();
    }

}

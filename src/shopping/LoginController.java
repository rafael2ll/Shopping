package shopping;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
    private OnConnectionListener connectionListener;
    private final String url = "jdbc:postgresql://localhost/shopping";
 

    @FXML
    private JFXTextField id_gerente;
    @FXML
    private JFXPasswordField senha_gerente;
    @FXML
    private JFXTextField id_usuario;
    @FXML
    private JFXPasswordField senha_usuario;
    
    
    
    public void onConnect(OnConnectionListener c){
        this.connectionListener = c;
    }

    @FXML
    private void gerenteLogin(ActionEvent event) {
        String senha = senha_gerente.getText();
        String id  = id_gerente.getText();
        Connection conn = null;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           
        try {
            conn = DriverManager.getConnection(url, id, senha);
            System.out.println("Connected to the PostgreSQL server successfully.");
            conn.setSchema("shopping");
           
            FXMLLoader loader = new FXMLLoader((getClass().getResource("fxml/Main.fxml")));
	    Parent root = (Parent) loader.load();
            ((MainController)loader.getController()).setConnection(conn);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setWidth(760);
            stage.setResizable(false);
            stage.setHeight(600);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
              JFXDialogLayout layout = new JFXDialogLayout();
                layout.setBody(new Label("Não foi possivel logar :("));
                JFXButton ok = new JFXButton("OK");
                layout.setActions(ok);
                JFXAlert<Void> alert = new JFXAlert<>(stage);
                ok.setOnAction(k-> alert.hide());
                alert.setOverlayClose(true);
                alert.setContent(layout);
                alert.setHideOnEscape(true);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.show();
        }
    }

    @FXML
    private void usuarioLogin(ActionEvent event) {
    }
    
   
    public interface OnConnectionListener{
        public void onConnected(Connection conn);
    }
}

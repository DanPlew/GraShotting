package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InstrukcjaController implements Initializable {

    @FXML
    Text text1, text2, text3, text4, text5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text1.setText("- Poruszanie w Prawo - A");
        text2.setText("- Poruszanie w Lewo - D");
        text3.setText("- Skok - Space");
        text4.setText("- Strzał - L");
        text5.setText("- Wyjście - Escape");

    }

    public void changeSceneButtonBack(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

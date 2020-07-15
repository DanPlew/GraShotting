package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;



public class SampleController {

    public void changeSceneButtonGraj(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GameFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void changeSceneButtonRanking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RankingFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void changeSceneButtonInstrukcja(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InstrukcjaFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void exitFromTheGameButton(){Platform.exit();}
}


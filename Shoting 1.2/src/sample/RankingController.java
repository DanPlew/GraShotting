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

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RankingController implements Initializable {

    @FXML
    Text text1, text2, text3, text4, text5, text6, text7, text8;
    Text[] textTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textTable = new Text[8];
        textTable[0] = text1;
        textTable[1] = text2;
        textTable[2] = text3;
        textTable[3] = text4;
        textTable[4] = text5;
        textTable[5] = text6;
        textTable[6] = text7;
        textTable[7] = text8;

        try{
            String line = "";
            FileReader input = new FileReader(new File("Ranking\\Ranking.txt"));
            BufferedReader bufferedReader = new BufferedReader(input);
            int i = 0;

            while((line = bufferedReader.readLine()) != null){
                textTable[i].setText(line);
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void changeSceneButtonBack(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

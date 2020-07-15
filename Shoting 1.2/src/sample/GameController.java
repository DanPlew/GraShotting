package sample;

import Engine.GameEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    Canvas canvasHP, canvasGamePlay;

    @FXML
    Text textPoints, textLevel;

    @FXML
    Pane pane;

    private GameEngine gameEngine;
    private Thread gameThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameEngine = new GameEngine(canvasGamePlay, canvasHP, textPoints, textLevel, pane);

        canvasGamePlay.setFocusTraversable(true);
        canvasGamePlay.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.W) gameEngine.up = true;
            if(event.getCode() == KeyCode.S) gameEngine.down = true;
            if(event.getCode() == KeyCode.A) gameEngine.left = true;
            if(event.getCode() == KeyCode.D) gameEngine.right = true;
            if(event.getCode() == KeyCode.K) gameEngine.shoting = true;
            if(event.getCode() == KeyCode.SPACE) gameEngine.falling = false;

            // Wyjście z gry do głównego Menu
            if(event.getCode() == KeyCode.ESCAPE) {
                gameEngine.isPlaying = false;
                gameEngine.currentLevel.isPlayingCurrentLevel = false;
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }catch(IOException e){ e.printStackTrace(); }
            }
        });

        canvasGamePlay.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.W) gameEngine.up = false;
            if(event.getCode() == KeyCode.S) gameEngine.down = false;
            if(event.getCode() == KeyCode.A) gameEngine.left = false;
            if(event.getCode() == KeyCode.D) gameEngine.right = false;
        });

        gameThread = new Thread(gameEngine);
        gameThread.start();
    }
}

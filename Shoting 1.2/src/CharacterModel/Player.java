package CharacterModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends CharacterModel {

    private Image playerLookLeft;
    private Image playerLookRigth;

    private boolean isRigthMoving = true;

    public Player(int x, int y, int width, int height, int i, int j) {
        super(x, y, width, height, i, j);

        try {
            FileInputStream input = new FileInputStream("Skins\\PlayerSkins\\PlayerRight.png");
            FileInputStream input2 = new FileInputStream("Skins\\PlayerSkins\\PlayerLeft.png");

            playerLookRigth = new Image(input);
            playerLookLeft = new Image(input2);

        }catch(FileNotFoundException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void paintCharacter(GraphicsContext graphicsContext) {
        if(isRigthMoving) graphicsContext.drawImage(playerLookRigth, x,y);
        else graphicsContext.drawImage(playerLookLeft, x,y);
    }

    public void setRigthMoving(boolean rigthMoving) {
        isRigthMoving = rigthMoving;
    }

    @Override
    public void interaction(Player player) {}
}

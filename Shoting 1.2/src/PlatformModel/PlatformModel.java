package PlatformModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlatformModel {

    int x,y;
    Image skin;
    String pathToSkin;

    public PlatformModel(int x, int y, String pathToSkin){
        this.x = x;
        this.y = y;
        this.pathToSkin = pathToSkin;
        loadPlatformSkin();
    }

    public void paintPlatform(GraphicsContext graphicsContext){
        graphicsContext.drawImage(skin, x,y);
    }

    public void loadPlatformSkin(){
        try {
            FileInputStream input = new FileInputStream(pathToSkin);
            skin = new Image(input);
        }catch(FileNotFoundException e ){
            e.printStackTrace();
        }
    }
}

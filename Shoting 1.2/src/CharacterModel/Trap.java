package CharacterModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Trap extends CharacterModel {
    public Trap(int x, int y, int width, int height, int i, int j) {
        super(x, y, width, height, i, j);
    }

    @Override
    public void paintCharacter(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.web("RED"));
        graphicsContext.fillRect(x, y, width, height);
    }

    @Override
    public void interaction(Player player){
        if(player.x == x && player.y == y) {
            player.HP -= 25;
            HP = 0;
            System.out.println("Utrata HP");
        }
    }
}

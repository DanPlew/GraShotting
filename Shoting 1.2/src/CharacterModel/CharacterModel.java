package CharacterModel;

import javafx.scene.canvas.GraphicsContext;

public abstract class CharacterModel {

    public int width, height;
    public int x,y,i,j; // x,y are positions for paiting an image. i,j are positions in MapStructureTable.
    public int HP = 100;

    public CharacterModel(int x, int y, int width, int height, int i, int j){
        this.x = x;
        this.y = y;
        this. width = width;
        this. height = height;
        this.i = i;
        this.j = j;
    }

    public abstract void paintCharacter(GraphicsContext graphicsContext);
    public abstract void interaction(Player player);
    public boolean isDead(){
        if(HP == 0) return true;
        else return false;
    }
}

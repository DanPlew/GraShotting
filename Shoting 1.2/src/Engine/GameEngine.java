package Engine;
import CharacterModel.CharacterModel;
import CharacterModel.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.List;


public class GameEngine implements Runnable{

    public boolean right = false, left = false, up = false, down = false, jumping = false, falling = true, shoting = false;
    public boolean isPlaying = true;
    private String[][] currentMapStructure;

    private int points = 0;
    private int LVL = 0;
    private Text textPoints, textLevel;
    private Pane pane;
    private Player player;
    private List<CharacterModel> listOfCharacters;

    public CurrentLevel currentLevel;
    private Canvas canvasGamePlay;

    public GameEngine(Canvas canvasGamePlay, Canvas canvasHP, Text textPoints, Text textLevel, Pane pane){
        this.textPoints = textPoints;
        this.textLevel = textLevel;
        this.canvasGamePlay = canvasGamePlay;
        this.pane = pane;

        // Starting game with first level
        getStartedWithNextLevel(canvasHP);
    }

    @Override
    public void run() {

        int turnsForJumping = 0;
        int lastPositionX = player.x;
        int lastPositionY = player.y;

        while(isPlaying) {

            textLevel.setText("Level: " + LVL);
            textPoints.setText("Points: " + points);
            currentLevel.paint();

            if(player.HP == 0) {
                System.out.println("Przegrałeś");
                currentLevel.gameOverAnimation();
                isPlaying = false;
                break;
            }

            if(falling){
                if (player.y == lastPositionY + 50 ) {
                    lastPositionY = player.y;
                    player.i++;
                }
                if (currentMapStructure[player.i + 1][player.j].equals("0")) {
                    player.y += 10;
                }
                else {
                    if (player.y < player.i * 50) {
                        player.y += 5;
                    }
                }

            }
            else{
                if (currentMapStructure[player.i - 1][player.j].equals("0")) {
                    if(turnsForJumping != 11) {
                        if (player.y == lastPositionY - 50) {
                            lastPositionY = player.y;
                            player.i--;
                        }
                        player.y -= 10;
                        turnsForJumping ++;
                    }
                    else{
                        turnsForJumping = 0;
                        falling = true;
                    }
                }
            }

            if (left) {
                player.setRigthMoving(false);

                boolean isUp = false;
                if((currentMapStructure[player.i][player.j - 1].equals("0") && currentMapStructure[player.i + 1][player.j].equals("0") && currentMapStructure[player.i + 1][player.j - 1].equals("G"))){
                    isUp = true;
                    if (player.x == lastPositionX) {
                        lastPositionX = player.x - 50;
                        player.j--;
                    }
                }
                else if(currentMapStructure[player.i][player.j - 1].equals("0")){
                    if (canvasGamePlay.getLayoutX() < 0 && player.x < (canvasGamePlay.getWidth() - (pane.getWidth() / 2)))
                        canvasGamePlay.setLayoutX(canvasGamePlay.getLayoutX() + 5);
                    player.x -= 5;
                } else {
                    if (player.x > player.j * 50){
                        if (canvasGamePlay.getLayoutX() < 0 && player.x < (canvasGamePlay.getWidth() - (pane.getWidth() / 2)))
                        canvasGamePlay.setLayoutX(canvasGamePlay.getLayoutX() + 5);
                        player.x -= 5;
                    }
                }

                if (player.x == lastPositionX - 50 && !isUp) {
                    lastPositionX = player.x;
                    player.j--;
                }
            }
            if (right) {
                player.setRigthMoving(true);

                boolean isUp = false;
                if((currentMapStructure[player.i][player.j + 1].equals("0") && currentMapStructure[player.i + 1][player.j].equals("0") && currentMapStructure[player.i + 1][player.j + 1].equals("G"))){
                    isUp = true;
                    if (player.x == lastPositionX) {
                        lastPositionX = player.x + 50;
                        player.j++;
                    }
                }
                else if(currentMapStructure[player.i][player.j + 1].equals("0") || currentMapStructure[player.i][player.j + 1].equals("T")){
                    if (canvasGamePlay.getLayoutX() > (pane.getWidth() - canvasGamePlay.getWidth()) && player.x > (pane.getWidth() / 2))
                        canvasGamePlay.setLayoutX(canvasGamePlay.getLayoutX() - 5);
                    player.x += 5;
                }
                else{
                    if(player.x < player.j*50) {
                        if (canvasGamePlay.getLayoutX() < 0 && player.x < (canvasGamePlay.getWidth() - (pane.getWidth() / 2)))
                        canvasGamePlay.setLayoutX(canvasGamePlay.getLayoutX() - 5);
                        player.x += 5;
                    }
                }

                if(player.x == lastPositionX + 50 && !isUp){
                    lastPositionX = player.x;
                    player.j++;
                }
            }

            for(int i=0;i<listOfCharacters.size();i++){
                if(listOfCharacters.get(i).isDead()){
                    currentMapStructure[listOfCharacters.get(i).i][listOfCharacters.get(i).j] = "0";
                    listOfCharacters.remove(i);
                    i--;
                }
                listOfCharacters.get(i).interaction(player);
            }

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getStartedWithNextLevel(Canvas canvasHP){
        LVL++;
        currentLevel = new CurrentLevel(canvasGamePlay, canvasHP, Integer.toString(LVL));
        player = currentLevel.getPlayer();
        listOfCharacters = currentLevel.getListOfCharacters();
        currentMapStructure = currentLevel.getMapStructure();
    }
}
            /*if (up) {
                if(pane.getLayoutY() < 0 && world.player.position.y < (canvas.getHeight() - (pane.getHeight()/2))) pane.setLayoutY(pane.getLayoutY() + 10);
                world.player.position.y -= 10;
            }

            if (down) {
                if(pane.getLayoutY() > (pane.getHeight() - canvas.getHeight()) && world.player.position.y > (pane.getHeight()/2)) pane.setLayoutY(pane.getLayoutY() - 10);
                world.player.position.y += 10;
            }*/
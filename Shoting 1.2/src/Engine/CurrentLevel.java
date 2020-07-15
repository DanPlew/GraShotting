package Engine;

import CharacterModel.*;
import PlatformModel.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class CurrentLevel{

    public boolean isPlayingCurrentLevel = true;

    private String LVL;
    private Image background;
    public Canvas canvasGamePlay, canvasHP;
    public List<CharacterModel> listOfCharacters;
    public List<PlatformModel> listOfPlatforms;

    public String[][] mapStructure;

    private GraphicsContext graphicForGamePlay, graphicsForHP;

    public CurrentLevel(Canvas canvasGamePlay, Canvas canvasHP, String LVL){
        this.LVL = LVL;
        this.canvasGamePlay = canvasGamePlay;
        this.canvasHP = canvasHP;

        listOfCharacters = new ArrayList<>();
        listOfPlatforms = new ArrayList<>();

        graphicForGamePlay = canvasGamePlay.getGraphicsContext2D();
        graphicsForHP = canvasHP.getGraphicsContext2D();

        loadData();
    }

    public void paint(){
        //Background
        graphicForGamePlay.drawImage(background,0,0);

        listOfCharacters.forEach(character -> character.paintCharacter(graphicForGamePlay));
        listOfPlatforms.forEach(platform -> platform.paintPlatform(graphicForGamePlay));


        graphicsForHP.setFill(Color.web("#2a3f54"));
        graphicsForHP.fillRect(0,0, 200, 20);

        graphicsForHP.setFill(Color.web("#ff3333"));
        graphicsForHP.fillRect(0,0, listOfCharacters.get(0).HP * 2, 20);
    }

    private void loadData(){
        try{
            // Load Background
            FileInputStream input = new FileInputStream("Skins\\Backgrounds\\BackGround" + LVL + ".jpg");
            background = new Image(input);
            input.close();

            // Load Map Strucutre from file
            BufferedReader bufferedReader = new BufferedReader(new FileReader("MapStructure\\Level" + LVL + ".txt"));
            int numberOfLines = (int)(bufferedReader.lines().count()); // Without 2 line becouse of resolution 2 lines.
            mapStructure = new String[numberOfLines][];

            bufferedReader = new BufferedReader(new FileReader("MapStructure\\Level" + LVL + ".txt")); // Reset is not working..
            String line = " ";
            int i = 0;
            while((line = bufferedReader.readLine()) != null){

                /*if(line.equals("Resolution")){
                    String[] resolution = bufferedReader.readLine().split("x");
                    canvasGamePlay.setWidth(Integer.parseInt(resolution[0]));
                    canvasGamePlay.setHeight(Integer.parseInt(resolution[1]));
                    bufferedReader.readLine();
                }*/

                mapStructure[i] = line.split(",");
                i++;
            }
            bufferedReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        for(int i=0;i<mapStructure.length;i++){
            for(int j=0;j<mapStructure[i].length;j++){

                switch (mapStructure[i][j]){
                    case "P":
                        listOfCharacters.add(new Player(j*50, i*50,50,50,i ,j));
                        break;

                    case "T":
                        listOfCharacters.add(new Trap(j*50, i*50, 50,25, i,j));
                        break;

                    case "G":
                        listOfPlatforms.add(new Grass(j*50, i*50, "Skins\\FloorSkins\\Grass.jpg"));
                        break;

                    case "D":
                        listOfPlatforms.add(new Dirt(j*50, i*50, "Skins\\FloorSkins\\Dirt.jpg"));
                        break;

                    case "S":
                        listOfPlatforms.add(new Stone(j*50, i*50, "Skins\\FloorSkins\\Stone.jpg"));
                        break;
                }
            }
        }
        mapStructure[listOfCharacters.get(0).i][listOfCharacters.get(0).j] = "0";
    }

    public void gameOverAnimation(){
        graphicForGamePlay.fillText("Game Over..", 400,400);
    }

    public String[][] getMapStructure(){
        return mapStructure;
    }

    public Player getPlayer(){
        return (Player)listOfCharacters.get(0);
    }

    public List<CharacterModel> getListOfCharacters(){
        return listOfCharacters;
    }
}

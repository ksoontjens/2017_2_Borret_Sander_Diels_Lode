package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import java.awt.Color;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener {
    HScene scene = HSceneFactory.getInstance().getDefaultHScene();
    int screenWidth = 720;
    int screenHeight = 576;
    int tileSize = 100;
    int tileColumns = 3;
    int tileRows = 3;
    int amountOfTiles = tileRows * tileColumns;
    int centerOffsetX = (screenWidth - (tileSize * tileRows)) / 2;
    int centerOffsetY = (screenHeight - (tileSize * tileRows)) / 2;
    int tilesIndex = 0;
    int emptyTilePosition = amountOfTiles;
    HTextButton tiles[] = new HTextButton[amountOfTiles];
    String buttonText = "";
    String actionCommand = "";
    String emptyString = "EMPTY";
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.setBackground(Color.BLACK);
        
        for(int i = 0; i < tileColumns; i++) {
            for(int j = 0; j < tileRows; j++) {
                if(i == tileColumns - 1 && j == tileRows - 1) {
                    buttonText = emptyString;
                    actionCommand = emptyString;
                }
                else {
                    buttonText = Integer.toString(tilesIndex + 1);
                    actionCommand = "tile" + Integer.toString(tilesIndex + 1);
                }
                tiles[tilesIndex] = new HTextButton(buttonText, centerOffsetX + (tileSize * j), centerOffsetY + (tileSize * i), tileSize, tileSize);
                tiles[tilesIndex].setBackgroundMode(HVisible.BACKGROUND_FILL);
                tiles[tilesIndex].setBackground(Color.RED);
                scene.add(tiles[tilesIndex]);
                tiles[tilesIndex].setActionCommand(actionCommand);
                tiles[tilesIndex].addHActionListener(this);
                tilesIndex++;
            }
        }
        
        for(int i = 0; i < amountOfTiles; i++) {
            if(i == 0) {
                tiles[i].setFocusTraversal(null, null, tiles[amountOfTiles - 1], tiles[i + 1]);
            }
            else if(i == amountOfTiles - 1) {
                tiles[i].setFocusTraversal(null, null, tiles[i - 1], tiles[0]);
            }
            else {
                tiles[i].setFocusTraversal(null, null, tiles[i - 1], tiles[i + 1]);
            }

        }
        
        tiles[0].requestFocus();

        scene.validate();
        scene.setVisible(true);
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
    
    public void actionPerformed(ActionEvent arg0) {
        String pressedActionCommand = arg0.getActionCommand();
        
        if(!pressedActionCommand.equals(emptyString)) {
            int pressedTileNumber = Integer.parseInt(pressedActionCommand.substring(actionCommand.length() - 1));
            
            if(pressedTileNumber == (emptyTilePosition - 1) || pressedTileNumber == (emptyTilePosition - 3) ||
               pressedTileNumber == (emptyTilePosition + 1) || pressedTileNumber == (emptyTilePosition + 3)) {
                tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                tiles[emptyTilePosition - 1].setTextContent(Integer.toString(pressedTileNumber), HVisible.NORMAL_STATE);
                emptyTilePosition = pressedTileNumber;
                
                tiles[pressedTileNumber - 1].setActionCommand(emptyString);
                tiles[pressedTileNumber - 1].setTextContent(emptyString, HVisible.NORMAL_STATE);
                
                System.out.println("Valid click");
                System.out.println(tiles[emptyTilePosition - 1].getActionCommand());
            }
            else {
                System.out.println(pressedActionCommand);
            }
        }
        else {
            for(int i = 0; i < amountOfTiles; i++) {
                if(tiles[i].getTextContent(HVisible.NORMAL_STATE).equals(emptyString)) {
                    System.out.println(tiles[i].getActionCommand());
                }
            }
        }
    }
}

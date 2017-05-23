package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.dvb.event.UserEvent;
import org.havi.ui.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import org.dvb.event.UserEventListener;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet extends HComponent implements Xlet, HActionListener, UserEventListener {
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
    HGraphicButton tiles[] = new HGraphicButton[amountOfTiles];
    Image images[] = new Image[amountOfTiles];
    String buttonText = "";
    String actionCommand = "";
    String emptyString = "EMPTY";
    MediaTracker mediaTracker = new MediaTracker(this);
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
        scene.setBounds(0, 0, screenWidth, screenHeight);
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.setBackground(Color.BLACK);
        
        for(int i = 0; i < amountOfTiles - 1; i++) {
            images[i] = this.getToolkit().getImage(Integer.toString(i + 1) + ".png");
            mediaTracker.addImage(images[i], 1);
        }
        images[amountOfTiles - 1] = this.getToolkit().getImage("EMPTY.png");
        mediaTracker.addImage(images[amountOfTiles - 1], 1);
        
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
                tiles[tilesIndex] = new HGraphicButton(images[tilesIndex], centerOffsetX + (tileSize * j), centerOffsetY + (tileSize * i), tileSize, tileSize);
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
                tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                emptyTilePosition = pressedTileNumber;
                
                tiles[pressedTileNumber - 1].setActionCommand(emptyString);
                tiles[pressedTileNumber - 1].setGraphicContent(images[amountOfTiles - 1], HVisible.NORMAL_STATE);
                
                System.out.println("--------------------");
                System.out.println("Valid click");
                System.out.println("Empty tile position: " + emptyTilePosition);
                System.out.println("Pressed tile number: " + pressedTileNumber);
                System.out.println("Pressed tile number action command: " + pressedActionCommand);
            }
            else {
                System.out.println("--------------------");
                System.out.println("Invalid click");
                System.out.println("Empty tile position: " + emptyTilePosition);
                System.out.println("Pressed tile number: " + pressedTileNumber);
                System.out.println("Pressed tile number action command: " + pressedActionCommand);
            }
        }
        else {
            System.out.println("--------------------");
            System.out.println("Pressed empty tile");
        }
    }

    public void userEventReceived(UserEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

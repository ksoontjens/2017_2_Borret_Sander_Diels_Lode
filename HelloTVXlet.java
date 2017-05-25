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
    
    // 't Is wel nogal een zootje
    public void initXlet(XletContext context) throws XletStateChangeException {
        scene.setBounds(0, 0, screenWidth, screenHeight);
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.setBackground(Color.BLACK);
        
        for(int i = 0; i < amountOfTiles - 1; i++) {
            images[i] = this.getToolkit().getImage(Integer.toString(i + 1) + ".png");
            mediaTracker.addImage(images[i], 1);
        }
        images[amountOfTiles - 1] = this.getToolkit().getImage(emptyString + ".png");
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
            
            /*
             * Het probleem is nu dat als ge een tegel verplaatst, die tegel zijn actionCommand behoudt.
             * Dus als ge bijvoorbeeld tegel 8 naar 9 verplaatst, de tegel die nu op plaats 9 staat nog altijd 'tile8' noemt.
             * Daardoor loopt het dan mis met de detectie van geldige tegels om te verplaatsen (de if hieronder).
             * Voel je vrij om mijn code te slopen als ge een beter systeem bedenkt.
             * Aja en elke keer als ge klikt op een treegel verschijnt er debuginfo in de console, kan handig zijn.
            */
            
            
            
            
        switch (emptyTilePosition) {
            case 1:  if(pressedTileNumber == 2 || pressedTileNumber == 4){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 2:  if(pressedTileNumber == 1 || pressedTileNumber == 5 || pressedTileNumber == 3){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 3:  if(pressedTileNumber == 2 || pressedTileNumber == 6){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 4:  if(pressedTileNumber == 1 || pressedTileNumber == 5 || pressedTileNumber == 7){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 5:  if(pressedTileNumber == 2 || pressedTileNumber == 4 || pressedTileNumber == 6 || pressedTileNumber == 8){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 6:  if(pressedTileNumber == 3 || pressedTileNumber == 5 || pressedTileNumber == 9){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 7:  if(pressedTileNumber == 4 || pressedTileNumber == 8){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 8:  if(pressedTileNumber == 7 || pressedTileNumber == 5 || pressedTileNumber == 9){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            case 9:  if(pressedTileNumber == 6 || pressedTileNumber == 8){
                            tiles[pressedTileNumber - 1].setActionCommand("tile" + Integer.toString(emptyTilePosition));
                            tiles[emptyTilePosition - 1].setActionCommand("tile" + Integer.toString(pressedTileNumber));
                            tiles[emptyTilePosition - 1].setGraphicContent(images[pressedTileNumber - 1], HVisible.NORMAL_STATE);
                            tiles[pressedTileNumber - 1].setGraphicContent(images[emptyTilePosition - 1], HVisible.NORMAL_STATE);
                            emptyTilePosition = pressedTileNumber;
                      }
                      else{
                            System.out.println("--------------------");
                            System.out.println("Invalid click");
                            System.out.println("Empty tile position: " + emptyTilePosition);
                            System.out.println("Pressed tile number: " + pressedTileNumber);
                            System.out.println("Pressed tile number action command: " + pressedActionCommand);
                      }
                     break;
            default: System.out.println("KAPOT");
                     break;
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

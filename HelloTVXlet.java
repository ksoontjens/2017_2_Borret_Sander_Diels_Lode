package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import org.havi.ui.event.HActionListener;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Sander Borret & Lode Diels
 */
public class HelloTVXlet extends HComponent implements Xlet, HActionListener {
    HScene scene = HSceneFactory.getInstance().getDefaultHScene();
    int screenWidth = 720;
    int screenHeight = 576;
    int tileSize = 100;
    int tileColumns = 3;
    int tileRows = 3;
    int amountOfTiles = tileRows * tileColumns;
    int centerOffsetX = (screenWidth - (tileSize * tileRows)) / 2;
    int centerOffsetY = (screenHeight - (tileSize * tileRows)) / 2;
    int tilesIndex;
    int emptyTilePosition;
    int moves = 0;
    int time = 0;
    int score = 1000000;
    int indexes[] = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int lostScorePerMove = 500;
    int lostScorePerSecond = 250;
    int textHeight = 40;
    boolean timerStart = false;
    HGraphicButton tiles[] = new HGraphicButton[amountOfTiles];
    // Images in C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
    Image images[] = new Image[amountOfTiles];
    HStaticText movesText;
    HStaticText timeText;
    HStaticText scoreText;
    String emptyString = "EMPTY";
    String movesString = "Moves: ";
    String timeString = "Time: ";
    String scoreString = "Score: ";
    MediaTracker mediaTracker = new MediaTracker(this);
  
    public HelloTVXlet() {
        
    }
    
    public void initXlet(XletContext context) throws XletStateChangeException {
        scene.setBounds(0, 0, screenWidth, screenHeight);
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.setBackground(Color.BLACK);
        
        Timer timer = new Timer();
        GameTimer gameTimer = new GameTimer();
        gameTimer.setCallback(this);
        timer.scheduleAtFixedRate(gameTimer, 0, 1000);
        
        shuffleArray(indexes);
        emptyTilePosition = getEmptyTilePosition();
        tilesIndex = 0;
        populateImageArray();
        
        for(int i = 0; i < tileColumns; i++) {
            for(int j = 0; j < tileRows; j++) {
                tiles[tilesIndex] = new HGraphicButton(images[indexes[tilesIndex]], centerOffsetX + (tileSize * j), centerOffsetY + (tileSize * i), tileSize, tileSize);
                tiles[tilesIndex].setBackgroundMode(HVisible.BACKGROUND_FILL);
                tiles[tilesIndex].setBackground(Color.RED);
                tiles[tilesIndex].setActionCommand(Integer.toString(tilesIndex + 1));
                tiles[tilesIndex].addHActionListener(this);
                scene.add(tiles[tilesIndex]);
                tilesIndex++;
            }
        }
        
      movesText = new HStaticText(movesString + moves, centerOffsetX, centerOffsetY + (tileSize * tileRows), tileSize * tileRows, textHeight);
      timeText = new HStaticText(timeString + time, centerOffsetX, centerOffsetY + (tileSize * tileRows) + textHeight, tileSize * tileRows, textHeight);
      scoreText = new HStaticText(scoreString + score, centerOffsetX, centerOffsetY + (tileSize * tileRows) + (textHeight * 2), tileSize * tileRows, textHeight);
      scene.add(movesText);
      scene.add(timeText);
      scene.add(scoreText);
        
        for(int i = 0; i < amountOfTiles; i++) {
            switch(i) {
                case 0: tiles[i].setFocusTraversal(tiles[i + 6], tiles[i + 3], tiles[i + 2], tiles[i + 1]); break;
                case 1: tiles[i].setFocusTraversal(tiles[i + 6], tiles[i + 3], tiles[i - 1], tiles[i + 1]); break;
                case 2: tiles[i].setFocusTraversal(tiles[i + 6], tiles[i + 3], tiles[i - 1], tiles[i - 2]); break;
                case 3: tiles[i].setFocusTraversal(tiles[i - 3], tiles[i + 3], tiles[i + 2], tiles[i + 1]); break;
                case 4: tiles[i].setFocusTraversal(tiles[i - 3], tiles[i + 3], tiles[i - 1], tiles[i + 1]); break;
                case 5: tiles[i].setFocusTraversal(tiles[i - 3], tiles[i + 3], tiles[i - 1], tiles[i - 2]); break;
                case 6: tiles[i].setFocusTraversal(tiles[i - 3], tiles[i - 6], tiles[i + 2], tiles[i + 1]); break;
                case 7: tiles[i].setFocusTraversal(tiles[i - 3], tiles[i - 6], tiles[i - 1], tiles[i + 1]); break;
                case 8: tiles[i].setFocusTraversal(tiles[i - 3], tiles[i - 6], tiles[i - 1], tiles[i - 2]); break;
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
        String pressedTile = arg0.getActionCommand();
        
        if(!pressedTile.equals(emptyString)) {
            int pressedTileNumber = Integer.parseInt(pressedTile);
            Image pressedTileImage = tiles[pressedTileNumber - 1].getGraphicContent(HVisible.NORMAL_STATE);
            
            if(pressedTileNumber == (emptyTilePosition - 1) && emptyTilePosition != 4  && emptyTilePosition != 7) {
                tiles[emptyTilePosition - 1].setActionCommand(Integer.toString(pressedTileNumber + 1));
            }
            else if(pressedTileNumber == (emptyTilePosition + 1) && emptyTilePosition !=3 && emptyTilePosition != 6) {
                tiles[emptyTilePosition - 1].setActionCommand(Integer.toString(pressedTileNumber - 1));
            }
            else if(pressedTileNumber == (emptyTilePosition - 3)) {
                tiles[emptyTilePosition - 1].setActionCommand(Integer.toString(pressedTileNumber + 3));
            }
            else if(pressedTileNumber == (emptyTilePosition + 3)) {
                tiles[emptyTilePosition - 1].setActionCommand(Integer.toString(pressedTileNumber - 3));
            }
            else {
                return;
            }
            
            if(!timerStart) {
                timerStart = true;
            }
            
            tiles[emptyTilePosition - 1].setGraphicContent(pressedTileImage, HVisible.NORMAL_STATE);

            tiles[pressedTileNumber - 1].setActionCommand(emptyString);
            tiles[pressedTileNumber - 1].setGraphicContent(images[amountOfTiles - 1], HVisible.NORMAL_STATE);
            
            emptyTilePosition = pressedTileNumber;
            
            moves++;
            movesText.setTextContent(movesString + Integer.toString(moves), HVisible.NORMAL_STATE);
            score -= lostScorePerMove;
            scoreText.setTextContent(scoreString + Integer.toString(score), HVisible.NORMAL_STATE);
        }
    }
    
    public void shuffleArray(int[] array) {
        for(int i = array.length - 1; i > 0; i--) {
            Random rnd = new Random();
            int index = rnd.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    
    public int getEmptyTilePosition() {
        for(int i = 0; i < indexes.length; i++) {
            if(indexes[i] == amountOfTiles - 1) {
                emptyTilePosition = i + 1;
                break;
            }
        }
        
        return emptyTilePosition;
    }
    
    public void populateImageArray() {
        for(int i = 0; i < amountOfTiles - 1; i++) {
            images[i] = this.getToolkit().getImage(Integer.toString(i + 1) + ".png");
            mediaTracker.addImage(images[i], 1);
        }
        
        images[amountOfTiles - 1] = this.getToolkit().getImage(emptyString + ".png");
        mediaTracker.addImage(images[amountOfTiles - 1], 1);
    }
    
    public void callback() {
        if(timerStart) {
            time++;
            timeText.setTextContent(timeString + Integer.toString(time), HVisible.NORMAL_STATE);
            score -= lostScorePerSecond;
            scoreText.setTextContent(scoreString + Integer.toString(score), HVisible.NORMAL_STATE);
        }
    }
}
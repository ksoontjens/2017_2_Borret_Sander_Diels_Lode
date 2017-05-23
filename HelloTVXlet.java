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
    HTextButton tiles[] = new HTextButton[amountOfTiles];
    int tilesIndex = 0;
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.setBackground(Color.BLACK);
        
        for(int i = 0; i < tileRows; i++) {
            for(int j = 0; j < tileColumns; j++) {
                tiles[tilesIndex] = new HTextButton(Integer.toString(tilesIndex + 1), centerOffsetX + (tileSize * j), centerOffsetY + (tileSize * i), tileSize, tileSize);
                tiles[tilesIndex].setBackgroundMode(HVisible.BACKGROUND_FILL);
                tiles[tilesIndex].setBackground(Color.RED);
                scene.add(tiles[tilesIndex]);
                tilesIndex++;
            }
        }
        /*
        tile1.setFocusTraversal(null, null, null, tile2);
        tile2.setFocusTraversal(null, null, tile1, tile3);
        tile3.setFocusTraversal(null, null, tile2, null);
      
        tile1.setActionCommand("tile1");
        tile2.setActionCommand("tile2");
        tile3.setActionCommand("tile3");
      
        tile1.addHActionListener((HActionListener) this);
        tile2.addHActionListener((HActionListener) this);
        tile3.addHActionListener((HActionListener) this);
      
        tile1.requestFocus();*/

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
      scene.repaint();
    }
}

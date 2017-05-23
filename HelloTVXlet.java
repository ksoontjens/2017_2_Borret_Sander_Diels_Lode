package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import java.awt.Color;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {
    HScene scene = HSceneFactory.getInstance().getDefaultHScene();
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
        HTextButton tile1 = new HTextButton("1", 0, 0, 100, 100); // x, y, w, h
        HTextButton tile2 = new HTextButton("2", 100, 0, 100, 100); // x, y, w, h
        HTextButton tile3 = new HTextButton("3", 200, 0, 100, 100); // x, y, w, h
        
        tile1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        tile1.setBackground(Color.RED);
        tile2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        tile2.setBackground(Color.RED);
        tile3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        tile3.setBackground(Color.RED);
        
        scene.add(tile1);
        scene.add(tile2);
        scene.add(tile3);
        
        tile1.setFocusTraversal(null, null, null, tile2); // up, down, left, right
        tile2.setFocusTraversal(null, null, tile1, tile3); // up, down, left, right
        tile3.setFocusTraversal(null, null, tile2, null); // up, down, left, right
      
        tile1.setActionCommand("tile1");
        tile2.setActionCommand("tile2");
        tile3.setActionCommand("tile3");
      
        tile1.addHActionListener((HActionListener) this);
        tile2.addHActionListener((HActionListener) this);
        tile3.addHActionListener((HActionListener) this);
      
        tile1.requestFocus();

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

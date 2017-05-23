package hellotvxlet;

import javax.tv.xlet.*;
import org.havi.ui.*;


public class HelloTVXlet implements Xlet {

  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();

        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        scene.add(slidingPuzzle);

        scene.validate();
        scene.setVisible(true);
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}

package hellotvxlet;

import javax.tv.xlet.*;
import org.havi.ui.*;


public class HelloTVXlet implements Xlet {

  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();

        DodgeStuff dodgeStuff = new DodgeStuff();
        scene.add(dodgeStuff);

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

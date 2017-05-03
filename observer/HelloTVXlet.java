package hellotvxlet;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;

import java.util.Timer;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

/**
 * Just a simple xlet that draws a String in the center of the screen.
 */
public class HelloTVXlet implements Xlet {

    private static Font font;
    private HScene scene;


    /** Creates a new instance of HelloTVXlet */
    public HelloTVXlet() {
    }

    public void initXlet(XletContext context) {

     
        scene = HSceneFactory.getInstance().getDefaultHScene();
       Sprite s=new Sprite(200,200);
       
        scene.add(s);
        
        Sprite s1=new Sprite(100,300);
       
        scene.add(s1);
        scene.add(s);
        scene.validate();
        Timer t=new Timer();
        
        Subject sub=new Subject() ;

        sub.registerObserver(s);
        sub.registerObserver(s1);
        
        t.scheduleAtFixedRate(sub, 0, 10);
               
    }

    public void startXlet() {
       
        scene.setVisible(true);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
       
        scene = null;
    }
    
  
}

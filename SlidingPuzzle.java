package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

/**
 *
 * @author Sander Borret & Lode Diels
 */
public class SlidingPuzzle extends HComponent implements UserEventListener {
    int screenWidth = 720;
    int screenHeight = 576;
    
    public SlidingPuzzle() {
        this.setBounds(0, 0, screenWidth, screenHeight); // x, y, b, h
        
        UserEventRepository repo = new UserEventRepository("repo");
        repo.addAllArrowKeys();
        
        EventManager manager = EventManager.getInstance();
        manager.addUserEventListener(this, repo);
        
        Timer timer = new Timer();
        TheTimerTask timerTask = new TheTimerTask();
        timerTask.setCallback(this);
        timer.scheduleAtFixedRate(timerTask, 0, 100);
        // Start op 0 ms elke 20 ms
    }
    
    public void callback() {
        this.repaint();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType() == HRcEvent.KEY_PRESSED) {
            
        }
    }

}

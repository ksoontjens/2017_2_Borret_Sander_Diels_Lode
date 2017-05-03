package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;

/**
 *
 * @author Sander Borret & Lode Diels
 */
public class DodgeStuff extends HComponent implements UserEventListener {
    int spaceShipX = 330;
    int spaceShipY = 456;
    int screenWidth = 720;
    int screenHeight = 576;
    int blockY = 0;
    boolean levelCreated = false;
    
    public DodgeStuff() {
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
        blockY += 20;
        this.repaint();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, screenWidth, screenHeight);
        
        g.setColor(Color.YELLOW);
        g.drawString("DODGE STUFF", 50, 50);
        
        g.setColor(Color.RED);
        g.fillRect(spaceShipX, spaceShipY, 60, 60); // x, y, b, h
        
        g.setColor(Color.GREEN);
    }
    
    public void paintLevel(Graphics g) {
        super.paint(g);
        for(int i = 0; i < 100; i++) {
            Random r = new Random();
            int randomWidthDivider = r.nextInt(7 - 3) + 3;

            g.fillRect(0, blockY - (screenHeight * i), screenWidth / randomWidthDivider, screenHeight);
            g.fillRect(screenWidth - (screenWidth / randomWidthDivider), blockY - (screenHeight * i), screenWidth / randomWidthDivider, screenHeight);
        }
    }
    paintLevel(g);
    
    public void userEventReceived(UserEvent e) {
        if(e.getType() == HRcEvent.KEY_PRESSED) {
            if(e.getCode() == HRcEvent.VK_LEFT) {
                if(spaceShipX > screenWidth / 5) {
                    spaceShipX -= 5;
                }
                this.repaint();
            }
            if(e.getCode() == HRcEvent.VK_RIGHT) {
                if(spaceShipX < screenWidth - ((screenWidth / 5) + 60)) {
                    spaceShipX += 5;
                }
                this.repaint();
            }
        }
    }
}

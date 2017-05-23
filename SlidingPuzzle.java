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

/**
 *
 * @author Sander Borret & Lode Diels
 */
public class SlidingPuzzle extends HComponent implements UserEventListener {
    int screenWidth = 720;
    int screenHeight = 576;
    int currentPosition = 1;
    int selectedTile = 0;
    
    int image0X = 226;
    int image0Y = 226;
    
    Image image1;
    Image image2;
    Image image3;
    Image image4;
    Image image5;
    Image image6;
    Image image7;
    Image image8;
    Image border;
    
    MediaTracker mt = new MediaTracker(this);
    
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
        
        // Images in C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
        image0 = this.getToolkit().getImage("0.png");
        image1 = this.getToolkit().getImage("1.png");
        image2 = this.getToolkit().getImage("2.png");
        image3 = this.getToolkit().getImage("3.png");
        image4 = this.getToolkit().getImage("4.png");
        image5 = this.getToolkit().getImage("5.png");
        image6 = this.getToolkit().getImage("6.png");
        image7 = this.getToolkit().getImage("7.png");
        border = this.getToolkit().getImage("b.png");
        
        mt.addImage(image0, 1);
        mt.addImage(image1, 1);
        mt.addImage(image2, 1);
        mt.addImage(image3, 1);
        mt.addImage(image4, 1);
        mt.addImage(image5, 1);
        mt.addImage(image6, 1);
        mt.addImage(image7, 1);
        mt.addImage(image8, 1);
        mt.addImage(border, 1);
        
        g.drawImage(image0, image0X, image0Y, null);
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, 113, 0, null);
        g.drawImage(image3, 226, 0, null);
        g.drawImage(image4, 0, 113, null);
        g.drawImage(image5, 113, 113, null);
        g.drawImage(image6, 226, 113, null);
        g.drawImage(image7, 0, 226, null);
        g.drawImage(image8, 113, 226, null);
        g.drawImage(border, 226, 226, null);
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType() == HRcEvent.KEY_PRESSED) {
            if(e.getCode() == HRcEvent.VK_LEFT) {
                if(currentPosition == 1) {
                    currentPosition = 9;
                }
                else {
                    currentPosition--;
                }
                this.repaint();
            }
            if(e.getCode() == HRcEvent.VK_RIGHT) {
                if(currentPosition == 9) {
                    currentPosition = 1;
                }
                else {
                    currentPosition++;
                }
                this.repaint();
            }
            if(e.getCode() == HRcEvent.VK_UP) {
                selectedTile = currentPosition;
                this.repaint();
            }
        }
    }

}

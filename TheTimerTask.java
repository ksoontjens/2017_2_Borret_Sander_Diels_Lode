package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author Sander Borret & Lode Diels
 */

public class TheTimerTask extends TimerTask {
    DodgeStuff dodgeStuff;
    
    public void setCallback(DodgeStuff dodgeStuff) {
        this.dodgeStuff = dodgeStuff;
    }
    
    public void run() {
        dodgeStuff.callback();
    }
}

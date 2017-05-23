package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author Sander Borret & Lode Diels
 */

public class TheTimerTask extends TimerTask {
    SlidingPuzzle slidingPuzzle;
    
    public void setCallback(SlidingPuzzle slidingPuzzle) {
        this.slidingPuzzle = slidingPuzzle;
    }
    
    public void run() {
        slidingPuzzle.callback();
    }
}

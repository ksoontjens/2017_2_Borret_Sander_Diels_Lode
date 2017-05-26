/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author Sander Borret & Lode Diels
 */
public class GameTimer extends TimerTask {
    HelloTVXlet game;
    
    public void setCallback(HelloTVXlet game) {
        this.game = game;
    }
    
    public void run() {
        game.callback();
    }
}

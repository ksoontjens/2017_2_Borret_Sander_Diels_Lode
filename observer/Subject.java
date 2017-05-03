/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author student
 */
public class Subject extends TimerTask {

ArrayList list=new ArrayList();
    
   
    
    public void registerObserver(ObserverInterface o)
    {
        list.add(o);
    }
    public void run() {
        System.out.println("run");
        for (int i=0;i<list.size();i++)
        {
            ((ObserverInterface)(list.get(i))).update();
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet.observer;

import java.awt.Color;
import java.awt.Graphics;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class Sprite extends HComponent implements ObserverInterface {
 
    
    int px,py;
    
   public Sprite(int x,int y)
   {
       px=x; py=y;
       
       this.setBounds(0,0,20,20);
       
   }
         
   public void update()
   {
      px++;
      this.setBounds(px,py,20,20);
      this.repaint();
      
      System.out.print("update sprite");
   }
      
       
   
   public void paint(Graphics g)
   {
       g.setColor(Color.RED);
       g.fillRect(0,0,10,10);
    
   }
}

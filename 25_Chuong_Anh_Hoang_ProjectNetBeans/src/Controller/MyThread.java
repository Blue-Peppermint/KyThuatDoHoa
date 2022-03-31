/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.Paint_2D.isStopCheckingProgress1;
import static Controller.Paint_2D.isStopCheckingProgress2;
import View.GUI;
import View.NewClass;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author chuon
 */
public class MyThread extends Thread{
    
    private static Paint_2D paint;
    private static int dem = 0;
    public MyThread(Paint_2D paint) {
        this.paint = paint;
    }
    
    @Override
    public synchronized void run() {
        while (GUI.drawing2D == true) {
            paint.repaint();
            //System.out.println("I'm rendering " + ++dem);
            // phat sinh cai thu duoi day, do ban than muon cho 2 WindMill ton tai cung 1 luc va thoi diem bi huy lai khong cung luc
            if (paint.drawingLocation != null) {
                if (paint.windMill != null) {
                    int progression1 = paint.windMill.checkProgress(paint.drawingLocation);
                    if (progression1 == 0 && isStopCheckingProgress1) {
                        isStopCheckingProgress1 = false;
                    }
                }
                if (paint.windMill2 != null) {
                    int progression2 = paint.windMill2.checkProgress(paint.drawingLocation);
                    if (progression2 == 0 && isStopCheckingProgress2) {
                        isStopCheckingProgress2 = false;
                    }
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

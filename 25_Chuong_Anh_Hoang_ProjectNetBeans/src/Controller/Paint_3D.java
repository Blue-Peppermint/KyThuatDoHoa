/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BasicMethod;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import static Model.BasicMethod.*;
import Model.MyLine;
import Model.MyPoint;
import Model.MyPoint3D;
import Model.MyRectanglePrism;
import Model.MySphere;
import java.awt.Color;
/**
 *
 * @author chuon
 */
public class Paint_3D extends JComponent {

    public static Rectangle drawingLocation;
    public static ArrayList<Object> list3DObject;
    public static MyRectanglePrism rectPrism;
    public static MySphere sphere;
    
    public Paint_3D(JPanel drawPane) {
        this.drawingLocation = Paint_2D.drawingLocation;
        list3DObject = new ArrayList();
        sphere = null;
        rectPrism = null;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                drawingLocation = drawPane.getVisibleRect();
                BasicMethod.toaDoXGoc = drawingLocation.getWidth() / 2;
                BasicMethod.toaDoYGoc = drawingLocation.getHeight() / 2;               
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if(drawingLocation != null){
            khung3D(g);
            for (Object o : list3DObject) {
                if (o instanceof MySphere) {
                    MySphere sphere = (MySphere) o;
                    sphere.draw(g);
                } else if (o instanceof MyRectanglePrism) {
                    MyRectanglePrism rectPrism = (MyRectanglePrism) o;
                    rectPrism.draw(g);
                }
            }
            if (sphere != null) {
                list3DObject.add(sphere);
                sphere.draw(g);
                // reset data object
                sphere = null;
            }
            if (rectPrism != null) {
                list3DObject.add(rectPrism);
                rectPrism.draw(g);
                // reset
                rectPrism = null;
            }
        }
    }

    private void khung3D(Graphics g) {
        // ve truc toa do OXYZ
        // Truc duong ve net lien, truc am ve net dut
        int screenHeight = (int) (drawingLocation.getHeight());
        int screenhWidth = (int) (drawingLocation.getWidth());
        MyPoint pointO = new MyPoint((drawingLocation.getWidth() / 2), (drawingLocation.getHeight() / 2));
        // ve Ox duong
        MyLine oxDuong = new MyLine(new MyPoint(pointO.x, screenHeight / 2),
                new MyPoint(screenhWidth, screenHeight / 2), Color.BLACK);
        // Ve Ox am
        MyLine oxAm = new MyLine(new MyPoint(pointO.x, screenHeight / 2),
                new MyPoint(0, screenHeight / 2), Color.CYAN);
        MyLine ozDuong = new MyLine(new MyPoint(pointO.x, screenHeight / 2),
                new MyPoint(pointO.x, 0), Color.BLACK);
        MyLine ozAm = new MyLine(new MyPoint(pointO.x, screenHeight / 2),
                new MyPoint(pointO.x, screenHeight), Color.CYAN);
        MyLine oyDuong = oxDuong.clone();
        oyDuong.rotate(pointO, 3.14 / 2 + 3.14 / 4);
        MyLine oyAm = oxDuong.clone();
        oyAm.rotate(pointO, -3.14 / 4);
        oyAm.setColor(Color.CYAN);
        oxDuong.draw(g);
        oyDuong.draw(g);
        ozDuong.draw(g);
        int dashLength = 10;
        int density = 10;
        oxAm.drawDash(g,dashLength,density);
        oyAm.drawDash(g, dashLength, density);
        ozAm.drawDash(g, dashLength, density);
        // ve ten truc
        g.setColor(Color.BLACK);
        g.drawString("O", (screenhWidth/2) - 15, (screenHeight/2)- 15);
        g.drawString("X", screenhWidth, (screenHeight/2) + 15);
        g.drawString("Y", (int) (oyDuong.getpB().x) - 15, (screenHeight) - 15);
        g.drawString("Z", (screenhWidth/2) -15 , 0 + 15);
    }
}

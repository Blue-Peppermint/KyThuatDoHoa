/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Paint_3D;
import static Model.BasicMethod.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author chuon
 */
public class MySphere {

    private MyCircle circle;
    private MyEllipse ellipse;
    private MyPoint3D pO3D;
    private MyPoint pO2D;
    private int radius;
    private Color color;
    private Boolean isNew = true;
    public MySphere(MyPoint3D pO3D, MyPoint pO2D,int radius, Color color) {
        this.pO3D = pO3D;
        this.pO2D = pO2D;
        this.radius = (radius);
        this.color = color;
        this.circle = new MyCircle(pO2D, radius, color);
        int banTrucLon = radius;
        int banTrucBe = (int) (radius * Math.sqrt(2) / 4);
        this.ellipse = new MyEllipse(pO2D, banTrucLon, banTrucBe, color);
    }
    
    public void draw(Graphics g){
        
        // ve tam
        putPixel(g, pO2D.x, pO2D.y, Color.RED);
        // ve hinh tron
        circle.draw(g);
        // ve ellipse
        int dashLength = 6;
        int density = 3;
        ellipse.drawDash(g, dashLength, density);
        // viet thong so sang ben canh man hinh luon. Viet goc Phai Man Hinh. Viet 1 lan
        if (isNew) {
            isNew = false;
            int screenWitdh = (int) Paint_3D.drawingLocation.getWidth();
            int screeenHeight = (int) Paint_3D.drawingLocation.getHeight();
            String toaDoTam = "O( " + pO3D.x
                    + ", " + pO3D.y + ", " + pO3D.z + ")";;
            String banKinhStr = "Bán Kính: " + (int) convertDistantToVirtual(radius);
            g.drawString("O", (int) pO2D.x - 10, (int) pO2D.y);
            g.drawString(toaDoTam, screenWitdh - 200, screeenHeight / 2 + 50);
            g.drawString(banKinhStr, screenWitdh - 200, screeenHeight / 2 + 75);
        }

    }
}

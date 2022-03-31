/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import static Model.BasicMethod.putPixel;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Model.BasicMethod.*;
/**
 *
 * @author chuon
 */
public class MyLine {
    private MyPoint pA; 
    private MyPoint pB;
    private Color color;
      
    public MyLine() {
        this.pA = null;
        this.pB = null;
    }

    public MyLine(MyPoint pA, MyPoint pB) {
        this.pA = pA;
        this.pB = pB;
        this.color = Color.BLACK;
    }

    public MyLine(MyPoint pA, MyPoint pB, Color color) {
        this.pA =  new MyPoint (pA);
        this.pB = new MyPoint (pB);
        this.color = color;
    }

    public void setData(MyPoint pA, MyPoint pB) {
        this.pA = pA;
        this.pB = pB;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public MyPoint getpA() {
        return pA;
    }

    public MyPoint getpB() {
        return pB;
    }
    
    
        public void rotate(MyPoint pTamQuay, double angleRadian) {
        // angle: -3.14 <= angle <= 3.14 (goc radian)
        // pTamQuay toaDo Tam quay sau khi da converted        
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        double xA = ((pA.x - pTamQuay.x) * cos - (pA.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yA = ((pA.x - pTamQuay.x) * sin + (pA.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do A moi
        pA = new MyPoint(xA, yA);
        double xB = ((pB.x - pTamQuay.x) * cos - (pB.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yB = ((pB.x - pTamQuay.x) * sin + (pB.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do B moi
        pB = new MyPoint(xB, yB);
    }
    
    public void draw(Graphics g){
         int x1 = (int) pA.x;
         int y1 = (int) pA.y;
         int x2 = (int) pB.x;
         int y2 = (int) pB.y;        
         float dist = ((Math.abs(x1 - x2) > Math.abs(y1 - y2)) ? Math.abs(x1 - x2) 
                : Math.abs(y1 - y2));
        float now_x = x1, now_y = y1;
        int push_x, push_y;
        int push = 0, next = 0;
        for (int i = 0; i < dist; i++) {           
            push_x = Math.round(now_x);
            push_y = Math.round(now_y);
            putPixel(g,  push_x, push_y, color);
            now_x += (x2 - x1) / dist;
            now_y += (y2 - y1) / dist;

        }
    }

        public void drawDash(Graphics g, int dashLength, int density) {
        Boolean drawing = true;
        int countNotDraw = 0;
        int countDraw = 0;
        int x1 = (int) pA.x;
        int y1 = (int) pA.y;
        int x2 = (int) pB.x;
        int y2 = (int) pB.y;
        float dist = ((Math.abs(x1 - x2) > Math.abs(y1 - y2)) ? Math.abs(x1 - x2)
                : Math.abs(y1 - y2));
        float now_x = x1, now_y = y1;
        int push_x, push_y;
        int push = 0, next = 0;
        for (int i = 0; i < dist; i++) {
            if (drawing) {
                if (countDraw == dashLength) {
                    countNotDraw = 0;
                    drawing = false;
                } else {
                    push_x = Math.round(now_x);
                    push_y = Math.round(now_y);
                    putPixel(g, push_x, push_y, color);
                    countDraw++;
                }
            } else {
                if (countNotDraw == density) {
                    countDraw = 0;
                    drawing = true;
                } else {
                    countNotDraw++;
                }
            }
  
//            if (i % 5 != 0) {
//                push_x = Math.round(now_x);
//                push_y = Math.round(now_y);
//                putPixel(g, push_x, push_y, color);
//            }
            now_x += (x2 - x1) / dist;
            now_y += (y2 - y1) / dist;
        }
    }

    @Override
    public MyLine clone() {
        MyLine line = new MyLine(pA, pB, color);
        return line;
    }
}

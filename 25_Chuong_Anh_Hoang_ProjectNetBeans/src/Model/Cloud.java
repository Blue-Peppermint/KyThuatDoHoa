/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Paint_2D;
import static Model.BasicMethod.donViPixel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author chuon
 */
public class Cloud {
    
    private MyCircle circleCenter;
    private MyCircle circleLeft;
    private MyCircle circleRight;
    private MyPoint pOCenter;
    private MyPoint pOLeft;
    private MyPoint pORight;
    private ArrayList<MyRectangle> listRect;
    //private ArrayList<Boolean> increaseS; // trang thai dang tang hay giam cua tung Rect
//    private double bienDoGiam1; // bien do giam Minimum canh AC cua tung Rect = canh AB
//    private double bienDoTang1; // bien do tang Maximum canh AC cua tung Rect
//    private double speed1; // toc do tang giam moi lan renderer
    private Color color1;
    private double dropWitdh; // do day hat mua
    private double dropHeight; // do dai hat mua
    private double dropSpeed;
    private double land; // mat dat = noi mua se bien mat
    private double cloudSpeed;
    private Boolean life = true; // life = true van con ve, = false Huy di, ve lai tu dau
    
    public Cloud(MyPoint pO, Color color1) {
        //circle = new MyCircle(pO, radius, Color.RED);
        circleCenter = new MyCircle(pO, 10 * donViPixel, color1);
        int distanctBetweenLeft_Center = 50;
        int distanctBetweenRight_Center = 50;
         pOLeft = new MyPoint(pO.x - distanctBetweenLeft_Center, pO.y);
         pORight = new MyPoint(pO.x + distanctBetweenRight_Center, pO.y);
        circleLeft = new MyCircle(pOLeft, 8 * donViPixel, color1);
        circleRight = new MyCircle(pORight, 8 * donViPixel, color1);
        this.pOCenter = pO;
        this.color1 = color1;
//        bienDoGiam1 = donViPixel * 0.5;
//        bienDoTang1 = donViPixel * 10;
        dropWitdh = donViPixel * 0.25;
        dropHeight = donViPixel * 2;
        dropSpeed = 25;
        land = Paint_2D.drawingLocation.getHeight()/2;
        cloudSpeed = 10;
        listRect = new ArrayList<>();
        khoiTaoListRect();
    }

    public Boolean getLife() {
        return life;
    }

    public MyCircle getCircleCenter() {
        return circleCenter;
    }

    public MyCircle getCircleLeft() {
        return circleLeft;
    }

    public MyCircle getCircleRight() {
        return circleRight;
    }

    private void khoiTaoListRect() {
        int soLuongRectMaximum = 10;        
        Random rand = new Random();
        int soLuongRect = rand.nextInt(soLuongRectMaximum);
        for (int i = 0; i < soLuongRect; i++) {
            int xAppear = rand.nextInt((int) ((int) (pORight.x - pOLeft.x) + circleLeft.getBanKinh() *2));
            MyRectangle rect = new MyRectangle(new MyPoint(pOLeft.x + 
                    (xAppear) - circleLeft.getBanKinh(), pOCenter.y + 5 * donViPixel + 15),
                    dropWitdh, dropHeight, new Color(0, 255, 255));
            listRect.add(rect);
        }
    }

    // hieu ung tao dam mua
    public void raining(){
        khoiTaoListRect();
        for(int i = 0; i <listRect.size(); i++){
            MyRectangle rect = listRect.get(i);
            rect.setpA(new MyPoint(rect.getpA().x, rect.getpA().y + dropSpeed));
            // yA != yB de tao hieu ung hat mua bi trong luc keo xuong (phan duoi cua hat mua day hon)
            rect.setpB(new MyPoint(rect.getpB().x, rect.getpA().y + dropSpeed));
            rect.setpC(new MyPoint(rect.getpC().x, rect.getpA().y + dropSpeed));
            rect.setpD(new MyPoint(rect.getpD().x, rect.getpA().y + dropSpeed));
            if (rect.getpA().y > land) {
                listRect.remove(i);
            }
        }
    }
    
    // hieu ung di chuyen may
    public void moving() {
        pOLeft = new MyPoint(pOLeft.x + cloudSpeed, pOLeft.y);
        pOCenter = new MyPoint(pOCenter.x + cloudSpeed, pOCenter.y);
        pORight = new MyPoint(pORight.x + cloudSpeed, pORight.y);       
        circleLeft.setpO(pOLeft);
        circleCenter.setpO(pOCenter);
        circleRight.setpO(pORight);
        // di chuyen hat mua?
//        for (int i = 0; i < listRect.size(); i++) {
//            MyRectangle rect = listRect.get(i);
//            rect.setpA(new MyPoint(rect.getpA().x, rect.getpA().y + dropSpeed));
//            rect.setpB(new MyPoint(rect.getpB().x, rect.getpA().y + dropSpeed));
//            rect.setpC(new MyPoint(rect.getpC().x, rect.getpA().y + dropSpeed));
//            rect.setpD(new MyPoint(rect.getpD().x, rect.getpA().y + dropSpeed));
//            if (rect.getpA().y > land) {
//                listRect.remove(i);
//            }
//        }
        if(pOLeft.x > Paint_2D.drawingLocation.getWidth()){
            life = false;
        }
    }

    public void draw(Graphics g) {
        circleCenter.draw(g);
        circleLeft.draw(g);
        circleRight.draw(g);
        g.fillArc((int) pOLeft.x - 40, (int) pOLeft.y - 40, (int) (circleLeft.getBanKinh() * 2),
                (int) (circleLeft.getBanKinh() * 2), 0, 360);
        g.fillArc((int) pOCenter.x - 50, (int) pOCenter.y - 50, (int) (circleCenter.getBanKinh() * 2),
                (int) (circleCenter.getBanKinh() * 2), 0, 360);
        g.fillArc((int) pORight.x - 40, (int) pORight.y - 40, (int) (circleRight.getBanKinh() * 2),
                (int) (circleRight.getBanKinh() * 2), 0, 360);
        for (MyRectangle rect : listRect) {
            rect.draw(g);
        }
    }
}

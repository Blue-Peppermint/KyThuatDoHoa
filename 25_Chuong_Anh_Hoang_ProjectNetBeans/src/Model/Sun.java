/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.ArrayList;
import static Model.BasicMethod.*;
import java.awt.Graphics;
import java.util.Random;
/**
 *
 * @author chuon
 */
public class Sun {

    private MyCircle circle;
    private MyPoint pO;
    private ArrayList<MyRectangle> listRect;
    private ArrayList<Boolean> increaseS; // trang thai dang tang hay giam cua tung Rect
    private double bienDoGiam1; // bien do giam Minimum canh AC cua tung Rect = canh AB
    private double bienDoTang1; // bien do tang Maximum canh AC cua tung Rect
    private double speed1; // toc do tang giam moi lan renderer
    private Color color1;

    public Sun(MyPoint pO, Color color1) {
        //circle = new MyCircle(pO, radius, Color.RED);
        circle = new MyCircle(pO, 5 * donViPixel, Color.RED);
        this.pO = pO;
        bienDoGiam1 = donViPixel * 0.5;
        bienDoTang1 = donViPixel * 8;
        khoiTaoListRect();
    }

    public MyCircle getCircle() {
        return circle;
    }   
    
    private void khoiTaoListRect() {
        int soLuongRect = 10;
        listRect = new ArrayList<>();
        increaseS = new ArrayList<>();
        for (int i = 0; i < soLuongRect; i++) {
            MyRectangle rect = new MyRectangle(new MyPoint(pO.x, pO.y + 5 * donViPixel + 15),
                    bienDoGiam1, bienDoTang1, Color.YELLOW);
            rect.rotate(pO, 2 * 3.14 * (i + 1)/soLuongRect);
            listRect.add(rect);
            increaseS.add(Boolean.FALSE);
        }
    }

    public void moving(){
        for(int i = 0; i <listRect.size(); i++){
            MyRectangle rect = listRect.get(i);
            int doDaiAC_new = (int) rect.getDoDaiAC();
            Random rand = new Random();
            int random = rand.nextInt((int) (bienDoTang1 - bienDoGiam1));
            for(int j = 0; j < random; j++){
                // kiem tra se tang hay giảm
                if(rect.getDoDaiAC() <= bienDoGiam1){
                    increaseS.set(i, Boolean.TRUE);
                }
                else if(rect.getDoDaiAC() >= bienDoTang1){
                    increaseS.set(i, Boolean.FALSE);
                }
                // neu dang tang
                if(increaseS.get(i)){
                    doDaiAC_new++;
                }
                else{
                    doDaiAC_new--;
                }               
            }
            if(doDaiAC_new < bienDoGiam1){
                doDaiAC_new = 0;
            }
            rect.setDoDaiAC(doDaiAC_new);
            MyPoint pA_origin = new MyPoint(pO.x, pO.y + 5 * donViPixel + 15);
            rect.calOtherPointBaseA_Angle(pO, pA_origin);
            rect.rotate(pO, 0.7);
        }
    }
    
    public void draw(Graphics g) {
        circle.draw(g);
        int duongKinh = (int)circle.getBanKinh() *2;
        g.fillArc((int)circle.getpO().x - 25, (int) circle.getpO().y - 25, duongKinh, duongKinh, 0, 720);
        for (MyRectangle rect : listRect) {
            rect.draw(g);
        }
    }

}

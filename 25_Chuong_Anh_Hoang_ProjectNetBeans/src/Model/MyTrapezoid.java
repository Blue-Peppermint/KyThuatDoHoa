/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.BasicMethod.putPixel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author chuon
 */
public class MyTrapezoid {

    private MyPoint pA;
    private MyPoint pB = null;
    private MyPoint pC;
    private MyPoint pD = null;
    private int canhTren;
    private Color color = null;

    public MyTrapezoid() {
        this.pA = null;
        this.pC = null;
        canhTren = -1;
    }

    public MyTrapezoid(MyPoint pA, MyPoint pC, int canhTren) {
        this.pA = pA;
        this.pC = pC;
        this.canhTren = canhTren;
        calOtherPoint();
    }
    
    public MyTrapezoid(MyPoint pA, MyPoint pC, int canhTren, Color color) {
        this.pA = pA;
        this.pC = pC;
        this.canhTren = canhTren;
        this.color = color;
        calOtherPoint();
    }
    
    public void setData(MyPoint pA, MyPoint pC, int canhTren) {
        this.pA = pA;
        this.pC = pC;
        this.canhTren = canhTren;
        calOtherPoint();
    }

    public MyPoint getpA() {
        return pA;
    }

    public void setpA(MyPoint pA) {
        this.pA = pA;
    }

    public MyPoint getpB() {
        return pB;
    }

    public void setpB(MyPoint pB) {
        this.pB = pB;
    }

    public MyPoint getpC() {
        return pC;
    }

    public void setpC(MyPoint pC) {
        this.pC = pC;
    }

    public MyPoint getpD() {
        return pD;
    }

    public void setpD(MyPoint pD) {
        this.pD = pD;
    }

    public int getCanhTren() {
        return canhTren;
    }

    public void setCanhTren(int canhTren) {
        this.canhTren = canhTren;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    
    
    private void calOtherPoint(){
        pB = new MyPoint(pA.x + canhTren, pA.y);
        // H la chan duong cao AH
        int chieuDai_CH = (int) Math.abs(pA.x - pC.x);
        pD = new MyPoint(pB.x + chieuDai_CH, pC.y);
    }

    public void draw(Graphics g) {
        // thuat toan midpoint
        // vẽ hình thang Cân pA.x > pC.x && pA.y < pC.y
        // vd:         MyPoint pA = new MyPoint(convertX(20),convertY(30));
        //             MyPoint pC = new MyPoint(convertX(18), convertY(25));
        //             canhTren = 40;
        MyLine ab = new MyLine(pA, pB, color);
        MyLine bd = new MyLine(pB, pD, color);
        MyLine dc = new MyLine(pD, pC, color);
        MyLine ca = new MyLine(pC, pA, color);
        g.setColor(getColor());
        ab.draw(g);
        bd.draw(g);
        dc.draw(g);
        ca.draw(g);
    }

}

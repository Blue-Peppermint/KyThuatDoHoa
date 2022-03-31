/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Paint_2D;
import static Model.BasicMethod.convertXToReality;
import static Model.BasicMethod.convertXToVirtual;
import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author chuon
 */
public class MyTriangle {

    private MyPoint pA;
    private MyPoint pB;
    private MyPoint pC;
    private Color color = null;
    // chỉ có một mình triangle quay (cối xoay gió) nên chỉ Class này mới có thuộc tính này, con co MyRectangle nua
    private double angleRadian; 

    
    public MyTriangle() {
        this.pA = null;
        this.pB = null;
        this.pC = null;
    }

    public MyTriangle(MyPoint pA, MyPoint pB, MyPoint pC) {
        this.pA = new MyPoint(pA.x, pA.y);
        this.pB = new MyPoint(pB.x, pB.y);
        this.pC = new MyPoint(pC.x, pC.y);
    }

    public MyTriangle(MyPoint pA, MyPoint pB, MyPoint pC, Color color) {
        this.pA = new MyPoint(pA.x, pA.y);
        this.pB = new MyPoint(pB.x, pB.y);
        this.pC = new MyPoint(pC.x, pC.y);
        this.color = color;
    }

    public void setData(MyPoint pA, MyPoint pB, MyPoint pC) {
        this.pA = new MyPoint(pA.x, pA.y);
        this.pB = new MyPoint(pB.x, pB.y);
        this.pC = new MyPoint(pC.x, pC.y);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getAngleRadian() {
        return angleRadian;
    }

    @Override
    public String toString() {
        return "MyTriangle{" + "pA=" + pA + ", pB=" + pB + ", pC=" + pC + ", angleRadian=" + angleRadian + '}';
    }
    
    public void draw(Graphics g) {
        // thuat toan midpoint
        MyLine ab = new MyLine(pA, pB,color);
        MyLine bc = new MyLine(pB, pC, color);
        MyLine ca = new MyLine(pC, pA, color);
        g.setColor(getColor());
        ab.draw(g);
        bc.draw(g);
        ca.draw(g);
    }

    public void rotate(MyPoint pTamQuay, double angleRadian) {
        // angle: -3.14 <= angle <= 3.14 (goc radian)
        // pTamQuay toaDo Tam quay sau khi da converted
        // quay 1 góc bao nhiêu đó so với 
        double angleRadianNow = this.angleRadian + angleRadian;  
        double cos = Math.cos(angleRadianNow);
        double sin = Math.sin(angleRadianNow);
        double xA = ((pA.x - pTamQuay.x) * cos - (pA.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yA = ((pA.x - pTamQuay.x) * sin + (pA.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do A moi
        pA = new MyPoint(xA, yA);
        double xB =  ((pB.x - pTamQuay.x) * cos - (pB.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yB =  ((pB.x - pTamQuay.x) * sin + (pB.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do B moi
        pB = new MyPoint(xB, yB);
        double xC =  ((pC.x - pTamQuay.x) * cos - (pC.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yC =  ((pC.x - pTamQuay.x) * sin + (pC.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do C moi
        pC = new MyPoint(xC, yC);
        this.angleRadian = angleRadianNow;
    }

    public MyTriangle mirrorThroughOY() {
        // tạo bản sao của triangle đối xứng qua trục OY
        //double xA_origin = convertXToVirtual(this.pA.x);
        double xA_clone = convertXToVirtual(this.pA.x) * -1.0;
        double yA_clone  = this.pA.y;
        double xB_clone = convertXToVirtual(this.pB.x) * -1.0;
        double yB_clone  = this.pB.y;
        double xC_clone = convertXToVirtual(this.pC.x) * -1.0;
        double yC_clone  = this.pC.y;
        MyPoint pA_clone = new MyPoint(convertXToReality(xA_clone),yA_clone);
        MyPoint pB_clone = new MyPoint(convertXToReality(xB_clone),yB_clone);
        MyPoint pC_clone = new MyPoint(convertXToReality(xC_clone),yC_clone);
        MyTriangle triangle_clone = new MyTriangle(pA_clone, pB_clone, pC_clone, color);
        triangle_clone.angleRadian =  - this.angleRadian;
        return triangle_clone;
    }
}

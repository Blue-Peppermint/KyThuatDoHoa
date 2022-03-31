/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.BasicMethod.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

/**
 *
 * @author chuon
 */
public class MyRectangle {

    private MyPoint pA;
    private MyPoint pB;
    private MyPoint pC;
    private MyPoint pD;
    private double doDaiAB;
    private double doDaiAC;
    private Color color;
    private double angleRadian; 
    
    public MyRectangle() {
        this.pA = null;
        this.pB = null;
        this.pC = null;
        this.pD = null;
        this.doDaiAB = -1;
        this.doDaiAC = -1;
        this.color = Color.BLACK;
    }

    public MyRectangle(MyPoint pA, double doDaiAB, double doDaiAC) {
        // cẩn thận khi sử dụng hàm này chứ ko color = null 
        this.pA = pA;
        this.doDaiAB = doDaiAB;
        this.doDaiAC = doDaiAC;
        calOtherPointBaseA();
    }

    public MyRectangle(MyPoint pA, double doDaiAB, double doDaiAC, Color color) {
        this.pA = new MyPoint(pA);
        this.doDaiAB = doDaiAB;
        this.doDaiAC = doDaiAC;
        this.color = color;
        calOtherPointBaseA();
    }

    public MyRectangle(MyPoint pA, MyPoint pB, MyPoint pC, MyPoint pD, Color color) {
        // hàm này đặc biệt dành riêng cho trường hợp setUP khi đã xác định rõ những điểm sẽ vẽ
        this.pA = pA;
        this.pB = pB;
        this.pC = pC;
        this.pD = pD;
        this.color = color;
    }
    
    public void setData(MyPoint pA, double doDaiAB, double doDaiAC) {
        this.pA = pA;
        this.doDaiAB = doDaiAB;
        this.doDaiAC = doDaiAC;
        calOtherPointBaseA();
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

    public double getDoDaiAB() {
        return doDaiAB;
    }

    public void setDoDaiAB(double doDaiAB) {
        this.doDaiAB = doDaiAB;
    }

    public double getDoDaiAC() {
        return doDaiAC;
    }

    public void setDoDaiAC(double doDaiAC) {
        this.doDaiAC = doDaiAC;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "MyRectangle{" + "pA=" + pA + ", pB=" + pB + ", pC=" + pC + ", pD=" + pD + '}';
    }
    
    public void calOtherPointBaseA() {
        pB = new MyPoint(pA.x + doDaiAB, pA.y);
        pC = new MyPoint(pA.x, pA.y + doDaiAC);
        pD = new MyPoint(pB.x, pC.y);
    }
    
    public void calOtherPointBaseA_Angle(MyPoint pTamQuay, MyPoint pA_origin){
        // tinh toan toa do cac diem khac dua vao A + goc quay hien tai
        double angleRadianNow = this.angleRadian;          
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);       
        pA = new MyPoint(pA_origin);
        pB = new MyPoint(pA.x + doDaiAB, pA.y);        
        pC = new MyPoint(pA.x, pA.y + doDaiAC);
        pD = new MyPoint(pB.x, pC.y);
                double xA = ((pA.x - pTamQuay.x) * cos - (pA.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yA =  ((pA.x - pTamQuay.x) * sin + (pA.y - pTamQuay.y) * cos) + pTamQuay.y;
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
        double xD =  ((pD.x - pTamQuay.x) * cos - (pD.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yD =  ((pD.x - pTamQuay.x) * sin + (pD.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do D moi
        pD = new MyPoint(xD, yD);
        
    }
    
    public void draw(Graphics g) {
        // thuat toan midpoint
        g.setColor(color);
        MyLine ab = new MyLine(pA, pB, color);
        MyLine bd = new MyLine(pB, pD, color);
        MyLine dc = new MyLine(pD, pC, color);
        MyLine ca = new MyLine(pC, pA, color);
        ab.draw(g);
        bd.draw(g);
        dc.draw(g);
        ca.draw(g);
    }

    public void rotate(MyPoint pTamQuay, double angleRadian) {
        // angle: -3.14 <= angle <= 3.14 (goc radian)
        // pTamQuay toaDo Tam quay sau khi da converted
        double angleRadianNow = this.angleRadian + angleRadian;          
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        double xA = ((pA.x - pTamQuay.x) * cos - (pA.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yA =  ((pA.x - pTamQuay.x) * sin + (pA.y - pTamQuay.y) * cos) + pTamQuay.y;
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
        double xD =  ((pD.x - pTamQuay.x) * cos - (pD.y - pTamQuay.y) * sin) + pTamQuay.x;
        double yD =  ((pD.x - pTamQuay.x) * sin + (pD.y - pTamQuay.y) * cos) + pTamQuay.y;
        // toa do D moi
        pD = new MyPoint(xD, yD);
        this.angleRadian = angleRadianNow;
    }

    private void rotateAroundCoordinateCenter(double angle) {
        // angle: -3.14 <= angle <= 3.14
        // quay quanh tam O(toa do nay chua duoc converted) : ham nay test thu thoi, ko su dung.
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        // quay diem A tai tam O
        int xA = (int) ((pA.x) * cos - (pA.y) * sin);
        int yA = (int) ((pA.x) * sin + (pA.y) * cos);
        // toa do A moi
        pA = new MyPoint(xA, yA);
        int xB = (int) ((pB.x) * cos - (pB.y) * sin);
        int yB = (int) ((pB.x) * sin + (pB.y) * cos);
        // toa do B moi
        pB = new MyPoint(xB, yB);
        int xC = (int) ((pC.x) * cos - (pC.y) * sin);
        int yC = (int) ((pC.x) * sin + (pC.y) * cos);
        // toa do C moi
        pC = new MyPoint(xC, yC);
        int xD = (int) ((pD.x) * cos - (pD.y) * sin);
        int yD = (int) ((pD.x) * sin + (pD.y) * cos);
        // toa do D moi
        pD = new MyPoint(xD, yD);
    }

    public MyRectangle mirrorThroughOY(){
        // tạo bản sao của rect đối xứng qua trục OY, với trục tọa độ OY đi qua điểm toaDoX
        // toaDoX = 
//        double xA_clone = convertXToVirtual(this.pA.x) * -1.0;
//        double yA_clone  = this.pA.y;
//        double xB_clone = convertXToVirtual(this.pB.x) * -1.0;
//        double yB_clone  = this.pB.y;
//        double xC_clone = convertXToVirtual(this.pC.x) * -1.0;
//        double yC_clone  = this.pC.y;
//        double xD_clone = convertXToVirtual(this.pD.x) * -1.0;
//        double yD_clone  = this.pD.y;
//        MyPoint pA_clone = new MyPoint(convertXToReality(xA_clone),yA_clone);
//        MyPoint pB_clone = new MyPoint(convertXToReality(xB_clone),yB_clone);
//        MyPoint pC_clone = new MyPoint(convertXToReality(xC_clone),yC_clone);
//        MyPoint pD_clone = new MyPoint(convertXToReality(xD_clone),yD_clone);
//        MyRectangle rect_clone = new MyRectangle(pA_clone, pB_clone, pC_clone, pD_clone, color);
//        System.out.println("base rect old: "+ this);
//        System.out.println("base rect new: " + rect_clone);

        double xA_clone = (this.pA.x) * -1.0;
        double yA_clone  = this.pA.y;
        double xB_clone = (this.pB.x) * -1.0;
        double yB_clone  = this.pB.y;
        double xC_clone = (this.pC.x) * -1.0;
        double yC_clone  = this.pC.y;
        double xD_clone = (this.pD.x) * -1.0;
        double yD_clone  = this.pD.y;
        MyPoint pA_clone = new MyPoint((xA_clone),yA_clone);
        MyPoint pB_clone = new MyPoint((xB_clone),yB_clone);
        MyPoint pC_clone = new MyPoint((xC_clone),yC_clone);
        MyPoint pD_clone = new MyPoint((xD_clone),yD_clone);
        MyRectangle rect_clone = new MyRectangle(pA_clone, pB_clone, pC_clone, pD_clone, color);
        return rect_clone;
    }
    
}

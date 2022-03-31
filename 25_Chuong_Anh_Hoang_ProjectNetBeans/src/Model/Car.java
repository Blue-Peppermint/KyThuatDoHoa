/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import static Model.BasicMethod.*;

/**
 *
 * @author chuon
 */
class Car_window {

    private MyTrapezoid trapezoid;
    private Color color;
    
    public Car_window( Color color, MyPoint pCar) {
        this.color = color;
        setUpData(pCar);
    }

    public MyTrapezoid getTrapezoid() {
        return trapezoid;
    }

    private void setUpData(MyPoint pCar) {
//        MyPoint pA = new MyPoint(convertX(20 - 20), convertY(30 + -30));
//        MyPoint pC = new MyPoint(convertX(18 - 20), convertY(25 + -30));
        MyPoint pA = new MyPoint(convertXToReality(0 + pCar.x), convertYToReality(0 + pCar.y));
        MyPoint pC = new MyPoint(convertXToReality(-2 + pCar.x), convertYToReality(-5 + pCar.y));
//        MyPoint pA = new MyPoint((0 + pCar.x), (0 + pCar.y));
//        MyPoint pC = new MyPoint((-2 + pCar.x), (-5 + pCar.y));
        int canhTren = 90;
        trapezoid = new MyTrapezoid(pA, pC, canhTren, color);
    }

    public void draw(Graphics g) {
        trapezoid.draw(g);
    }
}

class Car_roofPanel {

    private MyTrapezoid trapezoid;
    private Color color;
    
    public Car_roofPanel(Color color, Car_window window) {
        this.color = color;
        setUpData(window);
    }

    public MyTrapezoid getTrapezoid() {
        return trapezoid;
    }
    
    private void setUpData(Car_window window) {
        MyTrapezoid trapezoidWindow = window.getTrapezoid();
        int xA = (int) (trapezoidWindow.getpA().x - 5);
        int yA = (int) (trapezoidWindow.getpA().y - 3);
        MyPoint pA = new MyPoint(xA, yA);
        int xC = (int) (trapezoidWindow.getpC().x - 10);
        int yC = (int) (trapezoidWindow.getpC().y +8);
        MyPoint pC = new MyPoint(xC, yC);
        // bang canh tren cua car_window + 10
        int canhTren = trapezoidWindow.getCanhTren() + 10;
        trapezoid = new MyTrapezoid(pA, pC, canhTren, color);
    }

    public void draw(Graphics g) {
        trapezoid.draw(g);
    }

}

class Car_mirrors {
    private MyRectangle rect1;
    private MyRectangle rect2;
    private Color color;
    
    
    public Car_mirrors(Color color, Car_roofPanel roof){
        this.color = color;
        setUpData(roof);
    }

    public MyRectangle getRect1() {
        return rect1;
    }

    public MyRectangle getRect2() {
        return rect2;
    }
    
    private void setUpData(Car_roofPanel roof){
        MyTrapezoid trapezoidRoof = roof.getTrapezoid();
        MyPoint pA1 = new MyPoint(trapezoidRoof.getpC().x, trapezoidRoof.getpC().y);
        int doDaiAB = 15;
        int doDaiAC = 10;
        rect1 = new MyRectangle(pA1, doDaiAB, doDaiAC, color);
        // hcn quay quanh tam cua dinh A của chính HCN đó
        double xATamQuay1 = rect1.getpA().x;
        double yATamQuay1 = rect1.getpA().y;
        MyPoint pATamQuay1 = new MyPoint(xATamQuay1, yATamQuay1);
        rect1.rotate(pATamQuay1, 3.6);
        MyPoint pA2 = new MyPoint(trapezoidRoof.getpD().x, trapezoidRoof.getpD().y - doDaiAC);
        rect2 = new MyRectangle(pA2, doDaiAB, doDaiAC, color);
        double xATamQuay2 = rect2.getpC().x;
        double yATamQuay2 = rect2.getpC().y;
        MyPoint pATamQuay2 = new MyPoint(xATamQuay2, yATamQuay2);
        rect2.rotate(pATamQuay2, 5.95);
    }
    
    public void draw(Graphics g){
        rect1.draw(g);
        rect2.draw(g);
    }
}

class Car_rear {

    private MyTrapezoid trapezoid;
    private Color color;
    
    public Car_rear(Color color, Car_roofPanel roof) {
        this.color = color;
        setUpData(roof);
    }

    public MyTrapezoid getTrapezoid() {
        return trapezoid;
    }
    
    private void setUpData(Car_roofPanel roof) {
        // point A chia theo ti le cua PointC car_RoofPanel
        MyTrapezoid trapezoidRoof = roof.getTrapezoid();
        int xA = (int) (trapezoidRoof.getpC().x - 2);
        int yA = (int) trapezoidRoof.getpC().y;
        MyPoint pA = new MyPoint(xA, yA);
        // |xA - xC| < car_window && |yA - yC| > car_window
        int xC = xA - 3;
        int yC = yA + 30;
        MyPoint pC = new MyPoint(xC, yC);
        // bang canh Tren cua car_roofPanel + 46
        int canhTren = trapezoidRoof.getCanhTren() + 35;
        trapezoid = new MyTrapezoid(pA, pC, canhTren, color);
    }

    public void draw(Graphics g) {
        trapezoid.draw(g);
    }

}

class Car_tires {

    private MyRectangle rect1;
    private MyRectangle rect2;
    private Color color;
    
    public Car_tires(Color color, Car_rear rear) {
        this.color = color;
        setUpData(rear);
    }

    public MyRectangle getRect1() {
        return rect1;
    }

    public MyRectangle getRect2() {
        return rect2;
    }   
    
    private void setUpData(Car_rear rear) {
        MyTrapezoid trapezoidRear = rear.getTrapezoid();
        // point A chia theo ti le cua PointC car_rear
        double xA1 = trapezoidRear.getpC().x;
        double yA1 = trapezoidRear.getpC().y;       
        MyPoint pA1 = new MyPoint(xA1, yA1);
        int doDaiAC = 30;
        int doDaiAB = 20;
        rect1 = new MyRectangle(pA1, doDaiAB, doDaiAC, color);
        // xB = xD cua car_rear - doDaiAC vua tinh tren
        // neu tinh se kha phien vay nen chung ta nen tao 1 class cho tra ve gia tri A,B,C,D cua tat ca ca hinh da ve
        MyPoint pB = new MyPoint(convertYToReality(25 - 2 - 6), convertYToReality(25 - 2 - 6));
        double xA2 = trapezoidRear.getpD().x - doDaiAB;
        double yA2 = trapezoidRear.getpD().y;
        MyPoint pA2 = new MyPoint(xA2, yA2);
        rect2 = new MyRectangle(pA2, doDaiAB, doDaiAC, color);
    }
    
    public void draw(Graphics g) {
        rect1.draw(g);
        rect2.draw(g);
    }
}

class Car_bumper {
    private MyRectangle rect;
    private Color color;
    
    public Car_bumper(Color color, Car_tires tires){
        this.color = color;
        setUpData(tires);
    }
    
    private void setUpData(Car_tires tires){
        MyRectangle tire1 = tires.getRect1();
        MyRectangle tire2 = tires.getRect2();
        MyPoint pA = new MyPoint (tire1.getpB().x, tire1.getpB().y);
        int doDaiAB = (int) (tire2.getpA().x - tire1.getpB().x);
        int doDaiAC = 10;
        rect = new MyRectangle(pA, doDaiAB, doDaiAC, color);
    }
    
    public void draw(Graphics g){
        rect.draw(g);
    }
}

public class Car {
    
    private Car_window window;
    private Car_roofPanel roof;
    private Car_mirrors mirror;
    private Car_rear rear;
    private Car_tires tires;
    private Car_bumper bumper;
    private MyRectangle mirrorRect1;
    private MyRectangle mirrorRect2;
    private MyTrapezoid windowTrapezoid;
    private MyTrapezoid rearTrapezoid;
    
    
    public Car(MyPoint pCar) {
        // pCar: toa do vi tri dat xe = toaDo Window xe (Dung toa do ảo)
        window = new Car_window( Color.WHITE, pCar);
        roof = new Car_roofPanel(Color.BLACK,window);
        mirror = new Car_mirrors(Color.RED, roof);
        rear = new Car_rear(Color.BLACK, roof);
        tires = new Car_tires(Color.BLACK, rear);
        bumper = new Car_bumper(Color.BLACK, tires);
        windowTrapezoid = window.getTrapezoid();
        mirrorRect1 = mirror.getRect1();
        mirrorRect2 = mirror.getRect2();
        rearTrapezoid = rear.getTrapezoid();
    }

    public Car_window getWindow() {
        return window;
    }

    public Car_roofPanel getRoof() {
        return roof;
    }

    public Car_mirrors getMirror() {
        return mirror;
    }

    public Car_rear getRear() {
        return rear;
    }

    public Car_tires getTires() {
        return tires;
    }

    public Car_bumper getBumper() {
        return bumper;
    }

    public MyRectangle getMirrorRect1() {
        return mirrorRect1;
    }

    public MyRectangle getMirrorRect2() {
        return mirrorRect2;
    }

    public MyTrapezoid getWindowTrapezoid() {
        return windowTrapezoid;
    }

    public MyTrapezoid getRearTrapezoid() {
        return rearTrapezoid;
    }
    
    public void draw(Graphics g) {
        window.draw(g);
        roof.draw(g);
        mirror.draw(g);
        rear.draw(g);   
        tires.draw(g);
        bumper.draw(g);
    }
}

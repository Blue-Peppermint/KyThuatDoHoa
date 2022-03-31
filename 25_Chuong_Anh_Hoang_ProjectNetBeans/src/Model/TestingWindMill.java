/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.BasicMethod.convertXToReality;
import static Model.BasicMethod.convertXToVirtual;
import static Model.BasicMethod.convertYToReality;
import static Model.BasicMethod.convertYToVirtual;
import static Model.BasicMethod.donViPixel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author chuon
 */
//
////<editor-fold defaultstate="collapsed" desc="comment">
//class Base {
//
//    private MyRectangle rect;
//    private Graphics g;
//    private Color color;
//    private int cDai;
//    private int cRong;
//    private Point pWindMill;
//
//    public Base(Graphics g, Color color, Point pWindMill, int cCaoBody) {
//        this.g = g;
//        this.color = color;
//        this.pWindMill = pWindMill;
//        // CDai = 1/3.0 cCao WindMill
//        this.cDai = (int) (cCaoBody * 1/1.5);
//        // CRong = 1/8 cDai Base
//        this.cRong = (int) (cDai * 1/10.0);
//        setUpData(pWindMill);
//    }
//
//    public MyRectangle getRect() {
//        return rect;
//    }
//
//    public int getcDai() {
//        return cDai;
//    }
//
//    public int getcRong() {
//        return cRong;
//    }
//
//    public Point getpWindMill() {
//        return pWindMill;
//    }
//
//    private void setUpData(Point pWindMill) {
//        int xA = (int) (pWindMill.x - (cDai / 2.0));
//        int yA = pWindMill.y + cRong;
//        Point pA = new Point(convertXToReality(xA), convertYToReality(yA));
//        int doDaiAB = cDai * donViPixel;
//        int doDaiAC = cRong * donViPixel;
//        rect = new MyRectangle(pA, doDaiAB, doDaiAC, color);
//    }
//
//    public void draw() {
//        rect.draw(g);
//    }
//}
//
//class Body {
//
//    private MyTriangle triangle;
//    private Graphics g;
//    private Color color;
//    private int chieuCao;
//
//    public Body(Graphics g, Color color, Base base, int chieuCao) {
//        this.g = g;
//        this.color = color;
//        this.chieuCao = chieuCao;
//        setUpData(base);
//    }
//
//    public MyTriangle getTriangle() {
//        return triangle;
//    }
//
//    public int getChieuCao() {
//        return chieuCao;
//    }
//
//    private void setUpData(Base base) {
//        MyRectangle rectBase = base.getRect();
//        // cDaiOThutLe = cRong
//        int cDaiThutLe = base.getcRong();
//        int xA = convertXToReality(convertXToVirtual(rectBase.getpA().x) + cDaiThutLe);
//        int yA = rectBase.getpA().y;
//        Point pA = new Point(xA, yA);
//        int xC = convertXToReality(convertXToVirtual(rectBase.getpB().x) - cDaiThutLe);
//        int yC = yA;
//        Point pC = new Point(xC, yC);
//        int xB = convertXToReality(base.getpWindMill().x);
//        int yB = convertYToReality(convertYToVirtual(yA) + chieuCao);
//        Point pB = new Point(xB, yB);
//        triangle = new MyTriangle(pA, pB, pC, color);
//    }
//
//    public void draw() {
//        triangle.draw(g);
//    }
//}
//
//class Blades{
//
//    private MyTriangle blade1;
//    private MyTriangle blade2;
//    private MyTriangle blade3;
//    private MyCircle circle;
//    private Graphics g;
//    private Color color;
//
//    public Blades(Graphics g, Color color, Base base, Body body) {
//        this.g = g;
//        this.color = color;
//        setUpData(base, body);
//    }
//
//    private void setUpData(Base base, Body body) {
//        MyTriangle triangleBody = body.getTriangle();
//        int xB = triangleBody.getpB().x;
//        int yB = triangleBody.getpB().y;
//        Point pB = new Point(xB, yB);
//        // chieu dai canh quat = 1/2 body
//        int xA = xB;
//        int yA = (int) (yB + (body.getChieuCao() * donViPixel * (1 / 2.0)));
//        Point pA = new Point(xA, yA);
//        // toa do diem C se la dinh cua goc vuong trong tam giac vuong ABC vuông tại A
//        Point pC = toaDoCTrongTamGiacVuong(pA, pB);
//        blade1 = new MyTriangle(pA, pB, pC, color);
//        blade2 = new MyTriangle(pA, pB, pC, color);
//        // rotate 2pi * 1/3.0
//        blade2.rotate(pB, 2 * 3.14 * 1 / 3);
//        blade3 = new MyTriangle(pA, pB, pC, color);
//        // rotate 2pi * 2/3.0
//        blade3.rotate(pB, 2 * 3.14 * 2 / 3);
//        // vẽ Circle có bán kính = 1/4 CRong của Base
//        Point O = new Point(xB, yB);
//        int banKinh = (int) (1 / 4.0 * base.getcRong() * donViPixel);
//        circle = new MyCircle(O, banKinh, color);
//    }
//
//    private Point toaDoCTrongTamGiacVuong(Point pA, Point pB) {
//        // tinh toa do diem C trong tam giac vuong ABC vuong tai A voi AC = 1/5 AB
//        int cDaiAB = (int) Math.sqrt(Math.pow(pA.y - pB.y, 2));
//        int cDaiAC = (int) (cDaiAB * (1 / 5.0));
//        int xC = (int) (Math.sqrt(Math.pow(cDaiAB, 2) + Math.pow(cDaiAC, 2) - Math.pow(cDaiAB, 2)) + pB.x);
//        int yC = pA.y;
//        return new Point(xC, yC);
//    }
//
//    public void draw() {
//        blade1.draw(g);
//        //System.out.println("Blade1 xA: " + convertXToVirtual(blade1.getpA().x) + " yA: " + convertYToVirtual(blade1.getpA().y));
//        blade2.draw(g);
//        //System.out.println("Blade2 xA: " + convertXToVirtual(blade2.getpA().x) + " yA: " + convertYToVirtual(blade2.getpA().y));
//        blade3.draw(g);
//        //System.out.println("Blade3 xA: " + convertXToVirtual(blade3.getpA().x) + " yA: " + convertYToVirtual(blade3.getpA().y));
//        circle.draw(g);
//    }
//}
//
//public class TestingWindMill {
//
//    private Graphics g;
//    private Base base;
//    private Body body;
//    private Blades blades;
//
//    public TestingWindMill(Graphics g, Point pWindMill, int cCaoBody) {
//        // pWinMill la toa do o giua cua chan de WindMill. Dùng tọa độ ảo
//        // cac thuoc tinh chieuDai, cCao cung Dùng tọa độ ảo
//        this.g = g;
//        base = new Base(g, Color.RED, pWindMill,cCaoBody );
//        body = new Body(g, Color.RED, base, cCaoBody);
//        blades = new Blades(g, Color.RED, base, body);
//    }
//
//    public void draw() {
//        base.draw();
//        body.draw();
//        blades.draw();
//    }
//}
////</editor-fold>
//

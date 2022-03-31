/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chuon
 */
public class BasicMethod {

    private static Graphics graphics;
    public static final int donViPixel = 5; // quy uoc 1 don vi ao = 5 don vi pixel
    // co the lam chuc nang cho tuy chinh donViPixel va cac vat se phong to theo y muon
    //public static Rectangle drawingLocation = new Rectangle();
    public static double toaDoXGoc = 0;
    public static double toaDoYGoc = 0;
    public static Graphics getGraphics() {
        return graphics;
    }

    public static void setGraphics(Graphics graphics) {
        BasicMethod.graphics = graphics;
    }
   
    public static void putPixel(Graphics graphics, double x, double y, Color color) {
        // chi co luc putPixel moi can toa do X,Y integer thoi
        Graphics g = graphics;
        g.setColor(color);
        //g.fillRect(convertX(x), convertY(y), 5, 5);
        int x1 = (int)x;
        int y1 = (int)y;
        g.fillRect(x1, y1, 2,2 );
        //        Graphics2D g2 = (Graphics2D) g;
        //        g2.setStroke(new BasicStroke(3));
        //        g2.setColor(color);
        //        g2.drawLine(x, y, x, y);

    }
      
    public static double convertXToReality(double x) {
        // convert tọa độ ảo (người dùng tự định nghĩa) về toa do thực để may tinh hieu
        // toa do may tinh hieu la truc toa do Oy huong xuong la chieu duong (+)
        // đã tính luôn cả vụ quy ước giá trị ảo của 1 đơn vị pixel
        // LƯU Ý: NHỮNG HÀM NÀY CHỈ DÙNG CHO TỌA ĐỘ X, Y THÔI KHÔNG DÙNG CHO CHIỀU DÀI, CHIỀU CAO
        //return x * donViPixel + 300;
        return x * donViPixel + toaDoXGoc;
    }

    public static double convertYToReality(double y) {
        //return y * (-donViPixel) + 290;
        return y * (-donViPixel) + toaDoYGoc;
    }

    public static double convertXToVirtual(double x) {
        //return ((x - 300) / (donViPixel * 1.0));
        return ((x - toaDoXGoc) / (donViPixel * 1.0));
    }

    public static double convertYToVirtual(double y) {
        //return ((y - 290) / (donViPixel * -1.0));
        return ((y - toaDoYGoc) / (donViPixel * -1.0));
    }
    
    public static MyPoint convertPointToReality(MyPoint p){
        double x = convertXToReality(p.x);
        double y = convertYToReality(p.y);
        return new MyPoint(x,y);
    }
        
    public static MyPoint convertPointToVirtual(MyPoint p){
        double x = convertXToVirtual(p.x);
        double y = convertYToVirtual(p.y);
        return new MyPoint(x,y);
    }
    
    public static double convertDistantToReality(double distant){
        double val = distant * 1.0 * donViPixel;
        return val;
    }
    
    public static int convertDistantToReality(int distant){
        return distant * donViPixel;
    }
   
    public static double convertDistantToVirtual(double distant) {
        return distant * 1.0 / donViPixel;
    }

    public static Point convertPointShow(MyPoint p) {
        // chuuyen doi toa do ve kieu integer, trinh chieu cho nguoi xem
        int x = (int) convertXToVirtual(p.x);
        int y = (int) convertYToVirtual(p.y);
        return new Point(x, y);
    }

    public static MyPoint cabinet(MyPoint3D point) {
        // Chieu toa do 3D (x,y,z) sang 2D(x,y). Truc khu la OY
        int x2D = (int) (point.x - point.y * (Math.sqrt(2)) / 4);
        int y2D = (int) (point.z - point.y * (Math.sqrt(2)) / 4);
        return new MyPoint(x2D, y2D);
    }

//<editor-fold defaultstate="collapsed">
//    public static void put8pixel(Graphics graphics, int x0, int y0, int x, int y, Color color) {
//        putPixel(graphics, x + x0, y + y0, color);
//        putPixel(graphics, y + x0, x + y0, color);
//        putPixel(graphics, -y + x0, x + y0, color);
//        putPixel(graphics, -x + x0, y + y0, color);
//        putPixel(graphics, -x + x0, -y + y0, color);
//        putPixel(graphics, -y + x0, -x + y0, color);
//        putPixel(graphics, y + x0, -x + y0, color);
//        putPixel(graphics, x + x0, -y + y0, color);
//    }
//
//    public static void CircleMidpoint(Graphics graphics, int x0, int y0, int banKinh, Color color) {
//        //Graphics g = graphics.getGraphics();
//        int x, y, p;
//        y = banKinh;
//        x = 0;
//        p = 1 - banKinh;
//        while (x <= y) {
//            if (p < 0) {
//                p += 2 * x + 3;
//
//            } else {
//                p += 2 * (x - y) + 5;
//                y--;
//            }
//            x++;
//            put8pixel(graphics, x0, y0, x, y, color);
//        }
//    }
//
//    public static void lineMidpoint(Graphics graphics, Point p1, Point p2, Color c) {
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException ex) {
////            Logger.getLogger(BasicMethod.class.getName()).log(Level.SEVERE, null, ex);
////        }
//// 
//        float dist = ((Math.abs(p1.x - p2.x) > Math.abs(p1.y - p2.y))
//                ? Math.abs(p1.x - p2.x) : Math.abs(p1.y - p2.y));
//        float now_x = p1.x, now_y = p1.y;
//        int push_x, push_y;
//        for (int i = 0; i < dist; i++) {
//            push_x = Math.round(now_x);
//            push_y = Math.round(now_y);
//            putPixel(graphics, push_x, push_y, c);
//            now_x += (p2.x - p1.x) / dist;
//            now_y += (p2.y - p1.y) / dist;
//        }
//    }
//
//    public static void trapezoid(Graphics graphics, Point pA, Point pC, int canhTren, Color c) {
//        // vẽ hình thang Cân pA.x > pC.x && pA.y < pC.y
//        // vd:         Point pA = new Point(convertX(20),convertY(30));
//        //             Point pC = new Point(convertX(18), convertY(25));
//        //             canhTren = 40;
//        Point pB = new Point(pA.x + canhTren, pA.y);
//        // H la chan duong cao AH
//        int chieuDai_CH = Math.abs(pA.x - pC.x);
//        Point pD = new Point(pB.x + chieuDai_CH, pC.y);
//        lineMidpoint(graphics, pA, pB, c);
//        lineMidpoint(graphics, pB, pD, c);
//        lineMidpoint(graphics, pD, pC, c);
//        lineMidpoint(graphics, pC, pA, c);
//    }
//
//    public static void rectangle(Graphics graphics, Point pA, int doDaiAB, int doDaiAC, Color c) {
//        // vẽ hình chữ nhật
//        Point pB = new Point(pA.x + doDaiAB, pA.y);
//        Point pC = new Point(pA.x, pA.y + doDaiAC);
//        Point pD = new Point(pB.x, pC.y);
//        lineMidpoint(graphics, pA, pB, c);
//        lineMidpoint(graphics, pB, pD, c);
//        lineMidpoint(graphics, pD, pC, c);
//        lineMidpoint(graphics, pC, pA, c);
//    }
//
//    public static void triangle(Graphics graphics, Point pA, Point pB, Point pC, Color c) {
//        // ve hinh tam giac
//        lineMidpoint(graphics, pA, pB, c);
//        lineMidpoint(graphics, pB, pC, c);
//        lineMidpoint(graphics, pC, pA, c);
//    }
//</editor-fold>
}

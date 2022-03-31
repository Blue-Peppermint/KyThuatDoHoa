/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Paint_3D;
import java.awt.Color;
import java.awt.Graphics;
import static Model.BasicMethod.*;
import java.util.HashMap;
/**
 *
 * @author chuon
 */
public class MyRectanglePrism {
    
    private MyPoint3D pA3D;
    private int dai;
    private int rong;
    private int cao;
    private Color color;
    private HashMap<String,MyPoint> toaDo;
    private Boolean isNew = true;
    
    public MyRectanglePrism(MyPoint3D pA3D, int dai, int rong, int cao, Color color) {
        // ve hinh hop chu nhat, bat dau tu diem A o duoi goc ben trai
        this.pA3D = pA3D;
        this.dai = dai;
        this.rong = rong;
        this.cao = cao;
        this.color = color;
    }

    public HashMap<String, MyPoint> getToaDo() {
        return toaDo;
    }

    public void draw(Graphics g) {
        toaDo = new HashMap<>();
        int dai = (int) convertDistantToVirtual(this.dai);
        int rong = (int) convertDistantToVirtual(this.rong);
        int cao = (int) convertDistantToVirtual(this.cao);
        int x = pA3D.x;
        int y = pA3D.y;
        int z = pA3D.z;
        // cac diem 3D day duoi
        MyPoint3D B = new MyPoint3D(x + dai, y, z);
        MyPoint3D C = new MyPoint3D(x + dai, y + rong, z);
        MyPoint3D D = new MyPoint3D(x, y + rong, z);
        // cac diem 3D day tren
        MyPoint3D A2 = new MyPoint3D(x, y, z+cao);
        MyPoint3D B2 = new MyPoint3D(x + dai, y, z + cao);
        MyPoint3D C2 = new MyPoint3D(x + dai, y + rong, z + cao);
        MyPoint3D D2 = new MyPoint3D(x, y + rong, z + cao);
        // chuyen diem 3D -> 2D
        // 3 , 4, 5
        MyPoint a = convertPointToReality(cabinet(pA3D));
        MyPoint b = convertPointToReality(cabinet(B));
        MyPoint c = convertPointToReality(cabinet(C));
        MyPoint d = convertPointToReality(cabinet(D));
        MyPoint a2 = convertPointToReality(cabinet(A2));
        MyPoint b2 = convertPointToReality(cabinet(B2));
        MyPoint c2 = convertPointToReality(cabinet(C2));
        MyPoint d2 = convertPointToReality(cabinet(D2));
        // them cac toa do moi vao HashMap de show thong so cho nguoi xem
//        toaDo.put("a", convertPointToVirtual(a));
//        toaDo.put("b", convertPointToVirtual(b));
//        toaDo.put("c", convertPointToVirtual(c));
//        toaDo.put("d", convertPointToVirtual(d));
//        toaDo.put("a2", convertPointToVirtual(a2));
//        toaDo.put("b2", convertPointToVirtual(b2));
//        toaDo.put("c2", convertPointToVirtual(c2));
//        toaDo.put("d2", convertPointToVirtual(d2));
        // noi cac diem ben duoi day A,B,C,D
        int dashLength = 10;
        int density = 5;
        new MyLine(a, b, color).drawDash(g, dashLength, density);
        new MyLine(b, c, color).draw(g);
        new MyLine(d, c, color).draw(g);
        new MyLine(d, a, color).drawDash(g, dashLength, density);
        // noi cac diem day tren A',B',C',D'
        new MyLine(a2, b2, color).draw(g);
        new MyLine(b2, c2, color).draw(g);
        new MyLine(d2, c2, color).draw(g);
        new MyLine(d2, a2, color).draw(g);
        // noi cac canh ben
        new MyLine(a, a2, color).drawDash(g, dashLength, density);
        new MyLine(b, b2, color).draw(g);
        new MyLine(c, c2, color).draw(g);
        new MyLine(d, d2, color).draw(g);
        // ve cac diem day duoi
        g.drawString("A",(int) (a.x - 10), (int) a.y + 5);
        g.drawString("B",(int) (b.x + 5), (int) b.y + 10);
        g.drawString("C",(int) (c.x + 5), (int) c.y + 10);
        g.drawString("D",(int) (d.x - 10) , (int) d.y + 10);
        // ve cac diem day tren
        g.drawString("A'",(int) (a2.x  - 10), (int) a2.y  - 3);
        g.drawString("B'", (int) (b2.x + 10), (int) b2.y - 3);
        g.drawString("C'", (int) (c2.x + 5), (int) c2.y + 10);
        g.drawString("D'", (int) (d2.x - 10), (int) d2.y + 10);
        // viet thong so sang ben canh man hinh luon. Viet goc Phai Man Hinh. Chi viet 1 lan thoi
        if (isNew) {
            isNew = false;
            int screenWitdh = (int) Paint_3D.drawingLocation.getWidth();
            int screeenHeight = (int) Paint_3D.drawingLocation.getHeight();
            String toaDoA = "A( " + pA3D.x
                    + ", " + pA3D.y + ", " + pA3D.z + ")";
            String toaDoB = "B( " + B.x + ", " + B.y + ", " + B.z + ")";
            String toaDoC = "C( " + C.x + ", " + C.y + ", " + C.z + ")";
            String toaDoD = "D( " + D.x + ", " + D.y + ", " + D.z + ")";
            String toaDoA2 = "A'( " + A2.x + ", " + A2.y + ", " + A2.z + ")";
            String toaDoB2 = "B'( " + B2.x + ", " + B2.y + ", " + B2.z + ")";
            String toaDoC2 = "C'( " + C2.x + ", " + C2.y + ", " + C2.z + ")";
            //String toaDoD2 = "D'( " + D2.x + ", " + D2.y + ", " + D.2z + ")";
            String toaDoD2 = "D'( " + D2.x + ", " + D2.y + ", " + D2.z + ")";
            g.drawString(toaDoA, screenWitdh - 200, screeenHeight / 2 + 50);
            g.drawString(toaDoB, screenWitdh - 100, screeenHeight / 2 + 50);
            g.drawString(toaDoC, screenWitdh - 200, screeenHeight / 2 + 75);
            g.drawString(toaDoD, screenWitdh - 100, screeenHeight / 2 + 75);
            g.drawString(toaDoA2, screenWitdh - 200, screeenHeight / 2 + 100);
            g.drawString(toaDoB2, screenWitdh - 100, screeenHeight / 2 + 100);
            g.drawString(toaDoC2, screenWitdh - 200, screeenHeight / 2 + 125);
            g.drawString(toaDoD2, screenWitdh - 100, screeenHeight / 2 + 125);
            g.drawString(toaDoC2, screenWitdh - 200, screeenHeight / 2 + 125);
            g.drawString(toaDoD2, screenWitdh - 100, screeenHeight / 2 + 125);
            String cDaiStr = "Chiều Dài: " + dai;
            String cRongStr = "Chiều Rộng: " + rong;
            String cCaoStr = "Chiều Cao: " + cao;
            g.drawString(cDaiStr, screenWitdh - 300, screeenHeight / 2 + 175);
            g.drawString(cRongStr, screenWitdh - 200, screeenHeight / 2 + 175);
            g.drawString(cCaoStr, screenWitdh - 100, screeenHeight / 2 + 175);

        }       
    }

}

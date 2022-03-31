/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Graphics;
import javax.swing.JComponent;
import Model.*;
import View.GUI;
import java.awt.Color;
import static Model.BasicMethod.*;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chuon
 */
public class Paint_2D extends JComponent{

    public static GUI frame;
    public static Rectangle drawingLocation;
    // 2 điểm sẽ tạo lề đường
    private final MyPoint pA = new MyPoint(-30,10);
    private final MyPoint pB = new MyPoint(-125, -100);
    private final MyPoint pA2 = new MyPoint(30,10);
    private final MyPoint pB2 = new MyPoint(125, -100);
    private MyLine line;
    private MyLine lineMirrorOY;
    // pWindMill nay la sample thôi, cho lấy sử dụng để copy thôi, ko cho dùng trực tiếp
    private final MyPoint pWindMill = (new MyPoint(-35, 10));
    private final int cCaoWindMillBanDau = 10; // do cao real
    public static Windmill windMill;
    public static Windmill windMill2;
    private Windmill windMillMirrorOY;
    private Windmill windMillMirrorOY2;
    public static final double angleRotate = 0.5;
    private final double scaleSize = 1.05;
    private final double speedWindMill = 1.5;
    private final int khoangCach = 5;
    // kiếm tra tiến trình di chuyển của WindMill
    public static Boolean isStopCheckingProgress1;
    public static Boolean isStopCheckingProgress2;
    // đường đi, vạch kẻ đường
    private Road road;
    private final int x = 0;   // toa do x bat dau tao duong moi
    private final int yMin =  (10); // yMin se la pA.y cua le duong, toa do y bat dau
    private final int yMax =  (-100);
    private final int lengthMark = (int) (10); // dung cho cai nay bang 0 neu ko muon bi lag
    private final int witdhMark = (int) (2); // do day cua vach ke duong
    private final int lengthSpace = (int) (3); // khoang cach giua 2 vach ke duong
    private final int speedRoad = 20; // toc do tinh tien cua cac thanh phan trong road
    // duong chan troi
    private MyLine lineHorizon;
    //// nhung thanh phan phu se dung trong qua trinh hoan thanh bai thi
    // truc toa do OX, OY
   private final MyPoint pAOY = new MyPoint(0,-100);
   private final MyPoint pBOY = new MyPoint(0,100);
   private final MyPoint pAOX = new MyPoint(-100,0);
   private final MyPoint pBOX = new MyPoint(100,0);
   private MyLine lineOX;
   private MyLine lineOY;
   // xe
   private Car car;
   // mat troi
   private Sun sun;
   // may
   private Cloud cloud;
   
    public Paint_2D(GUI frame, JPanel drawPane) {
        this.frame = frame;
        this.drawingLocation = null;
        this.isStopCheckingProgress1 = false;
        this.isStopCheckingProgress2 = false;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                drawingLocation = drawPane.getVisibleRect();
                BasicMethod.toaDoXGoc = drawingLocation.getWidth() / 2;
                BasicMethod.toaDoYGoc = drawingLocation.getHeight() / 2;
                windMill = new Windmill(convertPointToReality(pWindMill), cCaoWindMillBanDau, new Color(0, 102, 204));
                road = new Road((int) convertXToReality(x), (int) convertYToReality(yMin),
                        (int) convertYToReality(yMax), lengthMark, witdhMark, lengthSpace, Color.BLUE);
                System.out.println("width: " + drawingLocation.getWidth() + "Height: " + drawingLocation.getHeight());
                MyPoint pAHorizon = new MyPoint(0, convertYToReality(pA.y));
                MyPoint pBHorizon = new MyPoint(drawingLocation.getWidth(), convertYToReality(pA.y));
                lineHorizon = new MyLine(pAHorizon, pBHorizon, Color.BLUE);
                car = new Car((new MyPoint(25, -40)));
                MyPoint pOSun = new MyPoint(drawingLocation.getWidth() - 100, 50);
                sun = new Sun(pOSun, Color.black);
                MyPoint pCloud = new MyPoint(100, 100);
                cloud = new Cloud(pCloud, Color.WHITE);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // nếu đang vẽ thì repaint liên tục để update
        // thứ tự vẽ:
        // 1. Vẽ lề đường, cho đối xứng qua OY
        // 2. Vẽ đường chân trời
        // 3. Vẽ xe
        // 4. Vẽ windMill, cho đối xứng qua OY
        // 5. Vẽ road          
        // ko được vẽ khi drawingLocation chưa tồn tại vì lúc này vẫn chưa tính tọa độ gốc của Paint_2D -> chạy những hàm convertPoint xảy ra lỗi
        if (drawingLocation != null) {
            line = new MyLine(convertPointToReality(pA), convertPointToReality(pB), Color.RED);
            lineMirrorOY = new MyLine(convertPointToReality(pA2),convertPointToReality(pB2), Color.RED);
            line.draw(g);
            lineMirrorOY.draw(g);
            lineOX = new MyLine(convertPointToReality(pAOX), convertPointToReality(pBOX), Color.LIGHT_GRAY);
            lineOY = new MyLine(convertPointToReality(pAOY), convertPointToReality(pBOY), Color.LIGHT_GRAY);
            lineOX.draw(g);
            lineOY.draw(g);
            lineHorizon.draw(g);
            car.draw(g);
            if (windMill != null) {
                if (!isStopCheckingProgress1) {
                    int progression = windMill.checkProgress(drawingLocation);
                    if (progression == 0) {
                        windMill = null;
                    } else if (progression == 1) {
                        windMill2 = new Windmill(convertPointToReality(pWindMill), cCaoWindMillBanDau, new Color(0, 255, 255));
                        isStopCheckingProgress1 = true;
                    }
                }
                // lý do viết thêm 1 lệnh if(windMill != null) y như trên
                // nguyên nhân do MyThread nó chạy nhiều repaint quá, repaint ko kịp, 
                // dẫn đến hệ quả lúc tiến trình cũ nhất cho windMill = null nhưng ở tiến trình mới nhất thì đã vào trong này r, 
                // nhưng windMill đã bị null mà tiến trình mới muốn truy cập vào các thuộc tính trong windMill sẽ bị exception NULL ngay 
                if (windMill != null) {
                    windMill.scale(scaleSize);
                    windMill.moveAlongLine(convertPointToReality(pA), convertPointToReality(pB), 
                            (int) convertDistantToReality(khoangCach), speedWindMill);
                    windMill.rotateBlades(angleRotate);
                    windMillMirrorOY = windMill.mirrorThroughOY();
                    windMill.draw(g);
                    windMillMirrorOY.draw(g);
                }
            }
            if (windMill2 != null) {
                if (!isStopCheckingProgress2) {
                    int progression = windMill2.checkProgress(drawingLocation);
                    if (progression == 0) {
                        windMill2 = null;
                    } else if (progression == 1) {
                        windMill = new Windmill(convertPointToReality(pWindMill), cCaoWindMillBanDau, new Color(0, 102, 204));
                        isStopCheckingProgress2 = true;
                    }
                }
                if (windMill2 != null) {
                    windMill2.scale(scaleSize);
                    windMill2.moveAlongLine(convertPointToReality(pA), convertPointToReality(pB), 
                            (int) convertDistantToReality(khoangCach), speedWindMill);
                    windMill2.rotateBlades(angleRotate);
                    windMillMirrorOY2 = windMill2.mirrorThroughOY();
                    windMill2.draw(g);
                    windMillMirrorOY2.draw(g);
                }
            }
            if (road != null) {
                road.moving(speedRoad);
                road.draw(g);
            }
            if(sun != null){
                sun.moving();
                sun.draw(g);
            }
            if(cloud != null){
                cloud.raining();
                cloud.moving();
                cloud.draw(g);
                if(!cloud.getLife()){
                    cloud = null;
                }
            }
            else{
                MyPoint pCloud = new MyPoint(0, 100);
                cloud = new Cloud(pCloud, Color.WHITE);
            }
            setInfo();
        }
    }

    private void setInfo() {
        // set cac lbl trong thong so
        if (windMill != null) {
            Point p1 = convertPointShow(windMill.getTriangle1Blades().getpA());
            frame.getLblBlade1A().setText("A" + "(" + p1.x + ", " + p1.y + ")");
            Point p2 = convertPointShow(windMill.getTriangle1Blades().getpB());
            frame.getLblBlade1B().setText("B" + "(" + p2.x + ", " + p2.y + ")");
            Point p3 = convertPointShow(windMill.getTriangle1Blades().getpC());
            frame.getLblBlade1C().setText("C" + "(" + p3.x + ", " + p3.y + ")");
            Point p4 = convertPointShow(windMill.getBaseRect().getpA());
            frame.getLblWindMillA().setText("A" + "(" + p4.x + ", " + p4.y + ")");
            Point p5 = convertPointShow(windMill.getBaseRect().getpB());
            frame.getLblWindMillB().setText("B" + "(" + p5.x + ", " + p5.y + ")");
            Point p6 = convertPointShow(windMill.getBaseRect().getpC());
            frame.getLblWindMillC().setText("C" + "(" + p6.x + ", " + p6.y + ")");
            Point p7 = convertPointShow(windMill.getBaseRect().getpD());
            frame.getLblWindMillD().setText("D" + "(" + p7.x + ", " + p7.y + ")");
            int radius = (int) windMill.getCircleBlades().getBanKinh();
            frame.getLblRadius().setText("Radius= " + radius);
            // Truc coi xoay gio doi xung
            Point p8 = convertPointShow(windMillMirrorOY.getBaseRect().getpA());
            frame.getLblWindMillA2().setText("A" + "(" + -p4.x + ", " + p4.y + ")");
            Point p9 = convertPointShow(windMillMirrorOY.getBaseRect().getpB());
            frame.getLblWindMillB2().setText("B" + "(" + -p5.x + ", " + p5.y + ")");
            Point p10 = convertPointShow(windMillMirrorOY.getBaseRect().getpC());
            frame.getLblWindMillC2().setText("C" + "(" + p10.x + ", " + p10.y + ")");
            Point p11 = convertPointShow(windMillMirrorOY.getBaseRect().getpD());
            frame.getLblWindMillD2().setText("D" + "(" + p11.x + ", " + p11.y + ")");
            // canh quat 1 coi xoay gio doi xung
            Point p12 = convertPointShow(windMillMirrorOY.getTriangle1Blades().getpA());
            frame.getLblBlade1A2().setText("A" + "(" + p12.x + ", " + p12.y + ")");
            Point p13 = convertPointShow(windMillMirrorOY.getTriangle1Blades().getpB());
            frame.getLblBlade1B2().setText("B" + "(" + p13.x + ", " + p13.y + ")");
            Point p14 = convertPointShow(windMill.getTriangle1Blades().getpC());
            frame.getLblBlade1C2().setText("C" + "(" + -p14.x + ", " + p14.y + ")");
        } else {
            frame.getLblBlade1A().setText("Null");
            frame.getLblBlade1B().setText("Null");
            frame.getLblBlade1C().setText("Null");
            frame.getLblWindMillA().setText("Null");
            frame.getLblWindMillB().setText("Null");
            frame.getLblWindMillC().setText("Null");
            frame.getLblWindMillD().setText("Null");
            frame.getLblRadius().setText("Null");
            frame.getLblWindMillA2().setText("Null");
            frame.getLblWindMillB2().setText("Null");
            frame.getLblWindMillC2().setText("Null");
            frame.getLblWindMillD2().setText("Null");
            frame.getLblBlade1A2().setText("Null");
            frame.getLblBlade1B2().setText("Null");
            frame.getLblBlade1C2().setText("Null");
        }
        
        if(cloud != null){
            Point p1 = convertPointShow(cloud.getCircleLeft().getpO());
            frame.getLblCloudLeft().setText("O( "+p1.x +", " + p1.y+ ")");
            Point p2 = convertPointShow(cloud.getCircleCenter().getpO());
            frame.getLblCloudCenter().setText("O( "+p2.x +", " + p2.y+ ")");
            Point p3 = convertPointShow(cloud.getCircleRight().getpO());
            frame.getLblCloudRight().setText("O( "+p3.x +", " + p3.y+ ")");
        }
        else{
             frame.getLblCloudLeft().setText("Null");
             frame.getLblCloudCenter().setText("Null");
             frame.getLblCloudRight().setText("Null");
        }
        
        if(sun != null){
            Point p1 = convertPointShow(sun.getCircle().getpO());
            frame.getLblSun().setText("O( "+p1.x +", " + p1.y+ ")");
        }
        
        if(car != null){
            // toa do Window
            Point p1 = convertPointShow(car.getWindowTrapezoid().getpA());
            frame.getLblWindowA().setText("A( " +p1.x + ", "+ p1.y + ")");
            Point p2 = convertPointShow(car.getWindowTrapezoid().getpB());
            frame.getLblWindowB().setText("A( " +p2.x + ", "+ p2.y + ")");
            Point p3 = convertPointShow(car.getWindowTrapezoid().getpC());
            frame.getLblWindowC().setText("A( " +p3.x + ", "+ p3.y + ")");
            Point p4 = convertPointShow(car.getWindowTrapezoid().getpD());
            frame.getLblWindowD().setText("A( " +p4.x + ", "+ p4.y + ")");
            // toa do Mirror Left
            Point p5 = convertPointShow(car.getMirrorRect1().getpA());
            frame.getLblLeftMirrorA().setText("A( " +p5.x + ", "+ p5.y + ")");
            Point p6 = convertPointShow(car.getMirrorRect1().getpB());
            frame.getLblLeftMirrorB().setText("B( " +p6.x + ", "+ p6.y + ")");
            Point p7 = convertPointShow(car.getMirrorRect1().getpC());
            frame.getLblLeftMirrorC().setText("C( " +p7.x + ", "+ p7.y + ")");
            Point p8 = convertPointShow(car.getMirrorRect1().getpD());
            frame.getLblLeftMirrorD().setText("D( " +p8.x + ", "+ p8.y + ")");
            // toa do Mirror Right
            Point p9 = convertPointShow(car.getMirrorRect2().getpA());
            frame.getLblRightMirrorA().setText("A( " +p9.x + ", "+ p9.y + ")");
            Point p10 = convertPointShow(car.getMirrorRect2().getpB());
            frame.getLblRightMirrorB().setText("B( " +p10.x + ", "+ p10.y + ")");
            Point p11 = convertPointShow(car.getMirrorRect2().getpC());
            frame.getLblRightMirrorC().setText("C( " +p11.x + ", "+ p11.y + ")");
            Point p12 = convertPointShow(car.getMirrorRect2().getpD());
            frame.getLblRightMirrorD().setText("D( " +p12.x + ", "+ p12.y + ")");
            // toa do Rear
            Point p13 = convertPointShow(car.getRearTrapezoid().getpA());
            frame.getLblRearA().setText("A( " +p12.x + ", "+ p12.y + ")");
            Point p14 = convertPointShow(car.getRearTrapezoid().getpB());
            frame.getLblRearB().setText("B( " +p14.x + ", "+ p14.y + ")");
            Point p15 = convertPointShow(car.getRearTrapezoid().getpC());
            frame.getLblRearC().setText("C( " +p15.x + ", "+ p15.y + ")");
            Point p16 = convertPointShow(car.getRearTrapezoid().getpD());
            frame.getLblRearD().setText("D( " +p16.x + ", "+ p16.y + ")");
        }
        
    }
}

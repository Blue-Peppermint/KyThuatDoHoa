/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import static Model.BasicMethod.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;
/**
 *
 * @author chuon
 */

class Base {

    private MyRectangle rect;
    private Color color;
    private double cDai;
    private double cRong;
    private MyPoint pWindMill;
    private double cCaoBody; // cCaoBody này hoàn toàn phụ thuộc vào Body (lấy từ body mà có)
    private double a; // a,b trong phuong trinh duong thang Y= aX + b
    private double b;   
    private MyPoint pLineA; // toạ độ A,B khi dịch chuyển song song với AB
    private MyPoint pLineB;
    private int khoangCach; // khoảng cách từ điểm D của rect -> đường thẳng AB
    private double tocDo; // tốc độ dịch Y của Base

    public Base() {
        // dung rieng cho method Clone
    }
    
    public Base(MyPoint pWindMill, double cCaoBody, Color color) {
        // dựa vào pWindMill tính cDai, cRong
        this.pWindMill = pWindMill;
        this.cCaoBody = cCaoBody;
        this.color = color;
        // thiết lập để khi chạy method moveAlongLine không di chuyển. Chỉ được phép di chuyển khi coder set các value
        pLineA = new MyPoint(0,0);
        pLineB = new MyPoint(0,0);
        calRect(pWindMill, cCaoBody);
    }

    public MyRectangle getRect() {
        return rect;
    }

    public void setRect(MyRectangle rect) {
        this.rect = rect;
    }

    public void setpWindMill(MyPoint pWindMill) {
        this.pWindMill = new MyPoint(pWindMill);
    }

    public double getcDai() {
        return cDai;
    }

    public double getcRong() {
        return cRong;
    }

    public MyPoint getpWindMill() {
        return pWindMill;
    }

    public double getcCaoBody() {
        return cCaoBody;
    }

    public void setcCaoBody(double cCaoBody) {
        this.cCaoBody = cCaoBody;
    }

    public void setTocDo(double tocDo) {
        this.tocDo = tocDo;
    }

    public void setKhoangCach(int khoangCach) {
        this.khoangCach = khoangCach;
    }

    public MyPoint getpLineA() {
        return pLineA;
    }

    public void setpLineA(MyPoint pLineA) {
        this.pLineA = pLineA;
    }

    public MyPoint getpLineB() {
        return pLineB;
    }

    public void setpLineB(MyPoint pLineB) {
        this.pLineB = pLineB;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setcDai(double cDai) {
        this.cDai = cDai;
    }

    public void setcRong(double cRong) {
        this.cRong = cRong;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Base{rect=").append(rect);
        sb.append(", color=").append(color);
        sb.append(", cDai=").append(cDai);
        sb.append(", cRong=").append(cRong);
        sb.append(", pWindMill=").append(pWindMill);
        sb.append(", cCaoBody=").append(cCaoBody);
        sb.append(", a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", pLineA=").append(pLineA);
        sb.append(", pLineB=").append(pLineB);
        sb.append(", khoangCach=").append(khoangCach);
        sb.append(", tocDo=").append(tocDo);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Base other = (Base) obj;
        return true;
    }
    
    @Override   
    protected Base clone() {
        Base base_clone = new Base();
        base_clone.setpWindMill(pWindMill);
        base_clone.setcCaoBody(cCaoBody);
        base_clone.setColor(color);               
        base_clone.setRect(rect);
        base_clone.setcDai(cDai);
        base_clone.setcRong(cRong);
        base_clone.setA(a);
        base_clone.setB(b);
        base_clone.setpLineA(new MyPoint(pLineA));
        base_clone.setpLineB(new MyPoint(pLineB));
        base_clone.setKhoangCach(khoangCach);
        base_clone.setTocDo(tocDo);
        return base_clone;
    }

    private void calRect(MyPoint pWindMill, double cCaoBody) {
        // tính toán Rect sẽ vẽ, cDai, cROng của Rect
        this.cCaoBody = cCaoBody;
        // CDai = 1/1.5 cCao WindMill
        this.cDai = (cCaoBody * 1/1.5);
        // CRong = 1/10 cDai Base
        this.cRong =  (cDai * 1/10.0);
        double xA =  (pWindMill.x - convertDistantToReality(cDai / 2.0));
        double yA = pWindMill.y + convertDistantToReality(cRong);
        MyPoint pA = new MyPoint(xA,yA);
        double doDaiAB = convertDistantToReality(cDai);
        double doDaiAC = convertDistantToReality(cRong);
        rect = new MyRectangle(pA, doDaiAB, doDaiAC, color);
    }
    
// <editor-fold >
    //    private void calRectMirrorOY(MyPoint pWindMill, double cCaoBody){
//        // tính toán Rect sẽ vẽ, cDai, cROng của Rect theo cong thuc song song voi OY
//        this.cCaoBody = cCaoBody;
//        // CDai = 1/1.5 cCao WindMill
//        this.cDai = (cCaoBody * 1/1.5);
//        // CRong = 1/10 cDai Base
//        this.cRong =  (cDai * 1/10.0);
//        double xA =  (pWindMill.x + convertDistantToReality(cDai / 2.0));
//        double yA = pWindMill.y - convertDistantToReality(cRong);
//        MyPoint pA = new MyPoint(xA,yA);
//        double doDaiAB = convertDistantToReality(cDai);
//        double doDaiAC = convertDistantToReality(cRong);
//        rect = new MyRectangle(pA, doDaiAB, doDaiAC, color);
//        //rect.calOtherPointBaseA_MirrorOY();
//    }
    
  //</editor-fold>
    
    public void draw(Graphics g) {
        updateData();
        rect.draw(g);
    }

//<editor-fold>
//    
//    public void drawMirror(Graphics g){
//        moveAlongLine(pLineA, pLineB, khoangCach, tocDo);
//        calRectMirrorOY(pWindMill, cCaoBody);
//        rect.draw(g);
//    }
//    
//</edtor-fold>
    
    public void updateData() {
        // chạy hàm này để update các thứ trước mỗi lần draw
        moveAlongLine(pLineA, pLineB, khoangCach, tocDo);
        calRect(pWindMill, cCaoBody);
    }

    private void moveAlongLine(MyPoint pA, MyPoint pB, int khoangCach, double tocDo) {
        // tính tọa độ pWindMill mới sau khi dich chuyen base song song voi duong thang AB y = ax+b
        // Coi Xoay Gio paint ben nao cua AB phu thuoc vi tri ma Coi Xoay xuat hien truoc do
        this.tocDo = tocDo;
        int result = calLineEquation(pA, pB);
        if (result == 1) {
            // nếu vô nghiệm thì dịch theo 1 đường thẳng
            pWindMill.y += this.tocDo * donViPixel;
        } else if (result == 2) {
            // kiem Tra xem se ve Base ve ben nao cua duong thang
            // kiem tra bang toa do X. So sanh toa do X hien tai voi toa do X tren AB (ứng với y hiện tại)
            // Y = aX + b => X = (Y - b)/(a * 1.0)
            double xOfAB = (pWindMill.y - b) / (a * 1.0);
            boolean leftOfAB = (pWindMill.x < xOfAB) ? true : false;
            // do di chuyen ve phia man hinh nen y += 1 donViPixel
            pWindMill.y += this.tocDo * donViPixel;
            // tính tọa độ X của AB sau khi đã dịch Y 
            xOfAB = (pWindMill.y - b) / (a * 1.0);
            // khoangCach = Khoang cach theo chieu X tu Base.D -> AB
            if (leftOfAB) {
                // bang x tu AB - khoangCach - 1/2 cDai của Base
                pWindMill.x = xOfAB - khoangCach * donViPixel - (cDai / 2.0) * donViPixel;
            } else {
                pWindMill.x = xOfAB + khoangCach * donViPixel + (cDai / 2.0) * donViPixel;
            }
        }
    }

    private int calLineEquation(MyPoint pA, MyPoint pB) {
        // tinh toan 2 diem a+b trong phuong trinh y =ax+b duong thang qua 2 diem cho truoc
        int result = -1;
        int x1 = (int) pA.x;
        int y1 = (int) pA.y;
        int x2 = (int) pB.x;
        int y2 = (int) pB.y;
        double D = x1 * 1 - x2 * 1;
        double Dx = y1 * 1 - y2 * 1;
        double Dy = x1 * y2 - x2 * y1;
        if (D != 0) {
            a = Dx / D;
            b = Dy / D;
            result = 2;
        } else {
            if (Dx + Dy == 0) {
                // nếu vô số nghiệm thì
                // vd: pA(0,0); pB(0,0)
                result = 0;
            } else {
                // neu vo nghiem
                // vd: toa do Ao     MyPoint pA = new MyPoint(5,5);
                //                   MyPoint pB = new MyPoint(5, -10);
                result = 1;
            }
        }
        return result;
    }
    
    
}

class Body {

    private MyTriangle triangle;
    private Color color;
    private double cCaoBody;
    private Base base;
    
    public Body(Base base, double cCaoBody,Color color) {
        this.color = color;
        this.base = base;
        this.cCaoBody = cCaoBody;
        calTriangle(base, this.cCaoBody);
    }

    public MyTriangle getTriangle() {
        return triangle;
    }

    public void setTriangle(MyTriangle triangle) {
        this.triangle = triangle;
    }

    public double getCCaoBody() {
        return cCaoBody;
    }

    public void setcCaoBody(double cCaoBody) {
        this.cCaoBody = cCaoBody;
    }

    public void setBase(Base base) {
        this.base = base;
    }
 
    @Override
    protected Body clone() {
        Body body_clone = new Body(base, cCaoBody, color);
        body_clone.setTriangle(triangle);
        return body_clone;
    }
   
    private void calTriangle(Base base, double cCaoBody) {
        // tính toán hình tam giác sẽ vẽ
        MyRectangle rectBase = base.getRect();
        // cDaiOThutLe = cRong
        double cDaiThutLe = base.getcRong();
        double xA = convertXToReality(convertXToVirtual(rectBase.getpA().x) + cDaiThutLe);
        double yA = rectBase.getpA().y;
        MyPoint pA = new MyPoint(xA, yA);
        double xC = convertXToReality(convertXToVirtual(rectBase.getpB().x) - cDaiThutLe);
        double yC = yA;
        MyPoint pC = new MyPoint(xC, yC);
        double xB = base.getpWindMill().x;
        double yB = convertYToReality(convertYToVirtual(yA) + cCaoBody);
        MyPoint pB = new MyPoint(xB, yB);
        triangle = new MyTriangle(pA, pB, pC, color);
    }

    public void draw(Graphics g) {
        updateData();
        triangle.draw(g);
    }

    public void updateData() {
        // chạy hàm này để update các thứ trước mỗi lần draw
        calTriangle(base, cCaoBody);
    }
}

class Blades{

    private MyTriangle blade1;
    private MyTriangle blade2;
    private MyTriangle blade3;
    private MyCircle circle;
    private Color color;
    private double angleRadian; // goc quay cua cac blade tinh bang radian
    private Base base; 
    private Body body;

    public Blades() {
        // dung cho truong hop dac biet, ham Clone duoi day
    }
      
    public Blades(Base base, Body body, Color color ) {
        this.base = base;
        this.body = body;
        this.color = color;
        // setUp để khi chạy method rotateBlades, Blades sẽ không quay
        this.angleRadian = 0;
        calObjectFirstTime(base, body);
    }

    public MyTriangle getBlade1() {
        return blade1;
    }

    public void setBlade1(MyTriangle blade1) {
        this.blade1 = blade1;
    }

    public MyTriangle getBlade2() {
        return blade2;
    }

    public void setBlade2(MyTriangle blade2) {
        this.blade2 = blade2;
    }

    public MyTriangle getBlade3() {
        return blade3;
    }

    public void setBlade3(MyTriangle blade3) {
        this.blade3 = blade3;
    }

    public double getAngleRadian() {
        return angleRadian;
    }

    public void setAngleRadian(double angleRadian) {
        this.angleRadian = angleRadian;
    }

    public void setCircle(MyCircle circle) {
        this.circle = circle;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public MyCircle getCircle() {
        return circle;
    }
    
    @Override
    protected Blades clone()  {
        Blades blades_clone = new Blades(base, body, color);
        blades_clone.setAngleRadian(angleRadian);
        blades_clone.setBlade1(blade1);
        blades_clone.setBlade2(blade2);
        blades_clone.setBlade3(blade3);
        blades_clone.setCircle(circle);
        return blades_clone;
    }
   
    private void calObjectFirstTime(Base base, Body body) {
        // tính toán các hình tam giác + tròn sẽ vẽ trong lần khởi tạo đầu tiên
        MyTriangle triangleBody = body.getTriangle();
        double xB = triangleBody.getpB().x;
        double yB = triangleBody.getpB().y;
        MyPoint pB = new MyPoint(xB, yB);
        // chieu dai canh quat = 1/2 body
        double xA = xB;
        double yA = (yB + (body.getCCaoBody() * donViPixel * (1 / 2.0)));
        MyPoint pA = new MyPoint(xA, yA);
        // toa do diem C se la dinh cua goc vuong trong tam giac vuong ABC vuông tại A
        MyPoint pC = toaDoCTrongTamGiacVuong(pA, pB);
        blade1 = new MyTriangle(pA, pB, pC, Color.RED);
        blade2 = new MyTriangle(pA, pB, pC, color);
        // rotate 2pi * 1/3.0
        blade2.rotate(pB, 2 * 3.14 * 1 / 3);
        blade3 = new MyTriangle(pA, pB, pC, color);
        // rotate 2pi * 2/3.0
        blade3.rotate(pB, 2 * 3.14 * 2 / 3);
        // vẽ Circle có bán kính = CRong của Base
        MyPoint O = new MyPoint(xB, yB);
        double banKinh = (base.getcRong()) * donViPixel;
        circle = new MyCircle(O, banKinh, color);
    }
    
    private MyPoint toaDoCTrongTamGiacVuong(MyPoint pA, MyPoint pB) {
        // tinh toa do diem C trong tam giac vuong ABC vuong tai A voi AC = 1/5 AB
        int cDaiAB = (int) Math.sqrt(Math.pow(pA.y - pB.y, 2));
        int cDaiAC = (int) (cDaiAB * (1 / 5.0));
        //double xC = (Math.sqrt(Math.pow(cDaiAB, 2) + Math.pow(cDaiAC, 2) - Math.pow(cDaiAB, 2)) + pB.x);
        double xC = pB.x + cDaiAC;
        double yC = pA.y;
        return new MyPoint(xC, yC);
    }
      
    public void draw(Graphics g) {
        updateData();
        blade1.draw(g);
        blade2.draw(g);
        blade3.draw(g);
        circle.setColor(Color.RED);
        circle.draw(g);
    }
    
    public void updateData() {
        // chạy hàm này để update các thứ trước mỗi lần draw      
        rotateBlades(angleRadian);
        updateBlades(base, body);
        
    }

    private void rotateBlades(double angleRadian) {
        // sau khi quay xong gia tri angle moi da duoc luu vao MyTriangle
        blade1.rotate(circle.getpO(), angleRadian);
        blade2.rotate(circle.getpO(), angleRadian);
        blade3.rotate(circle.getpO(), angleRadian);
    }

    private void updateBlades(Base base, Body body){
        // Dựa vào các data cũ
        // cap nhat lai tam giac moi, do phép dịch chuyển tâm. Nhưng vẫn giữ những dữ liệu vị trí của lần quay cũ
        // cap nhat lai luon hinh tron moi
        MyTriangle triangleBody = body.getTriangle();
        double xB = triangleBody.getpB().x;
        double yB = triangleBody.getpB().y;
        MyPoint pB = new MyPoint(xB, yB);
        // chieu dai canh quat = 1/2 body
        double xA = xB;
        double yA = (yB + (body.getCCaoBody() * donViPixel * (1 / 2.0)));
        MyPoint pA = new MyPoint(xA, yA);
        // toa do diem C se la dinh cua goc vuong trong tam giac vuong ABC vuông tại A
        MyPoint pC = toaDoCTrongTamGiacVuong(pA, pB);
        // vẽ Circle có bán kính = 1/4 CRong của Base
        MyPoint O = new MyPoint(xB, yB);
        double banKinh = (base.getcRong()) * donViPixel;
        circle = new MyCircle(O, banKinh, color);
        // lưu lại những góc quay vừa quay xong
        double angleRadianOldBlade1 = blade1.getAngleRadian();
        double angleRadianOldBlade2 = blade2.getAngleRadian();
        double angleRadianOldBlade3 = blade3.getAngleRadian();
        blade1 = new MyTriangle(pA, pB, pC, Color.RED);        
        blade2 = new MyTriangle(pA, pB, pC, color);
        blade3 = new MyTriangle(pA, pB, pC, color);
        blade1.rotate(pB, angleRadianOldBlade1);
        blade2.rotate(pB, angleRadianOldBlade2);
        blade3.rotate(pB, angleRadianOldBlade3);      
    }
}


public class Windmill {
   
    private Base base;
    private Body body;
    private Blades blades;
    private MyPoint pWindMill;
    private MyPoint pLineA; 
    private MyPoint pLineB;
    private int khoangCach;
    private double tocDo;
    private double scaleSize;
    private double angleRadian;
    
    public Windmill(Base base, Body body, Blades blades) {
        this.base = base;
        this.body = body;
        this.blades = blades;
    }
    
    public Windmill(MyPoint pWindMill, double cCaoBody, Color color) {
        // pWinMill la toa do o giua cua chan de WindMill. Dùng tọa độ thật
        // cac thuoc tinh chieuDai, cCao Dùng tọa độ ảo
        MyPoint pWindMill_clone = new MyPoint(pWindMill);
        base = new Base(pWindMill_clone, cCaoBody, color);
        body = new Body(base, cCaoBody, color);
        blades = new Blades(base, body, color);
    }

    public Base getBase() {
        return base;
    }

    public Body getBody() {
        return body;
    }

    public Blades getBlades() {
        return blades;
    }

    public MyPoint getpWindMill() {
        return pWindMill;
    }
    
    public int checkProgress(Rectangle drawingLocation){
        // kiểm tra xem y của pWindMill đã đạt đến vị trí maximum của khung hình chưa
        // và kiểm tra thời điểm tạo 1 WindMill mới
        // đối tượng windMill kiểm tra chủ yếu là vật di chuyển sang tiến về bên trái màn hình
       // canh thoi gian ben toa do x can than, chu khong bi loi lien
       // code duoi day danh cho truong hop trigger theo tọa độ x lẫn y 
//        if((base.getpWindMill().y >= drawingLocation.getHeight() - 100) || base.getpWindMill().x <= 0){
//            // cho bay màu
//            return 0;
//        }
//        else if((base.getpWindMill().y >= drawingLocation.getHeight() - 200) || base.getpWindMill().x <= 100)
//        {
//            // thêm 1 windMill mới
//            return 1;
//        }
//        else{
//            // cứ vẽ bình thường
//            return 2;
//        }
        // code này chỉ dành cho trường hợp kiểm tra y thôi
        if((base.getpWindMill().y >= drawingLocation.getHeight() - 100)){
            // cho bay màu
            return 0;
        }
        else if((base.getpWindMill().y >= drawingLocation.getHeight() - 200) )
        {
            // thêm 1 windMill mới
            return 1;
        }
        else{
            // cứ vẽ bình thường
            return 2;
        }
    }
    // Mục tiêu của những hàm này la chi để set cac gia tri vao cac class thôi.
    // còn thực thi phần update tọa độ các vật thì sẽ chạy hàm draw riêng lẽ và theo thứ tự
    // Quy Trình Update các tọa độ, độ dài như sau:
    // di chuyển: tọa độ AB, khoangCach, tocDo -> update pWindMill -> update Base, Body, Blades -> draw ALL
    // phóng to: size -> update cCaoBody -> update Base, Body, Blades
    // xoay:  angleRaidan -> update Blades  
    
    public void rotateBlades(double angleRadian) {
        blades.setAngleRadian(angleRadian);
        this.angleRadian = angleRadian;
        pWindMill = base.getpWindMill();
    }

    public void moveAlongLine(MyPoint pA, MyPoint pB, int khoangCach, double tocDo) {
        // di chuyen BASE song song voi le duong y = ax + b
        // tọa độ pA, pB dùng là tọa độ thật
        MyPoint pA_clone = new MyPoint(pA);
        MyPoint pB_clone = new MyPoint(pB);
        base.setpLineA(pA_clone);
        base.setpLineB(pB_clone);
        base.setKhoangCach(khoangCach);
        base.setTocDo(tocDo);
        this.pLineA = pA_clone;
        this.pLineB = pB_clone;
        this.khoangCach = khoangCach;
        this.tocDo = tocDo;
        pWindMill = base.getpWindMill();
    }
    
    public void scale (double scaleSize){
        // làm phóng to lên dần nên size 
        double cCaoBody = (scaleSize * body.getCCaoBody());
        base.setcCaoBody(cCaoBody);
        body.setcCaoBody(cCaoBody);
        this.scaleSize = this.scaleSize;
        pWindMill = base.getpWindMill();
    }
    
    public Windmill mirrorThroughOY(){
        // tạo 1 clone Windmill đối xứng qua trục OY
        // những thứ sẽ thay đổi về Windmill khi chạy method này 
        // (bản chất là 1 method sao chép vậy nên sẽ tính tất cả x này = -x của bên kia)
        // Ngoài việc đối xứng các hình vẽ, Bonus them nhung thu can doi xung: pWindMill, LineA, LineB, angleRadian
        Windmill windMill_new = (Windmill) clone();       
        MyRectangle rect_new = base.getRect().mirrorThroughOY();
        MyTriangle triangle_new = body.getTriangle().mirrorThroughOY();
        MyTriangle blades_new1 = blades.getBlade1().mirrorThroughOY();
        MyTriangle blades_new2 = blades.getBlade2().mirrorThroughOY();
        MyTriangle blades_new3 = blades.getBlade3().mirrorThroughOY();
        windMill_new.base.setRect(rect_new);
        windMill_new.body.setTriangle(triangle_new);
        windMill_new.blades.setBlade1(blades_new1);
        windMill_new.blades.setBlade2(blades_new2);
        windMill_new.blades.setBlade3(blades_new3);
        // bonus những thứ cần đối xứng: pWindMill, LineA, LineB, angleRadian       
        double xWindMill_new = - convertXToVirtual(base.getpWindMill().x);
        double yWindMill_new = base.getpWindMill().y;
        MyPoint pWindMill_new = new MyPoint(convertXToReality(xWindMill_new), yWindMill_new);
        windMill_new.base.setpWindMill(pWindMill_new);
        double xPLineA_new = - convertXToVirtual(windMill_new.base.getpLineA().x);
        double yPLineA_new = windMill_new.base.getpLineA().y;
        double xPLineB_new = - convertXToVirtual(windMill_new.base.getpLineB().x);
        double yPLineB_new = windMill_new.base.getpLineB().y;
        windMill_new.base.setpLineA(new MyPoint(convertXToReality(xPLineA_new), yPLineA_new));
        windMill_new.base.setpLineB(new MyPoint(convertXToReality(xPLineB_new), yPLineB_new));
        double angleRadian_new = - windMill_new.blades.getAngleRadian();
        windMill_new.blades.setAngleRadian(angleRadian_new);
        windMill_new.body.setBase(windMill_new.base);
        windMill_new.blades.setBase(windMill_new.base);
        windMill_new.blades.setBody(windMill_new.body);
        return windMill_new;
    }
    
    @Override
    protected Object clone(){
        // sao chép và tạo ra tất cả các bộ phận của windMill có bộ nhớ hoàn toàn mới
        Base base_clone = base.clone();
        Body body_clone = body.clone();
        Blades blades_clone = blades.clone();
        Windmill windmill_clone = new Windmill(base_clone, body_clone, blades_clone);
        return windmill_clone;
    }
      
    public MyRectangle getBaseRect(){
        return base.getRect();
    }
    
    public MyCircle getCircleBlades(){
        return blades.getCircle();
    }
    
    public MyTriangle getTriangle1Blades(){
        return blades.getBlade1();
    }
    
    public void draw(Graphics g){
        base.draw(g);
        body.draw(g);
        blades.draw(g);     
        pWindMill = base.getpWindMill();
    }

    //<editor-fold>
//        public void drawForMirrorOY(Graphics g) {
//        base.drawMirror(g);
//        body.draw(g);
//        blades.draw(g);
//        pWindMill = base.getpWindMill();
//    }
    
    //</editor-fold>

}

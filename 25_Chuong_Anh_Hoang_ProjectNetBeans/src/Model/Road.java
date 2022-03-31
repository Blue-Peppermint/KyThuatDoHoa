/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.BasicMethod.convertDistantToReality;
import static Model.BasicMethod.donViPixel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author chuon
 */
public class Road {

    private ArrayList<MyRectangle> roadMark;
    private ArrayList<Integer> roadSpace;
    private int x;
    private int yMin;
    private int yMax;
    private int lengthMark;
    private int witdhMark;
    private int lengthSpace;
    private Color color;
    private Boolean markDrawing1;
    private Boolean markDrawing2;
    private int speed; // toc do dich chuyen toan bo cac diem

    public Road(int x, int yMin, int yMax, int lengthMark, int witdhMark, int lengthSpace, Color color) {
        // khoi tao cac vach ke duong va khoang cach giua cac vach ke
        // cac toa do, cdai, khoang cach de kieu int cho de tinh
        // input, chieu dai, chieu cao la kiểu ảo
        // yMax, yMin nên để đúng thứ tự
        // bắt đầu vẽ từ điểm (x, yMax) -> (x, yMin) với HCN điểm A(x, yMax)
        // lengthMark: doDaiAC hcn của vạch vẽ
        // witdhMark: doDaiAB hcn cua vạch vẽ
        // lengthSpace: khoang trắng giua cac vach ve voi nhau
        roadMark = new ArrayList<>();
        roadSpace = new ArrayList<>();
        this.x = x;
        this.yMin = yMin;
        this.yMax = yMax;
        this.lengthMark = lengthMark;
        this.witdhMark = witdhMark;
        this.lengthSpace = lengthSpace;
        this.color = color;
        markDrawing1 = true; // để kiểm tra xem nên duyệt giá trị đầu tiên của mảng nào
        markDrawing2 = true; // để kiểm tra xem nên duyệt giá trị cuối cùng của mảng nào
        int distanctBetweenY = Math.abs(yMax - yMin);
        System.out.println("distanct in road" + distanctBetweenY);
        while (distanctBetweenY > 0) {
            if (markDrawing2) {
                MyPoint pA = null;
                double doDaiAB = witdhMark * donViPixel;
                double doDaiAC = 0;
                for (int i = 0; i < convertDistantToReality(lengthMark) && distanctBetweenY > 0; i++) {
                    doDaiAC += 1;
                    --distanctBetweenY;
                    //System.out.println("i = "+ i);
                    if (i == convertDistantToReality(lengthMark - 1)) {
                        markDrawing2 = false;
                    }
                }
                // thiet lap thông số cho roadMark
                if (!roadMark.isEmpty()) {
                    int lastIndexRoadMark = roadMark.size() - 1;
                    // yen tam, da chay duoc vao day thi truoc do roadSpace 
                    // da duoc hoan thanh voi full length roi, roadMark trước đó cũng full length                        
                    int yPA = (int) (roadMark.get(lastIndexRoadMark).getpA().y
                            - convertDistantToReality(lengthSpace) - doDaiAC);
                    int xPA = x;
                    pA = new MyPoint(xPA, yPA);
                } else {
                    int xPA = x;
                    int yPA = yMax - lengthMark;
                    pA = new MyPoint(xPA, yPA);
                }
                MyRectangle roadMark_new = new MyRectangle(pA, doDaiAB, doDaiAC, color);
                //roadMark_new.setData(pA, doDaiAB, doDaiAC);
                roadMark.add(roadMark_new);
            } else {
                int distanctSpaceNow = 0;
                for (int i = 0; i < convertDistantToReality(lengthSpace) && distanctBetweenY > 0; i++) {
                    distanctSpaceNow += 1;
                    --distanctBetweenY;
                    if (i == convertDistantToReality(lengthSpace - 1)) {
                        markDrawing2 = true;
                    }
                }
                roadSpace.add(distanctSpaceNow);
            }
        }

    }

    public ArrayList<MyRectangle> getRoadMark() {
        return roadMark;
    }

    public ArrayList<Integer> getRoadSpace() {
        return roadSpace;
    }

    public void moving(int speed) {
        this.speed = speed;
        // xử lý toàn bộ phần tử của các vạch đường, để dịch chuyển hướng về màn hình cho y += speed
        // đối với trường hợp tất cả các phần tử trong mảng đều vừa đủ, cân bằng sẽ bị bug
        for (int i = 0; i < roadMark.size() - 1; i++) {
            roadMark.get(i).getpA().y += speed;
            roadMark.get(i).getpB().y += speed;
            roadMark.get(i).getpC().y += speed;
            roadMark.get(i).getpD().y += speed;
        }
        // neu dang vẽ 1 vạch kẻ đường thì ko giảm yPA, pPB của HCN đó
        // đối với hcn đang vẽ thì sau khi cộng speed lên thì nhớ update độ dài AC mới
        // những cái khác ko cần update vì ko thuộc công việc markDrawing2 hoặc vì bọn nó đã maximum length r
        if (markDrawing2) {
            int lastIndexRoadMark = roadMark.size() - 1;
            MyRectangle rect = roadMark.get(lastIndexRoadMark);
            if (rect.getDoDaiAC() < convertDistantToReality(lengthMark)) {
                int speed_now = 0;
                for (int i = (int) rect.getDoDaiAC(); i < convertDistantToReality(lengthMark); i++) {
                    if (speed_now == speed) {
                        break;
                    }
                    rect.getpC().y += 1;
                    rect.getpD().y += 1;
                    speed_now++;
                }
                double doDaiAC = rect.getpC().y - rect.getpA().y;
                //System.out.println("doDaiAC: " + doDaiAC);
                rect.setDoDaiAC(doDaiAC);
                //rect.calOtherPointBaseA();
                //System.out.println("Khoang cach giua diemA moi cong vs diem A cu: " + (rect.getpA().y - roadMark.get(lastIndexRoadMark).getpA().y));
            }
        } else {
            int lastIndexRoadMark = roadMark.size() - 1;
            MyRectangle rect = roadMark.get(lastIndexRoadMark);
            rect.getpA().y += speed;
            rect.getpB().y += speed;
            rect.getpC().y += speed;
            rect.getpD().y += speed;
        }
        // xử lý những phần tử đầu trước (phần sẽ bị xén dần)
        if (markDrawing1) {
            // nếu object bị xén là vạch kẻ đường
            MyRectangle rect_now = roadMark.get(0);
            int doDaiAB = (int) rect_now.getDoDaiAB();
            int doDaiAC = (int) (rect_now.getDoDaiAC() - speed);
            rect_now.setData(rect_now.getpA(), doDaiAB, doDaiAC);
            if (rect_now.getDoDaiAC() <= 0) {
                roadMark.remove(0);
                markDrawing1 = false;
            }
        } else {
            int value = roadSpace.get(0) - speed;
            roadSpace.remove(0);
            roadSpace.add(0, value);
            if (roadSpace.get(0) <= 0) {
                roadSpace.remove(0);
                markDrawing1 = true;
            }
        }
        // xử lý những phần tử sau cùng (phần sẽ thêm vào)
        if (markDrawing2) {
            int lastIndexRoadMark = roadMark.size() - 1;
            MyRectangle rect_now = roadMark.get(lastIndexRoadMark);
            // phải cho lớn hơn hoặc bằng, chứ ko nó cộng dồn speed sẽ vượt qua lengthMark liền
            // trước khi cộng speed vào doDaiAC thì phải kiểm tra đã
            if (rect_now.getDoDaiAC() >= convertDistantToReality(lengthMark)) {
                // neu da ve xong thif khoi tao 1 khoang trong moi, de ti nua ve
                roadSpace.add(0);
                markDrawing2 = false;
            }
        } else {
            int lastIndexRoadSpace = roadSpace.size() - 1;
            int value = roadSpace.get(lastIndexRoadSpace);
            int speed_now = 0;
            for (int i = value; i < convertDistantToReality(lengthSpace); i++) {
                if (speed_now == speed) {
                    break;
                }
                value++;
                speed_now++;
            }
            roadSpace.remove(lastIndexRoadSpace);
            roadSpace.add(value);
            if (value == convertDistantToReality(lengthSpace)) {
                int lastIndexRoadMark = roadMark.size() - 1;
                MyRectangle rect = roadMark.get(lastIndexRoadMark);
                //int yPA = (int) (rect.getpA().y - convertDistantToReality(lengthSpace));
                int yPA = yMin;
                // int yPA += 
                int xPA = x;
                MyPoint pA = new MyPoint(xPA, yPA);
                int doDaiAB = (int) convertDistantToReality(witdhMark);
                int doDaiAC = 0;
                roadMark.add(new MyRectangle(pA, doDaiAB, doDaiAC, color));
                markDrawing2 = true;
            }
//                System.out.println("road Space progress: " + value);
        }

    }

    public void draw(Graphics g) {
        for (MyRectangle rect : roadMark) {
            rect.draw(g);
        }
    }
}

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
public class MyCircle {

    private MyPoint pO;
    private double banKinh;
    private Color color = null;

    public MyCircle() {
        this.pO = null;
        this.banKinh = -1;
    }

    public MyCircle(MyPoint pO, double banKinh) {
        this.pO = pO;
        this.banKinh = banKinh;
    }

    public MyCircle(MyPoint pO, double banKinh, Color color) {
        this.pO = pO;
        this.banKinh = banKinh;
        this.color = color;
    }
    
    public void setData(MyPoint pO, double banKinh) {
        this.pO = pO;
        this.banKinh = banKinh;
    }

    public void setData(MyPoint pO, double banKinh, Color color) {
        this.pO = pO;
        this.banKinh = banKinh;
        this.color = color;
    }

    public MyPoint getpO() {
        return pO;
    }

    public double getBanKinh() {
        return banKinh;
    }

    public void setpO(MyPoint pO) {
        this.pO = pO;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void draw(Graphics g) {
        // thuat toan midpoint
        int x, y, p;
        y = (int) banKinh;
        x = 0;
        p = (int) (1 - banKinh);
        while (x <= y) {
            if (p < 0) {
                p += 2 * x + 3;
            } else {
                p += 2 * (x - y) + 5;
                y--;
            }
            x++;
            put8pixel(g, (int)pO.x, (int)pO.y , x, y);
        }
    }

    private void put8pixel(Graphics graphics, int x0, int y0, int x, int y) {
        putPixel(graphics, x + x0, y + y0, color);
        putPixel(graphics, y + x0, x + y0, color);
        putPixel(graphics, -y + x0, x + y0, color);
        putPixel(graphics, -x + x0, y + y0, color);
        putPixel(graphics, -x + x0, -y + y0, color);
        putPixel(graphics, -y + x0, -x + y0, color);
        putPixel(graphics, y + x0, -x + y0, color);
        putPixel(graphics, x + x0, -y + y0, color);
    }

}

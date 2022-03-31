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
public class MyEllipse {
    private MyPoint pO;
    private int a;
    private int b;
    private Color color;
    
    public MyEllipse(MyPoint pO, int banTrucLon, int banTrucBe, Color color) {
        this.pO = pO;
        this.a = banTrucLon;
        this.b = banTrucBe;
        this.color = color;
    }

    private static void put2PixelLower(Graphics g, int x0, int y0, int x, int y, Color color) {
        putPixel(g, (int) (x + x0), (int) (y + y0), color);
        putPixel(g, (int) (x0 - x), (int) (y + y0), color);
    }

    private static void put2PixelUpper(Graphics g, int x0, int y0, int x, int y, Color color) {
        putPixel(g, (int) (x + x0), (int) (-y + y0), color);
        putPixel(g, (int) (x0 - x), (int) (y0 - y), color);
    }

    public void drawDash(Graphics g, int dashLength, int density) {
        // dashLength: chieu dai net dut
        // density: khoang cach giua cac net ve dut voi nhau
        Boolean drawingUpper = true;
        int countNotDraw = 0;
        int countDraw = 0;
        int x0 = (int) pO.x;
        int y0 = (int) pO.y;
        float p;
        int x = 0;
        int y = b;
        int a2 = a * a;
        int b2 = b * b;
        float xc = (float) (a * a / Math.sqrt(a2 + b2));
        p = (float) (b2 - a2 + a2 * 0.25);
        put2PixelLower(g, x0, y0, x, y, color);
        countNotDraw++;
        while (x <= xc) {
            if (p < 0) {
                p += b2 * (2 * x + 3);
            } else {

                p += b2 * (2 * x + 3) - 2 * a2 * (y - 1);
                y--;
            }
            x++;
            // neu nhu dang ve net o phan tren cua ellipse (y>0) thi duoc vao
            if (drawingUpper) {
                if (countDraw == dashLength) {
                    drawingUpper = false;
                    //countDraw = 0;
                    countNotDraw = 0;
                } else {
                    put2PixelUpper(g,x0, y0, x, y, color);
                    put2PixelLower(g, x0, y0, x, y, color);
                    countDraw++;
                }
            } else {
                if (countNotDraw == density) {
                    drawingUpper = true;
                    countDraw = 0;
                    //countNotDraw = 0;
                } else {
                    put2PixelLower(g, x0, y0, x, y, color);
                    countNotDraw++;
                }
            }
        }

        x = a;
        y = 0;
        p = (float) (a2 - b2 * a + 0.25 * b2);

        if (drawingUpper) {
            if (countDraw == dashLength) {
                drawingUpper = false;
                //countDraw = 0;
                countNotDraw = 0;
            } else {
                put2PixelUpper(g,x0, y0, x, y, color);
                put2PixelLower(g, x0, y0, x, y, color);
                countDraw++;
            }
        } else {
            if (countNotDraw == density) {
                drawingUpper = true;
                countDraw = 0;
                //countNotDraw = 0;
            } else {
                put2PixelLower(g, x0, y0, x, y, color);
                countNotDraw++;
            }
        }
        while (x > xc) {
            if (p < 0) {
                p += a2 * (2 * y + 3);
            } else {

                p += a2 * (2 * y + 3) - 2 * b2 * (x - 1);
                x--;
            }
            y++;

            if (drawingUpper) {
                if (countDraw == dashLength) {
                    drawingUpper = false;
                    //countDraw = 0;
                    countNotDraw = 0;
                } else {
                    put2PixelUpper(g,x0, y0, x, y, color);
                    put2PixelLower(g, x0, y0, x, y, color);
                    countDraw++;
                }
            } else {
                if (countNotDraw == density) {
                    drawingUpper = true;
                    countDraw = 0;
                    //countNotDraw = 0;
                } else {
                    put2PixelLower(g, x0, y0, x, y, color);
                    countNotDraw++;
                }
            }

        }
    }
     
}

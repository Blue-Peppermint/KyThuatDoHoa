/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author chuon
 */
public class MyPoint {
    public double x;
    public double y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint(MyPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MyThread;
import Model.MyPoint;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chuon
 */
public class NewClass {
    public static void main(String[] args) {
        MyPoint pA = new MyPoint(275,265);
        MyPoint pB = new MyPoint(200,440);
//        MyPoint pA = new MyPoint(100,50);
//        MyPoint pB = new MyPoint(212,429);
        calLineEquation(pA, pB);
    }
    
     private static void calLineEquation(MyPoint pA, MyPoint pB){
        // tinh toan 2 diem a+b trong phuong trinh y =ax+b duong thang qua 2 diem cho truoc
        int x1 = (int)pA.x;
        int y1 = (int)pA.y;
        int x2 = (int)pB.x;
        int y2 = (int)pB.y;
        double D = x1 * 1 - x2 * 1;
        double Dx = y1 * 1 - y2 * 1;
        double Dy = x1 * y2 - x2 * y1;
        double a,b;
        if(D != 0){
            a = Dx/D;
            b = Dy/D;
        }
        else{
            a = -1;
            b = -1;
        }
         System.out.println("Ket Qua a: " + a +", b: "+ b);
    }
}

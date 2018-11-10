package me.christophermedlin.beziercurve;

import me.christophermedlin.beziercurve.gui.RenderingPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bezier Curve");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000,1000));
        frame.add(new RenderingPanel(frame));
        frame.setVisible(true);
    }
    /*
    public static Point bezier(Point[] points, float t) {
        int length = points.length;
        if (length == 1) {
            return points[0];
        }
        else {
            Point[] newPoints = new Point[length - 1];
            for (int i = 0; i < length - 1; i++) {
                int newX = (int) (points[i].x + t * (points[i + 1].x - points[i].x));
                int newY = (int) (points[i].y + t * (points[i + 1].y - points[i].y));
                newPoints[i] = new Point(newX, newY);
            }
            return bezier(newPoints, t);
        }
    }
    */
}

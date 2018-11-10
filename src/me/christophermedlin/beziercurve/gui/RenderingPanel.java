package me.christophermedlin.beziercurve.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RenderingPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Point[] points;
    private final float T_INCREMENT = 0.0001f;

    private Point mousePos, formerMousePos, previousPoint;

    public RenderingPanel(JFrame parentFrame) {
        super(null);
        this.setSize(parentFrame.getSize());

        addMouseMotionListener(this);
        addMouseListener(this);

        mousePos = new Point(0, 0);
        formerMousePos = new Point(0, 0);

        points = new Point[]{
                new Point(100, this.getHeight() - 100),
                new Point(this.getWidth() / 2, 100),
                new Point(this.getWidth() - 100, this.getHeight() - 100)
        };
        previousPoint = points[0];
    }

    public Point bezier(Point[] points, float t) {
        int length = points.length;
        if (length == 1) {
            return points[0];
        } else {
            Point[] newPoints = new Point[length - 1];
            for (int i = 0; i < length - 1; i++) {
                int newX = (int) (points[i].x + t * (points[i + 1].x - points[i].x));
                int newY = (int) (points[i].y + t * (points[i + 1].y - points[i].y));
                newPoints[i] = new Point(newX, newY);
            }
            return bezier(newPoints, t);
        }
    }

    public void addPoint(int x, int y) {
        int length = points.length;
        Point[] newArray = new Point[length + 1];
        for (int i = 0; i < length; ++i) {
            newArray[i] = points[i];
        }
        newArray[length] = new Point(x, y);
        points = newArray;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        for (float t = 0f; t < 1.0f; t += T_INCREMENT) {
            Point p = bezier(points, t);
            g.drawLine(previousPoint.x, previousPoint.y, p.x, p.y);
            previousPoint = p;
        }
        g.setColor(Color.RED);
        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];
            g.fillOval(p.x - 4, p.y - 4, 8, 8);
            g.drawString("P" + i, p.x + 2, p.y - 2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        addPoint(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
        formerMousePos.x = e.getX();
        formerMousePos.y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        for (Point p : points) {
            if (e.getX() <= p.x + 75 && e.getX() >= p.x - 75 && e.getY() <= p.y + 75 && e.getY() >= p.y - 75) {
                p.x -= formerMousePos.x - e.getX();
                p.y -= formerMousePos.y - e.getY();
            }
        }

        formerMousePos.x = e.getX();
        formerMousePos.y = e.getY();

        repaint();
    }
}
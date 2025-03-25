package zhrfrd.particlelife;

import java.awt.*;

public class Particle {
    private double x;
    private double y;
    private double vx;
    private double vy;
    final private Color COLOR;

    Particle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        COLOR = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public Color getColor() {
        return COLOR;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }
}
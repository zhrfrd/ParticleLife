package zhrfrd.particlelife;

import java.awt.*;

public class Particle {
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    final private Color COLOR;

    Particle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        COLOR = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
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

    public void setVelocityX(double vx) {
        this.velocityX = vx;
    }

    public void setVelocityY(double vy) {
        this.velocityY = vy;
    }
}
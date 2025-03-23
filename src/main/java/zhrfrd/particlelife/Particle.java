package zhrfrd.particlelife;

public class Particle {
    double x;
    double y;
    double vx;
    double vy;
    char color;

    Particle(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.vx = 0;
        this.vy = 0;
    }

    public Particle getParticle() {
        return this;
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

    public char getColor() {
        return color;
    }
}
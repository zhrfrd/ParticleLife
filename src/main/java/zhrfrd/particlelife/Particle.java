package zhrfrd.particlelife;

public class Particle {
    double x;
    double y;
    double vx;
    double vy;
    char c;

    Particle(int x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.vx = 0;
        this.vy = 0;
    }

    public Particle getAtom() {
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

    public char getC() {
        return c;
    }
}
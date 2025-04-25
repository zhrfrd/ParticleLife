package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * JPanel containing the actual simulation.
 */
public class SimulationPanel extends JPanel {
    private static final int PANEL_SIZE = 600;
    ControlsPanel controlsPanel;
    Simulation simulation;
//    Utils utils;
    ArrayList<Particle> particles = new ArrayList<>();

    SimulationPanel() {
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setVisible(true);
        setBackground(Color.darkGray);
//        utils = new Utils(this);
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void setControlsPanel(ControlsPanel controlsPanel) {
        this.controlsPanel = controlsPanel;
    }

    public void setParticles() {
        particles = simulation.getParticles();
    }

    public void update() {
        repaint();
        simulation.updateInteraction();
    }

    public void draw(Graphics graphics) {
        for (Particle particle : particles) {
            graphics.setColor(particle.getColor());
            graphics.fillOval((int) particle.getX(), (int) particle.getY(), 5, 5);   // Cast x/y from double to int to draw atoms. Definitely not ideal casting double to int
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}

package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel implements ActionListener {
    int PANEL_SIZE = 600;
    int DELAY = 60;
    Random random = new Random();
    ControlsPanel controlsPanel;
    Utils utils = new Utils(this);
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Rule> rules = new ArrayList<>(12);
    Simulation simulation;

    /**
     * JPanel containing the actual simulation.
     */
    SimulationPanel() {
//        simulation = new Simulation(this);
//        controlsPanel = simulation.controlsPanel;
//        controlsPanel = new ControlsPanel(simulation);
//        controlsPanel.makeGUI(this);
//        simulation.startControls();
//        particles = simulation.particles;
//        controlsPanel = new ControlsPanel(simulation);
//        controlsPanel.makeGUI(this);
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

    public void draw(Graphics graphics) {
        for (Particle particle : particles) {
            graphics.setColor(utils.getColor(particle.getColor()));
            graphics.fillOval(Math.abs((int) particle.x), Math.abs((int) particle.y), 5, 5);   // Cast x/y from double to int to draw atoms. Definitely not ideal casting double to int
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
        simulation.updateInteraction();

        if (actionEvent.getSource() == controlsPanel.resetButton) {
            controlsPanel.resetSimulation();
        }

        if (actionEvent.getSource() == controlsPanel.randomResetButton) {
            controlsPanel.resetRandom();
        }

        if (actionEvent.getSource() == controlsPanel.randomRulesButton) {
            simulation.randomRules();
        }
    }
}

package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel implements ActionListener {
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 600;
    int DELAY = 60;
    Random random = new Random();
    Controls controls;// = new Controls(this);
    Utils utils = new Utils(this);
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Rule> rules = new ArrayList<>(12);
    Simulation simulation;

    SimulationPanel() {
        this.setBounds(0,0,600,600);
        simulation = new Simulation(this);
        controls = new Controls(simulation);
        controls.makeGUI(this);
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

        if (actionEvent.getSource() == controls.resetButton) {
            controls.resetSimulation();
        }

        if (actionEvent.getSource() == controls.randomResetButton) {
            controls.resetRandom();
        }

        if (actionEvent.getSource() == controls.randomRulesButton) {
            simulation.randomRules();
        }
    }
}

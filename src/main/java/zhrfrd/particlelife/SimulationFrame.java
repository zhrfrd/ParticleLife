package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private static final int WIDTH = 810;
    private static final int HEIGHT = 610;
    SimulationPanel simulationPanel;
    ControlsPanel controlsPanel;
    Simulation simulation;

    public SimulationFrame() {
        setupFrame();
        initializeComponents();
        begin();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        simulationPanel = new SimulationPanel();
        this.add(simulationPanel, BorderLayout.WEST);

        controlsPanel = new ControlsPanel();
        this.add(controlsPanel, BorderLayout.EAST);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        simulation = new Simulation(simulationPanel, controlsPanel);
        simulationPanel.setSimulation(simulation);
        simulationPanel.setControlsPanel(controlsPanel);   // TODO: Move, wrong place!
        simulationPanel.setParticles();

        controlsPanel.setSimulation(simulation);
        controlsPanel.setSimulationPanel(simulationPanel);
    }

    private void begin() {
        simulation.begin();
    }
}

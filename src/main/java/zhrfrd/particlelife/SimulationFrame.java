package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private static final int WIDTH = 810;
    private static final int HEIGHT = 610;
    private static final String TITLE = "ParticleLife";
    SimulationPanel simulationPanel;
    ControlsPanel controlsPanel;
    Simulation simulation;

    SimulationFrame() {
        setupFrame();
        initializeComponents();
        begin();
    }

    private void setupFrame() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        simulationPanel = new SimulationPanel();
        add(simulationPanel, BorderLayout.WEST);

        controlsPanel = new ControlsPanel();
        add(controlsPanel, BorderLayout.EAST);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        simulation = new Simulation(this, simulationPanel, controlsPanel);
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

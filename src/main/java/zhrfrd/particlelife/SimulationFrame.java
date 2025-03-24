package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private static final int WIDTH = 810;
    private static final int HEIGHT = 610;
    private static final int PANEL_SIZE = 600;
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
        simulationPanel.setBounds(0, 0, PANEL_SIZE, PANEL_SIZE);
        simulationPanel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));   // TODO: Maybe remove?
//        simulationPanel.setBackground(Color.BLUE);
        simulationPanel.setVisible(true);
        this.add(simulationPanel, BorderLayout.WEST);

//        ControlsPanel controlsPanel = simulationPanel.controlsPanel;   // TODO: Change with getControls()
        controlsPanel = new ControlsPanel();
        controlsPanel.setBackground(Color.red);
        this.add(controlsPanel, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void begin() {
        simulation = new Simulation(simulationPanel, controlsPanel);
        simulationPanel.setSimulation(simulation);
        simulationPanel.setControlsPanel(controlsPanel);
        simulationPanel.setParticles();

        controlsPanel.setSimulation(simulation);
        simulation.begin();
    }
}

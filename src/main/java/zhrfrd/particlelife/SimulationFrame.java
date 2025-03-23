package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private static final int WIDTH = 810;
    private static final int HEIGHT = 610;
    private static final int PANEL_SIZE = 600;

    public SimulationFrame() {
        setupFrame();
        initializeComponents();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        SimulationPanel simulationPanel = new SimulationPanel();
        simulationPanel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        simulationPanel.setVisible(true);
        this.add(simulationPanel, BorderLayout.WEST);
        Controls controls = simulationPanel.controls;
        this.add(controls, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

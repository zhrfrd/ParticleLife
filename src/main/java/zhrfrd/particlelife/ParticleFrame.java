package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;

public class ParticleFrame extends JFrame {
    private static final int WIDTH = 810;
    private static final int HEIGHT = 610;
    private static final int PANEL_SIZE = 600;

    public ParticleFrame() {
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
        ParticlePanel particlePanel = new ParticlePanel();
        particlePanel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        particlePanel.setVisible(true);
        this.add(particlePanel, BorderLayout.WEST);
        Controls controls = particlePanel.controls;
        this.add(controls, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

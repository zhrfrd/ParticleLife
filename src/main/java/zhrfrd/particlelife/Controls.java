package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Controls extends JPanel {
    ParticlePanel particlePanel;
    public JButton resetButton = new JButton();
    public JButton randomResetButton = new JButton();
    public JButton randomRulesButton = new JButton();
    public JLabel totalParticlesLabel = new JLabel();
    public JLabel totalRulesLabel = new JLabel();

    Controls(ParticlePanel particlePanel) {
        this.particlePanel = particlePanel;
        this.setLayout(new GridLayout(12,2));
        this.setPreferredSize(new Dimension(200,600));
        this.setVisible(true);
    }

    public void makeGUI(ActionListener actionListener) {
        // Reset button
        resetButton.setText("Reset");
        resetButton.addActionListener(actionListener);
        resetButton.setPreferredSize(new Dimension(60,20));
        resetButton.setVisible(true);
        this.add(resetButton);

        // Random reset button
        randomResetButton.setText("Random Reset");
        randomResetButton.addActionListener(actionListener);
        randomResetButton.setPreferredSize(new Dimension(60,20));
        randomResetButton.setVisible(true);
        this.add(randomResetButton);

        // Randomize rules
        randomRulesButton.setText("Random Rules");
        randomRulesButton.addActionListener(actionListener);
        randomRulesButton.setPreferredSize(new Dimension(60,20));
        randomRulesButton.setVisible(true);
        this.add(randomRulesButton);

        // Number of particles
        JPanel particlesPanel = new JPanel();
        particlesPanel.setLayout(new BorderLayout());
        JLabel particlesName = new JLabel();
        particlesPanel.setPreferredSize(new Dimension(160,20));
        totalParticlesLabel.setPreferredSize(new Dimension(60,20));
        particlesName.setPreferredSize(new Dimension(100,20));
        particlesName.setText("Total Atoms: ");
        totalParticlesLabel.setVisible(true);
        particlesName.setVisible(true);
        particlesPanel.add(particlesName, BorderLayout.WEST);
        particlesPanel.add(totalParticlesLabel, BorderLayout.EAST);
        this.add(particlesPanel);

        // Number of rules
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        JLabel rulesName = new JLabel();
        rulesPanel.setPreferredSize(new Dimension(160,20));
        totalRulesLabel.setPreferredSize(new Dimension(60,20));
        rulesName.setPreferredSize(new Dimension(100,20));
        rulesName.setText("Total Rules: ");
        totalRulesLabel.setVisible(true);
        rulesName.setVisible(true);
        rulesPanel.add(rulesName, BorderLayout.WEST);
        rulesPanel.add(totalRulesLabel, BorderLayout.EAST);
        this.add(rulesPanel);
    }

    public void resetSimulation(){
        particlePanel.timer.stop();
        particlePanel.particles.clear();
        particlePanel.red.clear();
        particlePanel.green.clear();
        particlePanel.blue.clear();
        particlePanel.yellow.clear();
        particlePanel.magenta.clear();
        particlePanel.createParticles();
        particlePanel.timer.start();
        particlePanel.randomRules();
    }

    public void resetRandom(){
        particlePanel.timer.stop();
        particlePanel.particles.clear();
        particlePanel.red.clear();
        particlePanel.green.clear();
        particlePanel.blue.clear();
        particlePanel.yellow.clear();
        particlePanel.magenta.clear();
        particlePanel.createRandomParticles();
        particlePanel.timer.start();
        particlePanel.randomRules();
    }

    public void start(){
        particlePanel.timer.start();
    }

    public void stop(){
        particlePanel.timer.stop();
    }
}

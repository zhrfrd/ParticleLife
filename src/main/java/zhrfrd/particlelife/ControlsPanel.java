package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel implements ActionListener {
    SimulationPanel simulationPanel;
    Simulation simulation;
    public JButton resetButton = new JButton();
    public JButton randomResetButton = new JButton();
    public JButton randomRulesButton = new JButton();
    public JLabel totalParticlesLabel = new JLabel();
    public JLabel totalRulesLabel = new JLabel();

//    ControlsPanel(Simulation simulation) {
//        this.simulation = simulation;
//        this.setLayout(new GridLayout(12,2));
//        this.setPreferredSize(new Dimension(200,600));
//        this.setVisible(true);
//    }

    /**
     * JPanel containing controls such as buttons and labels.
     */
    ControlsPanel() {
        setLayout(new GridLayout(12,2));
        setPreferredSize(new Dimension(200,600));
        setVisible(true);

        makeGUI(this);
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
        particlesPanel.setBackground(Color.yellow);
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
        rulesPanel.setBackground(Color.orange);
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

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void resetSimulation(){
        simulation.timer.stop();
        simulation.particles.clear();
        simulation.red.clear();
        simulation.green.clear();
        simulation.blue.clear();
        simulation.yellow.clear();
        simulation.magenta.clear();
        simulation.createParticles();
        simulation.timer.start();
        simulation.randomRules();
    }

    public void resetRandom(){
        simulation.timer.stop();
        simulation.particles.clear();
        simulation.red.clear();
        simulation.green.clear();
        simulation.blue.clear();
        simulation.yellow.clear();
        simulation.magenta.clear();
        simulation.createRandomParticles();
        simulation.timer.start();
        simulation.randomRules();
    }

    public void start(){
        simulation.timer.start();
    }

    public void stop(){
        simulation.timer.stop();
    }

//    public void draw(Graphics graphics) {
//        for (Particle particle : particles) {
//            graphics.setColor(utils.getColor(particle.getColor()));
//            graphics.fillOval(Math.abs((int) particle.x), Math.abs((int) particle.y), 5, 5);   // Cast x/y from double to int to draw atoms. Definitely not ideal casting double to int
//        }
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
        simulation.updateInteraction();

        if (actionEvent.getSource() == resetButton) {
            resetSimulation();
        }

        if (actionEvent.getSource() == randomResetButton) {
            resetRandom();
        }

        if (actionEvent.getSource() == randomRulesButton) {
            simulation.randomRules();
        }
    }
}

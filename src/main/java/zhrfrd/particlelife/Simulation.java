package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    SimulationPanel simulationPanel;
    private ArrayList<Particle> yellowParticles;
    private ArrayList<Particle> redParticles;
    private ArrayList<Particle> greenParticles;
    private ArrayList<Particle> blueParticles;
    private ArrayList<Particle> magentaParticles;
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 600;
    int DELAY = 60;
    Random random;
    int rulesLimit = 20;
    ControlsPanel controlsPanel;
    Timer timer;
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Rule> rules = new ArrayList<>(12);

    Simulation(SimulationPanel simulationPanel, ControlsPanel controlsPanel) {
        this.simulationPanel = simulationPanel;
        this.controlsPanel = controlsPanel;
        random = new Random();
        generateHeterogeneousNumberOfParticles();
        randomRules();
    }

    public void begin() {
        timer = new Timer(DELAY, controlsPanel);
        timer.start();
    }

    private void generateHomogeneousNumberOfParticles() {
        yellowParticles = generateParticles(300, Color.yellow);
        redParticles = generateParticles(300, Color.red);
        greenParticles = generateParticles(300, Color.green);
        blueParticles = generateParticles(300, Color.blue);
        magentaParticles = generateParticles(300, Color.magenta);
    }

    private void generateHeterogeneousNumberOfParticles() {
        yellowParticles = generateParticles(random.nextInt(500), Utils.randomColor(random));
        redParticles = generateParticles(random.nextInt(500), Utils.randomColor(random));
        greenParticles = generateParticles(random.nextInt(500), Utils.randomColor(random));
        blueParticles = generateParticles(random.nextInt(500), Utils.randomColor(random));
        magentaParticles = generateParticles(random.nextInt(500), Utils.randomColor(random));
    }

    public ArrayList<Particle> generateParticles(int numberOfParticles, Color color) {
        ArrayList<Particle> particlesGroup = new ArrayList<>();

        for (int i = 0; i < numberOfParticles; i ++) {
            Particle particle = new Particle(random.nextInt(100) * 6, random.nextInt(100) * 6, color);
            particlesGroup.add(i, particle);
            particles.add(particlesGroup.get(i));
        }

        return particlesGroup;
    }

    public void updateInteraction() {
        for (Rule rule : rules) {
            interactionRule(rule);
        }
        controlsPanel.totalParticlesLabel.setText(String.valueOf(particles.size()));
        controlsPanel.totalRulesLabel.setText(String.valueOf(rules.size()));
    }

    // TODO: Refactor and avoid recursion in else statement
    public void randomRules() {
        rules.clear();
        int numRule = random.nextInt(rulesLimit);   // Rules limit
        if (numRule > 5 && numRule < 20 && !(numRule % 2 == 0)) {
            for (int i = 0; i < numRule; i ++) {
                Rule rule = new Rule();
                rule.color1 = Utils.randomGroupOfParticles(this, random);
                rule.color2 = Utils.randomGroupOfParticles(this, random);
                rule.g = Utils.randomDouble(this);
                rules.add(i, rule);

            }
        } else {
            randomRules();
        }

        controlsPanel.totalRulesLabel.setText(String.valueOf(numRule));
    }

    public void reset(Action action) {
        timer.stop();
        particles.clear();
        redParticles.clear();
        greenParticles.clear();
        blueParticles.clear();
        yellowParticles.clear();
        magentaParticles.clear();

        if (Action.RESET_HOMOGENEOUS.equals(action)) {
            generateHomogeneousNumberOfParticles();
        } else if (Action.RESET_HETEROGENEOUS.equals(action)) {
            generateHeterogeneousNumberOfParticles();
        }

        timer.start();
        randomRules();
    }

    public void interactionRule(Rule rule) {
        ArrayList<Particle> particles1 = rule.color1;
        ArrayList<Particle> particles2 = rule.color1;
        double g = rule.g;

        for (Particle particle1 : particles1) {
            double fx = 0;
            double fy = 0;

            for (Particle particle2 : particles2) {
                double dx = particle1.getX() - particle2.getX();
                double dy = particle1.getY() - particle2.getY();
                double d = Math.sqrt(dx * dx + dy * dy);

                if (d > 0 && d < 80) {
                    double F = (g * 1) / d;
                    fx += F * dx;
                    fy += F * dy;
                }
            }

            particle1.setVx((particle1.getVx() + fx) * 0.5);
            particle1.setVy((particle1.getVy() + fy) * 0.5);
            particle1.setX(particle1.getX() + particle1.getVx());
            particle1.setY(particle1.getY() + particle1.getVy());

            if (particle1.getX() <= 0 || particle1.getX() >= SCREEN_WIDTH) {
                particle1.setVx(particle1.getVx() * -1);
            }
            if (particle1.getY() <= 0 || particle1.getY() >= SCREEN_HEIGHT) {
                particle1.setVy(particle1.getVy() * -1);
            }
        }
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public ArrayList<Particle> getGreenParticles() {
        return greenParticles;
    }

    public ArrayList<Particle> getRedParticles() {
        return redParticles;
    }

    public ArrayList<Particle> getBlueParticles() {
        return blueParticles;
    }

    public ArrayList<Particle> getYellowParticles() {
        return yellowParticles;
    }

    public ArrayList<Particle> getMagentaParticles() {
        return magentaParticles;
    }
}

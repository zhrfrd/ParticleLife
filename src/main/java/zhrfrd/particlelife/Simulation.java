package zhrfrd.particlelife;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    SimulationPanel simulationPanel;
    ArrayList<Particle> yellow;
    ArrayList<Particle> red;
    ArrayList<Particle> green;
    ArrayList<Particle> blue;
    ArrayList<Particle> magenta;
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 600;
    int DELAY = 60;
    Random random = new Random();
    int rulesLimit = 20;
    ControlsPanel controlsPanel;// = new ControlsPanel(this);
    Utils utils;// = new Utils(this);
    Timer timer;// = new Timer(DELAY, this);   // Start timer which activates action listener on DELAY interval
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Rule> rules = new ArrayList<>(12);

    public Simulation(SimulationPanel simulationPanel, ControlsPanel controlsPanel) {
        this.simulationPanel = simulationPanel;
        this.controlsPanel = controlsPanel;
        utils = new Utils(this.simulationPanel);
        timer = new Timer(DELAY, simulationPanel);
        createRandomParticles();
        randomRules();
//        controlsPanel.start();   // Start timer
    }

    public void begin() {
        timer.start();
    }

    public void startControls() {
        controlsPanel.start();   // Start timer
    }

    public void createParticles(){
        yellow = generateParticles(300, 'y');
        red = generateParticles(300, 'r');
        green = generateParticles(300, 'g');
        blue = generateParticles(300, 'b');
        magenta = generateParticles(300, 'm');
    }

    public ArrayList<Particle> generateParticles(int numberOfParticles, char color) {
        ArrayList<Particle> particlesGroup = new ArrayList<>();

        for (int i = 0; i < numberOfParticles; i ++) {
            Particle particle = new Particle(utils.random(this), utils.random(this), color);
            particlesGroup.add(i, particle);
            particles.add(particlesGroup.get(i));
        }

        return particlesGroup;
    }

    public void createRandomParticles(){
        yellow = generateParticles(random.nextInt(500), utils.randomColor(this));
        red = generateParticles(random.nextInt(500), utils.randomColor(this));
        green = generateParticles(random.nextInt(500), utils.randomColor(this));
        blue = generateParticles(random.nextInt(500), utils.randomColor(this));
        magenta = generateParticles(random.nextInt(500), utils.randomColor(this));
    }

    public void updateInteraction() {

        for (Rule rule : rules) {
            interactionRule(rule);
        }
        controlsPanel.totalParticlesLabel.setText(String.valueOf(particles.size()));
        controlsPanel.totalRulesLabel.setText(String.valueOf(rules.size()));
    }

    public void randomRules(){
        rules.clear();
        int numRule = random.nextInt(rulesLimit);// rules limit
        if (numRule > 5 && numRule < 20 && !(numRule % 2 == 0)) {
            for (int i = 0; i < numRule; i ++) {
                Rule rule = new Rule();
                rule.color1 = utils.randomGroupOfParticles(this);
                rule.color2 = utils.randomGroupOfParticles(this);
                rule.g = utils.randomDouble(this);
                rules.add(i, rule);

            }
        } else {
            randomRules();
        }

//        controlsPanel.totalRulesLabel.setText(String.valueOf(numRule));
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public void interactionRule(Rule rule) {
        ArrayList<Particle> particles1 = rule.color1;
        ArrayList<Particle> particles2 = rule.color1;
        double g = rule.g;

        for (Particle particle1 : particles1) {
            double fx = 0;
            double fy = 0;

            for (Particle particle2 : particles2) {
                double dx = particle1.x - particle2.x;
                double dy = particle1.y - particle2.y;
                double d = Math.sqrt(dx * dx + dy * dy);
                if (d > 0 && d < 80) {
                    double F = (g * 1) / d;
                    fx += F * dx;
                    fy += F * dy;
                }
            }
            particle1.vx = ((particle1.vx + fx) * 0.5);
            particle1.vy = ((particle1.vy + fy) * 0.5);
            particle1.x += particle1.vx;
            particle1.y += particle1.vy;
            if (particle1.x <= 0 || particle1.x >= SCREEN_WIDTH) {
                particle1.vx *= -1;
            }
            if (particle1.y <= 0 || particle1.y >= SCREEN_HEIGHT) {
                particle1.vy *= -1;
            }
        }
    }
}

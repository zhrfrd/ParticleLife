package zhrfrd.particlelife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ParticlePanel extends JPanel implements ActionListener {
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 600;
    int DELAY = 60;
    Random random = new Random();
    //simulation variables
    int rulesLimit = 20;
    Controls controls = new Controls(this);
    Utils utils = new Utils(this);
    Timer timer = new Timer(DELAY, this);   // Start timer which activates action listener on DELAY interval
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Rule> rules = new ArrayList<>(12);
    ArrayList<Particle> yellow;
    ArrayList<Particle> red;
    ArrayList<Particle> green;
    ArrayList<Particle> blue;
    ArrayList<Particle> magenta;

    ParticlePanel() {
        controls.makeGUI(this);
        this.setBounds(0,0,600,600);
        createRandomParticles();
        randomRules();
        controls.start();//start timer
    }

    public void createParticles(){
        yellow = generateParticles(300, 'y');
        red = generateParticles(300, 'r');
        green = generateParticles(300, 'g');
        blue = generateParticles(300, 'b');
        magenta = generateParticles(300, 'm');
    }
    public ArrayList<Particle> generateParticles(int number, char color) {
        // Create particles and pack into ArrayList(s)
        ArrayList<Particle> particleGroup = new ArrayList<>(); //used to pass atoms to individual color array(s), reference for ALL atoms packed into 'atoms' array
        for (int i = 0; i < number; i ++) { //with separate arrays for each color we can run interactions on each, then paint all atoms using 'atoms'
            Particle particle = new Particle(utils.random(this), utils.random(this), color);
            particleGroup.add(i, particle);
            particles.add(particleGroup.get(i));
        }
        return particleGroup;
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
        controls.totalParticlesLabel.setText(String.valueOf(particles.size()));
        controls.totalRulesLabel.setText(String.valueOf(rules.size()));
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

        controls.totalRulesLabel.setText(String.valueOf(numRule));
    }

    public void interactionRule(Rule rule) {
        ArrayList<Particle> particles1 = rule.color1;
        ArrayList<Particle> particles2 = rule.color1;
        double g = rule.g;

        for (Particle particle1 : particles1) {
            double fx = 0;
            double fy = 0;
            Particle a = particle1;
            for (Particle particle2 : particles2) {
                a = particle2;
                Particle b = particle2;
                double dx = a.x - b.x;
                double dy = a.y - b.y;
                double d = Math.sqrt(dx * dx + dy * dy);
                if (d > 0 && d < 80) {
                    double F = (g * 1) / d;
                    fx += F * dx;
                    fy += F * dy;
                }
            }
            a.vx = ((a.vx + fx) * 0.5);
            a.vy = ((a.vy + fy) * 0.5);
            a.x += a.vx;
            a.y += a.vy;
            if (a.x <= 0 || a.x >= SCREEN_WIDTH) {
                a.vx *= -1;
            }
            if (a.y <= 0 || a.y >= SCREEN_HEIGHT) {
                a.vy *= -1;
            }
        }
    }

    public void draw(Graphics graphics) {
        for (Particle particle : particles) {
            graphics.setColor(utils.getColor(particle.getC()));
            graphics.fillOval(Math.abs((int) particle.x), Math.abs((int) particle.y), 5, 5);// cast x/y from double to int to draw atoms. Definitely not ideal casting double to int
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
        updateInteraction();

        if (actionEvent.getSource() == controls.resetButton) {
            controls.resetSimulation();
        }

        if (actionEvent.getSource() == controls.randomResetButton) {
            controls.resetRandom();
        }

        if (actionEvent.getSource() == controls.randomRulesButton) {
            randomRules();
        }
    }
}

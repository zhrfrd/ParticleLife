package zhrfrd.particlelife;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation implements Runnable {
    private ArrayList<Particle> yellowParticles;
    private ArrayList<Particle> redParticles;
    private ArrayList<Particle> greenParticles;
    private ArrayList<Particle> blueParticles;
    private ArrayList<Particle> magentaParticles;
    private boolean running = false;
    /** Size of the actual simulation area */
    private final int SCREEN_SIZE = 600;
    int rulesLimit = 20;
    SimulationFrame simulationFrame;
    SimulationPanel simulationPanel;
    ControlsPanel controlsPanel;
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Rule> rules = new ArrayList<>(rulesLimit);

    Simulation(SimulationFrame simulationFrame, SimulationPanel simulationPanel, ControlsPanel controlsPanel) {
        this.simulationFrame = simulationFrame;
        this.simulationPanel = simulationPanel;
        this.controlsPanel = controlsPanel;
        generateHeterogeneousNumberOfParticles();
        randomRules();
    }

    public void begin() {
        running = true;
        Thread thread = new Thread(this, "Simulation Thread");
        thread.start();
    }

    private void generateHomogeneousNumberOfParticles() {
        yellowParticles = generateParticles(300, Color.yellow);
        redParticles = generateParticles(300, Color.red);
        greenParticles = generateParticles(300, Color.green);
        blueParticles = generateParticles(300, Color.blue);
        magentaParticles = generateParticles(300, Color.magenta);
    }

    private void generateHeterogeneousNumberOfParticles() {
        Random random = new Random();
        yellowParticles = generateParticles(random.nextInt(500), Color.yellow);
        redParticles = generateParticles(random.nextInt(500), Color.red);
        greenParticles = generateParticles(random.nextInt(500), Color.green);
        blueParticles = generateParticles(random.nextInt(500), Color.blue);
        magentaParticles = generateParticles(random.nextInt(500), Color.magenta);
    }

    public ArrayList<Particle> generateParticles(int numberOfParticles, Color color) {
        ArrayList<Particle> particlesGroup = new ArrayList<>();

        for (int i = 0; i < numberOfParticles; i ++) {
            Random random = new Random();
            Particle particle = new Particle(random.nextInt(SCREEN_SIZE), random.nextInt(SCREEN_SIZE), color);
            particlesGroup.add(particle);
            particles.add(particle);
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
        Random random = new Random();
        int numRule = random.nextInt(rulesLimit);   // Rules limit
        if (numRule > 5 && numRule < 20 && !(numRule % 2 == 0)) {
            for (int i = 0; i < numRule; i ++) {
                Rule rule = new Rule();
                rule.color1 = Utils.randomGroupOfParticles(this, random);
                rule.color2 = Utils.randomGroupOfParticles(this, random);
                rule.gravity = Utils.randomDouble();
                rules.add(rule);

            }
        } else {
            randomRules();
        }

        controlsPanel.totalRulesLabel.setText(String.valueOf(numRule));
    }

    public void reset(Action action) {
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

        randomRules();
    }

    public void interactionRule(Rule rule) {
        ArrayList<Particle> particles1 = rule.color1;
        ArrayList<Particle> particles2 = rule.color2;
        double gravity = rule.gravity;

        for (Particle particle1 : particles1) {
            double forceX = 0;
            double forceY = 0;

            for (Particle particle2 : particles2) {
                double distanceX = particle1.getX() - particle2.getX();
                double distanceY = particle1.getY() - particle2.getY();
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                // Force only apply when the distance between particles is grater than 0 and less than 80.
                if (distance > 0 && distance < 80) {
                    double force = gravity / distance;
                    forceX += force * distanceX;
                    forceY += force * distanceY;
                }
            }

            particle1.setVelocityX((particle1.getVelocityX() + forceX) * 0.5);
            particle1.setVelocityY((particle1.getVelocityY() + forceY) * 0.5);
            particle1.setX(particle1.getX() + particle1.getVelocityX());
            particle1.setY(particle1.getY() + particle1.getVelocityY());

            if (particle1.getX() <= 0 || particle1.getX() >= SCREEN_SIZE) {
                particle1.setVelocityX(particle1.getVelocityX() * (- 1));
            }
            if (particle1.getY() <= 0 || particle1.getY() >= SCREEN_SIZE) {
                particle1.setVelocityY(particle1.getVelocityY() * (- 1));
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

    public void update () {
        // TODO: Handle the simulation logic
    }

    public void render() {
        // TODO: Handle the graphic rendering
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0; // How many frames per second
        int updates = 0; // How many updates per second (it should be always 60)
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                simulationPanel.update();
                update(); // Update 60 times per second
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) { // Each second
                timer += 1000; // Increase by 10000 each time in order to keep the upper condition
                simulationFrame.setTitle("ParticleLife  |  " + updates + "ups " + frames + "fps");
                updates = 0;
                frames = 0;
            }
        }
    }
}

package zhrfrd.particlelife;

import java.awt.*;
import java.util.ArrayList;

public class Utils {
    SimulationPanel simulationPanel;

    Utils(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    public int random(Simulation simulation){
        return simulation.random.nextInt(100) * 6;
    }

    public double randomDouble(Simulation simulation) {
        double d;
        double upper = 0.3;
        double lower = - 0.3;
        d = Math.random() * (upper-lower) + lower;

        return d;
    }

    public Color getColor(char c) {
        //transcribe char to Java color object
        if (c=='g'||c==0) {return Color.green;}
        if (c=='r'||c==1) {return Color.red;}
        if (c=='b'||c==2) {return Color.blue;}
        if (c=='y'||c==3) {return Color.yellow;}
        if (c=='m'||c==4) {return Color.magenta;}

        return null;
    }

    public char randomColor(Simulation simulation) {
        int ran = simulation.random.nextInt(5);
        if (ran==0) {return 'g';}
        if (ran==1) {return 'r';}
        if (ran==2) {return 'b';}
        if (ran==3) {return 'y';}
        if (ran==4) {return 'm';}
        return 'c';
    }

    public ArrayList<Particle> randomGroupOfParticles(Simulation simulation) {
        int ran = simulation.random.nextInt(5);
        if (ran==0) {return simulation.green;}
        if (ran==1) {return simulation.red;}
        if (ran==2) {return simulation.blue;}
        if (ran==3) {return simulation.yellow;}
        return simulation.magenta;
    }
}

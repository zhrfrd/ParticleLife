package zhrfrd.particlelife;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Utils {
    public static double randomDouble(Simulation simulation) {
        double d;
        double upper = 0.3;
        double lower = - 0.3;
        d = Math.random() * (upper-lower) + lower;

        return d;
    }

    public static Color randomColor(Random random) {
        int value = random.nextInt(5);

        if (value == 0) return Color.green;
        if (value == 1) return Color.red;
        if (value == 2) return Color.blue;
        if (value == 3) return Color.yellow;

        return Color.magenta;
    }

    public static ArrayList<Particle> randomGroupOfParticles(Simulation simulation, Random random) {
        int value = random.nextInt(5);

        if (value == 0) return simulation.getGreenParticles();
        if (value == 1) return simulation.getRedParticles();
        if (value == 2) return simulation.getBlueParticles();
        if (value == 3) return simulation.getYellowParticles();

        return simulation.getMagentaParticles();
    }
}

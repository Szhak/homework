package part2;

import java.util.*;
import java.util.concurrent.*;

public class SimulationDriver {
    public static void main(String[] args) throws InterruptedException {
        ControlTower tower = new ControlTower();
        List<Aircraft> fleet = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 1; i <= 10; i++) {
            Aircraft a = switch (rnd.nextInt(3)) {
                case 0 -> new PassengerPlane("P" + i, tower);
                case 1 -> new CargoPlane("C" + i, tower);
                default -> new Helicopter("H" + i, tower);
            };
            fleet.add(a);
        }

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(3);

        // Every second, each aircraft randomly requests runway or MAYDAY
        for (Aircraft a : fleet) {
            exec.scheduleAtFixedRate(() -> {
                if (rnd.nextDouble() < 0.05) {
                    a.send("MAYDAY");
                } else {
                    a.requestRunway();
                }
            }, 0, 1, TimeUnit.SECONDS);
        }

        // Let simulation run 15 seconds then shutdown
        Thread.sleep(15000);
        exec.shutdownNow();
        System.out.println("--- Simulation ended ---");
    }
}
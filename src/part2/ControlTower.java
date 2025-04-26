package part2;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new ConcurrentLinkedQueue<>();
    private final Queue<Aircraft> takeoffQueue = new ConcurrentLinkedQueue<>();

    // Keep track of emergency planes waiting
    private final Deque<Aircraft> emergencyStack = new ArrayDeque<>();

    @Override
    public synchronized void broadcast(String msg, Aircraft sender) {
        if ("MAYDAY".equalsIgnoreCase(msg.trim())) {
            // Emergency protocol: clear runway, notify all others to hold, grant landing to sender
            System.out.println("--- EMERGENCY DECLARED by " + sender.getId() + " ---");
            notifyAllHold(sender);
            emergencyStack.push(sender);
            grantEmergencyLanding();
            return;
        }
        // Regular broadcast: notify every aircraft in both queues
        for (Aircraft a : getAllAircraft()) {
            if (a != sender) {
                a.receive(String.format("From %s: %s", sender.getId(), msg));
            }
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        // Determine request type from message or type
        // For simplicity: CargoPlane and PassengerPlane request landing; Helicopter requests takeoff
        if (a instanceof Helicopter) {
            takeoffQueue.offer(a);
        } else {
            landingQueue.offer(a);
        }
        scheduleNext();
        return isOnRunway(a);
    }

    private void scheduleNext() {
        // Emergency landings first
        if (!emergencyStack.isEmpty()) {
            return;
        }
        // Landing queue has priority over takeoff
        if (!landingQueue.isEmpty()) {
            Aircraft next = landingQueue.poll();
            System.out.println("[Tower] Clearing runway for landing: " + next.getId());
            notifyAircraft(next, "Cleared to LAND now.");
        } else if (!takeoffQueue.isEmpty()) {
            Aircraft next = takeoffQueue.poll();
            System.out.println("[Tower] Clearing runway for takeoff: " + next.getId());
            notifyAircraft(next, "Cleared to TAKEOFF now.");
        }
    }

    private void grantEmergencyLanding() {
        Aircraft em = emergencyStack.pop();
        System.out.println("[Tower] EMERGENCY LANDING granted to " + em.getId());
        notifyAircraft(em, "Emergency CLEARANCE to LAND NOW.");
    }

    private void notifyAllHold(Aircraft except) {
        for (Aircraft a : getAllAircraft()) {
            if (a != except) {
                NotifyHoldRunnable hold = new NotifyHoldRunnable(a);
                hold.run();
            }
        }
    }

    private void notifyAircraft(Aircraft a, String msg) {
        a.receive("Tower: " + msg);
    }

    private boolean isOnRunway(Aircraft a) {
        // Cleared if just notified
        return false; // simplified: rely on broadcast output
    }

    private List<Aircraft> getAllAircraft() {
        List<Aircraft> all = new ArrayList<>();
        all.addAll(landingQueue);
        all.addAll(takeoffQueue);
        all.addAll(emergencyStack);
        return all;
    }

    // Utility inner for hold message
    private static class NotifyHoldRunnable implements Runnable {
        private final Aircraft a;
        public NotifyHoldRunnable(Aircraft a) { this.a = a; }
        @Override public void run() {
            a.receive("Tower: HOLD position until further notice.");
        }
    }
}
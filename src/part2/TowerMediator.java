package part2;

public interface TowerMediator {
    // Broadcast a message to all aircraft except the sender
    void broadcast(String msg, Aircraft sender);

    // Aircraft requests runway access: true if cleared, false if queued
    boolean requestRunway(Aircraft a);
}
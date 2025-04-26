package part2;

public abstract class Aircraft {
    protected String id;
    protected TowerMediator tower;

    public Aircraft(String id, TowerMediator tower) {
        this.id = id;
        this.tower = tower;
    }


    // Receive a broadcast message
    public abstract void receive(String msg);

    // Send a message via the tower
    public void send(String msg) {
        System.out.printf("[%s] sending: %s%n", id, msg);
        tower.broadcast(msg, this);
    }
    // Request runway clearance (landing or takeoff)
    public void requestRunway() {
        boolean cleared = tower.requestRunway(this);
        System.out.printf("[%s] runway request %s%n", id, (cleared ? "GRANTED" : "QUEUED"));
    }

    public String getId() {
        return id;
    }
}
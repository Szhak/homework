package part2;

public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id, TowerMediator tower) {
        super(id, tower);
    }

    @Override
    public void receive(String msg) {
        System.out.printf("[%s] PassengerPlane console: %s%n", id, msg);
    }
}
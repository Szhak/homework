package part2;

public class CargoPlane extends Aircraft {
    public CargoPlane(String id, TowerMediator tower) {
        super(id, tower);
    }

    @Override
    public void receive(String msg) {
        System.out.printf("[%s] CargoPlane dashboard: %s%n", id, msg);
    }
}
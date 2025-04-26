package part2;

public class Helicopter extends Aircraft {
    public Helicopter(String id, TowerMediator tower) {
        super(id, tower);
    }

    @Override
    public void receive(String msg) {
        System.out.printf("[%s] Helicopter panel: %s%n", id, msg);
    }
}
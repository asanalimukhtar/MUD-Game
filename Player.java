import java.util.*;

public class Player {
    Room curr;
    List<Item> inv = new ArrayList<>();
    Player(Room start) {
        curr = start;
    }
    void move(Room newRoom) {
        curr = newRoom;
    }
    void pickUp(Item item) {
        inv.add(item);
    }
    boolean hasItem(String name) {
        return inv.stream().anyMatch(i -> i.getName().equalsIgnoreCase(name));
    }
    String inv() {
        return inv.isEmpty() ? "Nothing in inventory." : "Inventory: " + inv.stream().map(Item::getName).reduce("", (a, b) -> a + " " + b);
    }
}
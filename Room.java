import java.util.*;

public class Room {
    String name, desc, npc;
    Map<String, Room> exits = new HashMap<>();
    List<Item> items = new ArrayList<>();
    Room(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.npc = null;
    }

    void setExit(String dir, Room room) {
        exits.put(dir, room);
    }

    Room getExit(String dir) {
        return exits.get(dir);
    }

    void addItem(Item item) {
        items.add(item);
    }

    boolean removeItem(String name) {
        return items.removeIf(i -> i.getName().equalsIgnoreCase(name));
    }

    Item getItem(String name) {
        for (Item i : items) if (i.getName().equalsIgnoreCase(name)) return i;
        return null;
    }

    void setNPC(String npc) {
        this.npc = npc;
    }

    boolean hasNPC() {
        return npc != null;
    }

    void removeNPC() {
        this.npc = null;
    }

    String desc() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room: ").append(name).append("\n").append(desc).append("\n");
        sb.append("Items: ").append(items.isEmpty() ? "None\n" : items.stream().map(Item::getName).reduce("", (a, b) -> a + " " + b) + "\n");
        if (npc != null) sb.append(npc).append(" is here!\n");
        return sb.toString();
    }
}
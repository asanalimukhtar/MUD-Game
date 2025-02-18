public class Main {
    public static void main(String[] args) {
        Room start = new Room("Start Room", "A small stone chamber.");
        Room hall = new Room("Hallway", "A dark and narrow corridor.");
        Room arm = new Room("Armory", "An old storage room.");
        Room treas = new Room("Treasure Room", "A golden chamber. Locked!");
        Room boss = new Room("Boss Room", "A dark hall with a figure ahead.");
        Room lib = new Room("Library", "A room filled with magical books.");
        Room dung = new Room("Dungeon", "A mysterious underground cave. Locked!");

        start.setExit("forward", hall);
        hall.setExit("back", start);
        hall.setExit("left", arm);
        hall.setExit("right", treas);
        hall.setExit("forward", boss);
        hall.setExit("up", lib);
        arm.setExit("back", hall);
        lib.setExit("down", dung);

        arm.addItem(new Item("sword"));
        arm.addItem(new Item("shield"));
        arm.addItem(new Item("key"));
        treas.addItem(new Item("golden crown"));
        treas.addItem(new Item("gold bag"));
        lib.addItem(new Item("book"));
        dung.addItem(new Item("dark crystal"));

        boss.setNPC("Dark Knight");
        dung.setNPC("Dark Sorcerer");

        Player player = new Player(start);
        MUDController game = new MUDController(player, treas, dung);
        game.run();
    }
}
import java.util.Scanner;

public class MUDController {
    Player p;
    boolean run = true, puzzle = false;
    Room treas, dung;
    Scanner in = new Scanner(System.in);
    MUDController(Player p, Room treas, Room dung) {
        this.p = p;
        this.treas = treas;
        this.dung = dung;
    }
    void run() {
        System.out.println("Welcome to MUD Quest! Type 'help' for commands.");
        while (run) {
            System.out.print("> ");
            handle(in.nextLine().trim());
        }
        System.out.println("Game over.");
    }
    void handle(String input) {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0].toLowerCase(), arg = parts.length > 1 ? parts[1] : "";
        switch (cmd) {
            case "look": look(); break;
            case "move": move(arg); break;
            case "pick": if (arg.startsWith("up ")) pickUp(arg.substring(3)); else System.out.println("Use: pick up <item>"); break;
            case "inv": System.out.println(p.inv()); break;
            case "unlock": unlock(); break;
            case "solve": solve(); break;
            case "fight": fight(); break;
            case "help": help(); break;
            case "quit": case "exit": run = false; break;
            default: System.out.println("Unknown command. Type 'help'.");
        }
    }
    void look() {
        System.out.println(p.curr.desc());
    }
    void move(String dir) {
        Room next = p.curr.getExit(dir);
        if (next == null) {
            System.out.println("You can't go that way!");
            return;
        }
        if (next == treas && !p.hasItem("key")) {
            System.out.println("Locked! Need a key.");
            return;
        }
        if (next == dung && !puzzle) {
            System.out.println("A magic force blocks the way!");
            return;
        }
        p.move(next);
        System.out.println("Moved " + dir + ".");
        look();
    }
    void pickUp(String name) {
        Item item = p.curr.getItem(name);
        if (item == null) {
            System.out.println("No '" + name + "' here.");
        } else {
            p.curr.removeItem(name);
            p.pickUp(item);
            System.out.println("Picked up " + name + ".");
        }
    }
    void unlock() {
        if (p.hasItem("key")) {
            System.out.println("You used the key! The treasure room is open.");
        } else {
            System.out.println("You need a key!");
        }
    }
    void solve() {
        if (p.curr.name.equals("Library")) {
            if (p.hasItem("book")) {
                System.out.println("You read the book! The hidden dungeon is now open.");
                puzzle = true;
            } else {
                System.out.println("You need a book to solve this.");
            }
        } else {
            System.out.println("No puzzle here.");
        }
    }
    void fight() {
        if (p.curr.hasNPC()) {
            String boss = p.curr.npc;
            if (boss.equals("Dark Knight")) {
                if (!p.hasItem("sword")) {
                    System.out.println("No weapon! The knight defeats you.");
                    run = false;
                } else {
                    System.out.println("You defeat the Dark Knight!");
                    p.curr.removeNPC();
                }
            } else if (boss.equals("Dark Sorcerer")) {
                System.out.println("The sorcerer casts a spell...");
                System.out.println("To be continued...");
                run = false;
            }
        } else {
            System.out.println("No one to fight.");
        }
    }

    void help() {
        System.out.println("Commands: look, move <dir>, pick up <item>, unlock, solve, fight, inv, help, quit.");
    }
}
# 🏰 MUD GAME - A Simple Text Adventure

A **Multi-User Dungeon (MUD) game** where you **explore rooms, find items, solve puzzles, and fight bosses**.  
This is a **text-based adventure** created as a simple Java console game.

---

## 🗺️ **Room Map**
           [Library]     
	        |  
    [Start Room] - [Hallway] - [Treasure Room]
                |
       [Armory] - [Boss Room]
                |
     [Hidden Dungeon] (Secret Room)

  •	Library: Solve a puzzle to get the magic amulet.
	•	Armory: Find weapons and the golden key for the Treasure Room.
	•	Boss Room: Fight the Dark Knight.
	•	Hidden Dungeon: Only opens with the magic amulet. Defeat the Dark Sorcerer for the true ending.


  ### **📌 Locations & Features**
- 🏠 **Start Room** → The game begins here.  
- 🏰 **Hallway** → The central hub connecting to multiple rooms.  
- ⚔️ **Armory** → Find weapons and the **golden key** to unlock the Treasure Room.  
- 💰 **Treasure Room** → Contains gold and valuable loot, **but it's locked**!  
- 🏆 **Boss Room** → Fight the **Dark Knight** to prove your strength.  
- 📖 **Library** → Solve a **magic puzzle** to unlock the **Hidden Dungeon**.  
- 🕳️ **Hidden Dungeon** → Only opens with a **magic amulet**. The **final boss (Dark Sorcerer) awaits**!  

---

## 🎮 **Game Commands**
| Command | Description |
|---------|------------|
| `look` | Describes the current room. |
| `move <direction>` | Moves to another room (e.g., `move forward`). |
| `pick up <item>` | Picks up an item (e.g., `pick up sword`). |
| `inv` | Shows your inventory. |
| `unlock` | Unlocks the Treasure Room (if you have the key). |
| `solve` | Solves a puzzle (only in the Library). |
| `fight` | Fights the boss in the current room. |
| `help` | Shows a list of commands. |
| `quit` | Exits the game. |

---
---

## **📂 Project Structure**
| File | Description |
|------|------------|
| `Main.java` | Entry point of the game. Initializes rooms & player. |
| `MUDController.java` | Handles user commands, game logic, movement, and interactions. |
| `Player.java` | Stores player's inventory & location. |
| `Room.java` | Represents the rooms, their items, NPCs, and connections. |
| `Item.java` | Represents items that can be picked up. |
| `README.md` | This file – Game documentation. |

---

## **📝 Code Description**
### **1️⃣ `Main.java` (Game Entry Point)**
- Initializes all **rooms, items, and NPCs**.
- Creates a **player object** and starts the game using `MUDController`.

#### **Example Code:**
```java
public class Main {
    public static void main(String[] args) {
        Room start = new Room("Start Room", "A small stone chamber.");
        Room hall = new Room("Hallway", "A dark corridor.");
        Room armory = new Room("Armory", "A room filled with weapons.");
        Room treas = new Room("Treasure Room", "A locked golden chamber.");
        Room boss = new Room("Boss Room", "A dark hall with a knight.");
        Room lib = new Room("Library", "An ancient room of magic.");
        Room dung = new Room("Dungeon", "A sealed underground lair.");

        start.setExit("forward", hall);
        hall.setExit("left", armory);
        hall.setExit("right", treas);
        hall.setExit("forward", boss);
        hall.setExit("up", lib);
        lib.setExit("down", dung);

        armory.addItem(new Item("sword"));
        armory.addItem(new Item("key"));
        treas.addItem(new Item("golden crown"));

        boss.setNPC("Dark Knight");
        dung.setNPC("Dark Sorcerer");

        Player player = new Player(start);
        MUDController game = new MUDController(player, treas, dung);
        game.run();
    }
}

2️⃣ MUDController.java (Game Logic & Commands)
	•	Processes user input and executes commands.
	•	Handles room movement, inventory, unlocking doors, fighting, and puzzles.
	•	Implements assignment requirements:
	•	run() – Loops until the player quits.
	•	handle() – Parses commands (move, look, pick up, fight, etc.).
	•	move(String dir) – Moves player if the exit exists.
	•	pickUp(String item) – Adds item to inventory.
	•	fight() – Boss battle system.

Example Code:
import java.util.Scanner;

class MUDController {
    Player p;
    boolean run = true, puzzleSolved = false;
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
            case "pick": if (arg.startsWith("up ")) pickUp(arg.substring(3)); break;
            case "inv": System.out.println(p.inv()); break;
            case "unlock": unlock(); break;
            case "solve": solvePuzzle(); break;
            case "fight": fight(); break;
            case "help": help(); break;
            case "quit": run = false; break;
            default: System.out.println("Unknown command. Type 'help'.");
        }
    }
}

3️⃣ Room.java (Rooms & Connections)
	•	Stores room name, description, exits, items, and NPCs.
	•	Connects rooms using setExit(direction, room).
	•	Implements:
	•	getExit(String dir) – Returns the room in that direction.
	•	getItem(String name) – Finds an item in the room.
	•	desc() – Prints room details.

4️⃣ Player.java (Inventory & Movement)
	•	Stores the player’s current room & inventory.
	•	Implements:
	•	move(Room newRoom) – Updates player location.
	•	pickUp(Item item) – Adds item to inventory.
	•	hasItem(String name) – Checks if an item is in inventory.

Example Code:
class Player {
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

📢 Final Notes
	•	Game follows SOLID principles (single-responsibility, clean code).
	•	Implements all assignment requirements.
	•	Extra Features: A magic puzzle and a final boss battle.


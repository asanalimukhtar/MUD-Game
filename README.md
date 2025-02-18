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


MUD-Game/
│── Main.java          # Game entry point
│── MUDController.java # Handles commands & game logic
│── Player.java        # Stores player inventory & location
│── Room.java          # Represents game rooms
│── Item.java          # Represents items in the game
│── README.md          # This file (Game description)

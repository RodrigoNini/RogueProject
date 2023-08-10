package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Items.Key;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hero implements ImageTile {

    private int maxHealth = 8;
    private int currentHealth;
    private int score = 100;
    private int damage = 1;
    private Position position;
    private ArrayList<Itens> inventory = new ArrayList<>();
    private int fireBalls = 3;
    private Room currentRoom;
    private HashMap<Integer, Itens> currentItems; // Stores Items in the item slot

    private Hero() {

    }
    private static final Hero INSTANCE = new Hero();

    public void initCurrentRoom() {
        currentRoom = RoomManager.getInstance().getCurrentRoom();
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public static Hero getInstance() {
        return INSTANCE;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(Direction direction) {
        Position newPosition = this.getPosition().plus(direction.asVector());
        if(!RoomManager.getInstance().findObstacle(newPosition)){
            this.position = newPosition;
        }

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void pickUpItem() {
        ImageTile tile = currentRoom.getTile(position);
        if (tile instanceof Itens) {
            inventory.add((Itens) tile);
        }
    }

    public int getFireBalls() {
        return fireBalls;
    }

   /* public void useItem(String keyPressed) {
        Scanner scanner = new Scanner(System.in);
        keyPressed = scanner.nextLine();
        switch (keyPressed){
            case "1":
                System.out.println("Carrega no 1 para usar o item: " + inventory.get(0).getName() + ", ou carrega 0 para largar");
                keyPressed = scanner.nextLine();
                if(keyPressed.equals("0")){
                    System.out.println("Largaste o item: " + inventory.get(0).getName());
                    inventory.remove(0);
                    break;
                } else if (keyPressed.equals("1")) {
                    System.out.println("Usaste o item: " + inventory.get(0).getName());
                    inventory.get(0).use();
                    inventory.remove(0);
                }
                break;
            case "2":
                System.out.println("Carrega no 2 para usar o item: " + inventory.get(0).getName() + ", ou carrega 0 para largar");
                keyPressed = scanner.nextLine();
                if(keyPressed.equals("0")){
                    System.out.println("Largaste o item: " + inventory.get(0).getName());
                    inventory.remove(0);
                    break;
                } else if (keyPressed.equals("2")) {
                    System.out.println("Usaste o item: " + inventory.get(0).getName());
                    inventory.get(0).use();
                    inventory.remove(0);
                }
                    break;
            case "3":
                System.out.println("Carrega no 3 para usar o item: " + inventory.get(0).getName() + ", ou carrega 0 para largar");
                keyPressed = scanner.nextLine();
                if(keyPressed.equals("0")){
                    System.out.println("Largaste o item: " + inventory.get(0).getName());
                    inventory.remove(0);
                    break;
                } else if (keyPressed.equals("3")) {
                    System.out.println("Usaste o item: " + inventory.get(0).getName());
                    inventory.get(0).use();
                    inventory.remove(0);
                }
                    break;
        }
    }*/

    public List<Itens> getInventory(){
        return  inventory;
    }

    public int getHealth() {
        return currentHealth;
    }
    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        this.currentHealth = currentHealth - damage;
    }


    //items interaction section
    // Add item picked up to a HashMap<Integer, FloorInteractables> with keys 1-3 which represent the respective item slots
    public void addItem(Itens item) {
        // Adds item to the first slot available (checked with containsKey() )
        if (!(currentItems.containsKey(1))) {
            currentItems.put(1, item);
        } else if (!(currentItems.containsKey(2))) {
            currentItems.put(2, item);
        } else if (!(currentItems.containsKey(3))) {
            currentItems.put(3, item);
        }
    }

    // Check if there are item slots available, return TRUE if there are
    public boolean itemSlotsAvailable() {
        return (!(currentItems.containsKey(1) && currentItems.containsKey(2) && currentItems.containsKey(3)));
    }


    // Get Key from item slot
    public Itens getKeyfromSlot(String keyName) {
        for (int i = 1; i <= 3; i++) {
            if (currentItems.get(i) instanceof Key) {
                Key item = (Key) currentItems.get(i);
                // only if the key matches the door keycode
                if (item.getName().equals(keyName)){
                    currentItems.remove(i);
                    return item;
                }
            }
        }
        return null;
    }


    // Restarts the hero state for restarting the game
    public void resetState(){
        this.currentHealth = maxHealth;
        this.damage = 1; // start with 1 damage
        this.score = 100;
        //this.fireBalls = 3;
    }



}
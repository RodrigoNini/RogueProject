package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Items.ItemManager;
import pt.upskill.projeto1.objects.Items.Itens;
import pt.upskill.projeto1.objects.Items.Key;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.StatusObjects.StatusBar;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hero implements ImageTile {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private Position position;
    private int maxHealth = 8;
    private int currentHealth;
    private HashMap<Integer, Itens> inventory = new HashMap<Integer, Itens>(); //inventory is limited to three items
    //private ArrayList<Fireball> fireballList = new ArrayList<Fireball>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private int fireBalls = 3;
    private ItemManager itemObserver;
    private Itens weapon = null; // implementar como item e adicionar atributo dentro da classe que são armas que verifica se é arma ou não
    private boolean hasWeapon = false;
    private int currentRoom = 0;
    private StatusBar statusBar = new StatusBar();
    private boolean isDead = false;


    //    public Hero(Position position) {
//
//        super(position); //to do: set health and damage values
//        //this.currentRoom = currentRoom;
//    }

    private static final Hero INSTANCE = new Hero();


    public static Hero getInstance() {
        return INSTANCE;
    }

    public Hero(){
        this.currentHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public HashMap<Integer, Itens> getInventory() {
        return inventory;
    }

    public int getNumberOfFireballs() {
        return fireBalls;
    }
    //    public ArrayList<Fireball> getFireballList() {
//        return fireballList;
//    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isHasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    public void restoreHealth(){
        currentHealth = maxHealth;
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }
//    public String getCurrentRoom() {
//        return currentRoom;
//    }

//    public void setCurrentRoom(String currentRoom) {
//        this.currentRoom = currentRoom;
//    }

    public void update(Position position, RoomManager roomManager) {
        Room room = RoomManager.getINSTANCE().getCurrentRoom();
        ArrayList<ImageTile> tiles = roomManager.getCurrentRoom().getTiles();
            if (room.findObstacle(position)) {
                for (Door door : room.getDoorList()) {
                    if (door.getPosition().equals(position) && door instanceof Door) {
                        if (door.needsKey() && heroHasKey(door)) { //if (hero has key and it matches door key)
                            door.setRequiresKey(false);
                            door.setType("D");
                            door.setOpen(true); //"D" and "true" are required for Door to [getName() = "DoorOpen"]
                            gui.setStatus("You have successfully opened the door!");
                            break;
                        } else if (door.needsKey() && !heroHasKey(door)) {
                            gui.setStatus("You do not have the right key to open this door!");
                        } else if (!door.needsKey()) {
                            door.setType("D");
                            door.setOpen(true); //"D" and "true" are required for Door to [getName() = "DoorOpen"]
                            break;
                        }
                    }
                }
            }
            if (room.findObstacle(position)) {
                for (Door door : room.getDoorList()) {
                    if (door.getPosition().equals(getPosition()) && (door.getName().equals("DoorOpen") || door.getName().equals("DoorWay"))) {
                        setCurrentRoom(door.getNextToRoom());
                        RoomManager.getINSTANCE().changeRoom(door, getCurrentRoom(), Hero.getInstance()); // verificar sentido disto
                        break; //this break is required or for loop will continue reading doors from the new room??
                    }
                }
            }
            if (room.findObstacle(position)) {
                for (Itens item : room.getItemList()) {
                    if (item.getPosition().equals(position) && item instanceof Meat) {
                        restoreHealth();
                        moveItemToOutOfView(item);
                        gui.setStatus("You have eaten some food.");
                        break;
                    } else if (item.getPosition().equals(position) && item.isWeapon()) {
                        switchWeapon(item, roomManager);
                        gui.setStatus("You have picked up a weapon.");
//                    weapon = (Weapon) item;
//                    moveItemToOutOfView(item);
                        break;
                    } else if (item.getPosition().equals(position) && item instanceof Key) {
                        addToInventory(item);
                        gui.setStatus("You have picked up a key.");
                        break;
                    } else if (item.getPosition().equals(position) && item instanceof Itens) {
                        addToInventory(item);
                        gui.setStatus("You have picked up an item.");
                        break;
                    }
                }
            }
        }

    private boolean heroHasKey(Door door){
        for (Key key : keyList){
            if (door.getRequiredKey().equals(key.getDoor()))
                return true;
        }
        return false;
    }
    private void switchWeapon(Itens newWeapon, RoomManager roomManager) {
//        if (hasWeapon()) {  //to do: hero drops old weapon on the floor and picks up new one
//            Weapon droppedWeapon = weapon;
//            droppedWeapon.setPosition(getPosition());
//            map.getCurrentRoom().getMapTiles().add(droppedWeapon);
//            itemList.add(droppedWeapon);
//        }
//            addToInventory(newWeapon);
        //}
        addToInventory(newWeapon);
        weapon = newWeapon;
        hasWeapon = true;
    }
    private void addToInventory(Itens item) {
        if (!inventory.containsKey(0)) {
            inventory.put(0, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            statusBar.update(getHealth(), fireBalls, inventory);
            //inventory.remove(0);
            //moveItemToOutOfView(item);
        } else if (!inventory.containsKey(1)) {
            inventory.put(1, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            statusBar.update(getHealth(), fireBalls, inventory);
            //inventory.remove(0);
            //moveItemToOutOfView(item);
        } else if (!inventory.containsKey(2)) {
            inventory.put(2, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            statusBar.update(getHealth(), fireBalls, inventory);
            //inventory.remove(0);
            //moveItemToOutOfView(item);
        }
        //----------------------
//        if (!inventoryFull()){ //old method for when inventory was an ArrayList
//            inventory.add(item);
//            moveItemToOutOfView(item);
//            if (item instanceof Weapon)
//                weapon = (Weapon) item;
//        }
//    private boolean inventoryFull(){ //old method for when inventory was an ArrayList
//        int i = 0;
//        for (Item item : inventory){
//            i++;
//        }
//        if (i == 3){
//            return true;
//        }
//        return false;
//    }
    }
    //    private boolean hasWeapon() {
//        return weapon != null;
//    }
    private void moveItemToOutOfView(Itens item){
        item.setPosition(new Position(9, -1));
    }

    public void receiveDamage(int damage){
        if (currentHealth - damage <= 0) {
            currentHealth = 0;
            this.dies();
        }
        else
            currentHealth = currentHealth - damage;
        if (!isDead)
            gui.setStatus("You are being attacked!");
    }

    public void dies(){
        setDead(true);
        gui.showMessage("Game Over","Game over! Restart game and try again.");
        gui.setStatus("Game over! Restart game and try again.");
    }

    public int getHealth() {
        return currentHealth;
    }

    public void setPosition(Direction direction) {
        Position newPosition = this.getPosition().plus(direction.asVector());
        if(!RoomManager.getINSTANCE().findObstacle(newPosition)){
            this.position = newPosition;
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
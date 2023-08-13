package pt.upskill.projeto1.objects;


import pt.upskill.projeto1.game.FireBallThread;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.Items.Itens;
import pt.upskill.projeto1.objects.Items.Key;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Items.Weapons.Weapon;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.StatusObjects.StatusBar;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;


import java.util.*;

public class Hero implements ImageTile {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private HashMap<Integer, Itens> inventory = new HashMap<>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private int numberOfFireballs = 3;
    private Position position;
    private int maxHealth = 8;
    private int currentHealth;
    private int damage = 2;
    private int currentRoom = 0;
    private int score = 15;

    private static final Hero INSTANCE = new Hero();
    private Direction lastDirection;

    public Hero(){
        currentHealth = maxHealth;
    }
    public Hero(Position position) {
        this.position = position;
        currentHealth = maxHealth;
    }

    public static Hero getInstance() {
        return INSTANCE;
    }

    // Getters and Setters
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
        return numberOfFireballs;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    public void restoreHealth(){
       this.currentHealth = maxHealth;
    }

    public int getDamage() {
        return 2;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void setLastDirection(Direction direction) {
        this.lastDirection = direction;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }


    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Direction direction) {
        this.position = position.plus(direction.asVector());
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public void addDamage(int damage) {
        this.damage += damage;
    }

    public void addScore(int amount){
       score += amount;
    }

    public void update(Direction direction) {
        Room room = RoomManager.getInstance().getCurrentRoom();
        RoomManager roomManager = RoomManager.getInstance();
        Position nextPosition = getPosition().plus(direction.asVector());
        if (!isOutOfRoom(nextPosition)){
            if (!room.findObstacle(nextPosition)) {
                setPosition(nextPosition); //move hero
            }
            if (room.isEnemy(nextPosition)) {
                for (Enemies enemy : room.getEnemyList()) {
                    if (enemy.getPosition().equals(nextPosition)) {
                        enemy.takeDamage(getDamage());
                        break;
                    }
                }
            }
            if (room.isClosedDoor(nextPosition)) {
                for (Door door : room.getDoorList()) {
                    if (door.getPosition().equals(nextPosition) && door instanceof Door) {
                        if (door.requiresKey() && heroHasKey(door)) {
                            door.setNeedsKey(false);
                            door.setFrame("D");
                            door.setOpen(true);
                            StatusBar.getInstance().update();
                            gui.setStatus("Abriste uma porta!");
                          //  break;
                        } else if (door.requiresKey() && !heroHasKey(door)) {
                            gui.setStatus("Não tens a chave para esta porta.");
                        } else if (!door.requiresKey()) {
                            door.setFrame("D");
                            door.setOpen(true);
                            break;
                        }
                    }
                }
            }
            if (room.isOpenDoor(getPosition())) {
                for (Door door : room.getDoorList()) {
                    if (door.getPosition().equals(getPosition()) && (door.getName().equals("DoorOpen") || door.getName().equals("DoorWay"))) {
                        setCurrentRoom(door.getNextRoom());
                        roomManager.changeRoom(door, getCurrentRoom(), this);
                        break;
                    }
                }
            }
            if (room.isItem(getPosition())) {
                for (Itens item : room.getItemList()) {
                    if (item.getPosition().equals(nextPosition) && item instanceof Meat) {
                        addToInventory(item);
                        break;
                    } else if (item.getPosition().equals(nextPosition) && item instanceof Weapon) {
                        addToInventory(item);
                        break;
                    } else if (item.getPosition().equals(nextPosition) && item instanceof Key) {
                        addToInventory(item);
                        break;
                    }
                }
            }

        }
    }

    private boolean isOutOfRoom(Position position){
        if (position.getX() == -1 || position.getX() == 10)
            return true;
        if (position.getY() == -1 || position.getY() == 10)
            return true;
        return false;
    }
    private boolean heroHasKey(Door door){
        for (Key key : keyList){
            if (door.getKey().equals(key.getKeyName()))
                return true;
        }
        return false;
    }
    private void addToInventory(Itens item) {
        if (!inventory.containsKey(0)) {
            inventory.put(0, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            StatusBar.getInstance().update();
            if (item instanceof Weapon){
                addDamage(item.getDamage());
                gui.setStatus("Apanhaste uma arma! O teu dano agora é " + damage + "!");
            }
        } else if (!inventory.containsKey(1)) {
            inventory.put(1, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            StatusBar.getInstance().update();
        } else if (!inventory.containsKey(2)) {
            inventory.put(2, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            gui.setStatus("Apanhaste uma chave! Encontra a porta certa...");
            StatusBar.getInstance().update();
        }
        RoomManager.getInstance().getCurrentRoom().removeItem(item);
        StatusBar.getInstance().update();
        addScore(5);
    }

    public void useOrDrop(int keyPressed){
        int key = keyPressed - 1;
        if (inventory.containsKey(key)){
            Itens item = inventory.get(key);
            if (item instanceof Key){
                item.setPosition(Hero.getInstance().getPosition());
                RoomManager.getInstance().getCurrentRoom().repaintItem(item);
                gui.addImage(item);
                gui.setStatus("Largaste uma chave!");
                keyList.remove(item);
                inventory.remove(key);
                StatusBar.getInstance().update();
                gui.update();
            } else if (item instanceof Weapon){
                item.setPosition(Hero.getInstance().getPosition());
                RoomManager.getInstance().getCurrentRoom().repaintWeapon((Weapon) item);
                gui.addImage(item);
                inventory.remove(key);
                gui.setStatus("Largaste uma arma!");
                StatusBar.getInstance().update();
                gui.update();
            } else if (item instanceof Meat){
                gui.setStatus("Usaste carne!");
                inventory.remove(key);
                restoreHealth();
                gui.setStatus("A tua vida foi restaurada!");
                StatusBar.getInstance().update();
            }
        }
        gui.update();
    }

    public void takeDamage(int amount){
        if (getCurrentHealth() - amount <= 0) {
            setCurrentHealth(0);
            this.dies();
        }
        else
            setCurrentHealth(getCurrentHealth() - amount);
        setScore(getScore() - 5);
    }

    public void dies(){
        gui.showMessage("Game Over","Morreste, tenta novamente...");
        gui.dispose();
    }

    public void useFireball() {
        if (numberOfFireballs > 0) {
            numberOfFireballs--;
            StatusBar.getInstance().update();
            gui.setStatus("Usaste uma bola de fogo!");
            FireBall fireball = new FireBall(position);
            FireBallThread fireBallThread = new FireBallThread(lastDirection, fireball);
            fireBallThread.start();
            fireball.useFireball();
            gui.addImage(fireball);
            gui.update();

        } else {
            gui.setStatus("Não tens mais bolas de fogo!");
        }
    }

    public void removeFireBall(){
        numberOfFireballs--;
    }
}

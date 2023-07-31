package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hero implements ImageTile {

    private int health = 4;
    private int score = 100;
    private int damage = 1;
    private Position position;
    private Room currentRoom;
    private ArrayList<Itens> inventory;
    private ArrayList<FireBall> fireBalls = new ArrayList<>();

    private static final Hero INSTANCE = new Hero(new Position(4, 3));
    private Hero(Position position) {
        this.position = position;
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
        Room room = new Room(currentRoom.getTiles());
        Position newPosition = this.getPosition().plus(direction.asVector());
        if(!currentRoom.findObstacle(newPosition)){
            this.position = newPosition;
        }

    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void pickUpItem() {
        List<ImageTile> tiles = currentRoom.getTiles();
        for (ImageTile tile:
                tiles) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Itens){
                    inventory.add((Itens) tile);
                }
            }
        }
    }

    public ArrayList<FireBall> getFireBalls() {
        return fireBalls;
    }

    public void useItem(String keyPressed) {
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
    }

    public List<Itens> getInventory(){
        //código apenas para testar
        this.inventory = new ArrayList<>();
        inventory.add(new Hammer(new Position(8,6)));
        inventory.add(new Meat(new Position(3,3)));
        inventory.add(new Key(new Position(6,3)));
        return  inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

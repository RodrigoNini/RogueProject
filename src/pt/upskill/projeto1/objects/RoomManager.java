package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.RoomObjects.Wall;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class RoomManager implements ImageTile {

    private ArrayList<Room> rooms = new ArrayList<>();
    private Room currentRoom;
    private List<Enemies> currentEnemies = new ArrayList<>();
    private Position position;

    public final static RoomManager INSTANCE = new RoomManager();

    private RoomManager(){

    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.currentEnemies.clear();
        this.currentEnemies.addAll(currentRoom.getEnemies());
    }



    public static RoomManager getInstance() {
        return INSTANCE;
    }


/*
    public void restoreHealth() {
        List<Itens> itens = currentRoom.getItens();
        Position meatPosition = null;
        for (ImageTile tile:
                currentRoom.getTiles()) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Meat){
                    hero.setHealth(4);
                }
            }
        }
    }


        public void changeRoom() {
        // falta implementar abertura de portas fechadas com chaves
    for (ImageTile tile :
            currentRoom) {
        if (tile.getPosition().equals(position)) {
            if (tile instanceof Door) {
                currentRoom = Room1;
            }
        }
        setCurrentRoom();
        setCurrentAdversarios();
    }
}*/

    public boolean findObstacle(Position position){
        for (ImageTile tile:
                currentRoom.getTiles()) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Wall || tile instanceof Door || tile instanceof Enemies || tile instanceof Hero){
                    return true;
                }
            }
        }
        return false;
    }


    //getters and setters
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public List<ImageTile> getTiles(){
        return currentRoom.getTiles();
    }

    public List<Enemies> getCurrentEnemies() {
        return currentEnemies;
    }

    public void setCurrentAdversarios() {
        this.currentEnemies = currentRoom.getEnemies();
    }



/*    public void PickUp(Item item, Hero hero) {
        if (!hero.getInventory().containsKey(0)) {
            hero.getInventory().put(0, item);
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
            hero.getInventory().remove(0);
            //moveItemToOutOfView(item);
        } else if (!hero.getInventory().containsKey(1)) {
            hero.getInventory().put(1, item);
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
            hero.getInventory().remove(1);
            //moveItemToOutOfView(item);
        } else if (!hero.getInventory().containsKey(2)) {
            hero.getInventory().put(2, item);
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
            hero.getInventory().remove(2);
            //moveItemToOutOfView(item);
        }
    }*/



    @Override
    public String getName() {
        return "RoomManager";
    };

    @Override
    public Position getPosition() {
        return position;
    }
}

package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.objects.Meat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomManager implements ImageTile {

    private Room room0;
    private Room room1;
    private Room room2;
    private Room currentRoom;
    private List<Adversarios> currentAdversarios = new ArrayList<>();
    private Position position;

    public final static RoomManager INSTANCE = new RoomManager();
    public RoomManager(){
        room0 = new Room("rooms\\room0.txt");
        room1 = new Room("rooms\\room1.txt");
        room2 = new Room("rooms\\room2.txt");
        this.currentRoom = room0;
        this.currentAdversarios = currentRoom.getAdversarios();
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
                if(tile instanceof Wall || tile instanceof Door || tile instanceof Adversarios || tile instanceof Hero){
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

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<ImageTile> getTiles(){
        return currentRoom.getTiles();
    }

    public List<Adversarios> getCurrentAdversarios() {
        return currentAdversarios;
    }

    public void setCurrentAdversarios() {
        this.currentAdversarios = currentRoom.getAdversarios();
    }




    @Override
    public String getName() {
        return "RoomManager";
    };

    @Override
    public Position getPosition() {
        return position;
    }
}

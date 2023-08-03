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
    Room room = new Room();
    private Room room0 = new Room(room.readFile("C:\\Users\\rodri\\IdeaProjects\\Rogue_UPskill2023_v1_Rodrigo_Alves\\rooms\\room0.txt"));
    private Room room1 = new Room(room.readFile("C:\\Users\\rodri\\IdeaProjects\\Rogue_UPskill2023_v1_Rodrigo_Alves\\rooms\\room1.txt"));
    private Room room2 = new Room(room.readFile("C:\\Users\\rodri\\IdeaProjects\\Rogue_UPskill2023_v1_Rodrigo_Alves\\rooms\\room2.txt"));

    private Hero hero = Hero.getInstance();
    private Position heroPosition = hero.getPosition();
    private Room currentRoom;
    private List<Adversarios> currentAdversarios = new ArrayList<>();
    private Position position;

    public RoomManager(){
        currentRoom = room0;
        hero.setCurrentRoom(currentRoom);
        this.currentAdversarios = currentRoom.getAdversarios();
        this.position = new Position(0,0);
    }

    private static final RoomManager INSTANCE = new RoomManager();
    public static RoomManager getInstance() {
        return INSTANCE;
    }
/*
    public void restoreHealth() {
        List<Itens> itens = currentRoom.getItens();
        Position meatPosition = null;
        for (Itens item :
                itens) {
            if (item.getName().equals("Meat")) {
                meatPosition = item.getPosition();
            }
        }
        if (hero.getPosition() == meatPosition) {
            hero.setHealth(100);
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
                if(tile instanceof Wall || tile instanceof DoorClosed || tile instanceof Adversarios || tile instanceof Hero){
                    return true;
                }
            }
        }
        return false;
    }


    //getters and setters
    public Room getCurrentRoom() {
        return currentRoom;
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

package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Room implements ImageTile {

    private String name;
    private Position position;

    private List<ImageTile> room;

    public Room(String name, List<ImageTile> room) {
        this.name = name;
        this.room = room;
    }

    public static boolean findObstacle(Position position){
        List<ImageTile> tiles = getCurrentRoom();
        for (ImageTile tile:
                tiles) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Wall || tile instanceof DoorClosed || tile instanceof Adversarios){
                    return true;
                }
            }
        }
        return false;
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
    }
}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

}

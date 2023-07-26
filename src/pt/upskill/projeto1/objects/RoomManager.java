package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class RoomManager implements ImageTile {
    Room reader = Room.getInstance();
    List Room0 = reader.readFile("room0.txt");
    List Room1 = reader.readFile("room1.txt");
    List Room2 = reader.readFile("room2.txt");

    private static List CurrentRoom;

    public RoomManager(){
        CurrentRoom = Room0;
    }

    public void setCurrentRoom(List currentRoom) {
        CurrentRoom = currentRoom;
    }





    public static List getCurrentRoom() {
        return CurrentRoom;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}

package pt.upskill.projeto1.objects.RoomObjects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Doors;
import pt.upskill.projeto1.rogue.utils.Position;

public class Door extends Doors {

    private String name = "DoorClosed";
    private Position position;

    private String nextRoom;
    private int nextDoor;
    private String key;

    public Door(String name, String nextRoom) {
        this.name = name;
        this.nextRoom = nextRoom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setNextDoor(int nextDoor) {
        this.nextDoor = nextDoor;
    }

    public void setKey(String key) {
        this.key = key;
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

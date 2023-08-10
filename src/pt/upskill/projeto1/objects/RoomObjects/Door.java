package pt.upskill.projeto1.objects.RoomObjects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Door implements ImageTile {

    private Position position;
    private String number;
    private String type;
    private String nextRoom;
    private String nextDoor;
    private String requiredKey;
    private boolean needsKey = false;
    private boolean isOpen = false;

    public Door(Position position) {
        this.position = position;
    }
    public Door(String number, String type, String nextRoom, String nextDoor){
        this.number = number;
        this.type = type;
        this.nextDoor = nextDoor;
        this.nextRoom = nextRoom;
    }
    public Door(String number, String type, String nextRoom, String nextDoor, String requiredKey){
        this(number, type, nextRoom, nextDoor);
        this.requiredKey = requiredKey;
        needsKey = true;
    }

    public int getNextToRoom() {
        switch (nextRoom){
            case "room1.txt":
                return (1);
            case "room2.txt":
                return (2);
            case "room3.txt":
                return (3);
            case "room4.txt":
                return (4);
            default:
                return (0);
        }
    }
    public int getNextDoor() {
        switch (nextDoor){
            case "1":
                return (1);
            case "2":
                return (2);
            default:
                return (0);
        }
    }

    public boolean needsKey() {
        return needsKey;
    }

    public void setRequiresKey(boolean needsKey) {
        this.needsKey = needsKey;
    }

    public String getRequiredKey() {
        return requiredKey;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String getName() {
        if (type.equals("E")){
            return "DoorWay";
        } else {
            if (isOpen) {
                return "DoorOpen";
            }
            return "DoorClosed";
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Door{" +
                "position=" + position +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", nextRoom='" + nextRoom + '\'' +
                ", nextDoor='" + nextDoor + '\'' +
                ", requiredKey='" + requiredKey + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }
}

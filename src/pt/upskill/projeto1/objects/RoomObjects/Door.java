package pt.upskill.projeto1.objects.RoomObjects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Door implements ImageTile {
    private Position position;
    private String number;
    private String frame;
    private String nextRoom;
    private String nextDoor;
    private String key;
    private boolean needsKey = false;
    private boolean isOpen = false;
    public Door(String number, String frame, String nextRoom, String nextDoor){
        this.number = number;
        this.frame = frame;
        this.nextDoor = nextDoor;
        this.nextRoom = nextRoom;
    }
    public Door(String number, String frame, String nextRoom, String nextDoor, String key){
        this(number, frame, nextRoom, nextDoor);
        this.key = key;
        needsKey = true;
    }

    public int getNextRoom() {
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

    public boolean requiresKey() {
        return needsKey;
    }

    public void setNeedsKey(boolean needsKey) {
        this.needsKey = needsKey;
    }

    public String getKey() {
        return key;
    }

    public void setFrame(String type) {
        this.frame = type;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String getName() {
        if (frame.equals("E")){
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

}

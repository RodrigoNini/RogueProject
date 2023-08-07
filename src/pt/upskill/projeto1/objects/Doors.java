package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Doors implements ImageTile {
    private String name;
    private String nextRoom;
    private Position position;

    public Doors() {
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setNextRoom(String nextRoom) {
        this.nextRoom = nextRoom;
    }

    public String getName() {
        return name;
    }

    public String getNextRoom() {
        return nextRoom;
    }
}

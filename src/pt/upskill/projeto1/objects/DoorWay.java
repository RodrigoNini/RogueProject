package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class DoorWay extends Doors  {

    private Position position;
    private String nextRoom;

    public DoorWay(String nextRoom) {
        this.nextRoom = nextRoom;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "DoorWay";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

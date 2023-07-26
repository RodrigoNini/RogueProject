package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class DoorClosed extends Door {

    private String  name = "DoorClosed";
    private Position position;

    public DoorClosed(Position position) {
        this.position = position;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "DoorClosed";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

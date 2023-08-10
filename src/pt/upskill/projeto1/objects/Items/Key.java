package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Itens;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Position;

public class Key extends Itens {

    private Position position;
    private String name;

    public Key(Position position) {
        this.position = position;
    }
    public Key(String name) {
        this.name = name;
    }

    public String whichDoor(){
        return name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void use() {
    }

    @Override
    public void drop() {

    }
}
package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Itens implements ImageTile {
    private String name;
    private Position position;

    public Itens(){};
    public Itens(Position position) {
        this.position = position;
    }
    public Itens(String key) {
        this.name = key;
    }

public void setPosition(Position position){
    this.position = position;
}

public abstract boolean isWeapon();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

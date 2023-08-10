package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Key extends Itens {
    private String door;

    public Key(Position position) {
        super(position);
    }
    public Key(String door){
        this.door = door;
    }

    public String getDoor() {
        return door;
    }

    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public boolean isWeapon() {
        return false;
    }
}
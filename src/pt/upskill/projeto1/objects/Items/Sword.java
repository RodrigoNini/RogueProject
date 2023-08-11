package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Sword extends Itens {

    private Position position;

    private int damage;

    public Sword(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isWeapon() {
        return true;
    }
}

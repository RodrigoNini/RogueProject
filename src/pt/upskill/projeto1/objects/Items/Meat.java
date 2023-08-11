package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Meat extends Itens {

    private Position position;

    public Meat(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isWeapon() {
        return false;
    }
}

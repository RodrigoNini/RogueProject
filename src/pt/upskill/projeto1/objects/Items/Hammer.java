package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Hammer extends Itens {

    private Position position;

    public Hammer(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Hammer";
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

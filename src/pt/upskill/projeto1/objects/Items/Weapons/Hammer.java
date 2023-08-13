package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.objects.Items.Itens;
import pt.upskill.projeto1.rogue.utils.Position;

public class Hammer extends Weapon {

    public Hammer(Position position) {
        super("Hammer",position, 2);
        super.setDamage(2);
    }

}

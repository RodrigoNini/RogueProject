package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.objects.Items.Itens;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Weapon extends Itens {
    private int damage;

    public Weapon(String name, Position position, int damage) {
        super(name, position);
        this.damage = damage;
    }

}
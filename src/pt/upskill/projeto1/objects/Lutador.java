package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Lutador extends Adversarios{

    public Lutador() {
        super("Lutador", 80, 60);
    }

    @Override
    public void movement() {
        Hero hero = Hero.getInstance();
    }

    @Override
    public void attack() {

    }

    @Override
    public void die() {

    }
}

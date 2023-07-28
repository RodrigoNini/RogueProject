package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Adversarios implements ImageTile {


    public Adversarios() {

    }

    public abstract void movement();

    public abstract void attack();

    public abstract void die();


     public abstract String getName();

    public abstract int getHealth();

    public abstract void setHealth();

    public abstract int getDamage();

    public abstract void move();

    public abstract Position getPosition();

    public abstract void setPosition();
}

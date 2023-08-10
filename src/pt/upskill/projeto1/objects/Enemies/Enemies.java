package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Enemies implements ImageTile {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private String name;
    private Position position;
    private int health;
    private int damage;

    public Enemies(String name, Position position, int health, int damage) {
        this.name = name;
        this.position = position;
        this.health = health;
        this.damage = damage;
    }

    public abstract void movement();

    public void dies() { //when enemy dies, it is moved to out of view
        setPosition(new Position(-1, -1));
        gui.setStatus("You have killed an enemy!");
    }

    public void takeDamage(int damage){
        health = health - damage;
    }


     public abstract String getName();


    public abstract Position getPosition();

    public void setPosition(Position position) {
        this.position = position;

    }
}


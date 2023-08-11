package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.RoomManager;
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
        gui.removeImage(this);
        RoomManager.getINSTANCE().getCurrentRoom().removeEnemy(this);
        gui.setStatus("You have killed an enemy!");
    }

    public void takeDamage(int damage){
        health = health - damage;
        if (health <= 0){
            dies();
        } else {
            gui.setStatus("Ememy took damage!" + "Enemy health: " + health);
        }
    }


     public abstract String getName();


    public Position getPosition(){
        return position;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void die() { //when enemy dies, it is moved to out of view
        setPosition(new Position(-1, -1));
        gui.setStatus("You have killed an enemy!");
    }
}


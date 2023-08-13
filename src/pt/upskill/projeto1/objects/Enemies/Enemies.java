package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.objects.StatusObjects.StatusBar;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.Random;

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

    // Getters and Setters
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    private Position nearHeroMovement() {
        Position heroPosition = Hero.getInstance().getPosition();
        int dx = heroPosition.getX() - getPosition().getX();
        int dy = heroPosition.getY() - getPosition().getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            dx = dx > 0 ? 1 : -1;
            dy = 0;
        } else {
            dx = 0;
            dy = dy > 0 ? 1 : -1;
        }
        return getPosition().plus(new Vector2D(dx, dy));
    }

    public Position randomMovement() {
        Random random = new Random();
        int direction = random.nextInt(4);

        int dx = 0;
        int dy = 0;

        switch (direction) {
            case 0: // Para cima
                dy = -1;
                break;
            case 1: // Para baixo
                dy = 1;
                break;
            case 2: // Para a esquerda
                dx = -1;
                break;
            case 3: // Para a direita
                dx = 1;
                break;
        }
        return getPosition().plus(new Vector2D(dx, dy));
    }

    private int distanceBetween(Position pos1, Position pos2) {
        int distX = pos1.getX() - pos2.getX();
        int distY = pos1.getY() - pos2.getY();
        return distX + distY;
    }

    public void movement() {
        Position newPosition;

        int distance = distanceBetween(getPosition(), Hero.getInstance().getPosition());
        if (distance <= 3) {
            newPosition = nearHeroMovement();
        } else {
            newPosition = randomMovement();
        }

        if (!RoomManager.getInstance().getCurrentRoom().findObstacle(newPosition)) {
            setPosition(newPosition);
        }
        if (newPosition.equals(Hero.getInstance().getPosition())) {
            Hero.getInstance().takeDamage(damage);
            takeDamage(Hero.getInstance().getDamage());
            StatusBar.getInstance().update();
        }
    }

    public void dies() {
        setPosition(new Position(-1, -1));
        RoomManager.getInstance().getCurrentRoom().removeEnemy(this);
        gui.setStatus("Mataste um inimigo! O teu score é " + Hero.getInstance().getScore() + "!");
        Hero.getInstance().addScore(15);
    }

    public void takeDamage(int damage){
        if (getHealth() - damage <= 0) {
            setHealth(0);
            this.dies();
        }
        else
            setHealth(getHealth() - damage);
    }
}


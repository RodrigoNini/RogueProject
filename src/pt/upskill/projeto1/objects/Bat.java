package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Bat extends Adversarios{

    private String name;
    private int health;
    private int damage;
    private Position position;

    public Bat(Position position) {
        this.name = "Bat";
        this.health = 40;
        this.damage = 20;
        this.position = position;
    }


    @Override
    public void movement() {
        Hero hero = Hero.getInstance();
        Position heroPosition = hero.getPosition();
        int heroX = heroPosition.getX();
        int heroY = heroPosition.getY();
        int batX = position.getX();
        int batY = position.getY();
        if((batY - heroY)<3 && (batX - heroX)<3){
            if(batY<heroY){
                if(batX<heroX)
                position = new Position(batX, batY + 1);
            } else {
                position = new Position(batX, batY - 1);
            }

            position = new Position(batX - 1, batY - 1);
        }
    }

    @Override
    public void attack() {

    }

    @Override
    public void die() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth() {

    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition() {

    }
}

package pt.upskill.projeto1.objects.RoomObjects;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireBall implements FireTile {
    private Position position;
    int damage = 10;
    String name;

    public FireBall(Position position) {
        this.position = position;
        this.name = "Fire";
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean validateImpact() {
        return false;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public Position getPosition() {
        return null;
    }
}
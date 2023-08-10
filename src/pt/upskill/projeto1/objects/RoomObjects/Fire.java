package pt.upskill.projeto1.objects.RoomObjects;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.rogue.utils.Position;

public class Fire implements FireTile {
    String name;
    int damage = 6;
    private ImageTile collisionObject;
    private Position position;

    public Fire(Position position) {
        this.position = position;
        this.name = "Fire";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean validateImpact() {
        Room currentRoom = RoomManager.getInstance().getCurrentRoom();
        ImageTile foundImage = currentRoom.checkPosition(getPosition());
        // If it collides with a enemy, damage him.
        if (foundImage instanceof Enemies) {
            collisionObject = foundImage;
            Enemies enemy = (Enemies) collisionObject;
            enemy.takeDamage(damage);
            setName("FireExplosion");
            return false;
        } else if (foundImage instanceof Hero) {
            collisionObject = foundImage;
            setName("FireExplosion");
            return false;
        } else if (foundImage instanceof Door) {
            collisionObject = foundImage;
            setName("FireExplosion");
            return false;
        } else if (foundImage instanceof Wall) {
            collisionObject = foundImage;
            setName("FireExplosion");
            return false;
            // null means it's the Floor or Grass
        } else if (foundImage == null) {
            return true;
        }
        collisionObject = null;
        return true;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
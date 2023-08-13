package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireBall implements ImageTile, FireTile {
    private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private Position position;
    private int damage = 10;

    public FireBall(Position position) {
        this.position = position;
    }
    
    public void useFireball() {
        int numberFireballs = Hero.getInstance().getNumberOfFireballs();
        switch (numberFireballs){
            case 3,2,1:
                break;
                case 0:
                    gui.setStatus("Já não tens mais fireballs!");
                    Hero.getInstance().removeFireBall();
                    break;
            default:
               break; 
        }
    }

    @Override
    public boolean validateImpact() {
        final Direction last = Hero.getInstance().getLastDirection();
        for (ImageTile tile : RoomManager.getInstance().getCurrentRoom().getTiles()) {
            if (tile instanceof Enemies) {
                if (tile.getPosition().equals(getPosition())) {
                    ((Enemies) tile).takeDamage(damage);
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void setPosition(Position position) {
        this.position = position;

    }
    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

}

package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireBall implements ImageTile, FireTile{

    private Position position;

    public FireBall(Position position) {
    }

    @Override
    public boolean validateImpact() {
        return false;
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
        return position;
    }
}

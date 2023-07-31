package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireBall implements FireTile {

    public FireBall(Position position) {
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
        return "Fire.png";
    }

    @Override
    public Position getPosition() {
        return null;
    }
}

package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Red implements ImageTile {
    private static final Red INSTANCE = new Red();

    public static ImageTile getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Red.png";
    }

    @Override
    public Position getPosition() {
        return null;
    }
}


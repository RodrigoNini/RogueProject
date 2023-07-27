package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Black implements ImageTile {
    private static final Black INSTANCE = new Black();

    public static ImageTile getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Black.png";
    }

    @Override
    public Position getPosition() {
        return null;
    }
}

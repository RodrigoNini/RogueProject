package pt.upskill.projeto1.objects;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Green implements ImageTile {
    private static final Green INSTANCE = new Green();

    public static ImageTile getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Green.png";
    }

    @Override
    public Position getPosition() {
        return null;
    }
}

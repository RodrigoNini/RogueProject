package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Black extends Itens{
    private static final Black INSTANCE = new Black();

    public static Black getInstance() {
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

    @Override
    public void use() {

    }

    @Override
    public void drop() {

    }
}

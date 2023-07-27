package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.FireBallThread;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class StatusBar implements ImageTile {

    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    Hero hero = Hero.getInstance();

    private List<ImageTile> statusBar = new ArrayList<>(10);
    private List<ImageTile> fireBalls = new ArrayList<>();
    private List<ImageTile> health = new ArrayList<>(hero.getHealth());
    private List<ImageTile> itens = hero.getItens();

    private static final StatusBar INSTANCE = new StatusBar();

    public StatusBar(){
        for (ImageTile tile:
             statusBar) {
            statusBar.add(Black.getInstance());
        }
        while(true){
            statusBar.addAll(fireBalls);
            statusBar.addAll(health);
            statusBar.addAll(itens);
            gui.update();
        }

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

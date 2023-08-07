package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatusBar implements ImageTile {

    private Position position = new Position(0,0);
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

    private List<ImageTile> statusBar = new ArrayList<>();
    private List<ImageTile> fireBalls = new ArrayList<>();
    private List<ImageTile> health = new ArrayList<>();
    private List<ImageTile> itens = new ArrayList<>();

    private static final StatusBar INSTANCE = new StatusBar();

    public StatusBar(){
        statusBar.add(new Black(new Position(0,0)));
        statusBar.add(new FireBall(new Position(0,0)));
        statusBar.add(new Black(new Position(1,0)));
        statusBar.add(new FireBall(new Position(1,0)));
        statusBar.add(new Black(new Position(2,0)));
        statusBar.add(new FireBall(new Position(2,0)));
        statusBar.add(new Green(new Position(3,0)));
        statusBar.add(new Green(new Position(4,0)));
        statusBar.add(new Green(new Position(5,0)));
        statusBar.add(new Green(new Position(6,0)));
        statusBar.add(new Black(new Position(7,0)));
        statusBar.add(new Black(new Position(8,0)));
        statusBar.add(new Black(new Position(9,0)));
        gui.addStatusImage(this);
        // é possivel fazer 3 arrays diferentes e implementar através de ciclos dentro da lista StatusImage
    }

    public List<ImageTile> getStatusBar() {
        return statusBar;
    }

    public static StatusBar getINSTANCE() {
        return INSTANCE;
    }

    public void manageFireBalls(){
        int fireBalls = Hero.getInstance().getFireBalls();
        if(fireBalls == 2){
            this.fireBalls.remove(2);
        }
        if(fireBalls == 1){
            this.fireBalls.remove(1);
        }
        if(fireBalls == 0){
            this.fireBalls.remove(0);
        }
    }

    public void manageHeroHealth(){
        int heroHealth = Hero.getInstance().getHealth();
        if(heroHealth == 3){
            health.remove(3);
            health.add(new Red(new Position(6,0)));
        }
        if(heroHealth == 2){
            health.remove(2);
            health.add(new Red(new Position(5,0)));
        }
        if(heroHealth == 1){
            health.remove(1);
            health.add(new Red(new Position(4,0)));
        }
        if(heroHealth == 0){
            health.remove(0);
            health.add(new Red(new Position(3,0)));
        }
        //possivelmente integrar aqui o fim do jogo quando o heroi morre
    }

    public void manageHeroItens() {
        List<Itens> itens = Hero.getInstance().getInventory();
        for (Itens item : itens) {
            if (!item.equals(null)) {
                itens.add(item);
            } else{
                continue;
            }
        }
    }

    @Override
    public String getName() {
        return "StatusBar";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

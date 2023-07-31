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

    private List<ImageTile[]> statusBar = new ArrayList<>(3);
    private List<FireBall> fireBalls = new ArrayList<>(3);
    private List<ImageTile> health = new ArrayList<>(4);
    private List<Itens> itens =new ArrayList<>();
    private List<ImageTile> itensTiles =new ArrayList<>(3);

    private static final StatusBar INSTANCE = new StatusBar();

    public StatusBar(){

    }
    public void initStatusBar() {
        while (true) {
           //verificar isto! List<ImageTile> fireTiles = new ArrayList<>{new FireBall(new Position(0,0)), new FireBall(new Position(1,0)), new FireBall(new Position(2,0))};
            manageFireBalls();
            initializaHealthBar();
            manageHeroHealth();
            manageHeroItens();
            statusBar.add(fireBalls.toArray(new ImageTile[0]));
            statusBar.add(health.toArray(new ImageTile[0]));
            statusBar.add(itens.toArray(new ImageTile[0]));


        }
    }

    public static StatusBar getINSTANCE() {
        return INSTANCE;
    }

    public void manageFireBalls(){

    }

    public void initializaHealthBar(){

        for(int i = 0; i < hero.getHealth(); i++) {
            health.add(Green.getInstance());
        }
    }
    public void manageHeroHealth(){
        int heroHealth = hero.getHealth();
        if(heroHealth == 3){
            health.remove(3);
            health.add(Red.getInstance());
        }
        if(heroHealth == 2){
            health.remove(2);
            health.add(Red.getInstance());
        }
        if(heroHealth == 1){
            health.remove(1);
            health.add(Red.getInstance());
        }
        if(heroHealth == 0){
            health.remove(0);
            health.add(Red.getInstance());
        }
    }

    /*public void manageHeroItens() {
        itens = hero.getInventory();
        for (Itens item : itens) {
            itens.add(Black.getInstance());
            if (!item.equals(null)) {
                itens.add(item);
            } else{
                itens.add(Black.getInstance());
            }
        }
    }*/

    public void manageHeroItens() {
        List<Itens> updatedItens = new ArrayList<>();
        for (Itens item : hero.getInventory()) {
            updatedItens.add(Black.getInstance());
            if (item != null) {
                updatedItens.add(item);
            } else {
                updatedItens.add(Black.getInstance());
            }
        }
        itens = updatedItens;
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

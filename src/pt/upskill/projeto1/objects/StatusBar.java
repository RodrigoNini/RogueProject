package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.RoomObjects.Fire;
import pt.upskill.projeto1.objects.StatusObjects.Black;
import pt.upskill.projeto1.objects.StatusObjects.Green;
import pt.upskill.projeto1.objects.StatusObjects.Red;
import pt.upskill.projeto1.objects.StatusObjects.RedGreen;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class StatusBar implements ImageTile {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private List<ImageTile> statusList = new ArrayList<>();
    private Position position = new Position(0,0);

    private List<ImageTile> statusBar = new ArrayList<>();
    private int fireBalls;
    private List<ImageTile> health = new ArrayList<>();
    private List<ImageTile> itens = new ArrayList<>();
    private Hero hero = Hero.getInstance();

    public StatusBar(){
        this.fireBalls = 3;
    }
    public List<ImageTile> getStatusList() {
        return statusList;
    }
    public void setStatusList(ArrayList<ImageTile> statusList) {
        this.statusList = statusList;
    }

    private static final StatusBar INSTANCE = new StatusBar();

    public List<ImageTile> getStatusBar() {
        return statusBar;
    }

    public static StatusBar getINSTANCE() {
        return INSTANCE;
    }

 public void manageFireBalls(){
        int fireBalls = Hero.getInstance().getFireBalls();
        if(fireBalls == 2){
            statusList.get(2).po;
        }
        if(fireBalls == 1){
            this.fireBalls.remove(1);
        }
        if(fireBalls == 0){
            this.fireBalls.remove(0);
        }


    //Update game status bar
    public void updateStatus() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        statusList.clear();
        // Create black bars on the background
        for (int i = 0; i < 10; i++) {
            Position pos = new Position(i, 0);
            ImageTile black = new Black(pos);
            statusList.add(black);
        }
        // Create fireballs
        for (int i = 0; i < fireBalls; i++) {
            Position position = new Position(i, 0);
            FireTile fire = new Fire(position);
            statusList.add(fire);
        } // Update HP bars
        int hpLoss = hero.getMaxHealth() - hero.getCurrentHealth();
        for (int i = 3; i < 7; i++) {
            Position posLife = new Position(i, 0);
            if (hpLoss > 0) {
                if (hpLoss == 1) {
                    ImageTile redgreen = new RedGreen(posLife);
                    statusList.add(redgreen);
                    hpLoss--;
                } else if (hpLoss > 1) {
                    ImageTile red = new Red(posLife);
                    statusList.add(red);
                    hpLoss -= 2;
                } else if (hpLoss == 0) {
                    ImageTile green = new Green(posLife);
                    statusList.add(green);
                }
            } else {
                ImageTile green = new Green(posLife);
                statusList.add(green);
            }
        }
    }

    public void manageHeroHealth(){;
        if(hero.getCurrentHealth() == 3){
            health.remove(3);
            health.add(new Red(new Position(6,0)));
        }
        if(hero.getCurrentHealth() == 2){
            health.remove(2);
            health.add(new Red(new Position(5,0)));
        }
        if(hero.getCurrentHealth() == 1){
            health.remove(1);
            health.add(new Red(new Position(4,0)));
        }
        if(hero.getCurrentHealth() == 0){
            health.remove(0);
            health.add(new Red(new Position(3,0)));
        }
        //possivelmente integrar aqui o fim do jogo quando o heroi morre
    }

    public void manageHeroItens() {
        List<Itens> heroItens = Hero.getInstance().getInventory();
        List<ImageTile> newItens = new ArrayList<>();

        for (Itens item : heroItens) {
            if (item != null) {
                newItens.add(item);
            }
        }
        itens.clear();
        itens.addAll(newItens);
    }

    public void update(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        statusList.clear();
        for (int i = 0; i < 10; i++){
            statusList.add(new Black(new Position(i, 0))); //set everything black
        }
        manageHeroHealth();
        manageFireBalls();
        manageHeroItens();
        gui.newStatusImages(statusList);

    }

    @Override
    public String getName() {
        return "StatusBar";
    }

    @Override
    public Position getPosition() {
        return null;
    }
}

package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.StatusObjects.StatusBar;
import pt.upskill.projeto1.rogue.utils.Direction;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    private RoomManager roomManager = RoomManager.getInstance();
    private Hero hero = Hero.getInstance();

    public Engine(){

    }

    public void init(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        gui.setEngine(this);
        int startingRoom = 0;
        roomManager.runRoomEngine(startingRoom);
        gui.go();

        gui.setStatus("O jogo começou!");

        while (true){
            gui.update();
        }
    }

    public void notify(int keyPressed){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();
        List<Enemies> enemiesCopy = new ArrayList<>(roomManager.getCurrentRoom().getEnemyList());
        if (keyPressed == KeyEvent.VK_DOWN){
            System.out.println("User pressed down key!");
            hero.update(Direction.DOWN);
            hero.setLastDirection(Direction.DOWN);
        }
        if (keyPressed == KeyEvent.VK_UP){
            System.out.println("User pressed up key!");
            hero.update(Direction.UP);
            hero.setLastDirection(Direction.UP);
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            System.out.println("User pressed left key!");
            hero.update(Direction.LEFT);
            hero.setLastDirection(Direction.LEFT);
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            System.out.println("User pressed right key!");
            hero.update(Direction.RIGHT);
            hero.setLastDirection(Direction.RIGHT);
        }
        if (keyPressed == KeyEvent.VK_I) {
            gui.showMessage("Score:", "Tens " + hero.getScore() + " pontos.");
        }

       if (keyPressed == KeyEvent.VK_1) {
           hero.useOrDrop(1);
        } else if (keyPressed == KeyEvent.VK_2) {
           hero.useOrDrop(2);
        } else if (keyPressed == KeyEvent.VK_3) {
            hero.useOrDrop(3);
        }

        if (keyPressed == KeyEvent.VK_SPACE){
            System.out.println("Usaste uma bola de fogo!");
            hero.useFireball();
        }
        synchronized (enemiesCopy) {
            for (Enemies enemy : enemiesCopy) {
                enemy.movement();
            }
        }
        synchronized (RoomManager.getInstance().getCurrentRoom().getEnemyList()) {
            for (Enemies enemy : RoomManager.getInstance().getCurrentRoom().getEnemyList()) {
                if (enemy.getPosition().equals(Hero.getInstance().getPosition())) {
                    int enemyDamage = enemy.getDamage();
                    Hero.getInstance().takeDamage(enemyDamage);
                    enemy.takeDamage(Hero.getInstance().getDamage());
                    StatusBar.getInstance().update();
                }
            }
        }
    }


    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}

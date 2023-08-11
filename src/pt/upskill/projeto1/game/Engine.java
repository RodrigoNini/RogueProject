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
    private RoomManager roomManager;
    private Hero hero;

    public Engine(){
            roomManager = RoomManager.getINSTANCE();
            hero = Hero.getInstance();
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
        List<Enemies> Enemies = RoomManager.getINSTANCE().getCurrentRoom().getEnemyList();
        Hero hero = Hero.getInstance();
        if (keyPressed == KeyEvent.VK_DOWN){
            System.out.println("User pressed down key!");
            hero.setPosition(Direction.DOWN);
            for (ImageTile i: roomManager.getCurrentRoom().getTiles()
            ) {
                if(i instanceof Enemies){
                    ((Enemies) i).movement();
                }
            }
        }
        if (keyPressed == KeyEvent.VK_UP){
            System.out.println("User pressed up key!");
            hero.setPosition(Direction.UP);
            for (ImageTile i: roomManager.getCurrentRoom().getTiles()
            ) {
                if(i instanceof Enemies){
                    ((Enemies) i).movement();
                }
            }
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            System.out.println("User pressed left key!");
            hero.setPosition(Direction.LEFT);
            for (ImageTile i: roomManager.getCurrentRoom().getTiles()
            ) {
                if(i instanceof Enemies){
                    ((Enemies) i).movement();
                }
            }
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            System.out.println("User pressed right key!");
            hero.setPosition(Direction.RIGHT);
            for (ImageTile i: roomManager.getCurrentRoom().getTiles()
            ) {
                if(i instanceof Enemies){
                    ((Enemies) i).movement();
                }
            }
        }

            for (Enemies enemy : RoomManager.getINSTANCE().getCurrentRoom().getEnemyList()) {
                if (enemy.getPosition().equals(Hero.getInstance().getPosition())) {
                    int enemyDamage = enemy.getDamage();
                    Hero.getInstance().takeDamage(enemyDamage);
                    enemy.takeDamage(Hero.getInstance().getDamage());
                    StatusBar.getInstance().update();
                }

            }
    }


    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}

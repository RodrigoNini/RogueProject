package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyListener;

public class Engine {
    private RoomManager roomManager;
    public void init(){
        roomManager = RoomManager.getInstance();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        List<ImageTile> tiles = new ArrayList<>(RoomManager.getInstance().getTiles());
        tiles.add(Hero.getInstance());


        tiles.add(Hero.getInstance());
        StatusBar statusBar = StatusBar.getINSTANCE();
        gui.newStatusImages(statusBar.getStatusBar());
        gui.setEngine(this);
        gui.newImages(tiles);

        gui.go();

        gui.setStatus("O jogo começou!");

        while (true){
            gui.update();
        }
    }

    public void notify(int keyPressed){
        List<Adversarios> adversarios = RoomManager.getInstance().getCurrentAdversarios();
        Hero hero = Hero.getInstance();
        if (keyPressed == KeyEvent.VK_DOWN){
            System.out.println("User pressed down key!");
            hero.setPosition(Direction.DOWN);
            for (ImageTile i: roomManager.getTiles()
                 ) {
                if(i instanceof Adversarios){
                    ((Adversarios) i).movement();
                }
            }
        }
        if (keyPressed == KeyEvent.VK_UP){
            System.out.println("User pressed up key!");
            hero.setPosition(Direction.UP);
            for (ImageTile i: roomManager.getTiles()
            ) {
                if(i instanceof Adversarios){
                    ((Adversarios) i).movement();
                }
            }
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            System.out.println("User pressed left key!");
            hero.setPosition(Direction.LEFT);
            for (ImageTile i: roomManager.getTiles()
            ) {
                if(i instanceof Adversarios){
                    ((Adversarios) i).movement();
                }
            }

        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            System.out.println("User pressed right key!");
            hero.setPosition(Direction.RIGHT);
            for (ImageTile i: roomManager.getTiles()
            ) {
                if(i instanceof Adversarios){
                    ((Adversarios) i).movement();
                }
            }
        }
    }


    public static void main(String[] args){

        Engine engine = new Engine();
        engine.init();
    }

}

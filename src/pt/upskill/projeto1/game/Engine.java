package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Floor;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyListener;

public class Engine {


    public void init(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Room room = Room.getInstance();
        List<ImageTile> tiles = room.getCurrentRoom();

        Hero hero = Hero.getInstance();
        tiles.add(hero);

        gui.setEngine(this);
        gui.newImages(tiles);

        gui.go();

        gui.setStatus("O jogo começou!");

        while (true){
            gui.update();
        }
    }

    public void notify(int keyPressed){
        Hero hero = Hero.getInstance();
        if (keyPressed == KeyEvent.VK_DOWN){
            System.out.println("User pressed down key!");
            hero.setPosition(Direction.DOWN);
        }
        if (keyPressed == KeyEvent.VK_UP){
            System.out.println("User pressed up key!");
            hero.setPosition(Direction.UP);
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            System.out.println("User pressed left key!");
            hero.setPosition(Direction.LEFT);
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            System.out.println("User pressed right key!");
            hero.setPosition(Direction.RIGHT);
        }
    }


    public static void main(String[] args){

        Engine engine = new Engine();
        engine.init();
    }

}

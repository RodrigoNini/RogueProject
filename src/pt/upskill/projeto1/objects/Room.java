package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Room implements ImageTile {

    private String name;
    private Position position;
    private static final Room INSTANCE = new Room("Room");

    public Room(String name) {
        this.name = name;
    }

    public static Room getInstance() {
        return INSTANCE;
    }

    public List<ImageTile> readFile()  {
        List<ImageTile> tiles = new ArrayList<>();
        try(Scanner reader = new Scanner(new File("C:/Users/rodri/IdeaProjects/RogueProject/rooms/room0.txt"))){
            int y = 0;
            while(reader.hasNextLine()){
                String novaLinha = reader.nextLine();
                if(novaLinha.startsWith("#")) {continue;}
                String[] coluna = novaLinha.split("");
                int x = 0;
                for (String tile:
                     coluna) {
                    switch (tile){
                        case "W":
                            tiles.add(new Wall(new Position(x, y)));
                            break;
                        case "0":
                            tiles.add(new Door(new Position(x, y), true, true));
                            break;
                        case "k":
                            tiles.add(new Key(new Position(x, y)));
                            break;
                        case "S":
                            tiles.add(new Skeleton(new Position(x, y)));
                            break;
                        case "H":
                            Hero hero = Hero.getInstance();
                            hero.setPosition(new Position(x, y));
                            break;
                        case "1":
                            tiles.add(new Door(new Position(x, y), false, false));
                            break;
                        case "2":
                            tiles.add(new Door(new Position(x, y), false,false));
                            break;
                        default:
                            tiles.add(new Floor(new Position(x, y)));
                            break;
                    }
                    x++;
                }
                y++;
            }ImageMatrixGUI.getInstance().update();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return tiles;
    }

    public void printRoom(){

        List<ImageTile> tiles = new ArrayList<>();
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tiles.add(new Floor(new Position(i, j)));
            }
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}

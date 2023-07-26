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

    List Room0 = readFile("C:/Users/rodri/IdeaProjects/RogueProject/rooms/room0.txt");
    List Room1 = readFile("C:/Users/rodri/IdeaProjects/RogueProject/rooms/room1.txt");
    List Room2 = readFile("C:/Users/rodri/IdeaProjects/RogueProject/rooms/room2.txt");

    private static List<ImageTile> currentRoom;

    private static final Room INSTANCE = new Room("Room");

    public Room(String name) {
        this.name = name;
        currentRoom = Room0;
    }

    public static Room getInstance() {
        return INSTANCE;
    }

    Hero hero = Hero.getInstance();

    public List<ImageTile> readFile(String file)  {
        List<ImageTile> tiles = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(file))){
            int y = 0;
            while(reader.hasNextLine()){
                String novaLinha = reader.nextLine();
                if(novaLinha.startsWith("#")) {
                    String[] instruções = novaLinha.split(" ");
                    if(instruções[1].equals("0")  || instruções[1].equals("1") || instruções[1].equals("2")){
                        //verificar que tipo de porta é
                        // a que sala vai dar
                        //o número da porta na qual o herói vai aparecer nessa sala
                        // se é necessário chave e qual para abrir essa porta
                        if(instruções[2].equals("D")){
                            // tipo de porta deve ser fechada
                        }else if(instruções[2].equals("E")){
                            //tipo de porta deve ser doorway
                        }


                        switch(instruções[3]){
                     case "room0.txt":
                         //do something
                         break;
                     case "room1.txt":
                         //do something
                         break;
                     case "room2.txt":
                         //do something
                         break;
                 }
                    } else if (instruções[1].equals("k")) {

                    }

                    continue;}
                String[] coluna = novaLinha.split("");
                int x = 0;
                for (String tile:
                     coluna) {
                    switch (tile){
                        case "W":
                            tiles.add(new Wall(new Position(x, y)));
                            break;
                        case "0":
                            tiles.add(new Floor(new Position(x, y)));
                            tiles.add(new Doorway(new Position(x, y)));
                            break;
                        case "k":
                            tiles.add(new Floor(new Position(x, y)));
                            tiles.add(new Key(new Position(x, y)));
                            break;
                        case "S":
                            tiles.add(new Floor(new Position(x, y)));
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            tiles.add(skeleton);
                            break;
                        case "H":
                            tiles.add(new Floor(new Position(x, y)));
                            Hero hero = Hero.getInstance();
                            hero.setPosition(new Position(x, y));
                            break;
                        case "1":
                            /*Hero hero1 = Hero.getInstance();
                            if(hero1.getItens().contains("key1")){
                                tiles.add(new DoorOpen(new Position(x, y)));
                            } else {
                                tiles.add(new DoorClosed(new Position(x, y)));
                            }*/
                            tiles.add(new DoorClosed(new Position(x, y)));
                            break;
                        case "2":
                            tiles.add(new DoorClosed(new Position(x, y)));
                            break;
                        case "b":
                            tiles.add(new Floor(new Position(x, y)));
                            Bat bat = new  Bat(new Position(x, y));
                            tiles.add(bat);
                            break;
                        case "m":
                            tiles.add(new Floor(new Position(x, y)));
                            Meat meat = new Meat(new Position(x, y));
                            tiles.add(meat);
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


    public static boolean findObstacle(Position position){
        List<ImageTile> tiles = getCurrentRoom();
        for (ImageTile tile:
                tiles) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Wall || tile instanceof DoorClosed || tile instanceof Adversarios){
                    return true;
                }
            }
        }
        return false;
    }

public void changeRoom() {
        // falta implementar abertura de portas fechadas com chaves
    for (ImageTile tile :
            currentRoom) {
        if (tile.getPosition().equals(position)) {
            if (tile instanceof Door) {
                currentRoom = Room1;
            }
        }
    }
}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public static List<ImageTile> getCurrentRoom() {
        return currentRoom;
    }
}

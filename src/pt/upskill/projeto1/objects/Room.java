package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.BadGuy;
import pt.upskill.projeto1.objects.Enemies.Bat;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.Enemies.Skeleton;
import pt.upskill.projeto1.objects.Items.Key;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Items.Sword;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.RoomObjects.Floor;
import pt.upskill.projeto1.objects.RoomObjects.Wall;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room implements ImageTile {
    private ArrayList<ImageTile> tiles = new ArrayList<ImageTile>();
    private ArrayList<Enemies> enemyList = new ArrayList<Enemies>();
    private ArrayList<Itens> itemList = new ArrayList<Itens>();
    private ArrayList<Doors> doorList = new ArrayList<Doors>();
    private ArrayList<Key> keyList = new ArrayList<Key>();
    private String file = "/room/room0.txt";
    
    
    public Room(ArrayList<ImageTile> tiles) {
        this.tiles = tiles;
    }

    public Room(){}

    public Room(String file) {
        this.file = file;
        readRoomFile(file);
    }


    public void readRoomFile(String file) {
        this.file = file;
        ArrayList<ImageTile> tiles = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(file))){
            int y = 0;
            while(reader.hasNextLine()){
                String novaLinha = reader.nextLine();
                if(novaLinha.startsWith("#")) {
                    String[] instruções = novaLinha.split(" ");
                    if (instruções.length < 2) {
                        continue;
                    }
                    if (checkInteger(instruções[1])) {
                        if (instruções.length < 4) {
                            continue;
                        }
                        if (instruções[2].equals("D")) {
                            String roomFileName = instruções[3];
                            Door doorClosed = new Door("DoorClosed", roomFileName);
                            doorList.add(doorClosed);
                            if (instruções.length >= 5) {
                                doorClosed.setKey(instruções[4]);
                            }
                        } else if (instruções[2].equals("E")) {
                            String roomFileName = instruções[3];
                            DoorWay doorWay = new DoorWay(roomFileName);
                            doorList.add(doorWay);
                        }
                    } else if (instruções[1].equals("k")) {
                        Key key = new Key(instruções[2]);
                        keyList.add(key);
                    }
                    continue;
                }
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
                            doorList.get(0).setPosition(new Position(x, y));
                            tiles.add(doorList.get(0));
                            break;
                        case "1":
                            doorList.get(1).setPosition(new Position(x, y));
                            tiles.add(doorList.get(1));
                            break;
                        case "2":
                            doorList.get(2).setPosition(new Position(x, y));
                            tiles.add(doorList.get(2));
                            break;
                        case "k":
                            tiles.add(new Floor(new Position(x, y)));
                            tiles.add(keyList.get(0));
                            keyList.get(0).setPosition(new Position(x, y));
                            break;
                        case "S":
                            tiles.add(new Floor(new Position(x, y)));
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            tiles.add(skeleton);
                            enemyList.add(skeleton);
                            break;
                        case "H":
                            tiles.add(new Floor(new Position(x, y)));
                            Hero.getInstance().setPosition(new Position(x, y));
                            break;
                        case "B":
                            tiles.add(new Floor(new Position(x, y)));
                            Bat bat = new Bat(new Position(x, y));
                            tiles.add(bat);
                            enemyList.add(bat);
                            break;
                        case "G":
                            tiles.add(new Floor(new Position(x, y)));
                            BadGuy badGuy= new  BadGuy(new Position(x, y));
                            tiles.add(badGuy);
                            enemyList.add(badGuy);
                            break;
                        case "m":
                            tiles.add(new Floor(new Position(x, y)));
                            Meat meat = new Meat(new Position(x, y));
                            tiles.add(meat);
                            itemList.add(meat);
                            break;
                        case "s":
                            tiles.add(new Floor(new Position(x, y)));
                            Sword sword = new Sword(new Position(x, y));
                            tiles.add(sword);
                            itemList.add(sword);
                            break;
                        default:
                            tiles.add(new Floor(new Position(x, y)));
                            break;
                    }
                    x++;
                }
                y++;
            }
            ImageMatrixGUI.getInstance().update();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        this.tiles = tiles;
    }

    public boolean checkInteger(String num){
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<ImageTile> getTiles() {
        return tiles;
    }


    public ImageTile getTile(Position position) {
        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(position)) {
                return tile;
            }
        }
        return null;
    }

    public List<Enemies> getEnemies() {
        return enemyList;
    }

    public List<Itens> getItens() {
        return itemList;
    }

    public List<Doors> getDoors() {
        return doorList;
    }

    public List<Key> getKey() {
        return keyList;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }


    // novo código
    public boolean findObstacle(Position position){
        for (ImageTile tile:
                tiles) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Wall || tile instanceof Door || tile instanceof Enemies || tile instanceof Hero){
                    return true;
                }
            }
        }
        return false;
    }

    // Check a given position on the room, used for collision check and others
    public ImageTile checkPosition(Position position) {
        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(position)) {
                // If for a given position the object found is Not floor and Not Grass, return that object
                if (!(tile instanceof Floor)) {
                    return tile;
                }
            }
        }
        return null;
    }

}

package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.*;
import pt.upskill.projeto1.objects.Items.*;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.RoomObjects.Floor;
import pt.upskill.projeto1.objects.RoomObjects.Wall;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room implements ImageTile {
    private ArrayList<ImageTile> tiles = new ArrayList<>();
    private ArrayList<Enemies> enemyList = new ArrayList<>();
    private ArrayList<Itens> itemList = new ArrayList<>();
    private ArrayList<Door> doorList = new ArrayList<>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private String roomPath = "./rooms/room0.txt";
    private int roomNumber;

    public Room(ArrayList<ImageTile> tiles) {
        this.tiles = tiles;
    }

    public Room() {
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ArrayList<Enemies> getEnemyList() {
        return enemyList;
    }

    public ArrayList<Itens> getItemList() {
        return itemList;
    }

    public ArrayList<Door> getDoorList() {
        return doorList;
    }

    public ArrayList<Key> getKeyList() {
        return keyList;
    }

    public String getRoomPath() { //get room path for hero's current room
        switch (roomNumber) {
            case 1:
                return ("./rooms/room1.txt");
            case 2:
                return ("./rooms/room2.txt");
            case 3:
                return ("./rooms/room3.txt");
            case 4:
                return ("./rooms/room4.txt");
            default:
                return ("./rooms/room0.txt");
        }
    }

    public void readTextRoom() {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                tiles.add(new Floor(new Position(x, y)));
            }
        }
        try (Scanner roomFile = new Scanner(new File(getRoomPath()))) {
            int y = 0;
            String line = "";
            while (roomFile.hasNextLine()) {
                line = roomFile.nextLine();
                if (line.charAt(0) == '#') {
                    String[] tokens = line.split(" ");
                    switch (tokens.length) {
                        case 6:
                            doorList.add(new Door(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                            break;
                        case 5:
                            doorList.add(new Door(tokens[1], tokens[2], tokens[3], tokens[4]));
                            break;
                        case 3:
                            keyList.add(new Key(tokens[2]));
                            break;
                    }
                    continue;
                }
                for (int x = 0; x < line.length(); x++) {
                    char character = line.charAt(x);
                    switch (character) {
                        case 'W': //wall
                            tiles.add(new Wall(new Position(x, y)));
                            break;
                        case 'S': //skeleton
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            tiles.add(skeleton);
                            enemyList.add(skeleton);
                            break;
                        case 'G': //bad guy
                            BadGuy badguy = new BadGuy(new Position(x, y));
                            tiles.add(badguy);
                            enemyList.add(badguy);
                            break;
                        case 'T': //thief
                            Thief thief = new Thief(new Position(x, y));
                            tiles.add(thief);
                            enemyList.add(thief);
                            break;
                        case 'm': //meat
                            Meat meat = new Meat(new Position(x, y));
                            tiles.add(meat);
                            itemList.add(meat);
                            break;
                        case 'k': //key
                            Key key = keyList.get(0);
                            key.setPosition(new Position(x, y));
                            tiles.add(key);
                            itemList.add(key);
                            break;
                        case 's': //sword
                            Sword sword = new Sword(new Position(x, y));
                            tiles.add(sword);
                            itemList.add(sword);
                            break;
                        case 'H': //hammer
                            Hammer hammer = new Hammer(new Position(x, y));
                            tiles.add(hammer);
                            itemList.add(hammer);
                            break;
                        default: //door
                            if (Character.isDigit(character)) {
                                int i = character - '0';
                                Door door = doorList.get(i);
                                door.setPosition(new Position(x, y));
                                tiles.add(door);
                                doorList.add(door);
                            }

                    }
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean findObstacle(Position position) {
        for (ImageTile tile :
                tiles) {
            if (tile.getPosition().equals(position)) {
                if (tile instanceof Wall || tile instanceof Door || tile instanceof Enemies || tile instanceof Hero) {
                    if (tile instanceof Door) {
                        Door door = (Door) tile;
                        if (!door.isOpen()) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public ArrayList<ImageTile> getTiles() {
        return tiles;
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




package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.*;
import pt.upskill.projeto1.objects.Items.*;
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
    private ArrayList<ImageTile> mapTiles = new ArrayList<>();
    private ArrayList<Enemies> enemyList = new ArrayList<>();
    private ArrayList<Itens> itemList = new ArrayList<>();
    private ArrayList<Doors> doorList = new ArrayList<>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private String roomPath = "./rooms/room0.txt";
    private int roomNumber;

    public Room(ArrayList<ImageTile> tiles) {
        this.mapTiles = tiles;
    }

    public Room() {
    }
    public Room(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public ArrayList<ImageTile> getMapTiles() {
        return mapTiles;
    }

    public ArrayList<Enemies> getEnemyList() {
        return enemyList;
    }
    public ArrayList<Itens> getItemList() {
        return itemList;
    }

    public ArrayList<Doors> getDoorList() {
        return doorList;
    }

    public ArrayList<Key> getKeyList() {
        return keyList;
    }

    public String getRoomPath() { //get room path for hero's current room
        switch (roomNumber){
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
                mapTiles.add(new Floor(new Position(x, y)));
            }
        }
        try (Scanner roomFile = new Scanner(new File(getRoomPath()))){
            int y = 0;
            String line = "";
            while (roomFile.hasNextLine()) {
                line = roomFile.nextLine();
                if (line.charAt(0) == '#') {
                    String[] tokens = line.split(" ");
                    switch (tokens.length) {
                        case 6:
                            doorList.add(new Door(tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]));
                            break;
                        case 5:
                            doorList.add(new Door(tokens[1],tokens[2],tokens[3],tokens[4]));
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
                            mapTiles.add(new Wall(new Position(x, y)));
                            break;
                        case 'S': //skeleton
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            mapTiles.add(skeleton);
                            enemyList.add(skeleton);
                            break;
                        case 'G': //bad guy
                            BadGuy badguy = new BadGuy(new Position(x, y));
                            mapTiles.add(badguy);
                            enemyList.add(badguy);
                            break;
                        case 'T': //thief
                            Thief thief = new Thief(new Position(x, y));
                            mapTiles.add(thief);
                            enemyList.add(thief);
                            break;
                        case 'm': //meat
                            Meat meat = new Meat(new Position(x, y));
                            mapTiles.add(meat);
                            itemList.add(meat);
                            break;
                        case 'k': //key
                            Key key = keyList.get(0);
                            key.setPosition(new Position(x, y));
                            mapTiles.add(key);
                            itemList.add(key);
                            break;
                        case 's': //sword
                            Sword sword = new Sword(new Position(x, y));
                            mapTiles.add(sword);
                            itemList.add(sword);
                            break;
                        case 'H': //hammer
                            Hammer hammer = new Hammer(new Position(x, y));
                            mapTiles.add(hammer);
                            itemList.add(hammer);
                            break;
                        default: //door
                            if (Character.isDigit(character)){
                                int i = character-'0';
                                Doors door = doorList.get(i);
                                door.setPosition(new Position(x,y));
                                mapTiles.add(door);
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
    public boolean findsCollision(Position position){
        if (isWall(position) || isEnemy(position) || isClosedDoor(position) || isHero(position))
            return true;
        return false;
    }

    public boolean isWall(Position position) {
        for (ImageTile tile : mapTiles) { //to do: run through wall list instead
            if (tile.getPosition().equals(position) && tile instanceof Wall)
                return true;
        }
        return false;
    }
    public boolean isEnemy(Position position) {
        for (Enemies enemy : enemyList) {
            if (enemy.getPosition().equals(position))
                return true;
        }
        return false;
    }
    public boolean isHero(Position position) {
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && tile instanceof Hero)
                return true;
        }
        return false;
    }
    public boolean isClosedDoor(Position position) {
        for (Door door : doorList) {
            if (door.getPosition().equals(position) && door.getName().equals("DoorClosed"))
                return true;
        }
        return false;
    }
    public boolean isOpenDoor(Position position) {
        for (Doors door : doorList) {
            if (door.getPosition().equals(position) && ((door.getName().equals("DoorWay") || door.getName().equals("DoorOpen"))))
                return true;
        }
        return false;
    }
    public boolean isItem(Position position) {
        for (Itens item : itemList) {
            if (item.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
    public boolean isTrap(Position position) {
        for (ImageTile tile : mapTiles){
            if (tile.getPosition().equals(position) && tile.getName().equals("Trap"))
                return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    public ArrayList<ImageTile> getTiles() {
        return mapTiles;
    }
}

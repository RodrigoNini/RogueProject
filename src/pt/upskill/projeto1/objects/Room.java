package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.*;
import pt.upskill.projeto1.objects.Items.*;
import pt.upskill.projeto1.objects.Items.Weapons.Hammer;
import pt.upskill.projeto1.objects.Items.Weapons.Sword;
import pt.upskill.projeto1.objects.Items.Weapons.Weapon;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.RoomObjects.Floor;
import pt.upskill.projeto1.objects.RoomObjects.Wall;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Room implements ImageTile {

    private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private ArrayList<ImageTile> mapTiles = new ArrayList<>();
    private ArrayList<Enemies> enemyList = new ArrayList<>();
    private ArrayList<Itens> itemList = new ArrayList<>();
    private ArrayList<Door> doorList = new ArrayList<>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private int roomNumber;

    public Room(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public ArrayList<ImageTile> getTiles() {
        return mapTiles;
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

    public String getRoomPath() {
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
        try {
            Scanner roomFile = new Scanner(new File(getRoomPath()));
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
                    char id = line.charAt(x);
                    switch (id) {
                        case '0':
                            Door door0 = doorList.get(0);
                            door0.setPosition(new Position(x,y));
                            mapTiles.add(door0);
                            doorList.add(door0);
                            break;
                        case '1':
                            Door door1 = doorList.get(1);
                            door1.setPosition(new Position(x,y));
                            mapTiles.add(door1);
                            doorList.add(door1);
                            break;
                        case '2':
                            Door door2 = doorList.get(2);
                            door2.setPosition(new Position(x,y));
                            mapTiles.add(door2);
                            doorList.add(door2);
                            break;
                       case 'H':
                            Hero hero = Hero.getInstance();
                            hero.setPosition(new Position(x, y));
                            mapTiles.add(hero);
                            break;
                        case 'W':
                            mapTiles.add(new Wall(new Position(x, y)));
                            break;
                        case 'B':
                            Bat bat = new Bat(new Position(x, y));
                            mapTiles.add(bat);
                            enemyList.add(bat);
                            break;
                        case 'S':
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            mapTiles.add(skeleton);
                            enemyList.add(skeleton);
                            break;
                        case 'G':
                            BadGuy badguy = new BadGuy(new Position(x, y));
                            mapTiles.add(badguy);
                            enemyList.add(badguy);
                            break;
                        case 'T':
                            Thief thief = new Thief(new Position(x, y));
                            mapTiles.add(thief);
                            enemyList.add(thief);
                            break;
                        case 'm':
                            Meat meat = new Meat(new Position(x, y));
                            mapTiles.add(meat);
                            itemList.add(meat);
                            break;
                        case 'k':
                            Key key = keyList.get(0);
                            key.setPosition(new Position(x, y));
                            mapTiles.add(key);
                            itemList.add(key);
                            break;
                        case 's':
                            Sword sword = new Sword(new Position(x, y));
                            mapTiles.add(sword);
                            itemList.add(sword);
                            break;
                        case 'h':
                            Hammer hammer = new Hammer(new Position(x, y));
                            mapTiles.add(hammer);
                            itemList.add(hammer);
                            break;
                        default:
                            break;
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
                mapTiles) {
            if (tile.getPosition().equals(position)) {
                if (tile instanceof Wall || tile instanceof Door || tile instanceof Enemies || tile instanceof Hero) {
                    if (tile instanceof Door) {
                        Door door = (Door) tile;
                        if (!isOpenDoor(position)) {
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

    public boolean isEnemy(Position position) {
        for (Enemies enemy : enemyList) {
            if (enemy.getPosition().equals(position))
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
        for (Door door : doorList) {
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

    public void removeEnemy(Enemies enemy){
        mapTiles.remove(enemy);
        enemyList.remove(enemy);
    }

    public void repaintItem(Itens item){
        mapTiles.add(item);
        itemList.add(item);
        gui.addImage(item);
    }

    public void repaintWeapon(Weapon weapon){
        mapTiles.add(weapon);
        itemList.add(weapon);
        gui.addImage(weapon);
    }

    public void removeItem(Itens item){
        mapTiles.remove(item);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return new Position(-1, -1);
    }
}




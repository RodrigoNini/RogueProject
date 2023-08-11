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
    private String roomFile = "./rooms/room0.txt";
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

    public String getRoomFile() { //get room path for hero's current room
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
        try (Scanner reader = new Scanner(new File(getRoomFile()))) {
            int y = 0;
            while (reader.hasNextLine()) {
                String newLine = reader.nextLine();
                if (newLine.startsWith("#")) {
                    String[] instructions = newLine.split(" ");
                    if (instructions.length >= 3) {
                        if (instructions[2].equals("D")) {
                            Door door = new Door();
                            door.setType("D");
                            door.setNextRoom(instructions[3]);
                            door.setNextDoor(instructions[4]);
                            doorList.add(door);
                            door.setRequiresKey(instructions.length >= 6);
                            if (door.needsKey()) {
                                door.setRequiredKey(instructions[5]);
                            }
                        } else if (instructions[2].equals("E")) {
                            Door doorWay = new Door();
                            doorWay.setType("E");
                            doorWay.setNextRoom(instructions[3]);
                            doorList.add(doorWay);
                        } else if (instructions[2].equals("k") && instructions.length >= 4) {
                            keyList.add(new Key(instructions[3]));
                        }
                    }
                } else {
                    String[] coluna = newLine.split("");
                    int x = 0;
                    for (String tile : coluna) {
                        switch (tile) {
                            case "W":
                                tiles.add(new Wall(new Position(x, y)));
                                break;
                            case "0":
                            case "1":
                            case "2":
                                int doorIndex = Integer.parseInt(tile);
                                if (doorIndex < doorList.size()) {
                                    Door door = doorList.get(doorIndex);
                                    door.setPosition(new Position(x, y));
                                    tiles.add(door);
                                }
                                break;
                            case "k":
                                tiles.add(new Floor(new Position(x, y)));
                                if (!keyList.isEmpty()) {
                                    Key key = keyList.get(0);
                                    key.setPosition(new Position(x, y));
                                    tiles.add(key);
                                }
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
                                BadGuy badGuy = new BadGuy(new Position(x, y));
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
            }
            } catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

    private boolean checkInteger(String instructions) {
        try{
            Integer.parseInt(instructions);
            return true;
        } catch (NumberFormatException e){
            return false;
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

    public void removeEnemy(Enemies enemy){
        getTiles().remove(enemy);
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




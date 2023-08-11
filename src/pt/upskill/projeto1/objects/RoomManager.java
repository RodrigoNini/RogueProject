package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.objects.Items.ItemManager;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.RoomObjects.Wall;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager implements ImageTile {

    private HashMap<Integer, Room> roomList = new HashMap<Integer, Room>();
    private ImageMatrixGUI gui;
    private Room currentRoom;
    private Hero hero = Hero.getInstance();

    private static final RoomManager INSTANCE = new RoomManager();

    public static RoomManager getINSTANCE() {
        return INSTANCE;
    }

    public RoomManager() {
        this.gui = ImageMatrixGUI.getInstance();
    }

    public HashMap<Integer, Room> getRoomList() {
        return roomList;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getRoom(int roomNumber){ //if room exists in list, return it. Else generate new room and add it to room list
        if (roomList.containsKey(roomNumber)) {
            return (roomList.get(roomNumber));
        }
        else {
            Room newRoom = new Room(roomNumber);
            newRoom.readTextRoom();
            roomList.put(roomNumber, newRoom);
            return (newRoom);
        }

    }
    public void runRoomEngine(int roomNumber){ //initiates all visual elements
        hero.getStatusBar().update();
        gui.clearImages();
        currentRoom = getRoom(roomNumber);
        ArrayList<ImageTile> mapTiles;
        mapTiles = currentRoom.getTiles();
        ItemManager itemObserver = new ItemManager(gui);
        mapTiles.add(hero);
        //gui.addStatusImage(Hero.getInstance().getStatusBar());
        gui.newImages(mapTiles);
    }
    public void changeRoom(Door previousDoor, int nextRoomNumber, Hero hero){ //set visual elements to next room

        int nextDoor = previousDoor.getNextDoor();
        runRoomEngine(nextRoomNumber);
        hero.setPosition(currentRoom.getDoorList().get(nextDoor).getPosition());
    }

    public boolean findObstacle(Position position){
        for (ImageTile tile:
                currentRoom.getTiles()) {
            if(tile.getPosition().equals(position)){
                if(tile instanceof Wall || isClosedDoor(position) || tile instanceof Enemies || tile instanceof Hero){
                    return true;
                }
            }               //possivelmente ter que criar classe de portas fechadas
        }
        return false;
    }

    public boolean isClosedDoor(Position position) {
        for (Door door : getCurrentRoom().getDoorList()) {
            if (door.getPosition().equals(position) && door.getName().equals("DoorClosed"))
                return true;
        }
        return false;
    }

    public void removeEnemy(Enemies enemy){
        currentRoom.getTiles().remove(enemy);
    }

    @Override
    public String getName() {
        return "RoomManager";
    }

    @Override
    public Position getPosition() {
        return new Position(0,0);
    }
}

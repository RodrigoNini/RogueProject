package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.RoomObjects.Door;
import pt.upskill.projeto1.objects.StatusObjects.StatusBar;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager implements ImageTile {

    private HashMap<Integer, Room> roomList = new HashMap<>();
    private ImageMatrixGUI gui;
    private Room currentRoom;
    private Hero hero = Hero.getInstance();

    private static final RoomManager instance = new RoomManager(ImageMatrixGUI.getInstance());

    public static RoomManager getInstance() {
        return instance;
    }

    public RoomManager(ImageMatrixGUI gui) {
        this.gui = gui;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getRoom(int roomNumber){
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
    public void runRoomEngine(int roomNumber){
        StatusBar.getInstance().update();
        gui.clearImages();
        currentRoom = getRoom(roomNumber);
        ArrayList<ImageTile> mapTiles;
        mapTiles = currentRoom.getTiles();
        mapTiles.add(hero);
        gui.newImages(mapTiles);
    }
    public void changeRoom(Door previousDoor, int nextRoomNumber, Hero hero){ //set visual elements to next room
        int nextDoor = previousDoor.getNextDoor();
        runRoomEngine(nextRoomNumber);
        hero.setPosition(currentRoom.getDoorList().get(nextDoor).getPosition());
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

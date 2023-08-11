package pt.upskill.projeto1.objects.StatusObjects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Items.Itens;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class StatusBar implements ImageTile {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private ArrayList<ImageTile> statusList = new ArrayList<>();
    public StatusBar() {}
    public ArrayList<ImageTile> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<ImageTile> statusList) {
        this.statusList = statusList;
    }
    public void getFireBalls(int numberOfFireballs){
//        for (int i = 0; i < numberOfFireballs; i++){
//            FireTile fireball = new Fireball(new Position(i, 0));
//            statusList.add(fireball);
//        }
    }
    public void getHealth(int health){
        for (int i = 3; i <= 6; i++){
            statusList.add(new Green(new Position(i, 0))); //set life bar to green
        }
        if (health < 8){
            statusList.add(new RedGreen(new Position(6, 0)));
        }
        if (health < 7){
            statusList.add(new Red(new Position(6, 0)));
        }
        if (health < 6){
            statusList.add(new RedGreen(new Position(5, 0)));
        }
        if (health < 5){
            statusList.add(new Red(new Position(5, 0)));
        }
        if (health < 4){
            statusList.add(new RedGreen(new Position(4, 0)));
        }
        if (health < 3){
            statusList.add(new Red(new Position(4, 0)));
            gui.setStatus("Estás quase a morrer! Encontra comida...");
        }
        if (health < 2){
            statusList.add(new RedGreen(new Position(3, 0)));
            gui.setStatus("ESTÁS QUASE A MORRER! ENCONTRA COMIDA RÁPIDO!");
        }
        if (health < 1){
            statusList.add(new Red(new Position(3, 0)));
            gui.setStatus("Foste de Base");
        }
    }
    public void getInventory(HashMap<Integer, Itens> inventory){
        int key = 0;
        for (int i = 7; i < 10; i++){
            if (inventory.containsKey(key)){
                Position position = new Position(i, 0);
                Itens copyOfItem = inventory.get(key);
                //inventory.remove(key);
                copyOfItem.setPosition(position);
                statusList.add((ImageTile) copyOfItem);
            }
            key++;
        }
    }
    public void update(int health, int numberOfFireballs, HashMap<Integer, Itens> inventory){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        statusList.clear();
        for (int i = 0; i < 10; i++){
            statusList.add(new Black(new Position(i, 0))); //set everything black
        }
        getHealth(health);
        getFireBalls(numberOfFireballs);
        getInventory(inventory);
        gui.newStatusImages(statusList);

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



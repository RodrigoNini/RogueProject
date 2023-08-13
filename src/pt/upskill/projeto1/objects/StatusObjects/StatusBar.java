package pt.upskill.projeto1.objects.StatusObjects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;


import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.Items.Itens;

import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class StatusBar {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private ArrayList<ImageTile> statusList = new ArrayList<>();
    public StatusBar() {}
    private static final StatusBar INSTANCE = new StatusBar();

    public static StatusBar getInstance() {
        return INSTANCE;
    }


    public void getFireBalls(int numberOfFireballs){
        for (int i = 0; i < numberOfFireballs; i++){
           Fire fireball = new Fire(new Position(i, 0));
           statusList.add(fireball);
       }
    }
    public void getHealth(int health){
        for (int i = 3; i <= 6; i++){
            statusList.add(new Green(new Position(i, 0)));
        }
        if (health < 8){
            statusList.add(new RedGreen(new Position(6, 0)));
        }
        if (health < 7){
            statusList.add(new Red(new Position(6, 0)));
        }
        if (health < 7){
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
            Hero.getInstance().dies();
        }
    }
    public void getInventory(HashMap<Integer, Itens> inventory){
        for (int i = 7; i < 10; i++){
            if (inventory.containsKey(i - 7)){
                Position position = new Position(i, -1);
                Itens copyOfItem = inventory.get(i - 7);
                copyOfItem.setPosition(position);
                statusList.add((ImageTile) copyOfItem);
            }
        }
    }
    public void update(){
        int health = Hero.getInstance().getCurrentHealth();
        int numberOfFireballs = Hero.getInstance().getNumberOfFireballs();
        HashMap<Integer, Itens> inventory = Hero.getInstance().getInventory();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        statusList.clear();
        for (int i = 0; i < 10; i++){
            statusList.add(new Black(new Position(i, 0)));
        }
        getHealth(health);
        getFireBalls(numberOfFireballs);
        getInventory(inventory);
        gui.newStatusImages(statusList);

    }
}

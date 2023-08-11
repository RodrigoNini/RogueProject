package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Hero;

public class ItemManager {
    ImageMatrixGUI gui;

    public ItemManager(ImageMatrixGUI gui) {
        this.gui = gui;
    }
    public Hero hero = Hero.getInstance();
    public void PickUp(Itens item, Hero hero){
        if (!hero.getInventory().containsKey(0)){
            hero.getInventory().put(0, item);
            hero.getStatusBar().update();
            hero.getInventory().remove(0);
            //moveItemToOutOfView(item);
        }
        else if (!hero.getInventory().containsKey(1)){
            hero.getInventory().put(1, item);
            hero.getStatusBar().update();
            hero.getInventory().remove(1);
            //moveItemToOutOfView(item);
        } else if (!hero.getInventory().containsKey(2)){
            hero.getInventory().put(2, item);
            hero.getStatusBar().update();
            hero.getInventory().remove(2);
            //moveItemToOutOfView(item);
        }
    }
}

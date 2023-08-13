package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Key extends Itens {

    private String keyName;

    public Key(String keyName){
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
    @Override
    public String getName() {
        return "Key";
    }

}
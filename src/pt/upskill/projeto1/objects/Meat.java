package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Meat extends Itens{

    private String name;
    private Position position;

    public Meat(Position position) {
        this.name = "GoodMeat";
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }


    @Override
    public void use() {

    }

    @Override
    public void drop() {

    }
}

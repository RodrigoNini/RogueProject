package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Meat extends Itens {

    public Meat(Position position) {
        super("GoodMeat", position);
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }


}

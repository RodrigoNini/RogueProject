package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.objects.Items.Itens;
import pt.upskill.projeto1.rogue.utils.Position;

    public class Sword extends Weapon {

        public Sword(Position position) {
            super("Sword",position, 2);
            super.setDamage(2);
        }

    }


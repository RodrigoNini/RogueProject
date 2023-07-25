package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Door implements ImageTile {

    private Position position;

    private boolean open = false;

    private boolean isDorWay = false;

    public Door(Position position, boolean open, boolean isDorWay) {
        this.position = position;
        this.open = open;
        this.isDorWay = isDorWay;
    }


    @Override
    public String getName() {
        if (isDorWay) {
            return "DoorWay";
        } else {
            if (open) {
                return "DoorOpen";
            } else {
                return "DoorClosed";
            }
        }
        }

        @Override
        public Position getPosition(){
            return position;
        }
    }

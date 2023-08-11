package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.Random;

public class Skeleton extends Enemies {
    private Position position;


    public Skeleton(Position position) {
        super("Skeleton", position, 2, 2);

    }


    private void moveNear() {
        Position heroPosition = Hero.getInstance().getPosition();
        int dx = heroPosition.getX() - super.getPosition().getX();
        int dy = heroPosition.getY() - super.getPosition().getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            // Movimento horizontal
            dx = dx > 0 ? 1 : -1; // Se dx for positivo, movimento para a direita, caso contrário, movimento para a esquerda
            dy = 0; // Mantém dy igual a zero para evitar o movimento diagonal
        } else {
            // Movimento vertical
            dx = 0; // Mantém dx igual a zero para evitar o movimento diagonal
            dy = dy > 0 ? 1 : -1; // Se dy for positivo, movimento para baixo, caso contrário, movimento para cima
        }

        super.setPosition(super.getPosition().plus(new Vector2D(dx, dy)));
    }

    private void moveRandom() {
        Random random = new Random();
        int direction = random.nextInt(4); // Gera um número aleatório entre 0 e 3

        int dx = 0;
        int dy = 0;

        // Define a direção baseado no valor aleatório gerado
        switch (direction) {
            case 0: // Para cima
                dy = -1;
                break;
            case 1: // Para baixo
                dy = 1;
                break;
            case 2: // Para a esquerda
                dx = -1;
                break;
            case 3: // Para a direita
                dx = 1;
                break;
        }

    }

        private int distanceBetween(Position pos1, Position pos2) {
            int distX = pos1.getX() - pos2.getX();
            int distY = pos1.getY() - pos2.getY();
            return distX + distY;
        }

    @Override
    public void movement() {
        if(RoomManager.getINSTANCE().findObstacle(super.getPosition())){
            int distance = distanceBetween(super.getPosition(), Hero.getInstance().getPosition());
            if (distance <= 3) {
                moveNear();
            } else {
                moveRandom();
            }
        }

    }

    @Override
    public String getName() {
        return "Skeleton";
    }

}

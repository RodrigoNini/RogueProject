package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room implements ImageTile {

    private Position position;
    private List<ImageTile> tiles;
    private Key key;
    private List<Adversarios> adversarios = new ArrayList<>();
    private List<Itens> itens = new ArrayList<>();
    private List<Doors> doors = new ArrayList<>();
    private String file;
    public Room(List<ImageTile> tiles) {
        this.tiles = tiles;
    }

    public Room(String file) {
        this.file = file;
    }

    public List<ImageTile> readFile(String file)  {
        List<ImageTile> tiles = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(file))){
            int y = 0;
            while(reader.hasNextLine()){
                String novaLinha = reader.nextLine();
                if(novaLinha.startsWith("#")) {
                    String[] instruções = novaLinha.split(" ");
                    if (instruções.length < 2) {
                        continue;
                    }
                        if (instruções[1].equals("0") || instruções[1].equals("1") || instruções[1].equals("2")) {
                            if (instruções.length < 4) {
                                continue;
                            }
                            if (instruções[2].equals("D")) {
                                String roomFileName = instruções[3];
                                Door doorClosed = new Door("DoorClosed", roomFileName);
                                    doors.add(doorClosed);
                                    if (instruções.length >= 5) {
                                    doorClosed.setKey(instruções[4]);
                                }
                            } else if (instruções[2].equals("E")) {
                                String roomFileName = instruções[3];
                                DoorWay doorWay = new DoorWay(roomFileName);
                                doors.add(doorWay);
                            }
                        } else if (instruções[1].equals("k")) {
                            key = new Key(instruções[2]);
                        }
                        continue;
                    }
                String[] coluna = novaLinha.split("");
                int x = 0;
                for (String tile:
                        coluna) {
                    switch (tile){
                        case "W":
                            tiles.add(new Wall(new Position(x, y)));
                            break;
                        case "0":
                            tiles.add(new Floor(new Position(x, y)));
                            doors.get(0).setPosition(new Position(x, y));
                            tiles.add(doors.get(0));
                            break;
                        case "1":
                            doors.get(1).setPosition(new Position(x, y));
                            tiles.add(doors.get(1));
                            break;
                        case "2":
                            doors.get(2).setPosition(new Position(x, y));
                            tiles.add(doors.get(2));
                            break;
                        case "k":
                            tiles.add(new Floor(new Position(x, y)));
                            tiles.add(key);
                            key.setPosition(new Position(x, y));
                            break;
                        case "S":
                            tiles.add(new Floor(new Position(x, y)));
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            tiles.add(skeleton);
                            adversarios.add(skeleton);
                            break;
                        case "H":
                            tiles.add(new Floor(new Position(x, y)));
                            Hero.getInstance().setPosition(new Position(x, y));
                            break;
                        case "B":
                            tiles.add(new Floor(new Position(x, y)));
                            Bat bat = new  Bat(new Position(x, y));
                            tiles.add(bat);
                            adversarios.add(bat);
                            break;
                        case "G":
                            tiles.add(new Floor(new Position(x, y)));
                            BadGuy badGuy= new  BadGuy(new Position(x, y));
                            tiles.add(badGuy);
                            adversarios.add(badGuy);
                            break;
                        case "m":
                            tiles.add(new Floor(new Position(x, y)));
                            Meat meat = new Meat(new Position(x, y));
                            tiles.add(meat);
                            itens.add(meat);
                            break;
                        case "s":
                            tiles.add(new Floor(new Position(x, y)));
                            Sword sword = new Sword(new Position(x, y));
                            tiles.add(sword);
                            itens.add(sword);
                            break;
                        default:
                            tiles.add(new Floor(new Position(x, y)));
                            break;
                    }
                    x++;
                }
                y++;
            }
            ImageMatrixGUI.getInstance().update();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return tiles;
    }


    public List<ImageTile> getTiles() {
        return tiles;
    }


    public ImageTile getTile(Position position) {
        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(position)) {
                return tile;
            }
        }
        return null; // Return null if no tile is found at the specified position
    }

    public List<Adversarios> getAdversarios() {
        return adversarios;
    }

    public List<Itens> getItens() {
        return itens;
    }

    public List<Doors> getDoors() {
        return doors;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return position;
    }

}

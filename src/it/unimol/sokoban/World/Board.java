package it.unimol.sokoban.World;

import it.unimol.sokoban.Player.Actor;
import it.unimol.sokoban.Player.Baggage;
import it.unimol.sokoban.Player.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;


/** Classe per la creazione di tutta l'area di gioco
 * @author Iannuccillo Antonio
 * @version 1.0
 */
public class Board extends JPanel {


    //Distanza tra i bordi della finestra e bordi del gioco
    private final int OFFSET = 30;

    // DIMENSIONE DELLE PARETI INTERNE 20X20
    private final int SPACE = 20;

    /** 4 Tipi di collisioni identificate da costanti**/
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;



    private ArrayList<Wall> walls;
    private ArrayList<Baggage> baggs;
    private ArrayList<Area> areas;

    private Player soko;

    private int widht = 0;
    private int height = 0;

    private boolean isCompleted = false;

    /** Struttura del livello del gioco
     * # corrisponde al muro
     * $ corrisponde alle pedine da spostare
     * . rappresenta il posto dove dobbiamo spostare le pedine
     * @ è il sokoban ovvero il nostro personaggio
     * \n per andare accapo e creare un nuovo muro
     * **/
    private String level
            = "    ######\n"
            + "    ##   #\n"
            + "    ##$  #\n"
            + "  ####  $##\n"
            + "  ##  $ $ #\n"
            + "#### # ## #   ######\n"
            + "##   # ## #####  ..#\n"
            + "## $      $      ..#\n"
            + "###### ### #@##  ..#\n"
            + "    ##     #########\n"
            + "    ########\n";

    public String name;

    /** Metodo per la creazione della finestra
     * Chiama il meotodo {@link #initBoard()} per inizializzare la finestra di gioco
     */
    public Board() {
        String response;
        response = JOptionPane.showInputDialog("Inserisci il tuo nome");
        this.name = response;
        initBoard();

    }

    /** Inizializza la finestra di gioco
     * Chiama il meotodo {@link #initWorld()} per inizializzare l'area di gioco
     * Chiama la classe {@link TAdapter} per gestire i movimenti dei giocatori
     */
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }


    public int getBoardWidth() {
        return this.widht;
    }


    public int getBoardHeight() {
        return this.height;
    }

    /** Metodo che associa agli oggetti del livello la varie collection
     * @return void**/
    private void initWorld() {

        walls = new ArrayList<>();
        baggs = new ArrayList<>();
        areas = new ArrayList<>();

        /** Inizializzazione della distanza delle finestra dal gioco **/
        int x = OFFSET;
        int y = OFFSET;


        Wall wall;
        Baggage baggage;
        Area area;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            switch (item) {


                case '\n':

                    y += SPACE;

                    if (this.widht < x) {
                        this.widht = x;
                    }

                    x = OFFSET;
                    break;

                    /*Creazione di un muro e aggiunto alla collection dei muri
                     Ogni volta che si incrementa un muro aumenta la grandezza della x */
                case '#':
                    wall = new Wall(x, y);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case '$':
                    baggage = new Baggage(x, y);
                    baggs.add(baggage);
                    x += SPACE;
                    break;

                case '.':
                    area = new Area(x, y);
                    areas.add(area);
                    x += SPACE;
                    break;

                case '@':
                    soko = new Player(x, y);
                    x += SPACE;
                    break;

                case ' ':
                    x += SPACE;
                    break;

                default:
                    break;
            }

            height = y;
        }

    }

    /** Metodo per disegnare i vari oggetti, creando il nuovo mondo selezionando gli oggetti
     * dalle collection
     * @param g Metodo per disegnare il mondo
     * **/
    private void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(new Color(0, 0, 0));

        g.drawString("Press R to restart", 15, 20);
        g.drawString("Player: " + this.name, 15, 50);

        ArrayList<Actor> world = new ArrayList<>();

        world.addAll(walls);
        world.addAll(areas);
        world.addAll(baggs);
        world.add(soko);


        /** Disegno ogni oggetto **/
        for (int i = 0; i < world.size(); i++) {

            Actor item = world.get(i);

            //Posizionamento del Sokoban pirncipale nello spazio vuoto
            if (item instanceof Player || item instanceof Baggage) {
                g.drawImage(item.getImage(), item.getX() + 2, item.getY() + 2, this);
            } else {
                //Posizionamento delle pedine negli spazii
                g.drawImage(item.getImage(), item.getX(), item.getY(), this);
            }

            if (isCompleted) {

                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 305, 20);

            }

        }
    }

    /** Metodo per disegnare tutti le componenti create sull'area di gioco
     * Chiama il meotodo {@link #buildWorld(Graphics)} per prendere gli oggetti creati
     * e metterli sull'area di gioco**/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        buildWorld(g);
    }
    /** Classe per gestire i movimenti del sokobane.
     * @author Iannuccillo Antonio
     * @version 1.0**/
    private class TAdapter extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {

            //Se il gioco è completato allora non si possono muovere più gli oggetti
            if (isCompleted) {
                return;
            }

            int key = e.getKeyCode();

            switch (key) {

                case KeyEvent.VK_LEFT:


                    if (checkWallCollision(soko,
                            LEFT_COLLISION)) {
                        return;
                    }

                    if (checkBagCollision(LEFT_COLLISION)) {
                        return;
                    }

                    soko.move(-SPACE, 0);

                    break;

                case KeyEvent.VK_RIGHT:

                    if (checkWallCollision(soko, RIGHT_COLLISION)) {
                        return;
                    }

                    if (checkBagCollision(RIGHT_COLLISION)) {
                        return;
                    }

                    soko.move(SPACE, 0);

                    break;

                case KeyEvent.VK_UP:

                    if (checkWallCollision(soko, TOP_COLLISION)) {
                        return;
                    }

                    if (checkBagCollision(TOP_COLLISION)) {
                        return;
                    }

                    soko.move(0, -SPACE);

                    break;

                case KeyEvent.VK_DOWN:

                    if (checkWallCollision(soko, BOTTOM_COLLISION)) {
                        return;
                    }

                    if (checkBagCollision(BOTTOM_COLLISION)) {
                        return;
                    }

                    soko.move(0, SPACE);

                    break;

                case KeyEvent.VK_R:

                    restartLevel();

                    break;

                default:
                    break;
            }
            repaint();
        }
    }
    /** Metodo per garantire che un sokoban o una pedina non superino il muro
     * @param actor oggetto che sia sokoban o pedina
     * @param type tipo di collisione
     * @see Actor per vedere i metodi per il controllo delle collisioni**/
    private boolean checkWallCollision(Actor actor, int type) {

        switch (type) {

            case LEFT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);


                    if (actor.isLeftCollision(wall)) {

                        return true;
                    }
                }

                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case TOP_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isTopCollision(wall)) {

                        return true;
                    }
                }

                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isBottomCollision(wall)) {

                        return true;
                    }
                }

                return false;

            default:
                break;
        }

        return false;
    }

    /** Una pedina può scontrarsi con un muro, con un oggetto sokoban o con un altra pedina.
    * La pedina può essere spostato solo se si scontra con un sokoban e non si scontra con un altra pedina o un muro.
    * Quando la pedina viene spostata bisogna sempre controllare se il gioco è finito**/
    private boolean checkBagCollision(int type) {

        switch (type) {

            case LEFT_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Baggage bag = baggs.get(i);

                    /** Se il sokoban si incontra con una pedina bisogna controllare se c'è un altra pedina avanti
                     * e controllare se c'è un muro**/
                    if (soko.isLeftCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isLeftCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, LEFT_COLLISION)) {
                                return true;
                            }
                        }
                        /** Pedina si trova nel blocco finale con le altre pedine**/
                        bag.move(-SPACE, 0);
                        isCompleted();
                    }
                }

                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Baggage bag = baggs.get(i);

                    if (soko.isRightCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isRightCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, RIGHT_COLLISION)) {
                                return true;
                            }
                        }

                        /** Pedina si trova nel blocco finale con le altre pedine**/
                        bag.move(SPACE, 0);
                        isCompleted();
                    }
                }
                return false;

            case TOP_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Baggage bag = baggs.get(i);

                    if (soko.isTopCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isTopCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, TOP_COLLISION)) {
                                return true;
                            }
                        }

                        bag.move(0, -SPACE);
                        isCompleted();
                    }
                }

                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Baggage bag = baggs.get(i);

                    if (soko.isBottomCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isBottomCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag,BOTTOM_COLLISION)) {

                                return true;
                            }
                        }

                        bag.move(0, SPACE);
                        isCompleted();
                    }
                }

                break;

            default:
                break;
        }

        return false;
    }

    /** Metodo che controlla se tutte le pedine sono nell'area finale
     * **/
    public void isCompleted() {

        int numberOfBags = baggs.size();
        int finishedBags = 0;

        /** Controllo che tutte le pedine siano nell'area finale **/
        for (int i = 0; i < numberOfBags; i++) {

            Baggage bag = baggs.get(i);

            for (int j = 0; j < numberOfBags; j++) {

                Area area =  areas.get(j);

                if (bag.getX() == area.getX() && bag.getY() == area.getY()) {

                    /** Contatore che somma le pedine presenti nell'area finale **/
                    finishedBags += 1;
                }
            }
        }

        /** Se il numero di pedine nell'area finale corrisponde al numero di pedine
         * create allora il gioco è concluso **/
        if (finishedBags == numberOfBags) {

            isCompleted = true;
            repaint();
        }
    }

    /** Metodo che ritorna allo stato iniziale il gioco **/
    public void restartLevel() {

        areas.clear();
        baggs.clear();
        walls.clear();

        initWorld();

        if (isCompleted) {
            isCompleted = false;
        }
    }
}

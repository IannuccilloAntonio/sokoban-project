package it.unimol.sokoban.Player;

import it.unimol.sokoban.Player.Actor;

import java.awt.Image;
import javax.swing.ImageIcon;

/**Classe che crea e gestisce una pedina
 * @author Iannuccillo Antonio
 * @version 1.0
 * **/
public class Baggage extends Actor {


    public Baggage(int x, int y) {
        super(x, y);

        initBaggage();
    }
    /**Metodo che inizializza una pedina settandone l'immagine
     * **/
    private void initBaggage() {
        ImageIcon iicon = new ImageIcon("src/resources/baggage.png");
        Image image = iicon.getImage();
        setImage(image);
    }

    /** Metodo che setta i movimenti di una pedina
     * @param x
     * @param y posizione della pedina**/
    public void move(int x, int y) {

        int dx = getX() + x;
        int dy = getY() + y;

        setX(dx);
        setY(dy);
    }
}

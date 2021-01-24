package it.unimol.sokoban.Player;

import java.awt.Image;
import javax.swing.ImageIcon;

/**Classe che crea e gestisce un sokoban
 * @author Iannuccillo Antonio
 * @version 1.0
 * **/
public class Player extends Actor {

    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    /**Metodo che inizializza il giocatore settandone l'immagine
     * **/
    private void initPlayer() {
        /** L'oggetto conterrà l'immagine del sokoban **/
        ImageIcon iicon = new ImageIcon("src/resources/sokoban.png");
        Image image = iicon.getImage();
        setImage(image);
    }

    /** Metodo che setta i movimenti di una giocatore
     * @param x
     * @param y posizione dell sokoban**/
    public void move(int x, int y) {

        /*Lo spostamento del sokoban sarà dato dalla somma della x della classe attore
         * più la x del sokoban. Di quanti passi si muove */
        int dx = getX() + x;
        int dy = getY() + y;

        setX(dx);
        setY(dy);
    }
}

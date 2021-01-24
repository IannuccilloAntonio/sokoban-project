package it.unimol.sokoban.Player;

import java.awt.Image;

/**Classe che crea e gestisce sia sokoban che pedine
 * @author Iannuccillo Antonio
 * @version 1.0
 * **/
public class Actor {

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;


    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        image = img;
    }
    /** Posizioni x e y di un attore**/
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /** Metodo per controllo della collisione verso sinistra
     * @param actor il sokoban o pedina
     * @return true se si trovano nella stessa posizione , false altrimenti**/
    public boolean isLeftCollision(Actor actor) {
        return getX() - SPACE == actor.getX() && getY() == actor.getY();
    }

    /** Metodo per controllo della collisione verso destra
     * @param actor il sokoban o pedina
     * @return true se si trovano nella stessa posizione, false altrimenti**/
    public boolean isRightCollision(Actor actor) {
        return getX() + SPACE == actor.getX() && getY() == actor.getY();
    }

    /** Metodo per controllo della collisione verso l'alto se l'attore di muove in vero negativo delle ascisse
     * e sulla stessa ordinata
     * @param actor il sokoban o pedina
     * @return true se si trovano nella stessa posizione, false altrimenti**/
    public boolean isTopCollision(Actor actor) {
        return getY() - SPACE == actor.getY() && getX() == actor.getX();
    }

    /** Metodo per controllo della collisione verso l'alto se l'attore di muove in vero positivo delle ascisse
     * e sulla stessa ordinata
     * @param actor il sokoban o pedina
     * @return true se si trovano nella stessa posizione, false altrimenti**/
    public boolean isBottomCollision(Actor actor) {
        return getY() + SPACE == actor.getY() && getX() == actor.getX();
    }
}
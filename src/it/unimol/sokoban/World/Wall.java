package it.unimol.sokoban.World;

import it.unimol.sokoban.Player.Actor;

import java.awt.Image;
import javax.swing.ImageIcon;

/**Classe che crea un muro
 * @author Iannuccillo Antonio
 * @version 1.0
 * **/
public class Wall extends Actor {



    public Wall(int x, int y) {
        super(x, y);

        initWall();
    }
    /**Metodo che inizializza una muro settandone l'immagine
     * **/
    private void initWall() {
        ImageIcon iicon = new ImageIcon("src/resources/wall.png");
        Image image = iicon.getImage();
        setImage(image);
    }
}
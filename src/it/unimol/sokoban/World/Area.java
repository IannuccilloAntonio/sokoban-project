package it.unimol.sokoban.World;

import it.unimol.sokoban.Player.Actor;

import java.awt.Image;
import javax.swing.ImageIcon;

/**Classe che crea l'area dove posizionare le pedine
 * @author Iannuccillo Antonio
 * @version 1.0
 * **/
public class Area extends Actor {

    public Area(int x, int y) {
        super(x, y);

        initArea();
    }

    /** Metodo che crea l'area dove devono posizionarsi tutte le pedine **/
    private void initArea() {
        ImageIcon iicon = new ImageIcon("src/resources/area.png");
        Image image = iicon.getImage();
        setImage(image);
    }
}

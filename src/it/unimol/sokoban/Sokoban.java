package it.unimol.sokoban;

import it.unimol.sokoban.World.Board;

import java.awt.EventQueue;
import javax.swing.*;

/**Classe principale per il gioco Sokoban
 * @author Iannuccillo Antonio
 * @version 1.0
 * **/
public class Sokoban extends JFrame {

    private final int OFFSET = 30;


    public Sokoban() {
        initUI();
    }
    /**Metodo per inizializzare la finestra di gioco**/
    private void initUI() {
        Board board = new Board();
        add(board);


        setTitle("Sokoban");

        //Grandezza della finestra di gioco
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    /**Metodo main eseguibile**/
    public static void main(String[] args) {

        /** Crea un evento (Runnable) alla fine dell'elenco degli eventi Swings
         * e viene elaborato dopo l'elaborazione di tutti gli eventi della GUI precedenti.**/
        EventQueue.invokeLater(() -> {
            Sokoban game = new Sokoban();
            game.setVisible(true);
        });
    }
}


package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public interface Interface extends ActionListener {

    void actionPerformed(ActionEvent e);

    void atualizaPts();

    void jogadaAdversario();

    void paintComponent(Graphics g);

    void startGame();
    
}

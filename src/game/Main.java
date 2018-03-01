package game;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {

    static JFrame janela = new JFrame();
    static Pontuacao pts = new Pontuacao();
   

    public static void main(String[] args) {

        //Instancia um Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Instância responsável pelas imagens
            
                //Configuracação da janela
                janela.setSize(900, 800);
                janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janela.setContentPane(pts);

                pts.setSize(janela.getSize());
                janela.setVisible(true);
                pts.setVisible(true);

            
                //responsável pela atualização gráfica
                int delay = 1000 / 60;
                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        pts.atualizaPts();
                        pts.repaint();
                    }
                };
                new Timer(delay, taskPerformer).start();
            }
        });
    }
}

package game;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Imagens {

    BufferedImage[][] cartas = new BufferedImage[4][13];
    BufferedImage fc;
    BufferedImage fd;
    BufferedImage img;

    public Imagens() {
        try {
            fc = ImageIO.read(getClass().getResource("/cartas.png"));
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    //calculo de pixels pela imagem "card"
                    cartas[i][j] = fc.getSubimage(j * 79, i * 123, 79, 123);
                }
            }
            fd = fc.getSubimage(0, 4 * 123, 79, 123);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

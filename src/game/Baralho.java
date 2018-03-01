package game;

import java.util.Random;

public class Baralho {

    private Cartas[] baralho;
    private int tm = 52;
    private int v;

    //Instancia todas as cartas do baralho
    Baralho(boolean n) {
        int c = 0;
        baralho = new Cartas[52];
        if (n == true) {
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 13; ++j) {
                    baralho[c] = new Cartas(j, i);
                    c++;
                }
            }
            tm = 52;
        }
        if (n == false) {
            tm = 0;
        }
    }
    //Construtor, seta valores 

    Baralho() {
        v = 0;
        tm = 0;
        baralho = new Cartas[52];
    }

    //Não seta valores
    Baralho(boolean n, int tMax) {
        baralho = new Cartas[tMax];
        if (n == true) {
            for (int i = 0; i < tMax; ++i) {
                baralho[i] = new Cartas();
            }
            tm = tMax;
        }
        if (n == false) {
            tm = 0;
        }
    }

    Baralho(int tMax) {
        v = 0;
        tm = 0;
        baralho = new Cartas[tMax];
    }
//Instancia todas as cartas do baralho

    public Cartas returnCarta(boolean remove) {
        if (tm == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Random random = new Random();
        int ncarta = random.nextInt(tm);
        Cartas carta;

        carta = baralho[ncarta];
        if (remove == true) {
            baralho[ncarta] = baralho[tm - 1];
            baralho[tm - 1] = null;
            tm--;
        }

        return carta;
    }

    public String toString() {
        String string = "";
        if (tm == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < tm; i++) {
            string = string + baralho[i].toString();
            if (i != tm - 1) {
                string = string + ", ";
            }
        }
        return string;
    }

    public boolean addCarta(Cartas n) {
        if (tm >= baralho.length) {
            return false;
        }
        ++tm;
        baralho[tm - 1] = n;
        v += n.getValor();
        return true;
    }

    public Cartas getCarta(int i) {
        return baralho[i];
    }

    public int tamanho() {
        return this.tm;
    }

    public int getValor() {
        return v;
    }
}

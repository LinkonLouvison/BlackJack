package game;

import java.util.Random;

public class Cartas {

    //Todos os elementos do baralho
    private int valor, face, naipe = 0;
    private String[] figura = {"Copas", "Paus", "Espadas", "Ouros"};
    private String[] face_carta = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Reis", "Rainha"};

    //atribui valores a todas as cartas
    Cartas(int n1, int n2) {
        this.face = n1;
        this.naipe = n2;
        if (n1 > 0 && n1 < 10) {
            valor = n1 + 1;
        }
        if (n1 > 9) {
            valor = 10;
        }
        if (n1 == 0) {
            valor = 1;
        }

    }
//atribui valores a todas as cartas

    Cartas() {
        Random random = new Random();
        face = random.nextInt(13);
        naipe = random.nextInt(4);
        if (face > 0 && face < 10) {
            valor = face + 1;
        }
        if (face > 9) {
            valor = 10;
        }
        if (face == 0) {
            valor = 1;
        }
    }

    public String toString() {
        return face_carta[face] + " of " + figura[naipe];
    }

    public int geto_n() {
        return face;
    }

    public int getNaipe() {
        return naipe;
    }

    public int getValor() {
        return valor;
    }
}

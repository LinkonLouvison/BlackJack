package game;

//Classe responsável pelo dinheiro do jogador
public class Jogador {

    private int dinheiro;

    Jogador() {

    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Jogador(int dinheiro) {
        this.dinheiro = dinheiro;
    }

}

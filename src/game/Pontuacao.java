package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public final class Pontuacao extends JPanel implements Interface {

    int dificuldade = 1;
    JLabel info;
    public JButton botao1, botao2, botao3, botao4, botao5; //botoes hit e parar
    public JRadioButton d1, d2, d3, d4;
    Baralho bar, ca, co; // cartas, cartas do adversario e baralho
    Boolean aguardar;// aguardar joagada
    Timer tmp;// tempo
    Imagens imagem;
    JButton[] bA;// vetor botoes cartas
    Jogador jogador = new Jogador();
    int aposta = 10;

    public Pontuacao() {

        info = new JLabel("Programação Orientada a Objetos");

        //Jogador iniciar com 100
        jogador.setDinheiro(100);

        // info.setFont(new Font("Arial",Font.BOLD.86));
        //botoes de dificuldade
        d1 = new JRadioButton("Muito Fácil", true);
        d2 = new JRadioButton("Fácil");
        d3 = new JRadioButton("Médio");
        d4 = new JRadioButton("Difícil");

        ButtonGroup bg = new ButtonGroup();

        bg.add(d1);
        bg.add(d2);
        bg.add(d3);
        bg.add(d4);
        // controle de botoes
        botao1 = new JButton("PARAR");
        botao2 = new JButton("HIT");
        botao4 = new JButton("APOSTAR");
        botao5 = new JButton("RENDER-SE");
        //adiciona os botoes no panel
        add(botao2);
        add(botao1);
        add(botao4);
        add(botao5);
        add(d1);
        add(d2);
        add(d3);
        add(d4);

        // adiciona os botoes no ActionListener
        // add(info,true);
        botao1.addActionListener(this);
        botao2.addActionListener(this);
        botao4.addActionListener(this);
        botao5.addActionListener(this);
        d1.addActionListener(this);
        d2.addActionListener(this);
        d3.addActionListener(this);
        d4.addActionListener(this);

        bA = new JButton[4];
        imagem = new Imagens();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL u = classLoader.getResource("");
        System.out.println(u);
        startGame();
    }

    @Override
    //Printa os componentes no Panel e imprime o controle de pontos e dinheiro do jogador
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        int i;
        super.paintComponent(g);
        for (i = 0; i < ca.tamanho(); i++) {
            if (ca.getCarta(i).geto_n() == 0) {
            }
            g.drawImage(imagem.cartas[ca.getCarta(i).getNaipe()][ca.getCarta(i).geto_n()], i * 85, 36, this);
            g.drawString("PONTOS: " + ca.getValor(), 600, 100);
            g.drawString("DINHEIRO: R$: " + jogador.getDinheiro(), 600, 150);
            g.drawString("VALOR DA MÃO: " + aposta, 600, 200);

        }
        if (aguardar == false) {
            g.drawImage(imagem.fd, 0, 214, this);
            i = 1;
        } else {
            i = 0;
        }
        for (; i < co.tamanho(); i++) {
            g.drawImage(imagem.cartas[co.getCarta(i).getNaipe()][co.getCarta(i).geto_n()], i * 85, 214, this);
        }
    }

    @Override
    // Atualiza os pontos do jogador e define a situação dele
    public void atualizaPts() {
        int n;
        this.repaint();

        // jogador desistiu de jogar
        if (aposta == 5) {
            JOptionPane.showMessageDialog(Main.janela, "VOCÊ DESISTIU\n");
            n = jogador.getDinheiro() - aposta;
            jogador.setDinheiro(n);
            aposta = 10;
            startGame();
        }
        if (ca.getValor() > 21 || (co.getValor() < 22 && aguardar == true && co.getValor() >= ca.getValor())) {
            JOptionPane.showMessageDialog(Main.janela, "VOCÊ PERDEU\n");
            n = jogador.getDinheiro() - aposta;
            jogador.setDinheiro(n);
            aposta = 10;
            startGame();

        } else if (aguardar == true || co.getValor() > 21) {
            tmp.cancel();
            JOptionPane.showMessageDialog(Main.janela, "VOCÊ GANHOU");
            n = jogador.getDinheiro() + aposta;
            jogador.setDinheiro(n);
            aposta = 10;
            startGame();
        }
        if (jogador.getDinheiro() <= 0) {
            JOptionPane.showMessageDialog(Main.janela, "Você Faliu! De espaço para outro jogador\n");
            jogador.setDinheiro(100);
            startGame();
        }
    }

    //metodo responsável pelas bunções dos botoes
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botao2 && aguardar == false) {
            ca.addCarta(bar.returnCarta(true));
        }
        if (e.getSource() == botao1) {
            jogadaAdversario();
            aguardar = true;
        }
        if (e.getSource() == d1) {
            dificuldade = 1;
        }
        if (e.getSource() == d2) {
            dificuldade = 2;
        }
        if (e.getSource() == d3) {
            dificuldade = 3;
        }
        if (e.getSource() == d4) {
            dificuldade = 4;
        }
        if (e.getSource() == botao4) {
            aposta = 20;
        }
        if (e.getSource() == botao5) {
            aposta = 5;

        }
    }

    @Override
    public void jogadaAdversario() {
        TimerTask task;
        task = new TimerTask() {
            public void run() {
                if (co.getValor() < 22 || co.getValor() <= ca.getValor()) {
                    co.addCarta(bar.returnCarta(true));
                }
            }
        };
        tmp = new Timer();
        tmp.schedule(task, 400, 400);
    }

    @Override
    //método responsável pela dificuldade do jogo
    public void startGame() {

        aguardar = false;

        if (dificuldade == 1) {
            bar = new Baralho(true, 52 * 1);
        }
        if (dificuldade == 2) {
            bar = new Baralho(true, 52 * 2);
        }
        if (dificuldade == 3) {
            bar = new Baralho(true, 52 * 3);
        }
        if (dificuldade == 4) {
            bar = new Baralho(true, 52 * 4);
        }

        co = new Baralho(dificuldade * 26);
        ca = new Baralho(dificuldade * 26);

        ca.addCarta(bar.returnCarta(true));
        ca.addCarta(bar.returnCarta(true));

        co.addCarta(bar.returnCarta(true));
        co.addCarta(bar.returnCarta(true));

    }

}

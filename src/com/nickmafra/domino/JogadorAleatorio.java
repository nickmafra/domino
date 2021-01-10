package com.nickmafra.domino;

import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class JogadorAleatorio extends Jogador {

    private static Random random = new Random();

    public JogadorAleatorio(String nome) {
        super(nome);
    }

    @Override
    public void decidirDominoSaida(Mesa mesa) {
        int i = random.nextInt(dominos.size());
        Domino domino = dominos.get(i);
        mesa.jogarNaDireita(this, domino);
    }

    @Override
    public void decidirJogada(Mesa mesa, List<Domino> dominosJogaveis) {
        int i = random.nextInt(dominosJogaveis.size());
        Domino domino = dominosJogaveis.get(i);

        if (random.nextBoolean()) domino.girar();

        if (!mesa.podeJogarNaEsquerda(domino)) {
            if (!mesa.podeJogarNaDireita(domino)) {
                out.println("ERRO: Jogáveis: " + Domino.listToString(dominosJogaveis));
                throw new IllegalStateException("Não jogável? " + domino);
            }
            mesa.jogarNaDireita(this, domino);
        } else if (!mesa.podeJogarNaDireita(domino))
            mesa.jogarNaEsquerda(this, domino);
        else if (random.nextBoolean())
            mesa.jogarNaEsquerda(this, domino);
        else
            mesa.jogarNaDireita(this, domino);
    }
}

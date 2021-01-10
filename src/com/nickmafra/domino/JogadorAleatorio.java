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
        Domino domino = dominos.remove(i);
        mesa.jogarNaDireita(domino);
    }

    @Override
    public void decidirJogada(Mesa mesa, List<Domino> dominosJogaveis) {
        int i = random.nextInt(dominosJogaveis.size());
        Domino domino = dominosJogaveis.get(i);
        dominos.remove(domino);

        if (random.nextBoolean()) domino.girar();

        if (!mesa.podeJogarNaEsquerda(domino)) {
            if (!mesa.podeJogarNaDireita(domino)) {
                out.println("ERRO: Jogáveis: " + Domino.listToString(dominosJogaveis));
                throw new IllegalStateException("Não jogável? " + domino);
            }
            mesa.jogarNaDireita(domino);
        } else if (!mesa.podeJogarNaDireita(domino))
            mesa.jogarNaEsquerda(domino);
        else if (random.nextBoolean())
            mesa.jogarNaEsquerda(domino);
        else
            mesa.jogarNaDireita(domino);
    }
}

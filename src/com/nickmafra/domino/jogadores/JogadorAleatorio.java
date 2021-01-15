package com.nickmafra.domino.jogadores;

import com.nickmafra.domino.Domino;
import com.nickmafra.domino.Jogada;
import com.nickmafra.domino.Jogador;
import com.nickmafra.domino.Mesa;

import java.util.List;
import java.util.Random;

public class JogadorAleatorio extends Jogador {

    private static final Random random = new Random();

    public JogadorAleatorio(String nome, boolean exibirMao) {
        super(nome, exibirMao);
    }

    public JogadorAleatorio(boolean exibirMao) {
        super("JogadorAleatorio", exibirMao);
    }

    @Override
    public void decidirDominoSaida(Mesa mesa) {
        int i = random.nextInt(dominos.size());
        Domino domino = dominos.get(i);
        mesa.jogarNaDireita(this, domino);
    }

    @Override
    public void decidirJogada(Mesa mesa, List<Jogada> jogadasPossiveis) {
        int i = random.nextInt(jogadasPossiveis.size());
        Jogada jogada = jogadasPossiveis.get(i);
        mesa.realizarJogada(this, jogada);
    }
}

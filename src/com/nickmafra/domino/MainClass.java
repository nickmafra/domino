package com.nickmafra.domino;

import com.nickmafra.domino.jogadores.JogaAQueTemMais;
import com.nickmafra.domino.jogadores.JogadorAleatorio;

import static java.lang.System.out;

public class MainClass {

    public static void main(String[] args) {
        int qtPartidas = 1000;

        Jogo jogo = new Jogo();
        jogo.jogadores.add(new JogaAQueTemMais("JogaAQueTemMais"));
        jogo.jogadores.add(new JogadorAleatorio("2"));
        jogo.jogadores.add(new JogadorAleatorio("3"));
        jogo.jogadores.add(new JogadorAleatorio("4"));

        for (int i = 0; i < qtPartidas; i++) {
            jogo.prepararJogo();
            jogo.jogar();
            jogo.primeiroJogador = (jogo.primeiroJogador + 1) % 4;
        }

        out.println("\n\n\nFim de todas as partidas.");
        for (Jogador jogador : jogo.jogadores) {
            out.println("Jogador " + jogador + " teve " + jogador.vitorias + " vitórias.");
        }
    }
}

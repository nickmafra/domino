package com.nickmafra.domino;

import com.nickmafra.domino.jogadores.JogaAQueTemMais;
import com.nickmafra.domino.jogadores.JogadorAleatorio;
import com.nickmafra.domino.jogadores.JogadorHumano;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.exibeDados = true;

        jogo.setJogadores(Arrays.asList(
                new JogaAQueTemMais(false),
                new JogadorHumano(),
                new JogadorAleatorio("Fulano", false)
        ));

        jogo.jogarVariasVezes(1);
    }
}

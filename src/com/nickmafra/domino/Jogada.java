package com.nickmafra.domino;

import java.util.List;
import java.util.stream.Collectors;

public class Jogada {

    // -1 esquerda, 1 direita
    public final int lado;
    public final Domino domino;

    public Jogada(int lado, Domino domino) {
        this.lado = lado;
        this.domino = domino;
    }

    public Jogada(Domino domino) {
        this.lado = 0;
        this.domino = domino;
    }

    @Override
    public String toString() {
        if (lado < 0) {
            return "na esquerda jogar " + domino;
        } else if (lado > 0) {
            return "na direita jogar " + domino;
        } else {
            return "jogar " + domino;
        }
    }

    public static String listToString(List<Jogada> jogadas) {
        if (jogadas.isEmpty()) {
            return "vazia";
        }
        StringBuilder builder = new StringBuilder();
        for (Jogada jogada : jogadas) {
            builder.append(jogada);
            builder.append(", ");
        }
        return builder.toString();
    }

    public static List<Jogada> toJogadas(List<Domino> dominos) {
        return dominos.stream().map(Jogada::new).collect(Collectors.toList());
    }
}

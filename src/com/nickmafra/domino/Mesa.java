package com.nickmafra.domino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mesa {
    public final List<Domino> dominos = new ArrayList<>();
    public final List<Domino> dominosCompra = new ArrayList<>();

    public Domino pontaEsquerda() {
        return dominos.get(0);
    }

    public Domino pontaDireita() {
        return dominos.get(dominos.size() - 1);
    }

    public List<Domino> dominosDaPonta() {
        if (dominos.isEmpty()) return Collections.emptyList();
        if (dominos.size() == 1) return Collections.singletonList(dominos.get(0));

        return Arrays.asList(pontaEsquerda(), pontaDireita());
    }

    public boolean podeJogarNaEsquerda(Domino domino) {
        if (dominos.isEmpty()) {
            return true;
        }
        Domino ponta = pontaEsquerda();
        if (domino.getDigitoDireita() == ponta.getDigitoEsquerda())
            return true;
        domino.flip();
        return domino.getDigitoDireita() == ponta.getDigitoEsquerda();
    }

    public boolean podeJogarNaDireita(Domino domino) {
        if (dominos.isEmpty()) {
            return true;
        }
        Domino ponta = pontaDireita();
        if (domino.getDigitoEsquerda() == ponta.getDigitoDireita())
            return true;
        domino.flip();
        return domino.getDigitoEsquerda() == ponta.getDigitoDireita();
    }

    public void jogarNaEsquerda(Jogador jogador, Domino domino) {
        if (!podeJogarNaEsquerda(domino))
            throw new IllegalArgumentException(domino.toString());

        jogador.dominos.remove(domino);
        dominos.add(0, domino);
    }

    public void jogarNaDireita(Jogador jogador, Domino domino) {
        if (!podeJogarNaDireita(domino))
            throw new IllegalArgumentException(domino.toString());

        jogador.dominos.remove(domino);
        dominos.add(domino);
    }

    public void realizarJogada(Jogador jogador, Jogada jogada) {
        if (jogada.lado < 0) {
            jogarNaEsquerda(jogador, jogada.domino);
        } else {
            jogarNaDireita(jogador, jogada.domino);
        }
    }

    @Override
    public String toString() {
        return Domino.listToString(dominos);
    }
}

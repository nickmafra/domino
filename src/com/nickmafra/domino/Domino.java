package com.nickmafra.domino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Domino {

    int digitoEsquerda;
    int digitoDireita;

    public static List<Domino> gerarConjunto(int nBaseDomino) {
        int qtDominos = nBaseDomino * (nBaseDomino + 1) / 2;
        List<Domino> dominos = new ArrayList<>(qtDominos);
        for (int i = 0; i < nBaseDomino; i++) {
            for (int j = i; j < nBaseDomino; j++) {
                Domino domino = new Domino();
                domino.digitoEsquerda = i;
                domino.digitoDireita = j;
                dominos.add(domino);
            }
        }
        return dominos;
    }

    public static void embaralhar(List<Domino> dominos) {
        Collections.shuffle(dominos);
    }

    public int getDigitoEsquerda() {
        return digitoEsquerda;
    }

    public int getDigitoDireita() {
        return digitoDireita;
    }

    public boolean digitosIguais() {
        return digitoEsquerda == digitoDireita;
    }

    public boolean possuiDigito(int digito) {
        return digitoEsquerda == digito || digitoDireita == digito;
    }

    public boolean possuiDigitoDaPontaEmComum(Domino domino) {
        return domino.possuiDigito(digitoEsquerda) || domino.possuiDigito(digitoDireita);
    }

    public void girar() {
        int esquerdaBkp = digitoEsquerda;
        digitoEsquerda = digitoDireita;
        digitoDireita = esquerdaBkp;
    }

    @Override
    public String toString() {
        return "[" + digitoEsquerda + "|" + digitoDireita + "]";
    }

    public static String listToString(List<Domino> dominos) {
        if (dominos.isEmpty()) {
            return "vazia";
        }
        StringBuilder builder = new StringBuilder();
        for (Domino domino : dominos) {
            builder.append(domino);
            builder.append(" ");
        }
        return builder.toString();
    }

    public int pontos() {
        return digitoEsquerda + digitoDireita;
    }
}

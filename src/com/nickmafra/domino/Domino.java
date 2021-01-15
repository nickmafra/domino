package com.nickmafra.domino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Domino {

    public static final int N_BASE = 7;

    int digitoMenor;
    int digitoMaior;

    boolean flipped;

    public static List<Domino> gerarConjunto() {
        List<Domino> dominos = new ArrayList<>();
        for (int i = 0; i < N_BASE; i++) {
            for (int j = i; j < N_BASE; j++) {
                Domino domino = new Domino();
                domino.digitoMenor = i;
                domino.digitoMaior = j;
                dominos.add(domino);
            }
        }
        return dominos;
    }

    public static void embaralhar(List<Domino> dominos) {
        Collections.shuffle(dominos);
    }

    public int getDigitoEsquerda() {
        return flipped ? digitoMaior : digitoMenor;
    }

    public int getDigitoDireita() {
        return flipped ? digitoMenor : digitoMaior;
    }

    public boolean digitosIguais() {
        return digitoMenor == digitoMaior;
    }

    public boolean possuiDigito(int digito) {
        return digitoMenor == digito || digitoMaior == digito;
    }

    public boolean possuiDigitoDaPontaEmComum(Domino domino) {
        return domino.possuiDigito(digitoMenor) || domino.possuiDigito(digitoMaior);
    }

    public void flip() {
        flipped = !flipped;
    }

    @Override
    public String toString() {
        return "[" + getDigitoEsquerda() + "|" + getDigitoDireita() + "]";
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
        return digitoMenor + digitoMaior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domino domino = (Domino) o;
        return digitoMenor == domino.digitoMenor &&
                digitoMaior == domino.digitoMaior;
    }

    @Override
    public int hashCode() {
        return Objects.hash(digitoMenor, digitoMaior);
    }
}

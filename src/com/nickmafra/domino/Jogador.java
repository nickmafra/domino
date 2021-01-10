package com.nickmafra.domino;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public abstract class Jogador {

    public String nome = "Jogador#" + this.hashCode();
    public final List<Domino> dominos = new ArrayList<>();
    public int vitorias = 0;

    public Jogador() {
    }

    public Jogador(String nome) {
        this.nome = nome;
    }

    public void reiniciar() {
        dominos.clear();
    }

    public static void distribuir(List<Domino> dominos, List<Jogador> jogadores) {
        int qtPecasPorJogador = dominos.size() / jogadores.size();
        for (Jogador jogador : jogadores) {
            for (int i = 0; i < qtPecasPorJogador; i++) {
                jogador.dominos.add(dominos.remove(0));
            }
        }
    }

    public List<Domino> dominosJogaveis(Mesa mesa) {
        List<Domino> dominosJogaveis = new ArrayList<>();
        for (Domino domino : dominos) {
            if (mesa.podeJogarNaEsquerda(domino) || mesa.podeJogarNaDireita(domino)) {
                dominosJogaveis.add(domino);
            }
        }
        return dominosJogaveis;
    }

    public boolean jogar(Mesa mesa) {
        List<Domino> dominosJogaveis;
        while (true) {
            dominosJogaveis = dominosJogaveis(mesa);
            if (!dominosJogaveis.isEmpty()) {
                break;
            } else if (mesa.dominosCompra.isEmpty()) {
                out.println("Jogador " + this + " não possui pedra. Passando...");
                return false;
            } else {
                out.println("Jogador " + this + " não possui pedra. Comprando...");
                dominos.add(mesa.dominosCompra.remove(0));
            }
        }

        decidirJogada(mesa, dominosJogaveis);
        return true;
    }

    public abstract void decidirDominoSaida(Mesa mesa);

    public abstract void decidirJogada(Mesa mesa, List<Domino> dominosJogaveis);

    public boolean bateu() {
        return dominos.isEmpty();
    }

    public int contarPontos() {
        int total = 0;
        for (Domino domino : dominos)
            total += domino.pontos();
        return total;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String exibirMao() {
        return Domino.listToString(dominos);
    }
}

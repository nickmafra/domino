package com.nickmafra.domino;

import java.util.ArrayList;
import java.util.List;

public abstract class Jogador {

    public String nome = "Jogador#" + this.hashCode();
    public final List<Domino> dominos = new ArrayList<>();
    public int vitorias = 0;
    public boolean exibeMao;

    public Jogador(String nome, boolean exibeMao) {
        this.nome = nome;
        this.exibeMao = exibeMao;
    }

    public Jogador(boolean exibeMao) {
        this.exibeMao = exibeMao;
    }

    public void reiniciar() {
        dominos.clear();
    }

    public static void distribuir(List<Domino> dominos, List<Jogador> jogadores) {
        int qtPecasPorJogador = Domino.N_BASE;
        for (Jogador jogador : jogadores) {
            for (int i = 0; i < qtPecasPorJogador; i++) {
                jogador.dominos.add(dominos.remove(0));
            }
        }
    }

    public List<Jogada> getJogadasPossiveis(Mesa mesa) {
        List<Jogada> jogadasPossiveis = new ArrayList<>();
        for (Domino domino : dominos) {
            if (mesa.podeJogarNaEsquerda(domino)) {
                jogadasPossiveis.add(new Jogada(-1, domino));
            }
        }
        for (Domino domino : dominos) {
            if (mesa.podeJogarNaDireita(domino)) {
                jogadasPossiveis.add(new Jogada(1, domino));
            }
        }
        return jogadasPossiveis;
    }

    public boolean jogar(Mesa mesa) {
        List<Jogada> jogadasPossiveis = getJogadasPossiveis(mesa);
        if (jogadasPossiveis.isEmpty()) {
            return false;
        }

        decidirJogada(mesa, jogadasPossiveis);
        return true;
    }

    public abstract void decidirDominoSaida(Mesa mesa);

    public abstract void decidirJogada(Mesa mesa, List<Jogada> jogadasPossiveis);

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

    public String maoToString() {
        return Domino.listToString(dominos);
    }

    public String exibirMao() {
        return "Mão do jogador " + nome + ": " + maoToString();
    }
}

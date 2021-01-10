package com.nickmafra.domino;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Jogo {
    public static final int N_BASE_DOMINO = 7;

    public final List<Jogador> jogadores = new ArrayList<>(4);

    private List<Domino> dominos;
    private Mesa mesa;
    private int vez;
    private int passes;
    public int primeiroJogador;

    public void prepararJogo() {
        for (Jogador jogador : jogadores)
            jogador.dominos.clear();

        dominos = Domino.gerarConjunto(N_BASE_DOMINO);
        mesa = new Mesa();
        vez = 0;
        passes = 0;
    }

    public Jogador jogar() {
        Domino.embaralhar(dominos);
        Jogador.distribuir(dominos, jogadores);

        mesa.dominosCompra.addAll(dominos);

        Jogador vencedor;
        do {
            vencedor = proximo();
            vez++;
            if (vencedor == null && passes == jogadores.size()) {
                out.println("O jogo fechou!");
                vencedor = vencedorPorMenosPontos();
                out.println("Jogador " + vencedor + " venceu por menos pontos.");
            }
        } while (vencedor == null);

        out.println("\nMesa final: " + mesa);
        for (Jogador j : jogadores)
            out.println("Mão final do jogador " + j + ": " + j.exibirMao());

        vencedor.vitorias++;
        return vencedor;
    }

    public Jogador proximo() {
        out.println("Mesa: " + mesa);
        Jogador jogador = jogadores.get((primeiroJogador + vez) % jogadores.size());
        out.println("Mão do jogador " + jogador + ": " + jogador.exibirMao());

        if (vez == 0) {
            jogador.decidirDominoSaida(mesa);
            return null; // não tem como ganhar na primeira jogada kkk
        }

        if (jogador.jogar(mesa)) {
            passes = 0;
            if (jogador.bateu()) {
                out.println("Jogador " + jogador + " venceu.");
                return jogador;
            }
            return null;
        }
        // TODO fazer compra de peça
        boolean passou = !jogador.jogar(mesa);
        if (passou) {
            passes++;
        } else {
        }
        return null;
    }

    public Jogador vencedorPorMenosPontos() {
        Jogador vencedor = null;
        int pontosVencedor = Integer.MAX_VALUE;
        for (Jogador jogador : jogadores) {
            int pontos = jogador.contarPontos();
            if (pontos < pontosVencedor) {
                pontosVencedor = pontos;
                vencedor = jogador;
            }
        }
        return vencedor;
    }
}

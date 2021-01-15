package com.nickmafra.domino;

import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class Jogo {

    private static final Random random = new Random();

    private List<Jogador> jogadores;

    private List<Domino> dominos;
    private Mesa mesa;
    private int vez;
    private int passes;
    private int primeiroJogador;

    public boolean exibeDados = true;

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        sortearPrimeiroJogador();
    }

    public void prepararJogo() {
        for (Jogador jogador : jogadores)
            jogador.reiniciar();

        dominos = Domino.gerarConjunto();
        mesa = new Mesa();
        vez = 0;
        passes = 0;
    }

    private void sortearPrimeiroJogador() {
        primeiroJogador = random.nextInt(jogadores.size());
    }

    private void girarPrimeiroJogador() {
        primeiroJogador = (primeiroJogador + 1) % jogadores.size();
    }

    public Jogador jogar() {
        if (exibeDados)
            out.println("\nIniciando um novo jogo.");
        Domino.embaralhar(dominos);
        Jogador.distribuir(dominos, jogadores);

        mesa.dominosCompra.addAll(dominos);

        Jogador vencedor;
        do {
            vencedor = proximo();
            vez++;
            if (vencedor == null && passes == jogadores.size()) {
                vencedor = vencedorPorMenosPontos();
                if (exibeDados) {
                    out.println("O jogo fechou!");
                    out.println("Jogador " + vencedor + " venceu por menos pontos.");
                }
            }
        } while (vencedor == null);

        if (exibeDados) {
            out.println("\nMesa final: " + mesa);
            for (Jogador j : jogadores)
                out.println("Mão final do jogador " + j + ": " + j.maoToString());
        }

        vencedor.vitorias++;
        return vencedor;
    }

    public Jogador proximo() {

        Jogador jogador = jogadores.get((primeiroJogador + vez) % jogadores.size());
        if (exibeDados) {
            out.println("\n\nMesa: " + mesa);
            out.println("\nVez do jogador " + jogador);
            if (jogador.exibeMao)
                out.println(jogador.exibirMao());
        }

        if (vez == 0) {
            jogador.decidirDominoSaida(mesa);
            return null; // não tem como ganhar na primeira jogada kkk
        }

        boolean conseguiuJogar;
        do {
            conseguiuJogar = jogador.jogar(mesa);
            if (!conseguiuJogar) {
                if (exibeDados)
                    out.println("Jogador " + jogador.nome + " não possui pedra");

                if (mesa.dominosCompra.isEmpty()) {
                    if (exibeDados)
                        out.println("Jogador " + jogador.nome + " passou");
                    break;
                } else {
                    jogador.dominos.add(mesa.dominosCompra.remove(0));
                    if (exibeDados)
                        out.println("Jogador " + jogador.nome + " comprou");
                    if (exibeDados && jogador.exibeMao)
                        out.println(jogador.exibirMao());
                }
            }
        } while (!conseguiuJogar);

        if (conseguiuJogar) {
            passes = 0;
            if (jogador.bateu()) {
                if (exibeDados)
                    out.println("Jogador " + jogador + " venceu.");
                return jogador;
            }
        } else {
            passes++;
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

    public void prepararAndJogar() {
        prepararJogo();
        jogar();
    }

    public void jogarVariasVezes(int qtPartidas) {
        sortearPrimeiroJogador();
        for (int i = 0; i < qtPartidas; i++) {
            prepararAndJogar();
            girarPrimeiroJogador();
        }

        out.println("\n\n\nFim de todas as partidas.");
        for (Jogador jogador : jogadores) {
            out.println("Jogador " + jogador + " teve " + jogador.vitorias + " vitórias.");
        }
    }
}

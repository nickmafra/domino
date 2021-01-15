package com.nickmafra.domino.jogadores;

import com.nickmafra.domino.Domino;
import com.nickmafra.domino.Jogada;
import com.nickmafra.domino.Jogador;
import com.nickmafra.domino.Mesa;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class JogadorHumano extends Jogador {

    private final Scanner scanner = new Scanner(in);

    public JogadorHumano() {
        super("Você", true);
    }

    @Override
    public void decidirDominoSaida(Mesa mesa) {
        Jogada jogada = decidirJogada(Jogada.toJogadas(dominos));
        mesa.realizarJogada(this, jogada);
    }

    @Override
    public void decidirJogada(Mesa mesa, List<Jogada> jogadasPossiveis) {
        Jogada jogada = decidirJogada(jogadasPossiveis);
        mesa.realizarJogada(this, jogada);
    }

    public Domino decidirDomino(List<Domino> dominosPossiveis) {
        return dominosPossiveis.get(decidirOpcoes(Domino.listToString(dominosPossiveis), dominosPossiveis.size()));
    }

    public Jogada decidirJogada(List<Jogada> jogadasPossiveis) {
        return jogadasPossiveis.get(decidirOpcoes(Jogada.listToString(jogadasPossiveis), jogadasPossiveis.size()));
    }

    public int decidirOpcoes(String textoOpcoes, int range) {
        while (true) {
            out.println("Suas opções são: " + textoOpcoes);
            out.print("Decida sua opção (0 a " + (range - 1) + "): ");
            try {
                int i = Integer.parseInt(scanner.nextLine());
                if (0 <= i && i < range) {
                    return i;
                } else {
                    out.println("Valor fora do limite aceito.");
                }
            } catch (Exception e) {
                out.println("Valor inválido.");
            }
        }
    }

    @Override
    public String exibirMao() {
        return "Sua mão: " + maoToString();
    }
}

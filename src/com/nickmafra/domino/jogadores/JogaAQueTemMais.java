package com.nickmafra.domino.jogadores;

import com.nickmafra.domino.Domino;
import com.nickmafra.domino.Jogada;
import com.nickmafra.domino.Mesa;

import java.util.List;

public class JogaAQueTemMais extends JogadorAleatorio {

    public JogaAQueTemMais(String nome, boolean exibirMao) {
        super(nome, exibirMao);
    }

    public JogaAQueTemMais(boolean exibirMao) {
        super("JogaAQueTemMais", exibirMao);
    }

    public Jogada temMais(List<Jogada> jogadasPossiveis) {
        int[] pontoPorDigito = new int[Domino.N_BASE];
        for (int digito = 0; digito < Domino.N_BASE; digito++) {
            for (Jogada jogada : jogadasPossiveis)
                if (jogada.domino.possuiDigito(digito))
                    pontoPorDigito[digito]++;
        }
        Jogada jogadaVencedora = null;
        int pontoVencedor = -1;
        for (Jogada jogada : jogadasPossiveis) {
            int ocorrencia = Math.min(
                    pontoPorDigito[jogada.domino.getDigitoDireita()],
                    pontoPorDigito[jogada.domino.getDigitoEsquerda()]
            );
            if (ocorrencia > pontoVencedor) {
                jogadaVencedora = jogada;
                pontoVencedor = ocorrencia;
            }
        }
        return jogadaVencedora;
    }

    @Override
    public void decidirDominoSaida(Mesa mesa) {
        super.decidirDominoSaida(mesa);
    }

    @Override
    public void decidirJogada(Mesa mesa, List<Jogada> JogadasPossiveis) {
        Jogada jogada = temMais(JogadasPossiveis);
        if (jogada == null) {
            super.decidirJogada(mesa, JogadasPossiveis);
        } else {
            mesa.realizarJogada(this, jogada);
        }
    }
}

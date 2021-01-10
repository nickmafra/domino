package com.nickmafra.domino.jogadores;

import com.nickmafra.domino.Domino;
import com.nickmafra.domino.Jogador;
import com.nickmafra.domino.Jogo;
import com.nickmafra.domino.Mesa;

import java.util.List;

public class JogaAQueTemMais extends JogadorAleatorio {

    public JogaAQueTemMais(String nome) {
        super(nome);
    }

    public Domino temMais(List<Domino> dominosJogaveis) {
        int[] pontoPorDigito = new int[Jogo.N_BASE_DOMINO];
        for (int digito = 0; digito < Jogo.N_BASE_DOMINO; digito++) {
            for (Domino domino : dominosJogaveis)
                if (domino.possuiDigito(digito))
                    pontoPorDigito[digito]++;
        }
        Domino dominoVencedor = null;
        int pontoVencedor = -1;
        for (Domino domino : dominosJogaveis) {
            int ocorrencia = Math.min(
                    pontoPorDigito[domino.getDigitoDireita()],
                    pontoPorDigito[domino.getDigitoEsquerda()]
            );
            if (ocorrencia > pontoVencedor) {
                dominoVencedor = domino;
                pontoVencedor = ocorrencia;
            }
        }
        return dominoVencedor;
    }

    @Override
    public void decidirDominoSaida(Mesa mesa) {
        super.decidirDominoSaida(mesa);
    }

    @Override
    public void decidirJogada(Mesa mesa, List<Domino> dominosJogaveis) {
        Domino domino = temMais(dominosJogaveis);
        if (domino == null) {
            super.decidirJogada(mesa, dominosJogaveis);
        } else {
            super.jogarDominoOndeDer(mesa, domino);
        }
    }
}

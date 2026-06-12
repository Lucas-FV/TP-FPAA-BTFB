package solver;

import enums.Simbolo;
import models.Tabuleiro;

public class ForcaBrutaSolver {
    private long estadosGerados;

    public boolean resolver(Tabuleiro tabuleiro) {
        estadosGerados = 0;

        int totalVazias = contarCelulasVazias(tabuleiro);
        int[][] celulasVazias = new int[totalVazias][2];
        preencherCelulasVazias(tabuleiro, celulasVazias);

        return gerarEstados(tabuleiro, celulasVazias, 0);
    }

    public long getEstadosGerados() {
        return estadosGerados;
    }

    private boolean gerarEstados(Tabuleiro tabuleiro, int[][] celulasVazias, int indice) {
        if (indice == celulasVazias.length) {
            estadosGerados++;
            return ValidadorRegras.isCompleto(tabuleiro) && ValidadorRegras.isValido(tabuleiro);
        }

        int linha = celulasVazias[indice][0];
        int coluna = celulasVazias[indice][1];

        tabuleiro.setSimbolo(linha, coluna, Simbolo.SOL);
        if (gerarEstados(tabuleiro, celulasVazias, indice + 1)) {
            return true;
        }

        tabuleiro.setSimbolo(linha, coluna, Simbolo.LUA);
        if (gerarEstados(tabuleiro, celulasVazias, indice + 1)) {
            return true;
        }

        tabuleiro.setSimbolo(linha, coluna, Simbolo.VAZIO);
        return false;
    }

    private int contarCelulasVazias(Tabuleiro tabuleiro) {
        int tamanho = tabuleiro.getTamanho();
        Simbolo[][] grade = tabuleiro.getGrade();
        int total = 0;

        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                if (grade[linha][coluna] == Simbolo.VAZIO) {
                    total++;
                }
            }
        }

        return total;
    }

    private void preencherCelulasVazias(Tabuleiro tabuleiro, int[][] celulasVazias) {
        int tamanho = tabuleiro.getTamanho();
        Simbolo[][] grade = tabuleiro.getGrade();
        int indice = 0;

        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                if (grade[linha][coluna] == Simbolo.VAZIO) {
                    celulasVazias[indice][0] = linha;
                    celulasVazias[indice][1] = coluna;
                    indice++;
                }
            }
        }
    }
}

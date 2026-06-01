package models;

import enums.Simbolo;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int tamanho;
    private Simbolo[][] grade;
    private List<Restricao> restricoes;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.grade = new Simbolo[tamanho][tamanho];
        this.restricoes = new ArrayList<>();
        inicializarGradeVazia();
    }

    private void inicializarGradeVazia() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                grade[i][j] = Simbolo.VAZIO;
            }
        }
    }

    public void zerar() {
        inicializarGradeVazia();
        this.restricoes.clear();
    }

    public void setSimbolo(int linha, int coluna, Simbolo simbolo) {
        if (linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho) {
            grade[linha][coluna] = simbolo;
        }
    }

    public void adicionarRestricao(Restricao restricao) {
        this.restricoes.add(restricao);
    }

    public int getTamanho() {
        return tamanho;
    }

    public Simbolo[][] getGrade() {
        return grade;
    }

    public List<Restricao> getRestricoes() {
        return restricoes;
    }

    public void imprimir() {
        System.out.println("\n--- Estado do Tabuleiro ---");
        
        System.out.print("  ");
        for (int j = 0; j < tamanho; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamanho; j++) {
                System.out.print(grade[i][j].getCaractere() + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
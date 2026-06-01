package solver;

import enums.Simbolo;
import models.Restricao;
import models.Tabuleiro;
import java.util.List;

public class ValidadorRegras {

    /**
     * Valida se o estado atual do tabuleiro é válido.
     * Retorna false imediatamente se alguma regra for quebrada (Poda).
     */
    public static boolean isValido(Tabuleiro tabuleiro) {
        return validarAdjacencia(tabuleiro) && 
               validarEquilibrio(tabuleiro) && 
               validarRestricoes(tabuleiro);
    }

    // Regra 2: Não permite que mais de 2 símbolos idênticos fiquem lado a lado.
    private static boolean validarAdjacencia(Tabuleiro tabuleiro) {
        int tamanho = tabuleiro.getTamanho();
        Simbolo[][] grade = tabuleiro.getGrade();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho - 2; j++) {
                // Checagem Horizontal (Linhas)
                if (grade[i][j] != Simbolo.VAZIO && 
                    grade[i][j] == grade[i][j+1] && 
                    grade[i][j] == grade[i][j+2]) {
                    return false;
                }
                // Checagem Vertical (Colunas)
                if (grade[j][i] != Simbolo.VAZIO && 
                    grade[j][i] == grade[j+1][i] && 
                    grade[j][i] == grade[j+2][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Regra 3: Cada linha/coluna deve ter a mesma quantidade de Sóis e Luas.
    // Durante a resolução, a quantidade não pode exceder a metade do tamanho.
    private static boolean validarEquilibrio(Tabuleiro tabuleiro) {
        int tamanho = tabuleiro.getTamanho();
        int limite = tamanho / 2;
        Simbolo[][] grade = tabuleiro.getGrade();

        for (int i = 0; i < tamanho; i++) {
            int soisLinha = 0, luasLinha = 0;
            int soisColuna = 0, luasColuna = 0;

            for (int j = 0; j < tamanho; j++) {
                // Contagem Horizontal
                if (grade[i][j] == Simbolo.SOL) soisLinha++;
                else if (grade[i][j] == Simbolo.LUA) luasLinha++;

                // Contagem Vertical
                if (grade[j][i] == Simbolo.SOL) soisColuna++;
                else if (grade[j][i] == Simbolo.LUA) luasColuna++;
            }

            // Se alguma linha ou coluna estourar o limite máximo, o estado é inválido
            if (soisLinha > limite || luasLinha > limite || 
                soisColuna > limite || luasColuna > limite) {
                return false;
            }
        }
        return true;
    }

    // Regras 4 e 5: Validação das restrições geométricas (= e x).
    private static boolean validarRestricoes(Tabuleiro tabuleiro) {
        List<Restricao> restricoes = tabuleiro.getRestricoes();
        Simbolo[][] grade = tabuleiro.getGrade();

        for (Restricao r : restricoes) {
            Simbolo s1 = grade[r.getLinha1()][r.getColuna1()];
            Simbolo s2 = grade[r.getLinha2()][r.getColuna2()];

            // Só avalia a restrição se ambas as células envolvidas já estiverem preenchidas
            if (s1 != Simbolo.VAZIO && s2 != Simbolo.VAZIO) {
                if (r.getTipo() == '=' && s1 != s2) return false;
                if (r.getTipo() == 'x' && s1 == s2) return false;
            }
        }
        return true;
    }

    // Regra 1: Preenchimento Completo (Utilizado para verificar se encontramos a solução final)
    public static boolean isCompleto(Tabuleiro tabuleiro) {
        int tamanho = tabuleiro.getTamanho();
        Simbolo[][] grade = tabuleiro.getGrade();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (grade[i][j] == Simbolo.VAZIO) {
                    return false;
                }
            }
        }
        return true;
    }
}
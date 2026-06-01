import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import models.Tabuleiro;
import models.Restricao;
import enums.Simbolo;

public class Main {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Tabuleiro tabuleiro = null;
      int opcao = -1;

      while (opcao != 0) {
         System.out.println("\n=================================");
         System.out.println("      QUEBRA-CABEÇA TANGO        ");
         System.out.println("=================================");
         System.out.println("1. Popular Tabuleiro (Ler configuração)");
         System.out.println("2. Imprimir Tabuleiro Atual");
         System.out.println("3. Resolver usando Força Bruta");
         System.out.println("4. Resolver usando Backtracking");
         System.out.println("5. Zerar Tabuleiro");
         System.out.println("0. Sair");
         System.out.println("=================================");
         System.out.print("Escolha uma opção: ");

         if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            scanner.nextLine();
         } else {
            System.out.println("Entrada inválida! Digite um número.");
            scanner.nextLine();
            continue;
         }

         switch (opcao) {
            case 1:
               tabuleiro = popularTabuleiroMenu(scanner);
               break;
            case 2:
               if (tabuleiro != null) {
                  tabuleiro.imprimir();
               } else {
                  System.out.println("\nErro: Nenhum tabuleiro carregado. Escolha a opção 1 primeiro.");
               }
               break;
            case 3:
               System.out.println("\nExecutando Força Bruta...");
               break;
            case 4:
               System.out.println("\nExecutando Backtracking...");
               break;
            case 5:
               if (tabuleiro != null) {
                  tabuleiro.zerar();
                  System.out.println("\nTabuleiro zerado com sucesso!");
               } else {
                  System.out.println("\nErro: Não há tabuleiro para zerar.");
               }
               break;
            case 0:
               System.out.println("\nEncerrando o programa... Até logo!");
               break;
            default:
               System.out.println("\nOpção inválida! Tente novamente.");
         }
      }

      scanner.close();
   }

   private static Tabuleiro popularTabuleiroMenu(Scanner scannerConsole) {
        System.out.println("\n--- Lendo Tabuleiro do Arquivo ---");
        System.out.print("Digite o nome do arquivo (ex: tabuleiro.txt): ");
        String nomeArquivo = scannerConsole.nextLine();

        try {
            File arquivo = new File(nomeArquivo);
            Scanner leitorArquivo = new Scanner(arquivo);

            int tamanho = leitorArquivo.nextInt();
            Tabuleiro tabuleiro = new Tabuleiro(tamanho);

            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    String valor = leitorArquivo.next();
                    if (valor.equalsIgnoreCase("S")) {
                        tabuleiro.setSimbolo(i, j, Simbolo.SOL);
                    } else if (valor.equalsIgnoreCase("L")) {
                        tabuleiro.setSimbolo(i, j, Simbolo.LUA);
                    }
                }
            }

            while (leitorArquivo.hasNextInt()) {
                int l1 = leitorArquivo.nextInt();
                int c1 = leitorArquivo.nextInt();
                int l2 = leitorArquivo.nextInt();
                int c2 = leitorArquivo.nextInt();
                char tipo = leitorArquivo.next().charAt(0);

                tabuleiro.adicionarRestricao(new Restricao(l1, c1, l2, c2, tipo));
            }

            leitorArquivo.close();
            System.out.println("[!] Arquivo lido e tabuleiro populado com sucesso!");
            return tabuleiro;

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo '" + nomeArquivo + "' não encontrado!");
            System.out.println("Certifique-se de que ele está na raiz do projeto.");
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: Verifique se o formato está correto.");
            return null;
        }
    }
}
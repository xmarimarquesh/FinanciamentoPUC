package util;
import java.util.Scanner;

public class InterfaceUsuario {
    static Scanner scanner = new Scanner(System.in);

    public static double qualValorImovel(){
        double valorImovel = -1;

        while (valorImovel <= 0) {
            System.out.println("Digite o valor do imóvel: ");
            try {
                if (scanner.hasNextDouble()) {
                    valorImovel = scanner.nextDouble();
                    if (valorImovel <= 0) {
                        System.out.println("Valor inválido. Por favor, insira um valor positivo.");
                    }
                } else {
                    throw new IllegalArgumentException("Entrada inválida. Por favor, insira um número.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }

        return valorImovel;
    }

    public static int qualPrazoFinanciamento(){
        int prazoFinanciamento = -1;

        while (prazoFinanciamento <= 0) {
            System.out.println("Digite o prazo do financiamento em meses: ");
            try {
                if (scanner.hasNextInt()) {
                    prazoFinanciamento = scanner.nextInt();
                    if (prazoFinanciamento <= 0) {
                        System.out.println("Valor inválido. Por favor, insira um valor positivo.");
                    }
                } else {
                    throw new IllegalArgumentException("Entrada inválida. Por favor, insira um número inteiro.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }

        return prazoFinanciamento;
    }

    public static double qualTaxaJuros(){
        double taxaJuros = -1;

        while (taxaJuros <= 0) {
            System.out.println("Digite a taxa de juros anual (%): ");
            try {
                if (scanner.hasNextDouble()) {
                    taxaJuros = scanner.nextDouble();
                    if (taxaJuros <= 0) {
                        System.out.println("Valor inválido. Por favor, insira um valor positivo.");
                    }
                } else {
                    throw new IllegalArgumentException("Entrada inválida. Por favor, insira um número.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }

        return taxaJuros;
    }
}

package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.*;
import util.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------- SISTEMA DE FINANCIAMENTO ----------");

        ArrayList<Financiamento> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Solicitando os dados do usuário para um financiamento
        double valorImovel = InterfaceUsuario.qualValorImovel();
        int prazoFinanciamento = InterfaceUsuario.qualPrazoFinanciamento();
        double taxaJurosAnual = InterfaceUsuario.qualTaxaJuros();

        // Solicitando o tipo de imóvel
        System.out.println("Tipo de imóvel (1: Casa, 2: Apartamento, 3: Terreno): ");
        int tipoImovel = scanner.nextInt();

        // Adicionando o financiamento personalizado à lista com base no tipo de imóvel
        switch (tipoImovel) {
            case 1:
                System.out.print("Área construída (m²): ");
                double areaConstruida = scanner.nextDouble();
                System.out.print("Tamanho do terreno (m²): ");
                double tamanhoTerreno = scanner.nextDouble();
                list.add(new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, areaConstruida, tamanhoTerreno));
                break;
            case 2:
                System.out.print("Número de vagas da garagem: ");
                int vagasGaragem = scanner.nextInt();
                System.out.print("Número do andar: ");
                int numeroAndar = scanner.nextInt();
                list.add(new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, vagasGaragem, numeroAndar));
                break;
            case 3:
                System.out.print("Tipo de zona (residencial/comercial): ");
                String tipoZona = scanner.next();
                list.add(new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZona));
                break;
            default:
                System.out.println("Tipo de imóvel inválido.");
                break;
        }

        // Adicionando os financiamentos pré-definidos
        list.add(new Casa(200000, 12, 1, 150, 300));
        list.add(new Casa(230000, 24, 1.4, 200, 400));
        list.add(new Apartamento(120000, 14, 1, 1, 5));
        list.add(new Apartamento(100000, 20, 1, 2, 10));
        list.add(new Terreno(150000, 48, 1.3, "residencial"));

        // Calculando e exibindo os valores
        double somaValorImoveis = 0;
        double somaValorFinanciamentos = 0;

        for (int i = 0; i < list.size(); i++) {
            Financiamento financiamento = list.get(i);
            double valorImovelAtual = financiamento.getValorImovel();
            double valorFinanciamentoAtual = financiamento.calcularPagamentoTotal();

            somaValorImoveis += valorImovelAtual;
            somaValorFinanciamentos += valorFinanciamentoAtual;

            System.out.printf("\nFinanciamento %d - Valor do imóvel: R$%.2f, Valor do financiamento: R$%.2f",
                    i + 1, valorImovelAtual, valorFinanciamentoAtual);
            financiamento.mostrarFinanciamento();
        }

        // Exibindo as somas dos valores
        System.out.printf("\n\nSoma dos valores dos imóveis: R$%.2f", somaValorImoveis);
        System.out.printf("\nSoma dos valores dos financiamentos: R$%.2f", somaValorFinanciamentos);

        // Salvando os financiamentos em um arquivo de texto
        salvarFinanciamentosEmArquivo(list, "financiamentos.txt");

        // Lendo os financiamentos do arquivo de texto e exibindo
        ArrayList<Financiamento> financiamentosLidos = lerFinanciamentosDeArquivo("financiamentos.txt");
        System.out.println("\n\nFinanciamentos lidos do arquivo:");
        for (Financiamento financiamento : financiamentosLidos) {
            financiamento.mostrarFinanciamento();
        }

        // Serializando e desserializando o ArrayList de financiamentos
        serializarFinanciamentos(list, "financiamentos.dat");
        ArrayList<Financiamento> financiamentosDesserializados = desserializarFinanciamentos("financiamentos.dat");
        System.out.println("\n\nFinanciamentos desserializados:");
        for (Financiamento financiamento : financiamentosDesserializados) {
            financiamento.mostrarFinanciamento();
        }

        scanner.close();
    }

    private static void salvarFinanciamentosEmArquivo(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Financiamento financiamento : financiamentos) {
                writer.println(financiamento.serializarParaTexto());
            }
            System.out.println("\nFinanciamentos salvos no arquivo '" + nomeArquivo + "' com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os financiamentos no arquivo: " + e.getMessage());
        }
    }

    private static ArrayList<Financiamento> lerFinanciamentosDeArquivo(String nomeArquivo) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse da linha para criar o financiamento correspondente
                String[] parts = line.split(": ");
                double valorImovel = Double.parseDouble(parts[1]);
                int prazoFinanciamento = Integer.parseInt(parts[3]);
                double taxaJurosAnual = Double.parseDouble(parts[5]);
                String tipoImovel = parts[7].trim();

                switch (tipoImovel) {
                    case "Casa":
                        double areaConstruida = Double.parseDouble(parts[9].split("m²")[0].trim());
                        double tamanhoTerreno = Double.parseDouble(parts[11].split("m²")[0].trim());
                        financiamentos.add(new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, areaConstruida, tamanhoTerreno));
                        break;
                    case "Apartamento":
                        int vagasGaragem = Integer.parseInt(parts[9].trim());
                        int numeroAndar = Integer.parseInt(parts[11].trim());
                        financiamentos.add(new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, vagasGaragem, numeroAndar));
                        break;
                    case "Terreno":
                        String tipoZona = parts[9].trim();
                        financiamentos.add(new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZona));
                        break;
                    default:
                        System.out.println("Tipo de imóvel inválido encontrado no arquivo.");
                        break;
                }
            }
            System.out.println("\nFinanciamentos lidos do arquivo '" + nomeArquivo + "' com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao ler os financiamentos do arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler os financiamentos do arquivo: formato inválido.");
        }
        return financiamentos;
    }

    private static void serializarFinanciamentos(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(financiamentos);
            System.out.println("\nFinanciamentos serializados no arquivo '" + nomeArquivo + "' com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao serializar os financiamentos: " + e.getMessage());
        }
    }

    private static ArrayList<Financiamento> desserializarFinanciamentos(String nomeArquivo) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            financiamentos = (ArrayList<Financiamento>) in.readObject();
            System.out.println("\nFinanciamentos desserializados do arquivo '" + nomeArquivo + "' com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao desserializar os financiamentos: " + e.getMessage());
        }
        return financiamentos;
    }
}


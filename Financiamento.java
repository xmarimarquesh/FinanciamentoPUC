package modelo;

import java.io.Serializable;

public abstract class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public abstract double calcularPagamentoMensal();
    public abstract double calcularPagamentoTotal();

    public void mostrarFinanciamento() {
        System.out.println("--------- Informações do imóvel --------");
        System.out.println("Valor do imóvel: " + this.valorImovel);
        System.out.println("Prazo do financiamento: " + this.prazoFinanciamento);
        System.out.println("Taxa de Juros anual: " + this.taxaJurosAnual);
    }

    public String serializarParaTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("Valor do Imóvel: ").append(this.valorImovel).append("\n");
        sb.append("Prazo de Financiamento (meses): ").append(this.prazoFinanciamento).append("\n");
        sb.append("Taxa de Juros Anual (%): ").append(this.taxaJurosAnual).append("\n");
        return sb.toString();
    }
}

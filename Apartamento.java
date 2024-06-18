package modelo;

public class Apartamento extends Financiamento {
    private int vagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public int getNumeroAndar() {
        return numeroAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        int meses = getPrazoFinanciamento() * 12;

        double numerador = getValorImovel() * taxaMensal * Math.pow(1 + taxaMensal, meses);
        double denominador = Math.pow(1 + taxaMensal, meses) - 1;

        return numerador / denominador;
    }

    @Override
    public double calcularPagamentoTotal() {
        double valorMensal = calcularPagamentoMensal();
        return valorMensal * getPrazoFinanciamento() * 12;
    }

    @Override
    public void mostrarFinanciamento() {
        super.mostrarFinanciamento();
        System.out.println("Vagas de Garagem: " + vagasGaragem + ", Número do Andar: " + numeroAndar);
    }
}

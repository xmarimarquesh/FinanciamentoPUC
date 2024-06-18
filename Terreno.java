package modelo;

public class Terreno extends Financiamento {
    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        double pagamentoBase = (getValorImovel() / (getPrazoFinanciamento() * 12)) * (1 + (getTaxaJurosAnual() / 12));
        return pagamentoBase + pagamentoBase * 0.02;
    }

    @Override
    public double calcularPagamentoTotal() {
        double valorMensal = calcularPagamentoMensal();
        return valorMensal * getPrazoFinanciamento() * 12;
    }

    @Override
    public void mostrarFinanciamento() {
        super.mostrarFinanciamento();
        System.out.println("Tipo de Zona: " + tipoZona);
    }
}

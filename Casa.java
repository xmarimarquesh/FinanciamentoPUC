package modelo;

public class Casa extends Financiamento {
    private double areaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double pagamentoBase = (getValorImovel() / (getPrazoFinanciamento() * 12)) * (1 + (getTaxaJurosAnual() / 12));
        return pagamentoBase + 80.0;
    }

    @Override
    public double calcularPagamentoTotal() {
        double valorMensal = calcularPagamentoMensal();
        return valorMensal * getPrazoFinanciamento() * 12;
    }

    public double aplicarDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        double jurosMensal = getTaxaJurosAnual() / 12;
        if (desconto > jurosMensal) {
            throw new DescontoMaiorDoQueJurosException("O desconto não pode ser maior do que os juros mensais.");
        }
        return calcularPagamentoMensal() - desconto;
    }

    @Override
    public void mostrarFinanciamento() {
        super.mostrarFinanciamento();
        System.out.println("Área Construída: " + areaConstruida + "m², Tamanho do Terreno: " + tamanhoTerreno + "m²");
    }
}

package models;

import utils.Constantes;
import utils.PorcentagensCaixas;
import utils.Utils;

public class Individuo {

	private int cromossomoCaixaA;
	private int cromossomoCaixaB;
	private int cromossomoCaixaC;
	private int cromossomoCaixaD;
	private Integer pesoTotal;
	private Integer valorTotal;

	public Individuo() {
		cromossomoCaixaA = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaA());
		cromossomoCaixaB = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaB());
		cromossomoCaixaC = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaC());
		cromossomoCaixaD = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaD());
		calculaPesoTotal();
		calculaValorTotal();
	}

	public int getCromossomoCaixaA() {
		return cromossomoCaixaA;
	}

	public int getCromossomoCaixaB() {
		return cromossomoCaixaB;
	}

	public int getCromossomoCaixaC() {
		return cromossomoCaixaC;
	}

	public int getCromossomoCaixaD() {
		return cromossomoCaixaD;
	}

	public Integer getPesoTotal() {
		return this.pesoTotal;
	}
	
	public Integer getValorTotal() {
		return valorTotal;
	}

	private void calculaPesoTotal() {
		this.pesoTotal = (this.getCromossomoCaixaA() * Constantes.PESO_CAIXA_A)
				+ (this.getCromossomoCaixaB() * Constantes.PESO_CAIXA_B)
				+ (this.getCromossomoCaixaC() * Constantes.PESO_CAIXA_C)
				+ (this.getCromossomoCaixaD() * Constantes.PESO_CAIXA_D);
	}
	
	private void calculaValorTotal() {
		this.valorTotal = (this.getCromossomoCaixaA() * Constantes.VALOR_CAIXA_A)
				+ (this.getCromossomoCaixaB() * Constantes.VALOR_CAIXA_B)
				+ (this.getCromossomoCaixaC() * Constantes.VALOR_CAIXA_C)
				+ (this.getCromossomoCaixaD() * Constantes.VALOR_CAIXA_D);
	}

	@Override
	public String toString() {

		return "Informações do indivíduo ->\r" + " Quantidade de caixas A: " + getCromossomoCaixaA() + "\r"
				+ " Quantidade de caixas B: " + getCromossomoCaixaB() + "\r" + " Quantidade de caixas C: "
				+ getCromossomoCaixaC() + "\r" + " Quantidade de caixas D: " + getCromossomoCaixaD() + "\r"
				+" Peso total -> " + getPesoTotal()+" | Valor Total ->"+getValorTotal()+"\r";
	}

}

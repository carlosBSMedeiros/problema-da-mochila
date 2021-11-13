package models;

import java.util.Random;

import utils.Constantes;
import utils.PorcentagensCaixas;
import utils.Utils;

public class Individuo {

	private int[] fitaCromossomos;
	private Integer pesoTotal;
	private Integer valorTotal;

	public Individuo() {
		preencherFitaCromos();
		calcularPesoTotal();
		calcularValorTotal();
	}

	public int getCromossomoCaixaA() {
		return fitaCromossomos[0];
	}

	public int getCromossomoCaixaB() {
		return fitaCromossomos[1];
	}

	public int getCromossomoCaixaC() {
		return fitaCromossomos[2];
	}

	public int getCromossomoCaixaD() {
		return fitaCromossomos[3];
	}

	public Integer getPesoTotal() {
		return this.pesoTotal;
	}
	
	public Integer getValorTotal() {
		return valorTotal;
	}
	
	public int[] getFitaCromossomos() {
		return fitaCromossomos;
	}

	public void setFitaCromossomos(int[] fitaCromossomos) {
		this.fitaCromossomos = fitaCromossomos;
	}

	private void preencherFitaCromos() {
		fitaCromossomos = new int[Constantes.QUANT_CROMOSSOMOS];
		fitaCromossomos[0] = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaA());;
		fitaCromossomos[1] = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaB());;
		fitaCromossomos[2] = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaC());
		fitaCromossomos[3] = Utils.retornaQuantidadeCaixas(PorcentagensCaixas.getPctgCaixaD());

	}
	
//	private int[] preencheFita() {
//		Random gerador = new Random();
//		int[] fita;
//		do {
//			fita = new int[Constantes.QUANT_CROMOSSOMOS];
//			
//			for (int i = 0; i < Constantes.QUANT_CROMOSSOMOS; i++) {
//				fita[i] = gerador.nextInt(24);
//			}
//		} while (calcularPesoTotal(fita) > 25);
//		
//
//		return fita;
//	}
	
	private int calcularPesoTotal(int[] fita) {
		return (fita[0] * Constantes.PESO_CAIXA_A)
				+ (fita[1] * Constantes.PESO_CAIXA_B)
				+ (fita[2] * Constantes.PESO_CAIXA_C)
				+ (fita[3] * Constantes.PESO_CAIXA_D);
	}
	
	
	public void calcularPesoTotal() {
		this.pesoTotal = (this.getCromossomoCaixaA() * Constantes.PESO_CAIXA_A)
				+ (this.getCromossomoCaixaB() * Constantes.PESO_CAIXA_B)
				+ (this.getCromossomoCaixaC() * Constantes.PESO_CAIXA_C)
				+ (this.getCromossomoCaixaD() * Constantes.PESO_CAIXA_D);
	}
	
	public void calcularValorTotal() {
		this.valorTotal = (this.getCromossomoCaixaA() * Constantes.VALOR_CAIXA_A)
				+ (this.getCromossomoCaixaB() * Constantes.VALOR_CAIXA_B)
				+ (this.getCromossomoCaixaC() * Constantes.VALOR_CAIXA_C)
				+ (this.getCromossomoCaixaD() * Constantes.VALOR_CAIXA_D);
	}

	@Override
	public String toString() {

		return "Indivíduo ->\r" + " quant_A: " + getCromossomoCaixaA() + "; quant_B: " + getCromossomoCaixaB()
		+ "; quant_C: " + getCromossomoCaixaC() + "; quant_D: " + getCromossomoCaixaD() +"; Peso total -> " + getPesoTotal()+" | Valor Total ->"+getValorTotal()+"\r";
	}

}

package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PorcentagensCaixas {
	
	private static Double[] pctgCaixaA;
	private static Double[] pctgCaixaB;
	private static Double[] pctgCaixaC;
	private static Double[] pctgCaixaD;

	public static Double[] getPctgCaixaA() {
		return pctgCaixaA;
	}
	public static Double[] getPctgCaixaB() {
		return pctgCaixaB;
	}
	public static Double[] getPctgCaixaC() {
		return pctgCaixaC;
	}
	public static Double[] getPctgCaixaD() {
		return pctgCaixaD;
	}
	
	public static void calculaPorcentagensDasCaixas() {
		pctgCaixaA = instanciaVetsPorcentagem(Constantes.QUANT_CAIXA_A);
		pctgCaixaB = instanciaVetsPorcentagem(Constantes.QUANT_CAIXA_B);
		pctgCaixaC = instanciaVetsPorcentagem(Constantes.QUANT_CAIXA_C);
		pctgCaixaD = instanciaVetsPorcentagem(Constantes.QUANT_CAIXA_D);
	}
	
	private static Double[] instanciaVetsPorcentagem(Integer quantidadeCaixas) {
		int tamanhoVetor = quantidadeCaixas + 1;
		Double[] vetorCaixas = new Double[tamanhoVetor];
		Double pctgTipo = 1.0 / tamanhoVetor;
		Double pctgTipoacm = pctgTipo;
		for (int i = 0; i < vetorCaixas.length; i++) {
			if(i == (vetorCaixas.length - 1)) {
				vetorCaixas[i] = 1.0;
			}else {
				pctgTipoacm = new BigDecimal(pctgTipoacm).setScale(3, RoundingMode.FLOOR).doubleValue();
				vetorCaixas[i] = pctgTipoacm;
			}
			pctgTipoacm += pctgTipo;
		}
		return vetorCaixas;
	}
	
}

package controllers;

import models.Populacao;
import utils.PorcentagensCaixas;

public class Teste {

	public static void main(String[] args) {
		PorcentagensCaixas.calculaPorcentagensDasCaixas();
		Populacao populacao = new Populacao(true);
	}

}

package controllers;

import factorys.PopulacaoFactory;
import models.Populacao;
import utils.PorcentagensCaixas;

public class App {

	public int contadorAux = 3;
	public App() {}
	
	/*
	 * 	
	criar a populacao inicial 
		enquanto(sem parar){
		 recuperar populacao atual 
		 organizar os melhores e piores
		 verificar o melhor
		 verificar se o melhor individuo é o ideal -> fim do algoritmo
		 fazer o cross-over
		 fazer mutações
		 recuperar a nova população
		}
	 * */
	
	public void iniciar() {
		PorcentagensCaixas.calculaPorcentagensDasCaixas();
		PopulacaoFactory populacaoFactory = new PopulacaoFactory();
		Populacao populacao = populacaoFactory.criaPopulacaoInicial();
		
		while(!validarSatisfacaoResultado(populacao)) {
			
		}
		
	}
	
	private boolean validarSatisfacaoResultado(Populacao populacao) {
		
		return (contadorAux == 0);
	}
}

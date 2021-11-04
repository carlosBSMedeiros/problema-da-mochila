package controllers;

import java.util.Iterator;

import factorys.PopulacaoFactory;
import models.Individuo;
import models.Populacao;
import utils.Constantes;
import utils.PorcentagensCaixas;

public class App {

	public Individuo melhorInviduo;
	
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
		populacao.avaliarPopulacao();
		melhorInviduo = populacao.getMelhorIndividuo();
		int geracao = populacao.getGeracao();
		
		
		for (int i = 0; i < Constantes.MAXIMO_GERACOES; i++) {
			populacao = populacaoFactory.criarNovaPopulacao(populacao);	
			populacao.avaliarPopulacao();
			System.out.println("*** melhor individuo da população-> "+populacao.getMelhorIndividuo().toString());
			if(populacao.getMelhorIndividuo().getValorTotal() > melhorInviduo.getValorTotal()) {
				geracao = populacao.getGeracao();
				melhorInviduo = populacao.getMelhorIndividuo(); 
			}
		}
		
		System.out.println("melhor individuo geração "+geracao);
		System.out.println(melhorInviduo.toString());
			
	}
	
}

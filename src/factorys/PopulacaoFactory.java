package factorys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import models.Individuo;
import models.Populacao;
import utils.Constantes;
import utils.Utils;

public class PopulacaoFactory {

	IndividuosFactory individuosFactory;
	MutacaoFactory fabricaMutacao;

	
	public PopulacaoFactory() {
		individuosFactory = new IndividuosFactory();
		fabricaMutacao = new MutacaoFactory();
	}

	public Populacao criaPopulacaoInicial() {
		Populacao populacaoInicial = new Populacao(1);
		int tamanhoPop = Constantes.TAMANHO_POPULACAO;
		for (int i = 1; i <= tamanhoPop; i++) {
			Individuo individuo = individuosFactory.criarIndividuoValido();
			populacaoInicial.getIndividuos().add(individuo);
		}
		
		System.out.println("População inicial");
		System.out.println(populacaoInicial.toString());

		
		return populacaoInicial;
	}

	public Populacao criarNovaPopulacao(Populacao populacaoAnterior) {
		//cria nova população zerada, apnas com o numero da geração
		Populacao novaPopulacao = new Populacao(populacaoAnterior.getGeracao() + 1);
		
		//aqui inicia o crossover na população. O objetivo é selecionar
		//dois individuos, fazer o crossover deles, adicionar os dois individos a nova pop
		//e adicionar os individuos da população anterior que não sofreram cross over
		
		double numeroElitismo = (Constantes.ELITISMO * Constantes.TAMANHO_POPULACAO); 
		int elitismo = (int) Math.round(numeroElitismo);
		novaPopulacao.setIndividuos(populacaoAnterior.getIndividuos().subList(0, elitismo));
		

		for (int i = elitismo; i < Constantes.TAMANHO_POPULACAO; i++) {
			novaPopulacao = crossOver(novaPopulacao, populacaoAnterior);
		}
		novaPopulacao.setGeracaoAnterior(populacaoAnterior);
		
		System.out.println("====================");
		System.out.println("População nova");
		System.out.println(novaPopulacao.toString());
		
		return novaPopulacao;
	}

	public Populacao crossOver(Populacao novaPopulacao, Populacao populacaoAnterior) {
		Random random = new Random();
		
		//dividir em dois vetores auxiliares os dois grupos de individuos
		List<Individuo> melhoresIndividuos = novaPopulacao.getIndividuos();
		int index;
		int index2;
		Individuo indiv2;
		Individuo indiv1;
		
		do {
			index = random.nextInt(novaPopulacao.getIndividuos().size());
			indiv1 = melhoresIndividuos.get(index);
			
			index2 = random.nextInt(novaPopulacao.getIndividuos().size());
			indiv2 = melhoresIndividuos.get(index2);
			
		} while (index==index2);
		
		
		//individuos selecinados aleatoriamente para o cross over - SELEÇÃO FUNCIONANDO
		//efetivamente faz o crossover entre os individuos
		 Individuo novoIndiv = crossOver(indiv1, indiv2);
		 
		//o cross over pode gerar individuos invalidos. Se forem, geramos novos aleatorios
		if(!individuosFactory.validarIndividuo(novoIndiv)) {
			novoIndiv = individuosFactory.criarIndividuoValido();
		}

		novoIndiv = fabricaMutacao.mutacao(novoIndiv);
		fabricaMutacao.mutacao(novoIndiv);
		//adiciona os individuos a população nova apos o crossover
		novaPopulacao.getIndividuos().add(novoIndiv);
		
		return novaPopulacao;
	}
	
	
	/// por agora não vamos mais usar essa selção
	private Individuo escolherIndividuoParaCrossOver(List<Individuo> individuos, Populacao novaPopulacao) {
		Double[] porcentagens = new Double[individuos.size()];
		double valorTotal = 0.0;
		for (int i = 0; i < individuos.size(); i++) {
			valorTotal += individuos.get(i).getValorTotal();
		}
		
		for (int i = 0; i < individuos.size(); i++) {
			Double porcentagem = ((individuos.get(i).getValorTotal() * 100.0)/ valorTotal);
			porcentagem = new BigDecimal(porcentagem/100).setScale(3, RoundingMode.FLOOR).doubleValue();
			if(i > 0) {
				porcentagens[i] = porcentagem + porcentagens[i-1];
			} else {
				porcentagens[i] = porcentagem;
			}
		}
		
		int indice = Utils.verificaIndiceEquivalenteAporcentagem(porcentagens);
		
		if(indice != -1) {
			novaPopulacao.getIndividuos().add(individuos.get(indice));	
		}
		
		return individuos.get(indice);
	}
	
	private Individuo crossOver(Individuo ind1, Individuo ind2) {
		Individuo novo = individuosFactory.criarIndividuoValido();
		
		int indexCromossomo = Constantes.QUANT_CROMOSSOMOS/2;
		indexCromossomo = indexCromossomo - 1;
		for (int i = 0; i < ind1.getFitaCromossomos().length; i++) {
			if(i <= indexCromossomo) {
				novo.getFitaCromossomos()[i] = ind1.getFitaCromossomos()[i];				
			}
			if(i > indexCromossomo) {
				novo.getFitaCromossomos()[i] = ind2.getFitaCromossomos()[i];				
			}
			
		}
		
		return novo;
	}
	
}

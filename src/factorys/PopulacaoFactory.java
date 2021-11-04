package factorys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

import models.Individuo;
import models.Populacao;
import utils.Constantes;
import utils.Utils;

public class PopulacaoFactory {

	IndividuosFactory individuosFactory;
	
	public PopulacaoFactory() {
		individuosFactory = new IndividuosFactory();
	}

	public Populacao criaPopulacaoInicial() {
		Populacao populacaoInicial = new Populacao(1);
		int tamanhoPop = Constantes.TAMANHO_POPULACAO;
		for (int i = 1; i <= tamanhoPop; i++) {
			Individuo individuo = individuosFactory.criarIndividuoValido();
			populacaoInicial.getIndividuos().add(individuo);
		}
		return populacaoInicial;
	}

	public Populacao criarNovaPopulacao(Populacao populacaoAnterior) {
		
		System.out.println("População anterior");
		System.out.println(populacaoAnterior.toString());
		
		//cria nova população zerada, apnas com o numero da geração
		Populacao novaPopulacao = new Populacao(populacaoAnterior.getGeracao() + 1);
		
		//aqui inicia o crossover na população. O objetivo é selecionar
		//dois individuos, fazer o crossover deles, adicionar os dois individos a nova pop
		//e adicionar os individuos da população anterior que não sofreram cross over
		novaPopulacao = crossOver(novaPopulacao, populacaoAnterior);

		//selecionar um individuo para mutação
		mutacao();
		
		System.out.println("====================");
		System.out.println("População nova");
		System.out.println(novaPopulacao.toString());
		
		return novaPopulacao;
	}

	public Populacao crossOver(Populacao novaPopulacao, Populacao populacaoAnterior) {
		
		ArrayList<Individuo> individuosPopAnterior = populacaoAnterior.getIndividuos();
		//dividir em dois vetores auxiliares os dois grupos de individuos
		int metadeTamanhoPopulacao = Constantes.TAMANHO_POPULACAO / 2; 
		Individuo[] melhoresIndividuos = new Individuo[metadeTamanhoPopulacao];
		Individuo[] pioresIndividuos  = new Individuo[metadeTamanhoPopulacao];
		
		int auxM = 0;
		int auxP = 0;
		for (int i = 0; i < Constantes.TAMANHO_POPULACAO; i++) {
			if(i < metadeTamanhoPopulacao) {
				melhoresIndividuos[auxM] = individuosPopAnterior.get(i);
				auxM++;
			} else {
				pioresIndividuos[auxP] = individuosPopAnterior.get(i);
				auxP++;
			}
		}
		
		//população antiga dividida em dois grupos, os melhores e os piores individuos
		
//		System.out.println("Melhores individuos ->");
//		for (Individuo individuo : melhoresIndividuos) {
//			System.out.print(individuo.getValorTotal() + ", ");
//		}
//		
//		System.out.println("\rPiores individuos ->");
//		for (Individuo individuo : pioresIndividuos) {
//			System.out.print(individuo.getValorTotal() + ", ");
//		}
		
		Individuo MIndividuoCrossOver = escolherIndividuoParaCrossOver(melhoresIndividuos, novaPopulacao);
		Individuo PIndividuoCrossOver = escolherIndividuoParaCrossOver(pioresIndividuos, novaPopulacao);
		
		//individuos selecinados aleatoriamente para o cross over - SELEÇÃO FUNCIONANDO
		//efetivamente faz o crossover entre os individuos
		crossOver(MIndividuoCrossOver, PIndividuoCrossOver);
		
		
		//o cross over pode gerar individuos invalidos. Se forem, geramos novos aleatorios
		if(!individuosFactory.validarIndividuo(MIndividuoCrossOver)) {
			MIndividuoCrossOver = individuosFactory.criarIndividuoValido();
		}
		
		if(!individuosFactory.validarIndividuo(PIndividuoCrossOver)) {
			PIndividuoCrossOver = individuosFactory.criarIndividuoValido();
		}
		
		//adiciona os individuos a população nova apos o crossover
		novaPopulacao.getIndividuos().add(MIndividuoCrossOver);
		novaPopulacao.getIndividuos().add(PIndividuoCrossOver);
		
		return novaPopulacao;
	}
	
	private Individuo escolherIndividuoParaCrossOver(Individuo[] individuos, Populacao novaPopulacao) {
		Double[] porcentagens = new Double[individuos.length];
		double valorTotal = 0.0;
		for (int i = 0; i < individuos.length; i++) {
			valorTotal += individuos[i].getValorTotal();
		}
		
		for (int i = 0; i < individuos.length; i++) {
			Double porcentagem = ((individuos[i].getValorTotal() * 100.0)/ valorTotal);
			porcentagem = new BigDecimal(porcentagem/100).setScale(3, RoundingMode.FLOOR).doubleValue();
			if(i > 0) {
				porcentagens[i] = porcentagem + porcentagens[i-1];
			} else {
				porcentagens[i] = porcentagem;
			}
		}
		
		int indice = Utils.verificaIndiceEquivalenteAporcentagem(porcentagens);
		
		for(int i = 0; i < individuos.length; i++) {
			if(i != indice) {
				novaPopulacao.getIndividuos().add(individuos[i]);
			}
		}
		
		return individuos[indice];
	}
	
	private void crossOver(Individuo ind1, Individuo ind2) {
		Random random = new Random();
		int indexCromossomo = random.nextInt(Constantes.QUANT_CROMOSSOMOS);
		
		//trocar os cromossomos dos individuos
		int cromossomoAux = ind1.getFitaCromossomos()[indexCromossomo];
		ind1.getFitaCromossomos()[indexCromossomo] = ind2.getFitaCromossomos()[indexCromossomo];
		ind2.getFitaCromossomos()[indexCromossomo] = cromossomoAux;
		ind1.calcularPesoTotal();
		ind2.calcularPesoTotal();
	}
	
}

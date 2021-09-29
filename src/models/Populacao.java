package models;

import java.util.ArrayList;
import java.util.List;

import factory.IndividuosFactory;
import utils.Constantes;

public class Populacao {

	private List<Individuo> individuos;
	
	public Populacao() {
		individuos = new ArrayList<Individuo>();
	}
	
	public Populacao(boolean primeiraPopulacao) {
		this();
		criaPopulacaoInicial();
	}

	private void criaPopulacaoInicial() {
		int tamanhoPop = Constantes.TAMANHO_POPULACAO;
		for (int i = 1; i <= tamanhoPop; i++) {
			Individuo individuo = IndividuosFactory.criarIndividuoValido();
			this.individuos.add(individuo);
			System.out.println(individuo.toString());
		}
	}
	
	public Individuo getIndividuo(int index) {
		return individuos.get(index);
	}
}

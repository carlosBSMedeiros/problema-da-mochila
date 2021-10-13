package factorys;

import models.Individuo;
import models.Populacao;
import utils.Constantes;

public class PopulacaoFactory {

	public PopulacaoFactory() {
	}

	public Populacao criaPopulacaoInicial() {
		Populacao populacaoInicial = new Populacao();
		int tamanhoPop = Constantes.TAMANHO_POPULACAO;
		for (int i = 1; i <= tamanhoPop; i++) {
			Individuo individuo = IndividuosFactory.criarIndividuoValido();
			populacaoInicial.getIndividuos().add(individuo);
		}
		return populacaoInicial;
	}
}

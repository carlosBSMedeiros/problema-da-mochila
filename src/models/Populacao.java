package models;

import java.util.ArrayList;
import java.util.List;

import utils.Constantes;

public class Populacao {

	private int geracao;
	private List<Individuo> individuos;
	private Individuo melhorIndividuo;
	private Populacao proximaGeracao;
	private Populacao geracaoAnterior;

	public Populacao(int geracao) {
		this();
		this.geracao = geracao;
	}

	public Populacao() {
		individuos = new ArrayList<Individuo>();
	}

	public Individuo getIndividuo(int index) {
		return individuos.get(index);
	}
	
	public void setIndividuos(List<Individuo> individuos) {
		this.individuos = individuos;
	}

	
	public List<Individuo> getIndividuos() {
		return this.individuos;
	}

	public int getGeracao() {
		return geracao;
	}

	public void setGeracao(int geracao) {
		this.geracao = geracao;
	}

	public Individuo getMelhorIndividuo() {
		return melhorIndividuo;
	}

	public void setMelhorIndividuo(Individuo maiorIndividuo) {
		this.melhorIndividuo = maiorIndividuo;
	}

	public Populacao getProximaGeracao() {
		return proximaGeracao;
	}

	public void setProximaGeracao(Populacao proximaGeracao) {
		this.proximaGeracao = proximaGeracao;
	}

	public Populacao getGeracaoAnterior() {
		return geracaoAnterior;
	}

	public void setGeracaoAnterior(Populacao geracaoAnterior) {
		this.geracaoAnterior = geracaoAnterior;
	}

	public void avaliarPopulacao() {

		int j;
		int i;

		Individuo individuoAux = null;
//		Individuo[] individuosVet = new Individuo[Constantes.TAMANHO_POPULACAO];
		Individuo[] individuosVet = new Individuo[individuos.size()];
		
		individuos.toArray(individuosVet);
		
		for (j = 0; j < individuosVet.length; j++) {
			individuoAux = individuosVet[j];
			for (i = j - 1; (i >= 0) && (individuosVet[i].getValorTotal() < individuoAux.getValorTotal()); i--) {
				individuosVet[i + 1] = individuosVet[i];
			}
			individuosVet[i + 1] = individuoAux;
		}
		
		individuos.clear();
		
		
		for(Individuo individuo : individuosVet){
			individuos.add(individuo);
		}
		
//		System.out.print("Individuos organizados maior -> menor: ");
//		individuos.forEach(e->{
//			System.out.print(" " + e.getValorTotal());
//		});
		
		this.melhorIndividuo = individuos.get(0);
		System.out.println("\rMelhor individuo -> " + this.melhorIndividuo.getValorTotal());
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("População geração: ").append(this.geracao).append("\r");
		strb.append("Individuos");
		for(Individuo individuo: individuos) {
			strb.append(individuo.toString());
		}
		
		return strb.toString();
		
	}

}

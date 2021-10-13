package models;

import java.util.ArrayList;
import java.util.List;

import utils.Constantes;

public class Populacao {

	private List<Individuo> individuos;
	private int geracao;
	private Individuo[] melhoresIndividuos;
	private Individuo[] pioresIndividuos;
	
	public Populacao(int geracao) {
		this();
		this.geracao = geracao;
	}
	public Populacao() {
		individuos = new ArrayList<Individuo>();
		int aux = Constantes.TAMANHO_POPULACAO/2;
		melhoresIndividuos = new Individuo[aux];
		pioresIndividuos = new Individuo[aux];
	}
	
	public Individuo getIndividuo(int index) {
		return individuos.get(index);
	}
	
	public List<Individuo> getIndividuos(){
		return this.individuos;
	}

	public int getGeracao() {
		return geracao;
	}

	public void setGeracao(int geracao) {
		this.geracao = geracao;
	}
	public Individuo[] getMelhoresIndividuos() {
		return melhoresIndividuos;
	}
	public void setMelhoresIndividuos(Individuo[] melhoresIndividuos) {
		this.melhoresIndividuos = melhoresIndividuos;
	}
	public Individuo[] getPioresIndividuos() {
		return pioresIndividuos;
	}
	public void setPioresIndividuos(Individuo[] pioresIndividuos) {
		this.pioresIndividuos = pioresIndividuos;
	}
	
}

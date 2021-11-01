package factorys;

import models.Individuo;
import utils.Constantes;

public class IndividuosFactory {

	public Individuo criarIndividuoValido() {
		Individuo individuo = null;
		
		boolean individuoValido = false;
		while(!individuoValido) {
			
			individuo = new Individuo();
			individuoValido = validarIndividuo(individuo);
		}
		
		return individuo;
	}
	
	public boolean validarIndividuo(Individuo individuo) {

		if(individuo.getCromossomoCaixaA() > Constantes.QUANT_CAIXA_A) {
			return false;
		}
		if(individuo.getCromossomoCaixaB() > Constantes.QUANT_CAIXA_B) {
			return false;
		}
		if(individuo.getCromossomoCaixaC() > Constantes.QUANT_CAIXA_C) {
			return false;
		}
		if(individuo.getCromossomoCaixaD() > Constantes.QUANT_CAIXA_D) {
			return false;
		}
		if(individuo.getPesoTotal() > 25) {
			return false;
		}
		
		return true;
	}
}

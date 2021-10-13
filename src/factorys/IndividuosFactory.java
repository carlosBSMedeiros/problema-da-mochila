package factorys;

import models.Individuo;

public class IndividuosFactory {

	public static Individuo criarIndividuoValido() {
		Individuo individuo = null;
		
		boolean individuoValido = false;
		while(!individuoValido) {
			
			individuo = new Individuo();
			individuoValido = validaIndividuo(individuo);
		}
		
		return individuo;
	}
	
	private static boolean validaIndividuo(Individuo individuo) {

		if(individuo.getPesoTotal() <= 25) {
			return true;
		}
		return false;
	}
}

package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Utils {
	
	public static Double geraNumeroAleatorio() {
        Random gerador = new Random();
        Integer intAleatorio = gerador.nextInt(1000);
        
        Double ret = intAleatorio / 1000.0;
        ret = new BigDecimal(ret).setScale(3, RoundingMode.FLOOR).doubleValue();
        return ret;
	}
	
	public static Integer retornaQuantidadeCaixas(Double[] vetorCaixas) {

		Double numeroAleatorio = geraNumeroAleatorio();
		if (numeroAleatorio > 1) {
			throw new RuntimeException();
		}

		for (int i = 0; i < vetorCaixas.length; i++) {
			if (i == 0 && numeroAleatorio <= vetorCaixas[i]) {
				return 0;
			}
			if (i > 0 && numeroAleatorio > vetorCaixas[i - 1] && numeroAleatorio <= vetorCaixas[i]) {
				return i;
			}
		}

		return -1;
	}

}

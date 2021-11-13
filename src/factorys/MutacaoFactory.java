package factorys;

import java.util.Random;

import models.Individuo;
import utils.Constantes;
import utils.Utils;

public class MutacaoFactory {
    IndividuosFactory fabricaIndividuos;

    MutacaoFactory() {
        this.fabricaIndividuos = new IndividuosFactory();
    }

    public Individuo mutacao(Individuo individuo) {
        if (haMutacao()) {
            mutarIndividuo(individuo);
        }

        return individuo;
    }

    private boolean haMutacao() {
//        Double chanceMutacao = Utils.geraNumeroAleatorio();
//        if (chanceMutacao <= Constantes.PORCENTAGEM_MUTACAO) {
//            return true;
//        }
        return false;
    }

    private Individuo mutarIndividuo(Individuo individuo) {
        Double[] porcentagens = new Double[Constantes.QUANT_CROMOSSOMOS];
        Double porcentagem = (1.0 / Constantes.QUANT_CROMOSSOMOS);
        Double porcentagemAcumulada = porcentagem;

        for (int i = 0; i < porcentagens.length; i++) {
            porcentagens[i] = porcentagemAcumulada;
            porcentagemAcumulada += porcentagemAcumulada;
        }

        Integer indexCromossomoMutado = Utils.verificaIndiceEquivalenteAporcentagem(porcentagens);

        int[] fitaCromossomoMutada = individuo.getFitaCromossomos();

        Random random = new Random();

//        fitaCromossomoMutada[indexCromossomoMutado] = random.nextInt(Constantes.VALOR_MAX_MUTACAO);

        individuo.setFitaCromossomos(fitaCromossomoMutada);

        if (!fabricaIndividuos.validarIndividuo(individuo)) {
            individuo = fabricaIndividuos.criarIndividuoValido();
        }

        return individuo;
    }

}

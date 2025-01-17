package br.pucrs.ages.adocoes.Model.Body;

import java.io.Serializable;
import java.security.InvalidParameterException;

/**
 * Created by Israel Deorce on 10/11/2017.
 */

public class Interesse implements Serializable {

    final String refMenor;
    final String tipoInteresse;

    public Interesse(String refMenor, String tipoInteresse) {
        if (tipoInteresse.equals("favoritar") || tipoInteresse.equals("adotar") || tipoInteresse.equals("apadrinhar"))
            this.tipoInteresse = tipoInteresse;
        else
            throw new InvalidParameterException("Erro: Tipo de interesse deve ser: favoritar|adotar|apadrinhar");
        this.refMenor = refMenor;
    }

}


package br.pucrs.ages.adocoes.Model;

/**
 * Created by Israel Deorce on 18/11/2017.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe foi criada provisoriamente para receber um objeto que recebe uma lista de menores,
 * para possibilitar funcionamento da rota da api eu/menores?interesse=favoritar/apadrinhar/adotar
 */
public class ObjetoDeMenorEu implements Serializable {
    List<Menor> menores;

    public ObjetoDeMenorEu() {
        menores = new ArrayList<>();
    }

    public ObjetoDeMenorEu(List<Menor> menores) {
        this.menores = menores;
    }

    public List<Menor> getMenores() {
        return menores;
    }

    public void setMenores(List<Menor> menores) {
        this.menores = menores;
    }
}

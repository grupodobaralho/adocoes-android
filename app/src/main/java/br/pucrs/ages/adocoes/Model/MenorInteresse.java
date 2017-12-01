package br.pucrs.ages.adocoes.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Israel Deorce on 01/12/2017.
 *
 * Esta classe Model foi criada para receber os interesses da rota da API:
 * {{url}}/eu/menores/{id_menor}
 * Esta rota retorna uma lista de interesses de um menor referente ao
 * interessado logado,
 */

public class MenorInteresse implements Serializable {

    @SerializedName("_id")
    private String idInteresse;
    private String refMenor;
    private String refInteressado;
    private String tipoInteresse;
    private String timeStamp;

    public MenorInteresse(){

    }

    public MenorInteresse(String idInteresse, String refMenor, String refInteressado, String tipoInteresse, String timeStamp) {
        this.idInteresse = idInteresse;
        this.refMenor = refMenor;
        this.refInteressado = refInteressado;
        this.tipoInteresse = tipoInteresse;
        this.timeStamp = timeStamp;
    }

    public String getIdInteresse() {
        return idInteresse;
    }

    public void setIdInteresse(String idInteresse) {
        this.idInteresse = idInteresse;
    }

    public String getRefMenor() {
        return refMenor;
    }

    public void setRefMenor(String refMenor) {
        this.refMenor = refMenor;
    }

    public String getRefInteressado() {
        return refInteressado;
    }

    public void setRefInteressado(String refInteressado) {
        this.refInteressado = refInteressado;
    }

    public String getTipoInteresse() {
        return tipoInteresse;
    }

    public void setTipoInteresse(String tipoInteresse) {
        this.tipoInteresse = tipoInteresse;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

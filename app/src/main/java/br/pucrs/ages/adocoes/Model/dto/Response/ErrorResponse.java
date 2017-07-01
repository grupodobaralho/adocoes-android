package br.pucrs.ages.adocoes.Model.dto.Response;

public class ErrorResponse {

    private String message;
    private String code;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

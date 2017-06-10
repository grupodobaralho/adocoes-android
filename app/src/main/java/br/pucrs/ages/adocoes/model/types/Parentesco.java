package br.pucrs.ages.adocoes.model.types;

/**
 * Created by kuquert on 03/06/17.
 */

public enum Parentesco {

    PAI("pai"),
    MÃE("mãe"),
    IRMÃO("irmão"),
    IRMÃ("irmã"),
    TIO("tio"),
    TIA("tia"),
    PRIMO("primo"),
    PRIMA("prima"),
    AVÔ("avô"),
    AVÓ("avó"),
    TIO_AVÔ("tio avô"),
    TIA_AVÓ("tia avó"),
    BISAVÔ("bisavô"),
    BISAVÓ("bisavó"),
    FILHO("filho"),
    FILHA("filha");


    private String value;

    Parentesco(String value) {
        this.value = value;
    }
}

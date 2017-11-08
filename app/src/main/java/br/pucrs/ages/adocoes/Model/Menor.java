package br.pucrs.ages.adocoes.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.pucrs.ages.adocoes.Model.types.Sexo;

/**
 * Created by kuquert on 03/06/17.
 */

public class Menor implements Serializable {

    @SerializedName("_id")
    private String id;
    private String nome;
    private Sexo sexo;
    private String certidaoNascimento;
    private String dataNascimento;
    private List<String> familiares;
    private List<String> menoresVinculados;
    private String refRaca;
    private boolean saudavel;
    private String descricaoSaude;
    private boolean curavel;
    private boolean deficienciaFisica;
    private boolean deficienciaMental;
    private String guiaAcolhimento;
    private String refCidade;
    private String refAbrigo;
    private String processoPoderFamiliar;
    private List<Usuario> interessados;
    private List<RefMidia> midias;
    private boolean ativo;

    public Menor(){

    }

    public Menor(String nome) {
        this.nome = nome;

    }

    public Menor(String nome, String id){
        this.nome = nome;
        this.id = id;
    }

    public Menor(String id, String nome, Sexo sexo, String certidaoNascimento, String dataNascimento, List<String> familiares, List<String> menoresVinculados, String refRaca, boolean saudavel, String descricaoSaude, boolean curavel, boolean deficienciaFisica, boolean deficienciaMental, String guiaAcolhimento, String refCidade, String refAbrigo, String processoPoderFamiliar, List<Usuario> interessados, List<RefMidia> midias, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.certidaoNascimento = certidaoNascimento;
        this.dataNascimento = dataNascimento;
        this.familiares = familiares;
        this.menoresVinculados = menoresVinculados;
        this.refRaca = refRaca;
        this.saudavel = saudavel;
        this.descricaoSaude = descricaoSaude;
        this.curavel = curavel;
        this.deficienciaFisica = deficienciaFisica;
        this.deficienciaMental = deficienciaMental;
        this.guiaAcolhimento = guiaAcolhimento;
        this.refCidade = refCidade;
        this.refAbrigo = refAbrigo;
        this.processoPoderFamiliar = processoPoderFamiliar;
        this.interessados = interessados;
        this.midias = midias;
        this.ativo = ativo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setCertidaoNascimento(String certidaoNascimento) {
        this.certidaoNascimento = certidaoNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setFamiliares(List<String> familiares) {
        this.familiares = familiares;
    }

    public void setMenoresVinculados(List<String> menoresVinculados) {
        this.menoresVinculados = menoresVinculados;
    }

    public void setRefRaca(String refRaca) {
        this.refRaca = refRaca;
    }

    public void setSaudavel(boolean saudavel) {
        this.saudavel = saudavel;
    }

    public void setDescricaoSaude(String descricaoSaude) {
        this.descricaoSaude = descricaoSaude;
    }

    public void setCuravel(boolean curavel) {
        this.curavel = curavel;
    }

    public void setDeficienciaFisica(boolean deficienciaFisica) {
        this.deficienciaFisica = deficienciaFisica;
    }

    public void setDeficienciaMental(boolean deficienciaMental) {
        this.deficienciaMental = deficienciaMental;
    }

    public void setGuiaAcolhimento(String guiaAcolhimento) {
        this.guiaAcolhimento = guiaAcolhimento;
    }

    public void setRefCidade(String refCidade) {
        this.refCidade = refCidade;
    }

    public void setRefAbrigo(String refAbrigo) {
        this.refAbrigo = refAbrigo;
    }

    public void setProcessoPoderFamiliar(String processoPoderFamiliar) {
        this.processoPoderFamiliar = processoPoderFamiliar;
    }

    public void setInteressados(List<Usuario> interessados) {
        this.interessados = interessados;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getCertidaoNascimento() {
        return certidaoNascimento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public List<String> getFamiliares() {
        return familiares;
    }

    public List<String> getMenoresVinculados() {
        return menoresVinculados;
    }

    public String getRefRaca() {
        return refRaca;
    }

    public boolean isSaudavel() {
        return saudavel;
    }

    public String getDescricaoSaude() {
        return descricaoSaude;
    }

    public boolean isCuravel() {
        return curavel;
    }

    public boolean isDeficienciaFisica() {
        return deficienciaFisica;
    }

    public boolean isDeficienciaMental() {
        return deficienciaMental;
    }

    public String getGuiaAcolhimento() {
        return guiaAcolhimento;
    }

    public String getRefCidade() {
        return refCidade;
    }

    public String getRefAbrigo() {
        return refAbrigo;
    }

    public String getProcessoPoderFamiliar() {
        return processoPoderFamiliar;
    }

    public List<Usuario> getInteressados() {
        return interessados;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getNome() {
        return nome;
    }
    public String getId() { return this.id; }

    public List<RefMidia> getMidias() {
        return midias;
    }

    public void setMidias(List<RefMidia> midias) {
        this.midias = midias;
    }

    public  int getAge() {

        int age = 0;
        try {

            Calendar now = Calendar.getInstance();
            Calendar dob = toCalendar(dataNascimento);

            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH);
            int month2 = dob.get(Calendar.MONTH);
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age ;
    }

    public static Calendar toCalendar(final String iso8601string)
            throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid length", 0);
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        calendar.setTime(date);
        return calendar;
    }

}

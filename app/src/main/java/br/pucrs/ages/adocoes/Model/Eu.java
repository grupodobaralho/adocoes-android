package br.pucrs.ages.adocoes.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Israel Deorce on 11/11/2017.
 */

public class Eu implements Serializable {

    //Os atributos seguem o Json necessário para a API
    @SerializedName("_id")
    private String id;
    private String name;
    private String cpf;
    private String nomeConjuge;
    private String email;
    private String dataNascimento;
    private String renda;
    private String refUsuario;
    private String pontoSexo;
    private String pontoIdade;
    private boolean ativo;

    //Estas listas ainda precisam ser mais bem implementadas e podem sofrer alterações
    private List<String> visualizacoes;
    private List<String> interesses;
    private List<String> telefones;
    private List<String> enderecos;
    private List<String> outrosDocumentos;
    private List<String> comprovantesRenda;

    public Eu(){

    }
    public Eu(String id, String name, String cpf, String nomeConjuge, String email, String dataNascimento, String renda, String refUsuario, String pontoSexo, String pontoIdade, boolean ativo, List<String> visualizacoes, List<String> interesses, List<String> telefones, List<String> enderecos, List<String> outrosDocumentos, List<String> comprovantesRenda) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.nomeConjuge = nomeConjuge;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.renda = renda;
        this.refUsuario = refUsuario;
        this.pontoSexo = pontoSexo;
        this.pontoIdade = pontoIdade;
        this.ativo = ativo;
        this.visualizacoes = visualizacoes;
        this.interesses = interesses;
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.outrosDocumentos = outrosDocumentos;
        this.comprovantesRenda = comprovantesRenda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRenda() {
        return renda;
    }

    public void setRenda(String renda) {
        this.renda = renda;
    }

    public String getRefUsuario() {
        return refUsuario;
    }

    public void setRefUsuario(String refUsuario) {
        this.refUsuario = refUsuario;
    }

    public String getPontoSexo() {
        return pontoSexo;
    }

    public void setPontoSexo(String pontoSexo) {
        this.pontoSexo = pontoSexo;
    }

    public String getPontoIdade() {
        return pontoIdade;
    }

    public void setPontoIdade(String pontoIdade) {
        this.pontoIdade = pontoIdade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<String> getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(List<String> visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public List<String> getInteresses() {
        return interesses;
    }

    public void setInteresses(List<String> interesses) {
        this.interesses = interesses;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public List<String> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<String> enderecos) {
        this.enderecos = enderecos;
    }

    public List<String> getOutrosDocumentos() {
        return outrosDocumentos;
    }

    public void setOutrosDocumentos(List<String> outrosDocumentos) {
        this.outrosDocumentos = outrosDocumentos;
    }

    public List<String> getComprovantesRenda() {
        return comprovantesRenda;
    }

    public void setComprovantesRenda(List<String> comprovantesRenda) {
        this.comprovantesRenda = comprovantesRenda;
    }
}

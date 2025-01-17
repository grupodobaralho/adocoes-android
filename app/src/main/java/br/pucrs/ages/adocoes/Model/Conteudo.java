package br.pucrs.ages.adocoes.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by kuquert on 03/06/17.
 */

public class Conteudo implements Serializable {
    private String titulo;
    private String conteudo;
    private List<String> midia;
    private Date timestampCriacao;
    private Date timestampInicio;
    private Date timestampFim;
    private boolean ativo;

    public Conteudo(String titulo, String conteudo, List<String> midia, Date timestampCriacao, Date timestampInicio, Date timestampFim, boolean ativo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.midia = midia;
        this.timestampCriacao = timestampCriacao;
        this.timestampInicio = timestampInicio;
        this.timestampFim = timestampFim;
        this.ativo = ativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public List<String> getMidia() {
        return midia;
    }

    public void setMidia(List<String> midia) {
        this.midia = midia;
    }

    public Date getTimestampCriacao() {
        return timestampCriacao;
    }

    public void setTimestampCriacao(Date timestampCriacao) {
        this.timestampCriacao = timestampCriacao;
    }

    public Date getTimestampInicio() {
        return timestampInicio;
    }

    public void setTimestampInicio(Date timestampInicio) {
        this.timestampInicio = timestampInicio;
    }

    public Date getTimestampFim() {
        return timestampFim;
    }

    public void setTimestampFim(Date timestampFim) {
        this.timestampFim = timestampFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
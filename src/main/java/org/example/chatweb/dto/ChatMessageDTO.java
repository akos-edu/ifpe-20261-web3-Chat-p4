package org.example.chatweb.dto;

public class ChatMessageDTO {

    public enum Tipo {
        JOIN, LEAVE, CHAT
    }

    private Tipo tipo;
    private String conteudo;
    private String remetente;
    private String sala;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Tipo tipo, String conteudo, String remetente, String sala) {
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.sala = sala;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}

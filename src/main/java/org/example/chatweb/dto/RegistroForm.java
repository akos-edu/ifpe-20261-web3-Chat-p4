package org.example.chatweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistroForm {

    @NotBlank(message = "Informe o nome")
    @Size(max = 120)
    private String nome;

    @NotBlank(message = "Informe o e-mail")
    @Email(message = "E-mail invalido")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Informe a senha")
    @Size(min = 4, max = 100, message = "A senha deve ter entre 4 e 100 caracteres")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

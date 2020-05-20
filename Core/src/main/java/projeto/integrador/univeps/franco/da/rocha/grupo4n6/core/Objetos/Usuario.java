package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos;

import java.util.ArrayList;

public class Usuario {
    private String Nome, ChaveLogin, Email;
    public ArrayList<String> Inscricoes = new ArrayList<>();

    public Usuario(String nome, String chaveLogin, String email) {
        Nome = nome;
        ChaveLogin = chaveLogin;
        Email = email;
    }

    public String getNome() {
        return Nome;
    }

    public String getChaveLogin() {
        return ChaveLogin;
    }

    public String getEmail() {
        return Email;
    }
}

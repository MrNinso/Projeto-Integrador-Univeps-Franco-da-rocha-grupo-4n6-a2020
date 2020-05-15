package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.objetos;

import com.developer.base.utils.lib.object.BaseList;

public class Usuario {
    private String Nome, ChaveLogin, Email;
    public BaseList<String> Inscricoes = new BaseList<>();

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

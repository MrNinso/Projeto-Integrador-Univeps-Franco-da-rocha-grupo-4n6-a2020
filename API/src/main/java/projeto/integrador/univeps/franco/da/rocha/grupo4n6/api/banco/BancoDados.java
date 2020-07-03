package projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco;

import java.util.ArrayList;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Evento;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Usuario;

public abstract class BancoDados {
    public abstract Usuario getUserByEmail(String email);

    public abstract ArrayList<Evento> getEventosPage(String page);

    public abstract boolean inscreverUsuarioEvento(String email, String idEvento);

    public abstract boolean desinscreverUsuarioEvento(String email, String idEvento);

    public abstract String logarUsuario(String email, String chave);

    public abstract Exception cadastrarUsuario(Usuario u); //TODO MUDAR PARA byte para desempenho

    public abstract String getChaveByEmail(String email);

    public abstract Exception connectar();

    public abstract Exception cadastrarEvento(Evento e);

    public abstract Exception deletarEvento(String ID);
}

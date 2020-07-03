package projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.drives;

import java.util.ArrayList;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.BancoDados;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Evento;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Usuario;

public class mongoDriver extends BancoDados {

    @Override
    public Exception connectar() {
        return null;
    }

    @Override
    public Usuario getUserByEmail(String email) {
        return null;
    }

    @Override
    public String getChaveByEmail(String email) {
        return null;
    }

    @Override
    public ArrayList<Evento> getEventosPage(String page) {
        return null;
    }

    @Override
    public Exception cadastrarUsuario(Usuario u) {
        return null;
    }

    @Override
    public String logarUsuario(String email, String chave) {
        return null;
    }

    @Override
    public Exception cadastrarEvento(Evento e) {
        return null;
    }

    @Override
    public Exception deletarEvento(String ID) {
        return null;
    }

    @Override
    public boolean inscreverUsuario(String chave, String email, String idEvento) {
        return false;
    }
}

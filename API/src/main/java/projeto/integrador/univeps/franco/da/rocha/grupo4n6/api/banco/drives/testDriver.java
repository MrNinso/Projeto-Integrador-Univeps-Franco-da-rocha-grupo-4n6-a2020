package projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.drives;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.BancoDados;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Evento;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Usuario;

public class testDriver extends BancoDados {
    private Usuario mUsuario;

    @Override
    public Usuario getUserByEmail(String email) {
        return mUsuario;
    }

    @Override
    public ArrayList<Evento> getEventosPage(String page) {
        int p = Integer.parseInt(page);
        ArrayList<Evento> eventos = new ArrayList<>();

        if (p < 5) {
            for (int i = 0; i < 50; i++) {
                String id = p+""+i;
                eventos.add(new Evento(
                        id,
                        String.format("Evento %s", id),
                        "Um descricao bemmmmmm  loooooooooooooooogggaaaaaaaaaaaaaaaa Um descricao bemmmmmm  loooooooooooooooogggaaaaaaaaaaaaaaaa Um descricao bemmmmmm  loooooooooooooooogggaaaaaaaaaaaaaaaa Um descricao bemmmmmm  loooooooooooooooogggaaaaaaaaaaaaaaaa",
                        String.format("AV. Q existe, %s", id),
                        DateUtils.addHours(new Date(), p),
                        DateUtils.addDays(new Date(), i)
                ));
            }
        }

        return eventos;
    }

    @Override
    public boolean inscreverUsuarioEvento(String email, String idEvento) {
        return true;
    }

    @Override
    public boolean desinscreverUsuarioEvento(String email, String idEvento) {
        return true;
    }

    @Override
    public String logarUsuario(String email, String chave) {
        return null;
    }

    @Override
    public Exception cadastrarUsuario(Usuario u) {
        return null;
    }

    @Override
    public String getChaveByEmail(String email) {
        return null;
    }

    @Override
    public Exception connectar() {
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
}

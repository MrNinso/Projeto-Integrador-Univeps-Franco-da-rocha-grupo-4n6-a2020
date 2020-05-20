package projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.Routes;

import com.developer.Simple.Routers.Router;
import com.developer.Simple.core.ClientRequest;
import com.developer.Simple.core.HTTPCodes;
import com.developer.Simple.core.ServerResponse;


import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.API;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.API.Requisicao.Constantes;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.crypto.Hmacsha512;

public class UsuarioRouter extends Router {

    public UsuarioRouter(Builder b) {
        super(b);
    }

    @Override
    public ServerResponse request(ClientRequest clientRequest) {
        String email = clientRequest.Headers.getFirst(Constantes.EMAIL);
        String assinatura = clientRequest.Headers.getFirst(Constantes.ASSINATURA);
        if (email != null && assinatura != null) {
            String senha = API.Banco.getChaveByEmail(email);

            if (Hmacsha512.assinar(clientRequest.body, email, senha).equals(assinatura))
                return super.request(clientRequest);
            else
                return new ServerResponse(HTTPCodes.FORBIDDEN);
        }

        return new ServerResponse(HTTPCodes.BAD_REQUEST);
    }
}

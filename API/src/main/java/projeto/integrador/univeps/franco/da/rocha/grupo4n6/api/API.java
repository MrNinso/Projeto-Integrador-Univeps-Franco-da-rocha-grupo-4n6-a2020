package projeto.integrador.univeps.franco.da.rocha.grupo4n6.api;

import com.developer.Simple.HTTP.HTTPServer;
import com.developer.Simple.Routers.Router;
import com.developer.Simple.core.HTTPCodes;
import com.developer.Simple.core.Server;
import com.developer.Simple.core.ServerResponse;
import com.google.gson.Gson;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.Routes.AdminRouter;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.Routes.UsuarioRouter;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.BancoDados;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.drives.mongoDriver;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.api.banco.drives.testDriver;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.API.Requisicao.Constantes;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.API.Requisicao.LoginArgumento;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Evento;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Usuario;

public class API implements Constantes {

    public static BancoDados Banco;

    public static void main(String[] args) throws Exception {
        Banco = new testDriver();
        Exception e = Banco.connectar();
        if (e != null) {
            e.printStackTrace(); //TODO CRIAR SERVIÇO DE LOG
            System.exit(1);
        }

        HTTPServer server = new HTTPServer(8855, buildRoutes());

        server.start();

        System.out.println("Running");
    }

    private static Server.OnResquest buildRoutes() {
        return new Router(routes -> {
            routes.put("Eventos", clientRequest -> {
                if (clientRequest.URI.length != 1)
                    return new ServerResponse(HTTPCodes.BAD_REQUEST);

                ArrayList<Evento> e = Banco.getEventosPage(clientRequest.URI[0]);

                return e.size() > 0 ?
                        new ServerResponse(HTTPCodes.OK, new Gson().toJson(e).getBytes()) :
                        new ServerResponse(HTTPCodes.NO_CONTENT);
            });

            routes.put("Admin", new AdminRouter(adminRoutes -> {
                adminRoutes.put("index", clientRequest -> {
                    if (clientRequest.Method.equals("ADICIONAR")) {
                        Exception e = Banco.cadastrarEvento(new Gson().fromJson(clientRequest.body, Evento.class));
                        return e != null ?
                                new ServerResponse(HTTPCodes.INTERNAL_SERVER_ERROR, e.getMessage().getBytes()) :
                                new ServerResponse(HTTPCodes.OK);
                    } else if (clientRequest.Method.equals("REMOVER")){
                        Exception e = Banco.deletarEvento(clientRequest.body);
                        return e != null ?
                                new ServerResponse(HTTPCodes.INTERNAL_SERVER_ERROR, e.getMessage().getBytes()) :
                                new ServerResponse(HTTPCodes.OK);
                    } else {
                        return new ServerResponse(HTTPCodes.METHOD_NOT_ALLOWED);
                    }
                });
                return adminRoutes;
            }));

            routes.put("Logar", clientRequest -> {
                Map<?, ?> args = new Gson().fromJson(clientRequest.body, Map.class);
                Object email = args.get(LoginArgumento.EMAIL);
                Object chave = args.get(LoginArgumento.CHAVE_LOGIN);

                if (email != null && chave != null)
                    if (email instanceof String && chave instanceof String) {
                        String id = Banco.logarUsuario((String) email, (String) chave);
                        return  id != null ?
                                new ServerResponse(HTTPCodes.OK, id.getBytes()) :
                                new ServerResponse(HTTPCodes.FORBIDDEN);
                    }
                return new ServerResponse(HTTPCodes.BAD_REQUEST);
            });

            routes.put("Cadastrar", clientRequest -> {
                Usuario u = new Gson().fromJson(clientRequest.body, Usuario.class);

                if (u != null) {
                    Exception e = Banco.cadastrarUsuario(u);
                    return e != null ?
                            new ServerResponse(HTTPCodes.CREATED) :
                            new ServerResponse(HTTPCodes.NOT_ACCEPTABLE, e.getMessage().getBytes());
                }
                return new ServerResponse(HTTPCodes.BAD_REQUEST);
            });

            routes.put("Usuario", new UsuarioRouter(routasUsuario -> {
                routasUsuario.put("Evento", new Router(rotasEventos -> {
                    rotasEventos.put("Inscrever", clientRequest -> {
                        String id = clientRequest.body;
                        if (id == null || "".equals(id))
                            return new ServerResponse(HTTPCodes.BAD_REQUEST);
                        else {
                            String email = clientRequest.Headers.getFirst(Constantes.EMAIL);
                            return Banco.inscreverUsuarioEvento(email, id) ?
                                new ServerResponse(HTTPCodes.OK) :
                                new ServerResponse(HTTPCodes.INTERNAL_SERVER_ERROR);
                        }
                    });

                    rotasEventos.put("Desinscrever", clientRequest -> {
                        String id = clientRequest.body;
                        if (id == null || "".equals(id))
                            return new ServerResponse(HTTPCodes.BAD_REQUEST);
                        else {
                            String email = clientRequest.Headers.getFirst(Constantes.EMAIL);
                            return Banco.desinscreverUsuarioEvento(email, id) ?
                                    new ServerResponse(HTTPCodes.OK) :
                                    new ServerResponse(HTTPCodes.INTERNAL_SERVER_ERROR);
                        }
                    });

                    return rotasEventos;
                }));

                routasUsuario.put("Conta", clientRequest -> {
                    byte b = clientRequest.body.getBytes()[0];
                    clientRequest.body = new String(ArrayUtils.remove(clientRequest.body.getBytes(), 0));

                    TreeMap<?, ?> args = new Gson().fromJson(clientRequest.body, TreeMap.class);

                    return new ServerResponse(HTTPCodes.BAD_REQUEST);
                });

                return routasUsuario;
            }));

            return routes;
        });
    }
}
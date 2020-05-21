package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.API.remote;

import android.content.Context;

import com.developer.base.utils.lib.object.BaseMap;
import com.developer.base.utils.lib.object.BaseTask;
import com.developer.base.utils.lib.object.BaseThreadPool;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.API.local.DBHelper;

public class APIRequest {
    private static APIRequest instance;

    private BaseThreadPool pool = new BaseThreadPool();
    private DBHelper dbHelper;

    private APIRequest(Context c) {
        dbHelper = new DBHelper(c);
        pool = new BaseThreadPool();
    }

    public static APIRequest getInstance(Context c) {
        if (instance == null) {
            instance = new APIRequest(c);
        }

        return instance;
    }

    public void logar(String chave, OnResposta l) {
        pool.addTask(new BaseTask(task -> {
            doRequest(new BaseMap<>(), "dklsajkdlakd");//TODO REMOVER
            return null;
        }));
    }

    public void atualizarUser(String chave) {

    }

    private String doRequest(BaseMap<String, Object> postMapa, String chave, String Email) throws Exception {
        URL url = new URL("http://192.168.0.17:8000/Login");

        StringBuilder postBuilder = new StringBuilder();
        for (Map.Entry<String,Object> param : postMapa.entrySet()) {
            if (postBuilder.length() != 0)
                postBuilder.append('&');

            postBuilder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postBuilder.append('=');
            postBuilder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection(); // TODO MUDAR para https
        byte[] postBytes = postBuilder.toString().getBytes(); // TODO TESTAR UTF-8

        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conexao.setRequestProperty("Content-Length", String.valueOf(postBytes.length));
        conexao.setDoOutput(true);
        conexao.getOutputStream().write(postBytes);

        System.out.println(conexao.getResponseCode());

        return new String(IOUtils.toByteArray(conexao.getInputStream())); //TODO fechar objetos
    }

    public interface OnResposta {
        byte logando = 0x0;
        byte usuarioAtualizado = 0x1;

        void onResposta(byte Acao, Object resposta, Exception e);
    }
}

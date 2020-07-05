package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.base.utils.lib.object.BaseList;
import com.developer.base.utils.lib.tool.BaseCrypto;

import java.util.Date;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.API.local.DBHelper;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.adapters.EventsAdapters;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Evento;

public class MainActivity extends AppCompatActivity { //TODO VER O QUE FAZ COM O TEMA ESCURO 10

    //TODO PENSAR EM SOCKETS

    private RecyclerView EventList;
    private DBHelper mDBHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.inicio);

        EventList = findViewById(R.id.eventList);

        mDBHelper.updateEventos(new BaseList<>(61, index ->
            new Evento(
                    String.valueOf(index),
                    String.format("Evento %d", index),
                    String.format("Essa é a Descrição de um evento de test %d apenas para visualizar", index),
                    "Av. Alexios Jafet, 1569 - Jardim Ipanema (Zona Oeste), São Paulo - SP, 05181-010",
                    new Date(),
                    new Date(),
                    ((index%2) == 0) ? null : ""
            )
        ));

        EventsAdapters ea = new EventsAdapters(mDBHelper.getEventos(), this.getResources());

        ea.setOnLocalClickListener((v) -> {
            String uri = "https://maps.google.com/maps?daddr="+((TextView) v).getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            try { //TODO TESTAR EM VM SEM GOOGLE MAPS
                startActivity(intent);
            } catch(ActivityNotFoundException ex) {
                try {
                    Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(unrestrictedIntent);
                } catch(ActivityNotFoundException innerEx) {
                    Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show(); //TODO Colocar Dialog para o erro
                }
            }
        });

        EventList.setAdapter(ea);

        EventList.setHasFixedSize(true);
        EventList.setLayoutManager(new LinearLayoutManager(this));
    }
}
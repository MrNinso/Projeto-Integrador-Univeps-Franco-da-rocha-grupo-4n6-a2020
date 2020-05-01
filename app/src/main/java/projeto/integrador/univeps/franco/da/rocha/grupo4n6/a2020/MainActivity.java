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
import com.developer.base.utils.lib.tool.BaseDevice;

import java.util.Date;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.adapters.EventsAdapters;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.objetos.Event;

public class MainActivity extends AppCompatActivity { //TODO VER O QUE FAZ COM O TEMA ESCURO 10

    //TODO PENSAR EM SOCKETS

    private RecyclerView EventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.inicio);

        EventList = findViewById(R.id.eventList);

        BaseList<Event> e = new BaseList<>(61, index ->
            new Event(
                    String.valueOf(index),
                    String.format("Evento %d", index),
                    "a",
                    "Av. Alexios Jafet, 1569 - Jardim Ipanema (Zona Oeste), São Paulo - SP, 05181-010",
                    new Date(),
                    new Date(),
                    ((index%2) == 0) ? null : ""
            )
        );

        EventsAdapters ea = new EventsAdapters(e, this.getResources());

        ea.setOnLocalClickListener((v) -> {
            String uri = "http://maps.google.com/maps?daddr="+((TextView) v).getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            try { //TODO TESTAR EM VM SEM GOOGLE MAPS
                startActivity(intent);
            } catch(ActivityNotFoundException ex) {
                try {
                    Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(unrestrictedIntent);
                } catch(ActivityNotFoundException innerEx) {
                    Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
                }
            }
        });

        EventList.setAdapter(ea);

        EventList.setHasFixedSize(true);
        EventList.setLayoutManager(new LinearLayoutManager(this));


    }
}
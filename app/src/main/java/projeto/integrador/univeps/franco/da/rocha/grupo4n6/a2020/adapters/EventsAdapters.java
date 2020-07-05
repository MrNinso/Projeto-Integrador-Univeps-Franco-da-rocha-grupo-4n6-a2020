package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.adapters;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.base.utils.lib.object.BaseList;

import java.text.SimpleDateFormat;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.API.remote.APIRequest;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.R;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos.Evento;

public class EventsAdapters extends RecyclerView.Adapter<EventsAdapters.Holder> {

    private BaseList<Evento> eventos;
    private Resources Res;
    private onLocalClicked OnLocalClickListener;
    private final SimpleDateFormat simpleDateFormat;

    @SuppressLint("SimpleDateFormat")
    public EventsAdapters(BaseList<Evento> eventos, Resources res) {
        this.eventos = eventos;
        Res = res;
        simpleDateFormat = new SimpleDateFormat(res.getString(R.string.formatoData));
    }

    @NonNull
    @Override
    public EventsAdapters.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_view, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapters.Holder holder, int position) {
        Evento e = this.eventos.get(position);

        if (e.getCachedImgURL() != null)
            holder.banner.setVisibility(View.VISIBLE);//TODO CARREGAR IMAGEM EFICINETE
        else
            holder.banner.setVisibility(View.GONE);

        holder.titulo.setText(e.getTitulo());
        holder.des.setText(e.getDes());
        holder.local.setText(e.getLocal());

        holder.data.setText(String.format("%s - %s",
                simpleDateFormat.format(e.getDataEventoInico()),
                simpleDateFormat.format(e.getDataEventoFim()))
        );

        if (OnLocalClickListener != null)
        holder.local.setOnClickListener((v) -> OnLocalClickListener.click(v));

        holder.participar.setTag(e.getID());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public void setOnLocalClickListener(onLocalClicked o) {
        this.OnLocalClickListener = o;
    }

    public void atualizarDados(BaseList<Evento> e) {
        eventos = e;
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public TextView titulo, des, data, local;
        public Button participar;
        public ImageView banner;

        public Holder(@NonNull View v) {
            super(v);
            this.titulo = v.findViewById(R.id.eventTitle);
            this.des = v.findViewById(R.id.eventDes);
            this.data = v.findViewById(R.id.eventDate);
            this.local = v.findViewById(R.id.eventLocal); //TODO MOSTRAR NO MAPA
            this.participar = v.findViewById(R.id.eventParticipateBtn);
            this.banner = v.findViewById(R.id.eventBanner);

            this.participar.setOnClickListener(this::onClick);
        }

        private void onClick(View v) {
            String id = (String) v.getTag();
            APIRequest.getInstance(v.getContext()).logar("adkdlaskd", (Acao, resposta, e) -> {});
            System.out.println(id); //TODO CHAMAR TELA DE LOGIN CASO USUARIO NÃO TENHA LOGADO
        }
    }

    public interface onLocalClicked {
        void click(View v);
    }
}

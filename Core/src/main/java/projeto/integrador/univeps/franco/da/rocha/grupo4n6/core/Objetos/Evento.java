package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Objetos;

import java.util.Date;

public class Evento {
    private String ID, Titulo, Des, Local;
    private Date DataEventoInico, DataEventoFim;
    private String CachedImgURL;

    public Evento(String id, String titulo, String des, String local, Date dataEventoInico, Date dataEventoFim) {
        this.ID = id;
        this.Titulo = titulo;
        this.Des = des;
        this.Local = local;
        this.DataEventoInico = dataEventoInico;
        this.DataEventoFim = dataEventoFim;
    }

    public Evento(String id, String titulo, String des, String local, Date dataEventoInico, Date dataEventoFim, String cachedImgURL) {
        this.ID = id;
        this.Titulo = titulo;
        this.Des = des;
        this.Local = local;
        this.DataEventoInico = dataEventoInico;
        this.DataEventoFim = dataEventoFim;
        this.CachedImgURL = cachedImgURL;
    }

    public String getID() {
        return ID;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getDes() {
        return Des;
    }

    public String getLocal() {
        return Local;
    }

    public Date getDataEventoInico() {
        return DataEventoInico;
    }

    public Date getDataEventoFim() {
        return DataEventoFim;
    }

    public String getCachedImgURL() {
        return CachedImgURL;
    }
}

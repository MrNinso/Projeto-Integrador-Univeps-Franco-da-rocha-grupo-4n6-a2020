package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.API.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.developer.base.utils.lib.extras.SQLiteBaseHelper;
import com.developer.base.utils.lib.object.BaseList;

import java.util.Date;

import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.objetos.Evento;
import projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.objetos.Usuario;

public class DBHelper extends SQLiteBaseHelper {


    public DBHelper(@Nullable Context context) {
        super(context, "cache.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String eventos =
            "CREATE TABLE EVENTOS(" +
                "ID TEXT PRIMARY KEY," +
                "TITULO TEXT," +
                "DES TEXT," +
                "LOCAL TEXT," +
                "DATA_INICIO INTEGER," +
                "DATA_FINAL INTEGER," +
                "CACHEDIMGURL TEXT" +
            ")";

        db.execSQL(eventos);

        String Usuario =
            "CREATE TABLE USUARIO(" +
                "NOME TEXT," +
                "CHAVE TEXT," +
                "EMAIL TEXT" +
            ")";

        db.execSQL(Usuario);

        String inscricoes = "CREATE TABLE INSCRICOES(ID TEXT PRIMARY KEY)";

        db.execSQL(inscricoes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean updateInscricoes(BaseList<String> inscricoes) {

        return doTransaction(helper -> {
            SQLiteDatabase db = getWritableDatabase();
            final boolean[] r = { true };
            db.beginTransaction();
            db.delete("INSCRICOES", null, null);
            inscricoes.forEachBreakable((index, s) -> {
                ContentValues cv = new ContentValues();

                cv.put("ID", s);

                r[0] = db.insert("INSCRICOES", null, cv) > 0;

                return r[0] ? EachBreakable.CONTINUE : EachBreakable.BREAK;
            });

            return r[0];
        });

    }

    public boolean updateEventos(BaseList<Evento> eventos) {
        return doTransaction(helper -> {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("EVENTOS", null, null);
            final boolean[] r = {true};

            eventos.forEachBreakable((index, e) -> {
                ContentValues cv = new ContentValues();

                cv.put("ID", e.getID());
                cv.put("TITULO", e.getTitulo());
                cv.put("DES", e.getDes());
                cv.put("LOCAL", e.getLocal());
                cv.put("DATA_INICIO", e.getDataEventoInico().getTime());
                cv.put("DATA_FINAL", e.getDataEventoFim().getTime());
                cv.put("CACHEDIMGURL", e.getCachedImgURL());

                r[0] = db.insert("EVENTOS", null, cv) > 0;

                return r[0] ? EachBreakable.CONTINUE : EachBreakable.BREAK;
            });

            return r[0];
        });
    }

    public boolean updateUsuario(Usuario u) {
        SQLiteDatabase db = getWritableDatabase();

        return doTransaction(helper -> {
            db.delete("USUARIO", null, null);

            ContentValues cv = new ContentValues();

            cv.put("NOME", u.getNome());
            cv.put("CHAVE", u.getChaveLogin());
            cv.put("EMAIL", u.getEmail());

            return db.insert("USUARIO", null, cv) > 0 && updateInscricoes(u.Inscricoes);
        });
    }

    public BaseList<String> getInscricoesIDs() {
        return readList("SELECT * FROM INSCRICOES", null, (c, count) ->
            c.getString(0)
        );
    }

    public BaseList<Evento> getInscricoesEventos() {
        StringBuilder array = new StringBuilder();

        getInscricoesIDs().forEach((index, s) -> {
            array.append(s);
            array.append(",");
        });

        if (array.length() == 0)
            return new BaseList<>();

        array.delete(array.length()-1, array.length());


        return readList(
            String.format("SELECT * FROM EVENTOS WHERE ID IN (%s)", array.toString()), null,
            this::cursorToEventList
        );
    }

    public BaseList<Evento> getEventos() {
        return readList("SELECT * FROM EVENTOS", null, this::cursorToEventList);
    }

    public BaseList<Evento> getEventosBy(String whereArgs) {
        return readList(String.format("SELECT * FROM EVENTOS WHERE %s", whereArgs), null,
            this::cursorToEventList
        );
    }

    public Usuario getUsuario() {
        BaseList<String> i = getInscricoesIDs();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM USUARIO", null);
        Usuario u = null;

        if (c.moveToFirst()) {
            u = new Usuario(
                c.getString(0),
                c.getString(1),
                c.getString(2)
            );

            u.Inscricoes = i;
        }

        return u;
    }

    private Evento cursorToEventList(Cursor c, int count) {
        return new Evento(
            c.getString(0),
            c.getString(1),
            c.getString(2),
            c.getString(3),
            new Date(c.getLong(4)),
            new Date(c.getLong(5)),
            c.getString(6)
        );
    }

}

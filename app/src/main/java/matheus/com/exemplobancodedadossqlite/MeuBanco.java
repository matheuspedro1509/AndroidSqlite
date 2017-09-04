package matheus.com.exemplobancodedadossqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by logonrm on 21/08/2017.
 */

public class MeuBanco extends SQLiteOpenHelper {

    private static final String DBNAME = "MeuBanco";
    private static final Integer VERSAO = 1;
    private static final String TB_CLIENTE = "cliente";

    public MeuBanco(Context context){
        super(context,DBNAME,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE cliente (" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " email TEXT," +
                " datacadastro INTEGER NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Cliente cliente){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",cliente.getNome());
        cv.put("email", cliente.getEmail());
        cv.put("dataCadastro", cliente.getDataCadastro());

        db.insert(TB_CLIENTE, null,cv);
        db.close();
    }

    public List<Cliente> listarTodos(){
        SQLiteDatabase db = getReadableDatabase();
        List<Cliente> clientes = new ArrayList<Cliente>();

        Cursor c = db.query(
                TB_CLIENTE,
                new String[]{"id, nome, email, dataCadastro"},
                null,
                null,
                null,
                null,
                "nome"
        );

        while(c.moveToNext()){
            Cliente cliente = new Cliente();
            cliente.setId(c.getInt(0));
            cliente.setNome(c.getString(1));
            cliente.setEmail(c.getString(2));
            cliente.setDataCadastro(c.getLong(3));

            clientes.add(cliente);
        }

        c.close();

        db.close();

        return clientes;
    }

    public void alterar (Cliente c){
    SQLiteDatabase db = getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("nome",c.getNome());
    cv.put("email",c.getEmail());
    db.update(TB_CLIENTE,cv," id = ? ",new String[]{String.valueOf(c.getId()) });

    db.close();
    }
    public void excluir (int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TB_CLIENTE, "id = ?", new String[] { String.valueOf(id) });

        db.close();
    }
}

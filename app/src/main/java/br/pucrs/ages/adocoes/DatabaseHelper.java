package br.pucrs.ages.adocoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import br.pucrs.ages.adocoes.Model.Menor;

//http://guides.codepath.com/android/local-databases-with-sqliteopenhelper
//https://www.youtube.com/watch?v=KUq5wf3Mh0c
/**
 * Created by israeldeorce on 20/09/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Informacoes da DB
    public static final String DATABASE_NAME = "AdocoesLocal.db";
    public static final int DATABASE_VERSION = 1;

    //Nome das tabelas
    public static final String TABLE_FAVORITOS = "Menores_Favoritos";

    //Colunas das Tabelas
    public static final String COL_1 = "id"; //REMOVER ESTE FUTURAMENTE
    public static final String COL_2 = "nome";


    /**
     * Padrao de Projeto Singleton que instancia o db para toda a aplicacao
     */
    private static DatabaseHelper sInstance;
    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use o Contexto da aplicacao, o que garantirah que voce
        // nao mexa no contexto de uma Activity
        // Leia o artigo para maiores informacoes: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     *O Construtor deve ser privado para previnir instanciacao direta
     * Ao invez disso, faça uma chamada ao metodo "getInstance()".
     * @param context
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /**
     * Chamado quando o db eh criado pela PRIMEIRA vez
     * Script de Insercao de tabelas e colunas no Banco de Dados Local     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE " + TABLE_FAVORITOS);
        sql.append("(");
        sql.append(COL_1 + "INTEGER PRIMARY KEY AUTOINCREMENT,"); //REMOVER ESTE FUTURAMENTE
        sql.append(COL_2 + "TEXT");
        sql.append(")");
        db.execSQL(sql.toString());
    }

    /**
     * Executa quando o db precisa receber update
     * Este metodo soh execute se jah existir um db com o mesmo nome
     *
     * @param db
     * @param versaoAntiga
     * @param versaoNova
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        //Dropa tudo e recria
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);
        onCreate(db);
    }

    /**
     * Este metodo insere valores na tabela. Pode tomar quantos argumentos forem
     * necessarios
     *
     * @param menor
     * @return
     */
    public boolean insereFavorito(Menor menor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //Coluna a qual queremos inserir, e o valor que queremos inserir
        contentValues.put(COL_2, menor.getNome());

        long result = db.insert(TABLE_FAVORITOS, null, contentValues);

        //Testa se conseguiu inserir na tabela
        if(result == -1)
            return false;
        else
            return true;

    }

    /**
     * Metodo que retorna os dados da tabela Favoritos da DB Local
     * Cursor eh uma interface que prove acesso randomico de leitura e escrita
     * a um retorno de uma pesquisa em banco de dados
     * @return
     */
    public Cursor getAllFavoritos() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_FAVORITOS, null);
        return res;
    }

}
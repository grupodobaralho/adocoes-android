package br.pucrs.ages.adocoes.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.pucrs.ages.adocoes.Model.Menor;

//http://guides.codepath.com/android/local-databases-with-sqliteopenhelper
//https://www.youtube.com/watch?v=KUq5wf3Mh0c
/**
 * Created by israeldeorce on 20/09/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Informacoes da DBF
    public static final String DATABASE_NAME = "AdocoesLocal.db";
    public static final int DATABASE_VERSION = 1;

    //Nome das tabelas
    public static final String TABLE_FAVORITOS = "Menores_Favoritos";

    //Colunas das Tabelas
    public static final String COL_1 = "id"; //REMOVER ESTE FUTURAMENTE
    public static final String COL_2 = "nome";
    public static final String COL_3 = "id_menor";


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

    /** Chamado quando a conexao do banco esta sendo estabelecida
     * Configura as opcoes do banco de ados como suporte foreign key, escrita antes de loggar, etc.
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * Chamado quando o db eh criado pela PRIMEIRA vez
     * Script de Insercao de tabelas e colunas no Banco de Dados Local     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_FAVORITOS + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT"+")";
        db.execSQL(SQL_String);

        /*
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE " + TABLE_FAVORITOS);
        sql.append("(");
        sql.append(COL_1 + "INTEGER PRIMARY KEY AUTOINCREMENT,"); //REMOVER ESTE FUTURAMENTE
        sql.append(COL_2 + "TEXT");
        sql.append(")");
        db.execSQL(sql.toString());
        */
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
        System.out.println("antiga: "+ versaoAntiga + " nova: "+ versaoNova);
        if(versaoNova != versaoAntiga) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);
            onCreate(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        onUpgrade(db, versaoAntiga, versaoNova);
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

        //contentValues.put(COL_3, meno;r.getId());

        long result = db.insert(TABLE_FAVORITOS, null, contentValues);

        //Testa se conseguiu inserir na tabela
        if(result == -1)
            return false;
        else
            return true;

    }

    /**
     * Este metodo remove valores da tabela.
     * @param menor
     * @return
     */
    public boolean removeFavorito(Menor menor) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        int result = 0;
        try {
            result = db.delete(TABLE_FAVORITOS, COL_2 + "= ?", new String[] {menor.getNome()});
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(result > 0) {
            return true;
        } else {
            return false;
        }
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

    public boolean contemMenor(Menor menor){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_FAVORITOS, null);

        while(res.moveToNext()) {
            if (menor.getNome().equals(res.getString(1)))
                return true;
        }
        return false;
    }

}

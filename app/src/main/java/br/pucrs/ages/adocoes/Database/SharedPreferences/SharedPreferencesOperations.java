package br.pucrs.ages.adocoes.Database.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import br.pucrs.ages.adocoes.AdocoesApplication;

public class  SharedPreferencesOperations {

    private static final String PREFS_NAME = "SHARED_PREFS_ADOCOES";

    public static final String PONTO_IDADE = "pontoIdade";
    public static final String PONTO_SEXO = "pontoSexo";
    public static final String RAW_SEXO = "rawSexo";
    public static final String RAW_IDADE = "rawIdade";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String USER_ID = "userId";
    public static final String IS_LISTAGEM_VERTICAL = "isListagemVertical";

    public static void saveOnPrefs(String key, int value){
        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void saveOnPrefs(String key, float value){
        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void saveOnPrefs(String key, String value){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveOnPrefs(String key, Boolean value){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void removeFromPrefs(String key){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public static int loadIntFromPrefs(String key, int defaultValue){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return settings.getInt(key,defaultValue);
    }

    public static float loadFloatFromPrefs(String key, float defaultValue){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    public static String loadFromPrefs(String key){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return settings.getString(key, null);
    }

    public static Boolean loadBooleanFromPrefs(String key, boolean defaultValue){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return settings.getBoolean(key,defaultValue);
    }
}

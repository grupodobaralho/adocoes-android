package br.pucrs.ages.adocoes;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesOperations {

    private static final String PREFS_NAME = "SHARED_PREFS_ADOCOES";

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String USER_ID = "userId";

    public static void saveOnPrefs(String key, int value){
        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
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

    public static String loadFromPrefs(String key){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return settings.getString(key, null);
    }

    public static Boolean loadBooleanFromPrefs(String key, boolean defaultValue){

        SharedPreferences settings = AdocoesApplication.getAdocoesApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return settings.getBoolean(key,defaultValue);
    }
}

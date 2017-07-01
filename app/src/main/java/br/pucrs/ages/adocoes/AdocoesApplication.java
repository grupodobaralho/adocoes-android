package br.pucrs.ages.adocoes;

import android.app.Application;
import android.content.Context;

/**
 * Created by kuquert on 30/06/17.
 */


public class AdocoesApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAdocoesApplicationContext(){
        return AdocoesApplication.context;
    }
}

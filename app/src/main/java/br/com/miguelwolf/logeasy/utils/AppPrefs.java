package br.com.miguelwolf.logeasy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import br.com.miguelwolf.logeasy.view.LoginActivity;

public class AppPrefs {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;


    //Salva as Infos

    @SuppressLint("CommitPrefEdits")
    public AppPrefs(Context context){
        //this.ctx = context;
        prefs = context.getSharedPreferences("VO_Hinode", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode",logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }



    //Infomações Login
    private String nome, id;
    private int tipoPessoa;


    public String getId() {
        return prefs.getString(Constants.LOGIN_ID, id);
    }

    public void setId(String id) {
        editor.putString(Constants.LOGIN_ID, id);
        editor.commit();
    }



    public String getNome() {
        return prefs.getString(Constants.LOGIN_NOME, nome);
    }

    public void setNome(String nome) {
        editor.putString(Constants.LOGIN_NOME, nome);
        editor.commit();
    }



    public int getTipoPessoa() {
        return prefs.getInt(Constants.LOGIN_TIPO_PESSOA, tipoPessoa);
    }

    public void setTipoPessoa(int tipoPessoa) {
        editor.putInt(Constants.LOGIN_TIPO_PESSOA, tipoPessoa);
        editor.commit();
    }



    public void logOut(Activity Actx){

        setLoggedin(false);

        setId(null);
        setNome(null);

        setTipoPessoa(0);

        Intent intent = new Intent(Actx, LoginActivity.class);
        Actx.startActivity(intent);
        Actx.finish();

    }

}

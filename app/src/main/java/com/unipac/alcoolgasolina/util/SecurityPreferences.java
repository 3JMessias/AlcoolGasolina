package com.unipac.alcoolgasolina.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SecurityPreferences {
    public static final String NULO = "Nulo";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SecurityPreferences(Context context){
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = preferences.edit();
    }
    public void guardaFloat(String chave, Float valor) {
        editor.putFloat(chave, valor.floatValue()).commit();
    }

    public String recuperaString(String chave) {
        return preferences.getString(chave, NULO);
    }
    public Float recuperaFloat(String chave) {
        return new Float(preferences.getFloat(chave, 0));
    }

    public void guardaBoleano(String chave, boolean valor) {
        editor.putBoolean(chave, valor).commit();
    }

    public boolean recuperaBoleano(String chave) {
        return preferences.getBoolean(chave, Boolean.FALSE);
    }

}

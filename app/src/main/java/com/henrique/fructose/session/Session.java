package com.henrique.fructose.session;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.henrique.fructose.BuildConfig;

import java.util.Map;
import java.util.Set;

public class Session {

    private static final String SHARED_PREF_NAME                = "FructoseApp";
    private static final String KEY_FIRST_TIME                  = "firstTime";
    private static final int PRIVATE_MODE                       = 0;
    private static final String KEY_USER_APPVERSION_LAST_LOGIN  = "appversionlastlogin";
    private static final String USER_LOGGED                     = "userLogggedIn";
    private static final String KEY_FIREBASE_MESSAGING_ID       = "firebaseMessagingId";
    private static final String KEY_TERM_ACCEPTED               = "aceptTherm";


    private static UserSession userLogged;
    private SharedPreferences pref;
    private Context context;


    //VARIAVEL ESTATICA
    private static Session mSession;

    public static synchronized Session getInstance (Context c) {
        if (mSession == null) {
            mSession = new Session(c);
        }
        return mSession;
    }


    private Session(Context c) {

        this.context = c;

        //recupera instancia de sharedPref
        pref = c.getSharedPreferences(SHARED_PREF_NAME, PRIVATE_MODE);


        //verifica usuarios logados
        if (pref.getBoolean(USER_LOGGED, false)) {
            retrieveUserSession();
        }

        //informa log
        Log.v("SESSION", "Usuario logado:" + pref.getBoolean(USER_LOGGED, false));


    }

    public void updateFirebaseMessagingIdService(String FirebaseID) {

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_FIREBASE_MESSAGING_ID, FirebaseID);
        editor.apply();

    }

    public String getFirebaseMessagingId() {
        return pref.getString(KEY_FIREBASE_MESSAGING_ID, "");
    }


    public void CreateSessionUser(UserSession user) {

        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(USER_LOGGED, true);
        editor.putString(KEY_USER_APPVERSION_LAST_LOGIN, BuildConfig.VERSION_NAME);

        Map<String, String> values = user.getValues();

        Set<String> chaves = values.keySet();
        for (String chave : chaves)
        {
            if(chave != null) editor.putString(chave,values.get(chave));
        }


        editor.apply();

        userLogged = user;

    }

    public void retrieveUserSession() {

        UserSession userSession = new UserSession();
        Map<String, String> values = userSession.getValues();

        Set<String> chaves = values.keySet();
        for (String chave : chaves)
        {
            if(chave != null) values.put(chave,pref.getString(chave,values.get(chave)));
        }
        userSession.updateData(values);
        userLogged = userSession;

    }

    public void DeleteSessionUser() {

        SharedPreferences.Editor editor = pref.edit();
        //editor.remove(KEY_FIREBASE_MESSAGING_ID);

        //userdata
        UserSession user = userLogged;
        Map<String, String> values = user.getValues();

        Set<String> chaves = values.keySet();
        for (String chave : chaves)
        {
            if(chave != null) editor.remove(chave);
        }
        userLogged = null;
        editor.putBoolean(USER_LOGGED, false);
        editor.apply();
    }


    public void updateUserData() {
        SharedPreferences.Editor editor = pref.edit();

        UserSession user = userLogged;
        Map<String, String> values = user.getValues();

        Set<String> chaves = values.keySet();
        for (String chave : chaves)
        {
            if(chave != null) editor.putString(chave,values.get(chave));
        }
        editor.apply();
    }

    public boolean isUserLogged() {
        return userLogged != null;
    }

    public UserSession getUserSession() {

        if (userLogged != null) {
            return userLogged;
        } else {
            if (pref.getBoolean(USER_LOGGED, false)) {
                retrieveUserSession();
                return userLogged;
            } else return null;
        }
    }

    public boolean isThermAccepted() {
        return pref.getBoolean(KEY_TERM_ACCEPTED, false);
    }

    public void setThermAccepted(boolean b) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_TERM_ACCEPTED, b);
        editor.apply();
    }

    public boolean isFirstTime() {
        return pref.getBoolean(KEY_FIRST_TIME, true);
    }

    public void setFirstTime(boolean b) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_FIRST_TIME, b);
        editor.apply();
    }

    public String getLasLoginVersion() {
        return pref.getString(KEY_USER_APPVERSION_LAST_LOGIN, "");
    }
}

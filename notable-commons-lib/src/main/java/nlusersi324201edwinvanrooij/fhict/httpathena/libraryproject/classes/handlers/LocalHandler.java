package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.physical.Account;

/**
 * Created by Edwin on 10/30/2015.
 */
public class LocalHandler {
    //region Final fields
    private static final String TAG = "SessionHandler";
    private final String KEY_ID = "id";
    private final String KEY_USERNAME = "username";
    private final String KEY_EMAIL = "email";
    private final String KEY_PASSWORD = "password";
    private final String KEY_CREATION = "creation";
    private final String KEY_BLOCKED = "blocked";
    private final String KEY_LOGGED_IN = "logged_in";
    private final String KEY_TAB_NAME = "tabname";

    private final SharedPreferences pref;
    private SharedPreferences.Editor editor;
    //endregion

    public LocalHandler(Context c) {
        pref = c.getApplicationContext().getSharedPreferences("Login", 0);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_LOGGED_IN, false);
    }

    public void ExecuteLogin(Account a) {
        editor = pref.edit();

        Log.d(TAG, "ExecuteLogin: Starting executelogin");
        editor.putInt(KEY_ID, a.getId());
        editor.putString(KEY_USERNAME, a.getUsername());
        editor.putString(KEY_EMAIL, a.getEmail());
        editor.putString(KEY_PASSWORD, a.getPassword());
        editor.putString(KEY_CREATION, ConvertHandler.DateToString(a.getCreation()));
        editor.putBoolean(KEY_BLOCKED, a.getBlocked());

        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.apply();
        Log.d(TAG, "ExecuteLogin: Comitted executelogin");
    }

    public Account getAccount() {
        int id = pref.getInt(KEY_ID, 0);
        String username = pref.getString(KEY_USERNAME, "");
        String email = pref.getString(KEY_EMAIL, "");
        String password = pref.getString(KEY_PASSWORD, "");
        Date creation = ConvertHandler.StringToDate(pref.getString(KEY_CREATION, ""));
        boolean blocked = pref.getBoolean(KEY_BLOCKED, false);

        return new Account(id, username, email, password, creation, blocked);
    }

    public void onLogout() {
        editor = pref.edit();
        Log.d(TAG, "deleteAllRecords: Deleted all local notes");
        editor.clear();
        editor.apply();
    }

    public void setTab(String name) {
        editor = pref.edit();
        editor.putString(KEY_TAB_NAME, name);
        editor.apply();
    }

    public String getTabName(String default_tabname) {
        return pref.getString(KEY_TAB_NAME, default_tabname);
    }
}

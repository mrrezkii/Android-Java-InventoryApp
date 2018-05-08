package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.LoginActivity;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.UserModel;

public class SessionPreference {
    private static final String SHARED_PREF_NAME = "sessionsharedpref";
    private static final String KEY_NAMA = "keynama";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_ID = "keyid";
    private static final String KEY_BRANCH = "keybranch";

    private static SessionPreference sessionPreference = null;
    private static Context context;

    private SessionPreference(Context ctx) {
        context = ctx;
    }

    public static synchronized SessionPreference getInstance(Context ctx) {
        if (sessionPreference == null) {
            sessionPreference = new SessionPreference(ctx);
        }
        return sessionPreference;
    }

    public void userLogin(UserModel user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NAMA, user.getNama());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_BRANCH, user.getBranch());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public UserModel getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_BRANCH, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}

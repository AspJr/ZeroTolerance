package com.app.zerotolerance;

import android.content.Context;
import android.content.SharedPreferences;

public class Logout {
    SharedPreferences pref;
    public Logout(Context contexts){
        pref = contexts.getApplicationContext().getSharedPreferences("zerotolerance", 0);
        SharedPreferences.Editor edit=pref.edit();
        edit.remove("expires_at");
        edit.remove("token");
        edit.remove("id");
        edit.remove("name");
        edit.remove("email");
        edit.remove("username");
        edit.remove("privillage");
        edit.remove("code_industri");
        edit.remove("code_list");
        edit.remove("stts_account");
        edit.remove("code_industri_checking");
        edit.remove("code_industri_list_checking");
        edit.apply();
    }
}

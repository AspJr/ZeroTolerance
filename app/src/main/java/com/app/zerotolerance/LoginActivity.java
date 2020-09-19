package com.app.zerotolerance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zerotolerance.api.GeneralSetting;
import com.app.zerotolerance.api.MobileService;
import com.app.zerotolerance.model.ClsTokenLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username, password;
    Button btn_login;
    ProgressDialog progress;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("zerotolerance", 0);
        sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CheckingActivity.class);
                startActivity(i);
            }
        });
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill the blank", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });
        if (this.checkSession()) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }
    }

    private void login(){
        progress = new ProgressDialog(LoginActivity .this);
        progress.setMessage("Please Wait...");
        progress.setTitle("Connecting to server");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        ProgressBar progressbar= progress.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0065B3"), android.graphics.PorterDuff.Mode.SRC_IN);
        MobileService service = GeneralSetting.getRetrofit();
        Call<ClsTokenLogin> call=  service.getToken(username.getText().toString().trim(), password.getText().toString().trim());
        call.enqueue(new Callback<ClsTokenLogin>() {
            @Override
            public void onResponse(Call<ClsTokenLogin> call, Response<ClsTokenLogin> response) {
                try {
                    if(response.isSuccessful()){
                        progress.dismiss();
                        ClsTokenLogin clsTokenLogin = response.body();
                        editor = pref.edit();
                        editor.putString("expires_at", clsTokenLogin.getExpires_at());
                        editor.putString("token", "Bearer "+clsTokenLogin.getAccess_token());
                        editor.putInt("id", clsTokenLogin.getDetail().getId());
                        editor.putString("name", clsTokenLogin.getDetail().getName());
                        editor.putString("email", clsTokenLogin.getDetail().getEmail());
                        editor.putString("username", clsTokenLogin.getDetail().getUsername());
                        editor.putInt("privillage", clsTokenLogin.getDetail().getPrivillage());
                        editor.putInt("code_industri", clsTokenLogin.getDetail().getCode_industri());
                        editor.putInt("code_list", clsTokenLogin.getDetail().getCode_list());
                        editor.putInt("stts_account", clsTokenLogin.getDetail().getStts_account());
                        editor.apply();

                        GeneralSetting.Security.setToken("Bearer "+clsTokenLogin.getAccess_token());
                        GeneralSetting.Security.setCode_list_id(clsTokenLogin.getDetail().getCode_list());

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                        startActivity(intent);
                        callToast("Success");
                    } else {
                        callToast("Login failed, please check username or password");
                        progress.dismiss();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    callToast("Sorry, something went wrong");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ClsTokenLogin> call, Throwable t) {
                callToast("Failed connecting to server");
                progress.dismiss();
            }
        });
    }

    boolean checkSession(){
        if(!pref.getString("username","").equals("")){
            return true;
        }
        return false;
    }

    void callToast(String sMessage){
        Toast.makeText(getApplicationContext(),sMessage,Toast.LENGTH_SHORT).show();
    }
}

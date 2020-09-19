package com.app.zerotolerance;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.zerotolerance.adapter.AdapterListZTScreening;
import com.app.zerotolerance.api.GeneralSetting;
import com.app.zerotolerance.model.ClsListDetailZTScreening;
import com.app.zerotolerance.model.ClsListZTScreening;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton img_btn_history, img_btn_profile;
    FloatingActionButton btn_add;
    SharedPreferences pref;
    public static MainActivity inst;
    RecyclerView recyclerViewZTScreening;
    ProgressBar progressZTScreening;
    AdapterListZTScreening adapterListZTScreening;
    List<ClsListDetailZTScreening> clsListDetailZTScreenings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences("zerotolerance", 0);
        recyclerViewZTScreening = findViewById(R.id.recyclerViewZTScreening);
        progressZTScreening = findViewById(R.id.progressZTScreening);

        img_btn_history = findViewById(R.id.img_btn_history);
        img_btn_profile = findViewById(R.id.img_btn_profile);
        btn_add = findViewById(R.id.btn_add);
        img_btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(i);
            }
        });
        img_btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FrontlineActivity.class);
                startActivity(i);
            }
        });
        inst = this;
        getData();

        recyclerViewZTScreening.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewZTScreening.setLayoutManager(layoutManager);
    }

    void getData(){
        try {
            progressZTScreening.setVisibility(View.VISIBLE);
            recyclerViewZTScreening.setVisibility(View.GONE);
            Call<ClsListZTScreening> call = GeneralSetting.getRetrofit().getData(pref.getString("token", "0"), pref.getInt("code_list", 0));
            call.enqueue(new Callback<ClsListZTScreening>() {
                @Override
                public void onResponse(Call<ClsListZTScreening> call, Response<ClsListZTScreening> response) {

                    if (response.isSuccessful()){
                        ClsListZTScreening list = response.body();
                        clsListDetailZTScreenings = response.body().getData();

                        adapterListZTScreening = new AdapterListZTScreening( clsListDetailZTScreenings, MainActivity.this);
                        recyclerViewZTScreening.setAdapter(adapterListZTScreening);
                        adapterListZTScreening.notifyDataSetChanged();

                        recyclerViewZTScreening.setVisibility(View.VISIBLE);
                        progressZTScreening.setVisibility(View.GONE);
                        String toast = list.getMessage();
                        Toast.makeText(MainActivity.this,  toast, Toast.LENGTH_SHORT).show();
                    } else {
                        progressZTScreening.setVisibility(View.GONE);
                        callToast("Failed to get data");
                    }
                }

                @Override
                public void onFailure(Call<ClsListZTScreening> call, Throwable t) {
                    progressZTScreening.setVisibility(View.GONE);
                    callToast("Failed to connect to server");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void callToast(String sMessage){
        Toast.makeText(getApplicationContext(),sMessage,Toast.LENGTH_SHORT).show();
    }

    private void showQuestion(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_questions);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

//        final AppCompatButton bt_submit = (AppCompatButton) dialog.findViewById(R.id.bt_submit);
//        ((EditText) dialog.findViewById(R.id.et_post)).addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //bt_submit.setEnabled(!s.toString().trim().isEmpty());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

//        bt_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Post Submitted", Toast.LENGTH_SHORT).show();
//            }
//        });


        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}

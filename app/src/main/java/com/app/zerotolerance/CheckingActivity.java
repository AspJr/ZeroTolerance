package com.app.zerotolerance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.zerotolerance.api.GeneralSetting;
import com.app.zerotolerance.api.MobileService;
import com.app.zerotolerance.model.ClsDataIndustri;
import com.app.zerotolerance.model.ClsDetailDataIndustri;
import com.app.zerotolerance.model.ClsDetailLogin;
import com.app.zerotolerance.model.ClsResponseRegister;
import com.app.zerotolerance.model.ClsTokenLogin;
import com.app.zerotolerance.table.tbl_data_industri;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckingActivity extends AppCompatActivity {

    EditText edt_code, edt_name;
    Button btn_checking, btn_register;
    ProgressDialog progress;
    List<ClsDetailDataIndustri> list_data;
    Spinner spinner_nama, spinner_nama_user;
    RelativeLayout relative_code, relative_register;
    TextInputEditText edt_username, edt_password, edt_email, edt_code_industri, edt_code_list;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        pref = getApplicationContext().getSharedPreferences("zerotolerance", 0);
        relative_code = findViewById(R.id.relative_code);
        relative_register = findViewById(R.id.relative_register);
        spinner_nama = findViewById(R.id.spinner_nama);
        spinner_nama_user = findViewById(R.id.spinner_nama_user);
        edt_name = findViewById(R.id.edt_name);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        edt_email = findViewById(R.id.edt_email);
        edt_code_industri = findViewById(R.id.edt_code_industri);
        edt_code_list = findViewById(R.id.edt_code_list);
        btn_register = findViewById(R.id.btn_register);
        edt_code = findViewById(R.id.edt_code);
        btn_checking = findViewById(R.id.btn_checking);
        btn_checking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = edt_code.getText().toString().trim();
                if(code.equals("")){
                    Toast.makeText(CheckingActivity.this, "Please fill the blank", Toast.LENGTH_SHORT).show();
                } else {
                    check();
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = spinner_nama_user.getSelectedItem().toString();
                //String name_ = edt_name.getText().toString().trim();
                String user = edt_username.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                String email = edt_email.getText().toString().trim();
                if (name.equals("") || user.equals("") || password.equals("") || email.equals("")){
                    callToast("Please fill the blank");
                } else {
                    //Toast.makeText(CheckingActivity.this, "Value = "+name_+"\n"+user+"\n"+password+"\n"+email, Toast.LENGTH_SHORT).show();
                    register();
                }
            }
        });
    }

    private void register(){
        progress = new ProgressDialog(CheckingActivity .this);
        progress.setMessage("Please Wait...");
        progress.setTitle("Registering data to server");
        progress.setCancelable(false);
        progress.show();
        ProgressBar progressbar= progress.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0065B3"), android.graphics.PorterDuff.Mode.SRC_IN);
        MobileService service = GeneralSetting.getRetrofit();
        Call<ClsResponseRegister> call=  service.register(edt_name.getText().toString().trim(), edt_username.getText().toString().trim(),
                edt_password.getText().toString().trim(), edt_email.getText().toString().trim(), edt_code_industri.getText().toString().trim(),
                edt_code_list.getText().toString().trim());
        call.enqueue(new Callback<ClsResponseRegister>() {
            @Override
            public void onResponse(Call<ClsResponseRegister> call, Response<ClsResponseRegister> response) {
                try {
                    if (response.isSuccessful()){
                        progress.dismiss();
                        ClsResponseRegister clsResponseRegister = response.body();
                        callToast(clsResponseRegister.getMessage());
                        Intent intent = new Intent(CheckingActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                        startActivity(intent);
                    } else {
                        callToast("Get data failed, please check your code");
                        progress.dismiss();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    callToast("Sorry, something went wrong");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ClsResponseRegister> call, Throwable t) {
                callToast("Failed getting data from server");
                progress.dismiss();
            }
        });
    }
    private void check(){
        progress = new ProgressDialog(CheckingActivity .this);
        progress.setMessage("Please Wait...");
        progress.setTitle("Getting data from server");
        progress.setCancelable(false);
        progress.show();
        ProgressBar progressbar= progress.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0065B3"), android.graphics.PorterDuff.Mode.SRC_IN);
        MobileService service = GeneralSetting.getRetrofit();
        Call<ClsDataIndustri> call=  service.getIndustri(edt_code.getText().toString().trim());
        call.enqueue(new Callback<ClsDataIndustri>() {
            @Override
            public void onResponse(Call<ClsDataIndustri> call, Response<ClsDataIndustri> response) {
                try {
                    if (response.isSuccessful()){
                        new Logout(CheckingActivity.this);
                        progress.dismiss();
                        ClsDataIndustri clsDataIndustri = response.body();
                        editor = pref.edit();
                        editor.putInt("code_industri_checking", clsDataIndustri.getData().get(0).getCode_industri());
                        editor.putInt("code_industri_list_checking", clsDataIndustri.getData().get(0).getCode_industri_list());
                        editor.apply();
//                        tbl_data_industri.deleteAll(tbl_data_industri.class);
//                        List<tbl_data_industri> responseData = response.body().getData();
//                        for (tbl_data_industri industri : responseData){
//                            tbl_data_industri iTbl = new tbl_data_industri(industri.getId_user_kantor(), industri.getCode_industri_list(),
//                                    industri.getNm_user(), industri.getGender_user(), industri.getAlamat_user(), industri.getTgl_lahir_user(),
//                                    industri.getTlpn_user(), industri.getPekerjaan_user(), industri.getDivisi_user(), industri.getJabatan_user(),
//                                    industri.getStts_wfh(), industri.getKet_stts_wfh(), industri.getTransport(), industri.getHandsanitizer(),
//                                    industri.getKet_handsanitizer(), industri.getSuplement(), industri.getUsia(), industri.getRwt_penyakit(),
//                                    industri.getKet_rwt_penyakit(), industri.getStts_pekerjaan(), industri.getKet_pekerjaan(), industri.getStts_hamil(),
//                                    industri.getKet_hamil(), industri.getStts_shift(), industri.getStts_rapid_swap(), industri.getKet_rapid_swap(),
//                                    industri.getCreated_at(), industri.getUpdated_at(), industri.getCreated_by(), industri.getUpdated_by(), industri.getStts_rec());
//                            iTbl.save();
//                        }

                        relative_code.setVisibility(View.GONE);
                        relative_register.setVisibility(View.VISIBLE);

                        list_data = response.body().getData();
                        ArrayList<String> arr = new ArrayList<>();
                        for(ClsDetailDataIndustri ind : list_data){
                            arr.add(ind.getNm_user());
                        }

                        ArrayAdapter<String> spinnerNamaAdapter = new ArrayAdapter<>(CheckingActivity.this,
                                R.layout.item_spinner_3, arr);
                        spinnerNamaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_nama_user.setAdapter(spinnerNamaAdapter);
                        spinner_nama_user.setSelection(list_data.indexOf(list_data.get(0).getNm_user()));

                        edt_code_industri.setText(Integer.toString(pref.getInt("code_industri_checking", 0)));
                        edt_code_list.setText(Integer.toString(pref.getInt("code_industri_list_checking", 0)));
                        callToast(clsDataIndustri.getMessage());
                    } else {
                        callToast("Get data failed, please check your code");
                        progress.dismiss();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    callToast("Sorry, something went wrong");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ClsDataIndustri> call, Throwable t) {
                callToast("Failed getting data from server");
                progress.dismiss();
            }
        });
    }

    void callToast(String sMessage){
        Toast.makeText(getApplicationContext(),sMessage,Toast.LENGTH_SHORT).show();
    }
}

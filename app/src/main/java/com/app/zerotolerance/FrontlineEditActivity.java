package com.app.zerotolerance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.app.zerotolerance.api.GeneralSetting;
import com.app.zerotolerance.api.MobileService;
import com.app.zerotolerance.model.ClsResponseUpdateZTScreening;
import com.app.zerotolerance.model.ClsRowListDetailZTScreening;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrontlineEditActivity extends AppCompatActivity {

    EditText edt_id, edt_nama, edt_suhu, edt_nomor_telfon;
    Button btn_simpan;
    RadioGroup rgMasker, rgDemam, rgObat, rgBatukFlu, rgSesakNafas, rgLuarNegeri, rgLuarDaerah, rgKontakCovid;
    RadioButton rbMaskerYa, rbMaskerTidak, rbDemamYa, rbDemamTidak, rbObatYa, rbObatTidak, rbBatukFluYa, rbBatukFluTidak, rbSesakNafasYa,
            rbSesakNafasTidak, rbLuarNegeriYa, rbLuarNegeriTidak, rbLuarDaerahYa, rbLuarDaerahTidak, rbKontakCovidYa, rbKontakCovidTidak;
    String radio_masker = null;
    String radio_demam = null;
    String radio_obat = null;
    String radio_batuk_flu = null;
    String radio_sesak_nafas = null;
    String radio_luar_negeri = null;
    String radio_luar_daerah = null;
    String radio_kontak_covid = null;
    SharedPreferences pref;
    String new_telfon;
    ProgressDialog progress;
    ProgressBar progressEditZTScreening;
    ConstraintLayout constraint_edit_screening;
    String id_zt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontline_edit);
        pref = getApplicationContext().getSharedPreferences("zerotolerance", 0);
        constraint_edit_screening = findViewById(R.id.constraint_edit_screening);
        progressEditZTScreening = findViewById(R.id.progressEditZTScreening);
        id_zt = getIntent().getStringExtra("id_zt");

        edt_id = findViewById(R.id.edt_id);
        rgMasker = findViewById(R.id.rgMasker);
        rbMaskerYa = findViewById(R.id.rbMaskerYa);
        rbMaskerTidak = findViewById(R.id.rbMaskerTidak);
        rgMasker.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbMaskerYa:
                        radio_masker = "Ya";
                        break;
                    case R.id.rbMaskerTidak:
                        radio_masker = "Tidak";
                        break;
                }
            }
        });

        rgDemam = findViewById(R.id.rgDemam);
        rbDemamYa = findViewById(R.id.rbDemamYa);
        rbDemamTidak = findViewById(R.id.rbDemamTidak);
        rgDemam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbDemamYa:
                        radio_demam = "Ya";
                        break;
                    case R.id.rbDemamTidak:
                        radio_demam = "Tidak";
                        break;
                }
            }
        });

        rgObat = findViewById(R.id.rgObat);
        rbObatYa = findViewById(R.id.rbObatYa);
        rbObatTidak = findViewById(R.id.rbObatTidak);
        rgObat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbObatYa:
                        radio_obat = "Ya";
                        break;
                    case R.id.rbObatTidak:
                        radio_obat = "Tidak";
                        break;
                }
            }
        });

        rgBatukFlu = findViewById(R.id.rgBatukFlu);
        rbBatukFluYa = findViewById(R.id.rbBatukFluYa);
        rbBatukFluTidak = findViewById(R.id.rbBatukFluTidak);
        rgBatukFlu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbBatukFluYa:
                        radio_batuk_flu = "Ya";
                        break;
                    case R.id.rbBatukFluTidak:
                        radio_batuk_flu = "Tidak";
                        break;
                }
            }
        });

        rgSesakNafas = findViewById(R.id.rgSesakNafas);
        rbSesakNafasYa = findViewById(R.id.rbSesakNafasYa);
        rbSesakNafasTidak = findViewById(R.id.rbSesakNafasTidak);
        rgSesakNafas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbSesakNafasYa:
                        radio_sesak_nafas = "Ya";
                        break;
                    case R.id.rbSesakNafasTidak:
                        radio_sesak_nafas = "Tidak";
                        break;
                }
            }
        });

        rgLuarNegeri = findViewById(R.id.rgLuarNegeri);
        rbLuarNegeriYa = findViewById(R.id.rbLuarNegeriYa);
        rbLuarNegeriTidak = findViewById(R.id.rbLuarNegeriTidak);
        rgLuarNegeri.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbLuarNegeriYa:
                        radio_luar_negeri = "Ya";
                        break;
                    case R.id.rbLuarNegeriTidak:
                        radio_luar_negeri = "Tidak";
                        break;
                }
            }
        });

        rgLuarDaerah = findViewById(R.id.rgLuarDaerah);
        rbLuarDaerahYa = findViewById(R.id.rbLuarDaerahYa);
        rbLuarDaerahTidak = findViewById(R.id.rbLuarDaerahTidak);
        rgLuarDaerah.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbLuarDaerahYa:
                        radio_luar_daerah = "Ya";
                        break;
                    case R.id.rbLuarDaerahTidak:
                        radio_luar_daerah = "Tidak";
                        break;
                }
            }
        });

        rgKontakCovid = findViewById(R.id.rgKontakCovid);
        rbKontakCovidYa = findViewById(R.id.rbKontakCovidYa);
        rbKontakCovidTidak = findViewById(R.id.rbKontakCovidTidak);
        rgKontakCovid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbKontakCovidYa:
                        radio_kontak_covid = "Ya";
                        break;
                    case R.id.rbKontakCovidTidak:
                        radio_kontak_covid = "Tidak";
                        break;
                }
            }
        });

        edt_nama = findViewById(R.id.edt_nama);
        edt_suhu = findViewById(R.id.edt_suhu);
        edt_nomor_telfon = findViewById(R.id.edt_nomor_telfon);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = edt_nama.getText().toString().trim();
                String suhu = edt_suhu.getText().toString().trim();
                String telfon = edt_nomor_telfon.getText().toString().trim();
                if (nama.equals("") || suhu.equals("") || telfon.equals("") || radio_masker == null){
                    Toast.makeText(FrontlineEditActivity.this, "Please fill the blank", Toast.LENGTH_SHORT).show();
                } else {
                    new_telfon = telfon = edt_nomor_telfon.getText().toString().trim();
                    String first_number = telfon.substring(0, 1);
                    if (first_number.equals("0")){
                        new_telfon = "+62"+new_telfon.substring(1,new_telfon.length());
                    } else {
                        new_telfon = "+62"+edt_nomor_telfon.getText().toString().trim();
                    }
                    updateZTScreening();
                }
            }
        });
        getDetail();
    }

    private void getDetail(){
        progressEditZTScreening.setVisibility(View.VISIBLE);
        constraint_edit_screening.setVisibility(View.GONE);
        Call<ClsRowListDetailZTScreening> call = GeneralSetting.getRetrofit().getDataDetail(pref.getString("token", "0"), pref.getInt("code_list", 0), Integer.parseInt(id_zt));
        call.enqueue(new Callback<ClsRowListDetailZTScreening>() {
            @Override
            public void onResponse(Call<ClsRowListDetailZTScreening> call, Response<ClsRowListDetailZTScreening> response) {
                try {
                    if (response.isSuccessful()){
                        ClsRowListDetailZTScreening list = response.body();
                        callToast(list.getMessage());
                        progressEditZTScreening.setVisibility(View.GONE);
                        constraint_edit_screening.setVisibility(View.VISIBLE);
                        edt_id.setText(Integer.toString(list.getData().get(0).getId_zt()));
                        edt_nama.setText(list.getData().get(0).getNama());
                        edt_suhu.setText(Integer.toString(list.getData().get(0).getSuhu()));
                        edt_nomor_telfon.setText(list.getData().get(0).getNo_tlpn());
                        if(list.getData().get(0).getMasker().equals("Ya")){
                            rbMaskerYa.setChecked(true);
                        } else {
                            rbMaskerTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getDemam().equals("Ya")){
                            rbDemamYa.setChecked(true);
                        } else {
                            rbDemamTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getKonsumsi_obat_demam().equals("Ya")){
                            rbObatYa.setChecked(true);
                        } else {
                            rbObatTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getBatuk_flu().equals("Ya")){
                            rbBatukFluYa.setChecked(true);
                        } else {
                            rbBatukFluTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getSesak_nafas().equals("Ya")){
                            rbSesakNafasYa.setChecked(true);
                        } else {
                            rbSesakNafasTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getKeluar_negeri().equals("Ya")){
                            rbLuarNegeriYa.setChecked(true);
                        } else {
                            rbLuarNegeriTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getKeluar_daerah().equals("Ya")){
                            rbLuarDaerahYa.setChecked(true);
                        } else {
                            rbLuarDaerahTidak.setChecked(true);
                        }
                        if(list.getData().get(0).getKontak_covid().equals("Ya")){
                            rbKontakCovidYa.setChecked(true);
                        } else {
                            rbKontakCovidTidak.setChecked(true);
                        }
                    } else {
                        progressEditZTScreening.setVisibility(View.GONE);
                        callToast("Failed to get data");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    callToast("Sorry, something went wrong");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ClsRowListDetailZTScreening> call, Throwable t) {
                progressEditZTScreening.setVisibility(View.GONE);
                callToast("Failed to connect to server");
            }
        });
    }
    private void updateZTScreening(){
        progress = new ProgressDialog(FrontlineEditActivity.this);
        progress.setMessage("Please Wait...");
        progress.setTitle("Updating data to server");
        progress.setCancelable(false);
        progress.show();
        ProgressBar progressbar= progress.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0065B3"), android.graphics.PorterDuff.Mode.SRC_IN);
        MobileService service = GeneralSetting.getRetrofit();
        Call<ClsResponseUpdateZTScreening> call=  service.updateZTScreening(pref.getString("token", "0"), Integer.parseInt(id_zt),
                edt_nama.getText().toString().trim(), new_telfon, edt_suhu.getText().toString().trim(), radio_masker, radio_demam, radio_obat, radio_batuk_flu,
                radio_sesak_nafas, radio_luar_negeri, radio_luar_daerah, radio_kontak_covid, pref.getInt("id", 0));
        call.enqueue(new Callback<ClsResponseUpdateZTScreening>() {
            @Override
            public void onResponse(Call<ClsResponseUpdateZTScreening> call, Response<ClsResponseUpdateZTScreening> response) {
                try {
                    if (response.isSuccessful()){
                        progress.dismiss();
                        ClsResponseUpdateZTScreening clsResponse = response.body();
                        callToast(clsResponse.getMessage());
                        Intent intent = new Intent(FrontlineEditActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                        startActivity(intent);
                    } else {
                        callToast("Failed to store data");
                        progress.dismiss();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    callToast("Sorry, something went wrong");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ClsResponseUpdateZTScreening> call, Throwable t) {
                callToast("Failed connecting to server");
                progress.dismiss();
            }
        });
    }

    void callToast(String sMessage){
        Toast.makeText(getApplicationContext(),sMessage,Toast.LENGTH_SHORT).show();
    }
}

package com.app.zerotolerance.api;

import android.content.SharedPreferences;

import com.app.zerotolerance.model.ClsDataIndustri;
import com.app.zerotolerance.model.ClsListZTScreening;
import com.app.zerotolerance.model.ClsResponseInsertZTScreening;
import com.app.zerotolerance.model.ClsResponseRegister;
import com.app.zerotolerance.model.ClsResponseUpdateZTScreening;
import com.app.zerotolerance.model.ClsRowDetailZTScreening;
import com.app.zerotolerance.model.ClsRowListDetailZTScreening;
import com.app.zerotolerance.model.ClsTokenLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MobileService {

    @FormUrlEncoded
    @POST("auth/login")
    Call<ClsTokenLogin> getToken(@Field("login") String login, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/checking")
    Call<ClsDataIndustri> getIndustri(@Field("idpt") String idpt);

    @FormUrlEncoded
    @POST("auth/signup")
    Call<ClsResponseRegister> register(@Field("name") String name, @Field("username") String username, @Field("password") String password,
                                       @Field("email") String email, @Field("code_industri") String code_industri, @Field("code_list") String code_list);

    @FormUrlEncoded
    @POST("screening/insert/zt")
    Call<ClsResponseInsertZTScreening> insertZTScreening(@Header("Authorization") String Authorization, @Field("code_industri") int code_industri, @Field("code_list_industri") int code_list_industri,
                                                         @Field("nama") String nama, @Field("notlpn") String notlpn, @Field("suhu") String suhu, @Field("demam") String demam, @Field("obat_demam") String obat_demam,
                                                         @Field("batuk_flu") String batuk_flu, @Field("sesak_nafas") String sesak_nafas, @Field("keluar_negeri") String keluar_negeri,
                                                         @Field("keluar_daerah") String keluar_daerah, @Field("kontak_covid") String kontak_covid, @Field("user_id") int user_id,
                                                         @Field("masker") String masker);

    @GET("screening/zt/today/{input}")
    Call <ClsListZTScreening> getData(@Header("Authorization") String Authorization, @Path("input") int input);

    @GET("screening/detail/zt/{input1}/{input2}")
    Call <ClsRowListDetailZTScreening> getDataDetail(@Header("Authorization") String Authorization, @Path("input1") int input1, @Path("input2") int input2);

    @FormUrlEncoded
    @POST("screening/update/zt")
    Call<ClsResponseUpdateZTScreening> updateZTScreening(@Header("Authorization") String Authorization, @Field("id_zt") int id_zt, @Field("nama") String nama,
                                                         @Field("notlpn") String notlpn, @Field("suhu") String suhu, @Field("masker") String masker,
                                                         @Field("demam") String demam, @Field("obat_demam") String obat_demam, @Field("batuk_flu") String batuk_flu,
                                                         @Field("sesak_nafas") String sesak_nafas, @Field("keluar_negeri") String keluar_negeri,
                                                         @Field("keluar_daerah") String keluar_daerah, @Field("kontak_covid") String kontak_covid, @Field("user_id") int user_id);
}
package com.app.zerotolerance.table;

import com.orm.SugarRecord;

public class tbl_data_industri extends SugarRecord {
    private int id_user_kantor;
    private int code_industri_list;
    private String nm_user;
    private String gender_user;
    private String alamat_user;
    private String tgl_lahir_user;
    private String tlpn_user;
    private String pekerjaan_user;
    private String divisi_user;
    private String jabatan_user;
    private String stts_wfh;
    private String ket_stts_wfh;
    private String transport;
    private String handsanitizer;
    private String ket_handsanitizer;
    private String suplement;
    private String usia;
    private String rwt_penyakit;
    private String ket_rwt_penyakit;
    private String stts_pekerjaan;
    private String ket_pekerjaan;
    private String stts_hamil;
    private String ket_hamil;
    private String stts_shift;
    private String stts_rapid_swap;
    private String ket_rapid_swap;
    private String created_at;
    private String updated_at;
    private int created_by;
    private int updated_by;
    private int stts_rec;

    public tbl_data_industri() {
    }

    public tbl_data_industri(int id_user_kantor, int code_industri_list, String nm_user, String gender_user, String alamat_user, String tgl_lahir_user, String tlpn_user, String pekerjaan_user, String divisi_user, String jabatan_user, String stts_wfh, String ket_stts_wfh, String transport, String handsanitizer, String ket_handsanitizer, String suplement, String usia, String rwt_penyakit, String ket_rwt_penyakit, String stts_pekerjaan, String ket_pekerjaan, String stts_hamil, String ket_hamil, String stts_shift, String stts_rapid_swap, String ket_rapid_swap, String created_at, String updated_at, int created_by, int updated_by, int stts_rec) {
        this.id_user_kantor = id_user_kantor;
        this.code_industri_list = code_industri_list;
        this.nm_user = nm_user;
        this.gender_user = gender_user;
        this.alamat_user = alamat_user;
        this.tgl_lahir_user = tgl_lahir_user;
        this.tlpn_user = tlpn_user;
        this.pekerjaan_user = pekerjaan_user;
        this.divisi_user = divisi_user;
        this.jabatan_user = jabatan_user;
        this.stts_wfh = stts_wfh;
        this.ket_stts_wfh = ket_stts_wfh;
        this.transport = transport;
        this.handsanitizer = handsanitizer;
        this.ket_handsanitizer = ket_handsanitizer;
        this.suplement = suplement;
        this.usia = usia;
        this.rwt_penyakit = rwt_penyakit;
        this.ket_rwt_penyakit = ket_rwt_penyakit;
        this.stts_pekerjaan = stts_pekerjaan;
        this.ket_pekerjaan = ket_pekerjaan;
        this.stts_hamil = stts_hamil;
        this.ket_hamil = ket_hamil;
        this.stts_shift = stts_shift;
        this.stts_rapid_swap = stts_rapid_swap;
        this.ket_rapid_swap = ket_rapid_swap;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.stts_rec = stts_rec;
    }

    public int getId_user_kantor() {
        return id_user_kantor;
    }

    public void setId_user_kantor(int id_user_kantor) {
        this.id_user_kantor = id_user_kantor;
    }

    public int getCode_industri_list() {
        return code_industri_list;
    }

    public void setCode_industri_list(int code_industri_list) {
        this.code_industri_list = code_industri_list;
    }

    public String getNm_user() {
        return nm_user;
    }

    public void setNm_user(String nm_user) {
        this.nm_user = nm_user;
    }

    public String getGender_user() {
        return gender_user;
    }

    public void setGender_user(String gender_user) {
        this.gender_user = gender_user;
    }

    public String getAlamat_user() {
        return alamat_user;
    }

    public void setAlamat_user(String alamat_user) {
        this.alamat_user = alamat_user;
    }

    public String getTgl_lahir_user() {
        return tgl_lahir_user;
    }

    public void setTgl_lahir_user(String tgl_lahir_user) {
        this.tgl_lahir_user = tgl_lahir_user;
    }

    public String getTlpn_user() {
        return tlpn_user;
    }

    public void setTlpn_user(String tlpn_user) {
        this.tlpn_user = tlpn_user;
    }

    public String getPekerjaan_user() {
        return pekerjaan_user;
    }

    public void setPekerjaan_user(String pekerjaan_user) {
        this.pekerjaan_user = pekerjaan_user;
    }

    public String getDivisi_user() {
        return divisi_user;
    }

    public void setDivisi_user(String divisi_user) {
        this.divisi_user = divisi_user;
    }

    public String getJabatan_user() {
        return jabatan_user;
    }

    public void setJabatan_user(String jabatan_user) {
        this.jabatan_user = jabatan_user;
    }

    public String getStts_wfh() {
        return stts_wfh;
    }

    public void setStts_wfh(String stts_wfh) {
        this.stts_wfh = stts_wfh;
    }

    public String getKet_stts_wfh() {
        return ket_stts_wfh;
    }

    public void setKet_stts_wfh(String ket_stts_wfh) {
        this.ket_stts_wfh = ket_stts_wfh;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getHandsanitizer() {
        return handsanitizer;
    }

    public void setHandsanitizer(String handsanitizer) {
        this.handsanitizer = handsanitizer;
    }

    public String getKet_handsanitizer() {
        return ket_handsanitizer;
    }

    public void setKet_handsanitizer(String ket_handsanitizer) {
        this.ket_handsanitizer = ket_handsanitizer;
    }

    public String getSuplement() {
        return suplement;
    }

    public void setSuplement(String suplement) {
        this.suplement = suplement;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getRwt_penyakit() {
        return rwt_penyakit;
    }

    public void setRwt_penyakit(String rwt_penyakit) {
        this.rwt_penyakit = rwt_penyakit;
    }

    public String getKet_rwt_penyakit() {
        return ket_rwt_penyakit;
    }

    public void setKet_rwt_penyakit(String ket_rwt_penyakit) {
        this.ket_rwt_penyakit = ket_rwt_penyakit;
    }

    public String getStts_pekerjaan() {
        return stts_pekerjaan;
    }

    public void setStts_pekerjaan(String stts_pekerjaan) {
        this.stts_pekerjaan = stts_pekerjaan;
    }

    public String getKet_pekerjaan() {
        return ket_pekerjaan;
    }

    public void setKet_pekerjaan(String ket_pekerjaan) {
        this.ket_pekerjaan = ket_pekerjaan;
    }

    public String getStts_hamil() {
        return stts_hamil;
    }

    public void setStts_hamil(String stts_hamil) {
        this.stts_hamil = stts_hamil;
    }

    public String getKet_hamil() {
        return ket_hamil;
    }

    public void setKet_hamil(String ket_hamil) {
        this.ket_hamil = ket_hamil;
    }

    public String getStts_shift() {
        return stts_shift;
    }

    public void setStts_shift(String stts_shift) {
        this.stts_shift = stts_shift;
    }

    public String getStts_rapid_swap() {
        return stts_rapid_swap;
    }

    public void setStts_rapid_swap(String stts_rapid_swap) {
        this.stts_rapid_swap = stts_rapid_swap;
    }

    public String getKet_rapid_swap() {
        return ket_rapid_swap;
    }

    public void setKet_rapid_swap(String ket_rapid_swap) {
        this.ket_rapid_swap = ket_rapid_swap;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public int getStts_rec() {
        return stts_rec;
    }

    public void setStts_rec(int stts_rec) {
        this.stts_rec = stts_rec;
    }
}

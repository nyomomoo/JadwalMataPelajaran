package com.example.android.jadwalmatapelajaran.helper;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends RealmObject {
    @PrimaryKey
    private int id;
    private String hari;
    private String keterangan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getHari() {
        return hari;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
package com.example.android.jadwalmatapelajaran.model;

public class ArticleModel {
    private int id;
    private String hari;
    private String keterangan;
 
 
    public ArticleModel(int id, String hari, String keterangan) {
        this.id = id;
        this.hari = hari;
        this.keterangan = keterangan;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getHari() {
        return hari;
    }


    public void setHari(String hari) {
        this.hari = hari;
    }


    public String getKeterangan() {
        return keterangan;
    }


    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
} 
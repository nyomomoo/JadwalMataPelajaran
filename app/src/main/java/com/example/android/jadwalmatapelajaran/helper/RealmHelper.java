package com.example.android.jadwalmatapelajaran.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.jadwalmatapelajaran.model.ArticleModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class RealmHelper {


    private static final String TAG = "RealmHelper";


    private Realm realm;
    private RealmResults<Article> realmResult;
    public Context context;

    /**
     * constructor untuk membuat instance realm
     *
     * @param context
     */
    public RealmHelper(Context context) {
        realm = Realm.getInstance(context);
        this.context = context;
    }


    /**
     * menambah data
     *
     * @param hari
     * @param keterangan
     */
    public void addArticle(String hari, String keterangan) {
        Article article = new Article();
        article.setId((int) (System.currentTimeMillis() / 1000));
        article.setHari(hari);
        article.setKeterangan(keterangan);


        realm.beginTransaction();
        realm.copyToRealm(article);
        realm.commitTransaction();


        showLog("Added ; " + hari);
        showToast(hari + " berhasil disimpan.");
    }


    /**
     * method mencari semua article
     */
    public ArrayList<ArticleModel> findAllArticle() {
        ArrayList<ArticleModel> data = new ArrayList<>();


        realmResult = realm.where(Article.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        if (realmResult.size() > 0) {
            showLog("Size : " + realmResult.size());


            for (int i = 0; i < realmResult.size(); i++) {
                String hari, keterangan;
                int id = realmResult.get(i).getId();
                hari = realmResult.get(i).getHari();
                keterangan = realmResult.get(i).getKeterangan();
                data.add(new ArticleModel(id, hari, keterangan));
            }

        } else {
            showLog("Size : 0");
            showToast("Database Kosong!");
        }

        return data;
    }


    /**
     * method update article
     *
     * @param id
     * @param hari
     * @param keterangan
     */
    public void updateArticle(int id, String hari, String keterangan) {
        realm.beginTransaction();
        Article article = realm.where(Article.class).equalTo("id", id).findFirst();
        article.setHari(hari);
        article.setKeterangan(keterangan);
        realm.commitTransaction();
        showLog("Updated : " + hari);

        showToast(hari + " berhasil diupdate.");
    }


    /**
     * method menghapus article berdasarkan id
     *
     * @param id
     */
    public void deleteData(int id) {
        RealmResults<Article> dataDesults = realm.where(Article.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataDesults.remove(0);
        dataDesults.removeLast();
        dataDesults.clear();
        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }


    /**
     * membuat log
     *
     * @param s
     */
    private void showLog(String s) {
        Log.d(TAG, s);

    }

    /**
     * Membuat Toast Informasi
     */
    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
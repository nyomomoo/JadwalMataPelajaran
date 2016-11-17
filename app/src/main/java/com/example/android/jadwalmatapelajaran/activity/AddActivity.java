package com.example.android.jadwalmatapelajaran.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.jadwalmatapelajaran.R;
import com.example.android.jadwalmatapelajaran.helper.RealmHelper;


public class AddActivity extends AppCompatActivity {


    private RealmHelper realmHelper;
    private EditText inputKeterangan;
    private EditText inputHari;
    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        realmHelper = new RealmHelper(AddActivity.this);


        inputHari = (EditText) findViewById(R.id.inputHari);
        inputKeterangan = (EditText) findViewById(R.id.inputKeterangan);
        save = (Button) findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi string
                String hari, keterangan;

                //mengambil text dr edittext
                hari = inputHari.getText().toString();
                keterangan = inputKeterangan.getText().toString();

                //menambahkan data pada database
                realmHelper.addArticle(hari, keterangan);

                //menutup Add Activity
                finish();
                //kembali ke MainActivity
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Menangani perintah saat tombol back ditekan
     */
    @Override
    public void onBackPressed() {
        //berpindah ke MainActivity
        startActivity(new Intent(AddActivity.this, MainActivity.class));
        finish();
    }


}
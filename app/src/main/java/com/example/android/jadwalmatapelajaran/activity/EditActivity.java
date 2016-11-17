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
import com.example.android.jadwalmatapelajaran.model.ArticleModel;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {


    private int position;
    private Button delete, save;
    private EditText inputHari, inputKeterangan;
    private RealmHelper helper;
    private String hari, keterangan;
    private String intentHari, intentKeterangan;
    private ArrayList<ArticleModel> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        //tombol back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        helper = new RealmHelper(EditActivity.this);
        data = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);
        intentHari = getIntent().getStringExtra("hari");
        intentKeterangan = getIntent().getStringExtra("keterangan");


        delete = (Button) findViewById(R.id.delete);
        save = (Button) findViewById(R.id.save);


        inputHari = (EditText) findViewById(R.id.inputHari);
        inputKeterangan = (EditText) findViewById(R.id.inputKeterangan);


        inputHari.setText(intentHari);
        inputKeterangan.setText(intentKeterangan);


        delete.setVisibility(View.VISIBLE);

        //perintah untuk menghapus
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menghapus data dari database
                helper.deleteData(position);

                //berpindah ke MainActivity
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });

        //perintah mengupdate data
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mengambil text dari edittext
                hari = inputHari.getText().toString();
                keterangan = inputKeterangan.getText().toString();

                //melakukan update artikel
                helper.updateArticle(position, hari, keterangan);

                //berpindah ke MainActivity
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                startActivity(new Intent(EditActivity.this, MainActivity.class));
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
        startActivity(new Intent(EditActivity.this, MainActivity.class));
        finish();
    }


} 
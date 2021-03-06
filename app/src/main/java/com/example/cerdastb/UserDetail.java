package com.example.cerdastb;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetail extends AppCompatActivity {

    private TextView tvNama, tvPretest, tvPosttest, tvJkel, tvJTB, tvNoHp, tvTgl, tvTgl2, tvKota,
            tvstatus, tvKunjung;
    private ImageView img;
    DatabaseReference table_user = FirebaseDatabase.getInstance().getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Intent intent = getIntent();
        String Nama = intent.getExtras().getString("Nama");
        String jkel,jtb,tgl,tgl2,kota;
        jkel = intent.getExtras().getString("jkel");
        jtb = intent.getExtras().getString("jenisTb");
        tgl = intent.getExtras().getString("tanggalLahir");
        tgl2 = intent.getExtras().getString("tanggalDiagnosa");
        kota = intent.getExtras().getString("kota");

        TypedArray avatarImg = getResources().obtainTypedArray(R.array.genderImg);

        String NoHp = intent.getExtras().getString("NoHandphone");
        String Pretest = intent.getExtras().getString("Pretest");
        String Posttest = intent.getExtras().getString("Posttest");
        String status = intent.getExtras().getString("statusObat");
        String kunjung = intent.getExtras().getString("kunjung");

        tvNama = (TextView) findViewById(R.id.nama_user);
        tvPosttest = (TextView) findViewById(R.id.posttest);
        tvPretest = (TextView) findViewById(R.id.pretest);
        img = (ImageView) findViewById(R.id.gambar_user);
        tvJkel = (TextView) findViewById(R.id.jkel);
        tvJTB = (TextView) findViewById(R.id.jenisTB);
        tvKota = (TextView) findViewById(R.id.kota);
        tvNoHp = (TextView) findViewById(R.id.noHandphone);
        tvTgl = (TextView) findViewById(R.id.tanggallahir);
        tvTgl2 = (TextView) findViewById(R.id.tgldiagnosa);
        tvstatus = (TextView) findViewById(R.id.statusobat);
        tvKunjung = (TextView) findViewById(R.id.kunjung);

        tvNama.setText(Nama);
        tvPretest.setText(Pretest);
        tvPosttest.setText(Posttest);
        tvNoHp.setText(": "+NoHp);
        tvJkel.setText(": "+jkel);
        tvJTB.setText(": "+jtb);
        tvTgl.setText(": "+tgl);
        tvTgl2.setText(": "+tgl2);
        tvKota.setText(": "+kota);
        tvstatus.setText(": "+status);
        tvKunjung.setText(": "+kunjung);

       if (jkel.equals("Pria")){
           img.setImageResource(avatarImg.getResourceId(0,-1));
       }else{
           img.setImageResource(avatarImg.getResourceId(1,-1));
       }

    }
}

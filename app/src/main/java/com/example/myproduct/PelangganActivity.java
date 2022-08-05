package com.example.myproduct;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;

public class PelangganActivity extends AppCompatActivity {
    private String TAG = PelangganActivity.class.getSimpleName();
    private EditText namaPelanggan;
    private RadioGroup jenisKelamin;
    private RadioButton laki, perempuan;
    private Button submit;
    private String np, key2, jk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan);
        initView();

        final Pelanggan pelanggan = (Pelanggan) getIntent().getSerializableExtra("dataPelanggan");
        if (pelanggan != null){
            namaPelanggan.setText(pelanggan.getNamaPelanggan());
            String selected = pelanggan.getJenisKelamin();
            if (selected.equals("Laki-laki")){
                laki.setChecked(true);
            } else if (selected.equals("Perempuan")){
                perempuan.setChecked(true);
            } else {
                laki.setChecked(false);
                perempuan.setChecked(false);
            }
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(pelanggan);
                }
            });
        } else {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    key2 = FirebaseUtils2.getRefrence(FirebaseUtils2.ITEMS_PATH).push().getKey();
                    np = namaPelanggan.getText().toString().trim();
                    int selectedId = jenisKelamin.getCheckedRadioButtonId();
                    if (selectedId == laki.getId()){
                        jk = laki.getText().toString();
                    } else if (selectedId == perempuan.getId()) {
                        jk = perempuan.getText().toString();
                    } else {
                        jk = null;
                    }

                    Pelanggan pelanggan = new Pelanggan(key2, np, jk);

                    FirebaseUtils2.getRefrence(FirebaseUtils2.ITEMS_PATH).child(key2).setValue(pelanggan);
                    Toast.makeText(PelangganActivity.this, "Pelanggan Berhasil Ditambahkan.", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            });
        }
    }

    private void update(Pelanggan pelanggan) {
        pelanggan.setNamaPelanggan(namaPelanggan.getText().toString().trim());
        int selectedId = jenisKelamin.getCheckedRadioButtonId();
        if (selectedId == laki.getId()){
            jk = laki.getText().toString();
        } else if (selectedId == perempuan.getId()) {
            jk = perempuan.getText().toString();
        } else {
            jk = null;
        }

        FirebaseUtils2.getRefrence(FirebaseUtils2.ITEMS_PATH).child(pelanggan.getKey2()).setValue(pelanggan)
                .addOnSuccessListener(PelangganActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PelangganActivity.this, "Pelanggan Berhasil Diubah.", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                });
    }

    private void initView() {
        namaPelanggan = findViewById(R.id.NamaPelanggan);
        jenisKelamin = findViewById(R.id.JenisKelamin);
        laki = findViewById(R.id.Laki);
        perempuan = findViewById(R.id.Perempuan);
        submit = findViewById(R.id.Submit);
    }
}

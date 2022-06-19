package com.example.myproduct;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;

public class ItemActivity extends AppCompatActivity {
    private String TAG = ItemActivity.class.getSimpleName();
    private EditText namaProduk, harga, stok;
    private RadioGroup jenisProduk;
    private RadioButton baju, celana;
    private Button submit;
    private String np, key, jp;
    private int h, s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initView();

        final Item items = (Item) getIntent().getSerializableExtra("data");
        if (items != null){
            namaProduk.setText(items.getNamaProduk());
            String selected = items.getJenisProduk();
            if (selected.equals("Baju")){
                baju.setChecked(true);
            } else if (selected.equals("Celana")){
                celana.setChecked(true);
            } else {
                baju.setChecked(false);
                celana.setChecked(false);
            }
            harga.setText(String.valueOf(items.getHarga()));
            stok.setText(String.valueOf(items.getStok()));
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(items);
                }
            });
        } else {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    key = FirebaseUtils.getRefrence(FirebaseUtils.ITEMS_PATH).push().getKey();
                    np = namaProduk.getText().toString().trim();
                    int selectedId = jenisProduk.getCheckedRadioButtonId();
                    if (selectedId == baju.getId()){
                        jp = baju.getText().toString();
                    } else if (selectedId == celana.getId()) {
                        jp = celana.getText().toString();
                    } else {
                        jp = null;
                    }
                    h = Integer.parseInt(harga.getText().toString().trim());
                    s = Integer.parseInt(stok.getText().toString().trim());

                    Item item = new Item(key, np, jp, h, s);

                    FirebaseUtils.getRefrence(FirebaseUtils.ITEMS_PATH).child(key).setValue(item);
                    Toast.makeText(ItemActivity.this, "Produk Berhasil Ditambahkan.", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            });
        }
    }
    private void initView(){
        namaProduk = findViewById(R.id.NamaProduk);
        jenisProduk = findViewById(R.id.jenisProduk);
        baju = findViewById(R.id.Baju);
        celana = findViewById(R.id.Celana);
        harga = findViewById(R.id.Harga);
        stok = findViewById(R.id.Stok);
        submit = findViewById(R.id.Submit);
    }

    private void update(Item item){
        item.setNamaProduk(namaProduk.getText().toString().trim());
        int selectedId = jenisProduk.getCheckedRadioButtonId();
        if (selectedId == baju.getId()){
            jp = baju.getText().toString();
        } else if (selectedId == celana.getId()) {
            jp = celana.getText().toString();
        } else {
            jp = null;
        }
        item.setHarga(Integer.parseInt(harga.getText().toString().trim()));;
        item.setStok(Integer.parseInt(stok.getText().toString().trim()));

        FirebaseUtils.getRefrence(FirebaseUtils.ITEMS_PATH).child(item.getKey()).setValue(item)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ItemActivity.this, "Produk Berhasil Diubah.", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                });
    }
}

package com.example.myproduct;

import android.widget.EditText;
import android.widget.RadioButton;

import java.io.Serializable;

public class Item implements Serializable {
    private String namaProduk, key, jenisProduk;
    private int harga, stok;

    public Item(){

    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    public Item(String key, String namaProduk, String jenisProduk ,int harga, int stok){
        this.key = key;
        this.namaProduk = namaProduk;
        this.jenisProduk = jenisProduk;
        this.harga = harga;
        this.stok = stok;
    }
}

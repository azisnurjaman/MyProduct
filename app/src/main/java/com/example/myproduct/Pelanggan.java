package com.example.myproduct;

import java.io.Serializable;

public class Pelanggan implements Serializable {
    private String key2, namaPelanggan, jenisKelamin;

    public Pelanggan(){

    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Pelanggan(String key2, String namaPelanggan, String jenisKelamin){
        this.key2 = key2;
        this.namaPelanggan = namaPelanggan;
        this.jenisKelamin = jenisKelamin;
    }
}

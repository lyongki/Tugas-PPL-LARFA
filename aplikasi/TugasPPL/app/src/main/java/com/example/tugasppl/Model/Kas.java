package com.example.tugasppl.Model;

public class Kas {
    private String nim,nama, jumlah, tanggal;

    public Kas(String nim, String jumlah) {
        this.nim = nim;
        this.jumlah = jumlah;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Kas(String nim, String nama, String jumlah, String tanggal) {


        this.nim = nim;
        this.nama = nama;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }
}

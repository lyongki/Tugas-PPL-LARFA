package com.example.tugasppl.Model;

public class Rapat {
    private String nama, tanggal, hasil;

    public Rapat(String nama, String tanggal, String hasil) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.hasil = hasil;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }
}

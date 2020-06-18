package com.example.tugasppl.Model;

public class History {
    private String tanggal,jumlah, keterangan;

    public History(String tanggal, String jumlah, String keterangan) {
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}

package com.example.tugasppl.Model;

public class History {
    private String tanggal,jumlah, keterangan,role;

    public History(String tanggal, String jumlah, String keterangan, String role) {
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

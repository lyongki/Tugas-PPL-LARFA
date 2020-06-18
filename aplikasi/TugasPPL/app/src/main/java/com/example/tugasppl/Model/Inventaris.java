package com.example.tugasppl.Model;

public class Inventaris {
    private String id, nama, jumlah_tersedia,jumlah_pinjam;

    public Inventaris(String id, String nama, String jumlah_tersedia, String jumlah_pinjam) {
        this.id = id;
        this.nama = nama;
        this.jumlah_tersedia = jumlah_tersedia;
        this.jumlah_pinjam = jumlah_pinjam;
    }

    public Inventaris(String id, String nama, String jumlah_tersedia) {
        this.id = id;
        this.nama = nama;
        this.jumlah_tersedia = jumlah_tersedia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah_tersedia() {
        return jumlah_tersedia;
    }

    public void setJumlah_tersedia(String jumlah_tersedia) {
        this.jumlah_tersedia = jumlah_tersedia;
    }

    public String getJumlah_pinjam() {
        return jumlah_pinjam;
    }

    public void setJumlah_pinjam(String jumlah_pinjam) {
        this.jumlah_pinjam = jumlah_pinjam;
    }
}

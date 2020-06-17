package com.example.tugasppl.Model;

public class Surat {
    private String id,nama,tanggal,role,file;

    public Surat(String id, String nama, String tanggal, String role, String file) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.role = role;
        this.file = file;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

package com.example.tugasppl.Model;

import java.io.Serializable;

public class Event implements Serializable {
    private String id,nama_event,nama_ukm,thumbnail,tanggal,evaluasi,deskripsi;

    public Event(String id, String nama_event, String nama_ukm, String thumbnail, String tanggal, String evaluasi, String deskripsi) {
        this.id = id;
        this.nama_event = nama_event;
        this.nama_ukm = nama_ukm;
        this.thumbnail = thumbnail;
        this.tanggal = tanggal;
        this.evaluasi = evaluasi;
        this.deskripsi = deskripsi;
    }

    public Event(String id, String nama_event, String nama_ukm, String thumbnail, String tanggal, String deskripsi) {
        this.id = id;
        this.nama_event = nama_event;
        this.nama_ukm = nama_ukm;
        this.thumbnail = thumbnail;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
    }

    public Event(String nama_ukm) {
        this.nama_ukm = nama_ukm;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_event() {
        return nama_event;
    }

    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getNama_ukm() {
        return nama_ukm;
    }

    public void setNama_ukm(String nama_ukm) {
        this.nama_ukm = nama_ukm;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getEvaluasi() {
        return evaluasi;
    }

    public void setEvaluasi(String evaluasi) {
        this.evaluasi = evaluasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}

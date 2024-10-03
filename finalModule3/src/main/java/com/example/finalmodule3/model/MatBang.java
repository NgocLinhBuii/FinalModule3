package com.example.finalmodule3.model;

import java.time.LocalDate;

public class MatBang {
    private String maMatBang;
    private String trangThai;
    private float dienTich;
    private int tang;
    private String loaiMatBang;
    private float giaTien;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    // Constructor


    public MatBang() {
    }

    public MatBang(String maMatBang, String trangThai, float dienTich, int tang, String loaiMatBang, float giaTien, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maMatBang = maMatBang;
        this.trangThai = trangThai;
        this.dienTich = dienTich;
        this.tang = tang;
        this.loaiMatBang = loaiMatBang;
        this.giaTien = giaTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    // Getters and Setters
    public String getMaMatBang() {
        return maMatBang;
    }

    public void setMaMatBang(String maMatBang) {
        this.maMatBang = maMatBang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public String getLoaiMatBang() {
        return loaiMatBang;
    }

    public void setLoaiMatBang(String loaiMatBang) {
        this.loaiMatBang = loaiMatBang;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}

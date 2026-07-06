package com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Sach {
    @Id
    private String maSH ;
    private String tenSH ;
    private String tacGia ;
    private String loai ;
    private String tinhTrang ;

    public String getMaSH() {
        return maSH;
    }

    public void setMaSH(String maSH) {
        this.maSH = maSH;
    }

    public String getTenSH() {
        return tenSH;
    }

    public void setTenSH(String tenSH) {
        this.tenSH = tenSH;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Sach(String maSH, String tenSH, String tacGia, String loai, String tinhTrang) {
        this.maSH = maSH;
        this.tenSH = tenSH;
        this.tacGia = tacGia;
        this.loai = loai;
        this.tinhTrang = tinhTrang;
    }
    public Sach () {

    }
}

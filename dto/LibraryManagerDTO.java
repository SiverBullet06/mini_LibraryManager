package com.example.demo.SpringBoot__Core.miniQL_ThuVien.dto;

import jakarta.persistence.Id;

import java.sql.Date;

public class LibraryManagerDTO {
    // Entity Sach
    private String maSH ;
    private String tenSH ;
    private String tacGia ;
    private String loai ;
    private String tinhTrang ;

    //Entity DocGia
    private String maDg ;
    private String tenDg ;
    private Date ngaySinh ;
    private String phai ;
    private String diaChi ;
    private int slMuon ;

    // Entity MuonSach
    private String fk_Madg ;
    private String fk_Msach ;
    private Date ngayMuon ;
    private Date ngayTra ;
    private String quaHan ;

    // Getter && Setter


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

    public String getMaDg() {
        return maDg;
    }

    public void setMaDg(String maDg) {
        this.maDg = maDg;
    }

    public String getTenDg() {
        return tenDg;
    }

    public void setTenDg(String tenDg) {
        this.tenDg = tenDg;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSlMuon() {
        return slMuon;
    }

    public void setSlMuon(int slMuon) {
        this.slMuon = slMuon;
    }

    public String getFk_Madg() {
        return fk_Madg;
    }

    public void setFk_Madg(String fk_Madg) {
        this.fk_Madg = fk_Madg;
    }

    public String getfk_Msach() {
        return fk_Msach;
    }

    public void setfk_Msach(String fk_Msach) {
        this.fk_Msach = fk_Msach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getQuaHan() {
        return quaHan;
    }

    public void setQuaHan(String quaHan) {
        this.quaHan = quaHan;
    }

    public LibraryManagerDTO(String maSH, String tenSH, String tacGia, String loai, String tinhTrang, String maDg, String tenDg, Date ngaySinh, String phai, String diaChi, int slMuon, String fk_Madg, String fk_Msach, Date ngayMuon, Date ngayTra, String quaHan) {
        this.maSH = maSH;
        this.tenSH = tenSH;
        this.tacGia = tacGia;
        this.loai = loai;
        this.tinhTrang = tinhTrang;
        this.maDg = maDg;
        this.tenDg = tenDg;
        this.ngaySinh = ngaySinh;
        this.phai = phai;
        this.diaChi = diaChi;
        this.slMuon = slMuon;
        this.fk_Madg = fk_Madg;
        this.fk_Msach = fk_Msach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.quaHan = quaHan;
    }
    public LibraryManagerDTO () {

    }
}

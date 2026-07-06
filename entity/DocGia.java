package com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class DocGia {
    @Id
    private String maDg ;
    private String tenDg ;
    private Date ngaySinh ;
    private String phai ;
    private String diaChi ;
    private int slMuon ;

    public DocGia(String maDg, String tenDg, Date ngaySinh, String phai, String diaChi, int slMuon) {
        this.maDg = maDg;
        this.tenDg = tenDg;
        this.ngaySinh = ngaySinh;
        this.phai = phai;
        this.diaChi = diaChi;
        this.slMuon = slMuon;
    }

    public DocGia() {
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


}

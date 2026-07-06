package com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;


@Entity
public class MuonSach {
    private String fk_Madg ;
    private String fk_Msach ;
    @Id
    private Date ngayMuon ;
    private Date ngayTra ;
    private String quaHan ;

    public MuonSach() {
    }

    public MuonSach(String fk_Madg, String fk_Msach, Date ngayMuon, Date ngayTra, String quaHan) {
        this.fk_Madg = fk_Madg;
        this.fk_Msach = fk_Msach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.quaHan = quaHan;
    }

    public String getFk_Madg() {
        return fk_Madg;
    }

    public void setFk_Madg(String fk_Madg) {
        this.fk_Madg = fk_Madg;
    }

    public String getFk_Msach() {
        return fk_Msach;
    }

    public void setFk_Msach(String fk_Msach) {
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
}

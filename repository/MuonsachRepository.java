package com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.MuonSach;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.MuonSach;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MuonsachRepository {
    
    public static List <MuonSach> listMS ; 
    public MuonsachRepository () { 
        listMS = new ArrayList<>() ; 
    }
    
    public List<MuonSach> getAllMuonSach (Connection conn ) throws SQLException {
        String sql = " SELECT * FROM MuonSach " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        try(ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                MuonSach ms = new MuonSach( ) ;
                ms.setFk_Madg(rs.getString("MADG"));
                ms.setFk_Msach(rs.getString("MASH"));
                ms.setNgayMuon(rs.getDate("NGAYMUON"));
                ms.setNgayTra(rs.getDate("NGAYTRA"));
                ms.setQuaHan(rs.getString("QUAHAN"));
                listMS.add(ms) ;
            }
        }
        return listMS ;
    }

    public int updateMuonsach (Connection conn , String madg , String mash
    , Date ngtra , String quahan ) throws SQLException {
        String sql = "UPDATE FROM MUONSACH " +
                "SET NGAYTRA = ? ," +
                "QUAHAN = ? " +
                "WHERE MADG = ? " +
                "AND MASH = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setDate(1,ngtra);
        ps.setString(2,quahan);
        ps.setString(3,madg);
        ps.setString(4,mash);

        return ps.executeUpdate() ;
    }

    public int deleteMuonsach ( Connection conn , String madg , String mash ) throws SQLException {
        String sql = "DELETE FROM MUONSACH " +
                "WHERE MADG = ? " +
                "AND MASH = ? ";
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setString(1,madg);
        ps.setString(2,mash);
        return ps.executeUpdate() ;
    }
     public List<MuonSach> getMuonSach_ByMaDG_MaSH ( Connection conn  ,  String madg , String mash  ) throws SQLException {
        String sql = "SELECT * " +
               "FROM MuonSach " +
                "WHERE MADG =?" +
                " AND MASH = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,madg);
        ps.setString(2,mash);
         try ( ResultSet rs = ps.executeQuery() ) {
            while ( rs.next()) {
                MuonSach ms = new MuonSach( ) ;
                ms.setFk_Madg(rs.getString("MADG"));
                ms.setFk_Msach(rs.getString("MASH"));
                ms.setNgayMuon(rs.getDate("NGAYMUON"));
                ms.setNgayTra(rs.getDate("NGAYTRA"));
                ms.setQuaHan(rs.getString("QUAHAN"));
                listMS.add(ms) ;
            }
        }
        return listMS ;
    }

    public List<MuonSach> getListMuonSach_in_Year ( Connection conn , Date year  ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM MuonSach" +
                "WHERE YEAR(NGAYMUON)= YEAR(?) " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setDate(1,year);
        try ( ResultSet rs = ps.executeQuery() ) {
            while ( rs.next() ) {
                MuonSach ms = new MuonSach( ) ;
                ms.setFk_Madg(rs.getString("MADG"));
                ms.setFk_Msach(rs.getString("MASH"));
                ms.setNgayMuon(rs.getDate("NGAYMUON"));
                ms.setNgayTra(rs.getDate("NGAYTRA"));
                ms.setQuaHan(rs.getString("QUAHAN"));
                listMS.add(ms) ;
            }
        }
        return listMS;
    }

    public boolean checkExists_Mams ( Connection conn , String mams ) throws  SQLException {
        boolean check = false ;
        String sql = " SELECT 1 " +
                "FROM MuonSach" +
                "WHERE MAms = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setString(1,mams );
        try ( ResultSet rs = ps.executeQuery()) {
            if ( rs.next()) {
                check = true;
                System.out.println(" Ton tai ma doc gia trong du lieu ");
            }
        }
        return check ;
    }

    public void readAll_MuonSach() {
        System.out.println("\n--- DANH SACH MUON SACH  ---");
        for (MuonSach ms : listMS) {
            System.out.printf("Ma doc gia : %s" +
                            "\tMa sach : %s " +
                            "\tNgay muon : %s " +
                            "\tNgay tra : %s " +
                            "\tQua han : %s%n",
                    ms.getFk_Madg(),
                    ms.getFk_Msach(), ms.getNgayMuon(), ms.getNgayTra(),
                    ms.getQuaHan()
            );
        }
    }


}

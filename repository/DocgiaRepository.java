package com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository;

import com.example.demo.JDBC.Model.SinhVien;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.DocGia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocgiaRepository {
    public static  List <DocGia > listDG ;

    public DocgiaRepository () {
        listDG = new ArrayList<>() ;
    }

    public List <DocGia> getAllDocGia (Connection conn ) throws SQLException {
        String sql = " SELECT * FROM DOCGIA " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        try(ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                DocGia dg = new DocGia( ) ;
                dg.setMaDg(rs.getString("MADG"));
                dg.setTenDg(rs.getString("TENTG"));
                dg.setNgaySinh(rs.getDate("NGAYSINH"));
                dg.setPhai(rs.getString("PHAI"));
                dg.setDiaChi(rs.getString("DIACHI"));
                dg.setSlMuon(rs.getInt("SoLanMuon")) ;
                listDG.add(dg) ;
            }
        }
        return listDG ;
    }
    public DocGia getOneDocGia ( Connection conn  ,  String madg ) throws SQLException {
        String sql = "SELECT * " +
                "FROM DOCGIA " +
                "WHERE MADG =? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,madg);
        try ( ResultSet rs = ps.executeQuery() ) {
            if ( rs.next()) {
                DocGia dg = new DocGia( ) ;
                dg.setMaDg(rs.getString("MADG"));
                dg.setTenDg(rs.getString("TENDG"));
                dg.setNgaySinh(rs.getDate("NGAYSINH"));
                dg.setPhai(rs.getString("PHAI"));
                dg.setDiaChi(rs.getString("DIACHI"));
                dg.setSlMuon(rs.getInt("SoLanMuon")) ;
                return dg ;
            }
        }
        return null ;
    }

    public List<DocGia> getListDG_in_Location( Connection conn , String diachi ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM DOCGIA" +
                "WHERE DIACHI = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        try ( ResultSet rs = ps.executeQuery() ) {
            while ( rs.next() ) {
                DocGia dg = new DocGia( ) ;
                dg.setMaDg(rs.getString("MADG"));
                dg.setTenDg(rs.getString("TENTG"));
                dg.setNgaySinh(rs.getDate("NGAYSINH"));
                dg.setPhai(rs.getString("PHAI"));
                dg.setDiaChi(rs.getString("DIACHI"));
                dg.setSlMuon(rs.getInt("SoLanMuon")) ;
            }
        }
        return listDG;
    }

    public boolean checkExists_MaDG ( Connection conn , String madg ) throws  SQLException {
        boolean check = false ;
        String sql = " SELECT 1 " +
                "FROM DOCGIA" +
                "WHERE MADG = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setString(1,madg );
        try ( ResultSet rs = ps.executeQuery()) {
            if ( rs.next()) {
                check = true;
                System.out.println(" Ton tai ma doc gia trong du lieu ");
            }
        }
        return check ;
    }

    public DocGia getOneDG_ByName ( Connection conn , String name ) throws SQLException {
        String sql  = " SELECT *" +
                "FROM DOCGIA" +
                "WHERE TENDG = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,name);
        try ( ResultSet rs = ps.executeQuery() ) {
            if ( rs.next()) {
                DocGia dg = new DocGia( ) ;
                dg.setMaDg(rs.getString("MADG"));
                dg.setTenDg(rs.getString("TENTG"));
                dg.setNgaySinh(rs.getDate("NGAYSINH"));
                dg.setPhai(rs.getString("PHAI"));
                dg.setDiaChi(rs.getString("DIACHI"));
                dg.setSlMuon(rs.getInt("SoLanMuon")) ;
                return dg;
            }
            return null ;
        }
    }

    public List <DocGia> getDocGia_By_Sex ( Connection conn , String sex ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM DOCGIA " +
                "WHERE PHAI = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setString(1,sex );
        try ( ResultSet rs =ps.executeQuery()) {
            while ( rs.next()) {
                DocGia dg = new DocGia( ) ;
                dg.setMaDg(rs.getString("MADG"));
                dg.setTenDg(rs.getString("TENTG"));
                dg.setNgaySinh(rs.getDate("NGAYSINH"));
                dg.setPhai(rs.getString("PHAI"));
                dg.setDiaChi(rs.getString("DIACHI"));
                dg.setSlMuon(rs.getInt("SoLanMuon")) ;
                listDG.add(dg ) ;
            }
        }
        return listDG ;
    }

    public void  insertDocGia (Connection conn , String madg ,
                               String ten , Date ngsinh , String phai ,
                               String diachi , int slMuon ) throws  SQLException {
        String sql = " INSERT INTO DOCGIA (MADG , TENDG ," +
                "NGAYSINH , PHAI , DIACHI , SoLanMuon ) VALUES (?,?,?,?,?,?)" ;
        PreparedStatement ps =conn.prepareStatement(sql ) ;
        ps.setString(1,madg);
        ps.setString(2,ten);
        ps.setDate(3,ngsinh);
        ps.setString(4,phai);
        ps.setString(5,diachi);
        ps.setInt(6,slMuon);
        ps.executeUpdate() ;
        System.out.println("Da cap nhat doc gia thanh cong ");
    }

    public void deleteDocGia ( Connection conn , String madg ) throws SQLException {
        String sql = " DELETE FROM DOCGIA" +
                "WHERE MADG = ?" ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,madg ) ;
        ps.executeUpdate() ;
        System.out.println("Da xoa ma doc gia : "+madg);
    }

    public void updateDocGia ( Connection conn , int slmuon , String madg ) throws SQLException {
        String sql = " UPDATE DOCGIA " +
                "SET SoLanMuon = ? " +
                "WHERE MADG = ?" ;
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setInt(1,slmuon);
        ps.setString(2,madg);
        ps.executeUpdate()  ;
        System.out.println("Da cap nhat thanh cong ! ");
    }
    public void readAll_DocGia() {
        System.out.println("\n--- DANH SACH DOC GIA  ---");
        for (DocGia dg : listDG) {
            System.out.printf("Ma doc gia : %s" +
                            "\tHo va ten: %s " +
                            "\tNgay sinh: %s " +
                            "\tGioi tinh: %s " +
                            "\tDia chi: %s " +
                            "\tSo lan muon : %s%n",
                    dg.getMaDg() ,
                    dg.getTenDg() , dg.getNgaySinh() , dg.getPhai(),
                    dg.getDiaChi() , dg.getSlMuon()
            );
        }
    }
    public void readOne_DocGia(DocGia dg) {
        if (dg != null) {
            System.out.println("\n--- THONG TIN CHI TIET DOC GIA ---");
            System.out.printf("Ma doc gia : %s" +
                            "\tHo va ten: %s " +
                            "\tNgay sinh: %s " +
                            "\tGioi tinh: %s " +
                            "\tDia chi: %s " +
                            "\tSo lan muon : %s%n",
                    dg.getMaDg(),
                    dg.getTenDg(), dg.getNgaySinh(), dg.getPhai(),
                    dg.getDiaChi(), dg.getSlMuon()
            );
        }
    }


}

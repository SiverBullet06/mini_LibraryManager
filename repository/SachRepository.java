package com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.Sach;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.Sach;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//
//    hiển thị danh sách sách
//    tìm sách theo mã
//    hiển thị sách theo tên
//    hiển thị sách theo tác giả
//    hiển thị sách theo trạng thái
@Repository
public class SachRepository {
    public static List <Sach> listSach ; 
    public SachRepository () { 
        listSach = new ArrayList<>() ; 
        
    }
    // Lấy tất cả loại sách
    public List <Sach> getAllSach (Connection conn ) throws SQLException {
        String sql = " SELECT * FROM SACH " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        try(ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                Sach s = new Sach( ) ;
                s.setMaSH(rs.getString("MASACH"));
                s.setTenSH(rs.getString("TENSH"));
                s.setTacGia(rs.getString("TACGIA"));
                s.setLoai(rs.getString("LOAI"));
                s.setTinhTrang(rs.getString("TINHTRANG"));
                listSach.add(s) ;
            }
        }
        return listSach ;
    }
    // Tìm sach theo id sách
    public Sach getOneSach (Connection conn ,  String masach ) throws SQLException {
        String sql = " SELECT *" +
                " FROM SACH " +
                "WHERE MASH = ?" ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,masach);
        try(ResultSet rs = ps.executeQuery()) {
            if (rs.next()){
                Sach s = new Sach() ;
                s.setMaSH(rs.getString("MASACH"));
                s.setTenSH(rs.getString("TENSH"));
                s.setTacGia(rs.getString("TACGIA"));
                s.setLoai(rs.getString("LOAI"));
                s.setTinhTrang(rs.getString("TINHTRANG"));
                return s ;
            }
        }
        return null ;
    }
    // Danh sách sách theo loại
    public List<Sach> getListSach_ByType( Connection conn , String loai ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM SACH" +
                "WHERE LOAI = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;

        ps.setString(1,loai);
        try ( ResultSet rs = ps.executeQuery() ) {
            while ( rs.next() ) {
                Sach s = new Sach( ) ;
                s.setMaSH(rs.getString("MASACH"));
                s.setTenSH(rs.getString("TENSH"));
                s.setTacGia(rs.getString("TACGIA"));
                s.setLoai(rs.getString("LOAI"));
                s.setTinhTrang(rs.getString("TINHTRANG"));
                listSach.add(s) ;
            }
        }
        return listSach;
    }
    // Kiểm tra mã sách tồn tại
    public boolean checkExists_Masach ( Connection conn , String masach ) throws  SQLException {
        String sql = " SELECT 1 " +
                "FROM Sach" +
                "WHERE MASH = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setString(1,masach );
        try ( ResultSet rs = ps.executeQuery()) {
            if ( rs.next()) {
                return true ;
            }
        }
        return false ;
    }
    // Danh sách sách theo
    public List<Sach> getListsSach_ByAuthor( Connection conn , String author ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM SACH" +
                "WHERE TACGIA = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,author);
        try ( ResultSet rs = ps.executeQuery() ) {
            while ( rs.next() ) {
                Sach s = new Sach( ) ;
                s.setMaSH(rs.getString("MASACH"));
                s.setTenSH(rs.getString("TENSH"));
                s.setTacGia(rs.getString("TACGIA"));
                s.setLoai(rs.getString("LOAI"));
                s.setTinhTrang(rs.getString("TINHTRANG"));
                listSach.add(s) ;
            }
        }
        return listSach;
    }
    // Danh sách sách theo tên sách
    public Sach getListSach_ByBookName( Connection conn , String bookName ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM SACH" +
                "WHERE TENSH = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,bookName);
        try ( ResultSet rs = ps.executeQuery() ) {
            if ( rs.next() ) {
                Sach s = new Sach( ) ;
                s.setMaSH(rs.getString("MASACH"));
                s.setTenSH(rs.getString("TENSH"));
                s.setTacGia(rs.getString("TACGIA"));
                s.setLoai(rs.getString("LOAI"));
                s.setTinhTrang(rs.getString("TINHTRANG"));
                return s ;
            }
        }
        return null ;
    }
   // Danh sách sách theo tình trạng
    public List<Sach> getListsSach_ByStatus( Connection conn , String status ) throws  SQLException {
        String sql = " SELECT * " +
                "FROM SACH" +
                "WHERE TACGIA = ? " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,status);
        try ( ResultSet rs = ps.executeQuery() ) {
            while ( rs.next() ) {
                Sach s = new Sach( ) ;
                s.setMaSH(rs.getString("MASACH"));
                s.setTenSH(rs.getString("TENSH"));
                s.setTacGia(rs.getString("TACGIA"));
                s.setLoai(rs.getString("LOAI"));
                s.setTinhTrang(rs.getString("TINHTRANG"));
                listSach.add(s) ;
            }
        }
        return listSach;
    }
    // Thêm sách
    public int insert_Sach (Connection conn , String masach , String tensach , String tacgia , String loai , String  tinhtrang ) throws  SQLException {
        String sql = " INSERT INTO SACH (MASH , TENSH ," +
                "TACGIA , LOAI ,TINHTRANG ) VALUES (?,?,?,?,?)" ;
        PreparedStatement ps =conn.prepareStatement(sql ) ;
        ps.setString(1,masach);
        ps.setString(2,tensach);
        ps.setString(3,tacgia);
        ps.setString(4,loai);
        ps.setString(5,tinhtrang);
        return ps.executeUpdate() ;
    }
    // Xóa sách
    public int  deleteSach ( Connection conn , String masach ) throws SQLException {
        String sql = " DELETE FROM Sach" +
                "WHERE MASH = ?" ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        ps.setString(1,masach ) ;
        return ps.executeUpdate() ;

    }
    //Cas[j nhật sách
    public int  updateSach ( Connection conn , String tinhtrang , String masach ) throws SQLException {
        String sql = " UPDATE Sach " +
                "SET TINHTRANG = ? " +
                "WHERE MASH = ?" ;
        PreparedStatement ps = conn.prepareStatement(sql) ;
        ps.setString(1,tinhtrang);
        ps.setString(2,masach);
        return ps.executeUpdate()  ;
    }
    public void readAll_Sach() {
        System.out.println("\n--- DANH SACH SACH  ---");
        for (Sach s : listSach) {
            System.out.printf("Ma sach : %s" +
                            "\tTen sach: %s " +
                            "\tTac gia : %s " +
                            "\tLoai : %s " +
                            "\ttinh trang sach : %s%n ",
                    s.getMaSH() , s.getTenSH() , s.getTacGia() , s.getLoai()
                    , s.getTinhTrang()
            );
        }
    }
    public void readOne_Sach( Sach s) {
        if (s != null) {
            System.out.println("\n--- DANH SACH SACH  ---");
                System.out.printf("Ma sach : %s" +
                                "\tTen sach: %s " +
                                "\tTac gia : %s " +
                                "\tLoai : %s " +
                                "\ttinh trang sach : %s%n ",
                        s.getMaSH() , s.getTenSH() , s.getTacGia() , s.getLoai()
                        , s.getTinhTrang()
                );
        }
    }

}

package com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.dto.LibraryManagerDTO;
import jdk.jfr.consumer.RecordingStream;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.DumperOptions;

import javax.naming.ldap.PagedResultsControl;
import java.sql.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
@Repository
public class DTORepository {

    public static List <LibraryManagerDTO > listdto ;

    public DTORepository () {
        listdto = new ArrayList<>() ;

    }
    //  Lấy danh sách đọc giả  đã trả sách
    public List <LibraryManagerDTO> getReturnSach (Connection conn ) throws SQLException {
        String sql = " SELECT DG.MADG , DG.TENDG ,S.TENSH , s.LOAI  " +
                "FROM DOCGIA DG " +
                "JOIN MUONSACH MS ON DG.MADG = MS.MADG " +
                "JOIN SACH S ON S.MASH = MS.MASH " +
                "WHERE MS.NGAYTRA IS NOT NULL  " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        try (ResultSet rs = ps.executeQuery()) {
            while ( rs.next()) {
                LibraryManagerDTO lbdto = new LibraryManagerDTO() ;
                lbdto.setMaDg(rs.getString("MADG"));
                lbdto.setTenDg(rs.getString("TENDG"));
                lbdto.setTenSH(rs.getString("TENSH"));
                lbdto.setLoai(rs.getString("LOAI"));
                listdto.add(lbdto) ;
            }
        }
        return listdto  ;
    }

    // Lấy những đọc giã đã mượn sách
    public List<LibraryManagerDTO > getsDGMuonSach ( Connection conn ) throws SQLException {
        String sql = "SELECT * " +
                "FROM DOCGIA DG " +
                "JOIN MUONSACH MS ON MS.MADG = DG.MADG " +
                "WHERE MS.MADG IS NOT NULL  " ;
        PreparedStatement ps = conn.prepareStatement(sql ) ;
        try ( ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LibraryManagerDTO lbdto = new LibraryManagerDTO() ;
                lbdto.setMaDg(rs.getString("MADG"));
                lbdto.setTenDg(rs.getString("TENDG"));
                lbdto.setNgaySinh(rs.getDate("NGAYSINH"));
                lbdto.setPhai(rs.getString("PHAI"));
                lbdto.setDiaChi(rs.getString("DIACHI"));
                listdto.add(lbdto) ;
            }
        }
        return listdto ;
    }

}

package com.example.demo.SpringBoot__Core.miniQL_ThuVien.service;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.MuonSach;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository.MuonsachRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuonsachService {

    private final MuonsachRepository muonsachRepository ;

    public MuonsachService ( MuonsachRepository muonsachRepository ) {
        this.muonsachRepository = muonsachRepository ;
    }
    // Danh sách giao dịch mượn sách
    public List <MuonSach> danhsachMuonsach (Connection conn ) throws SQLException {
        return muonsachRepository.getAllMuonSach(conn ) ;
    }
    // Danh sách giao dịch mượn sách theo mã độc giã và mã sách
    public MuonSach danhsachMuocsach_byMaDG ( Connection conn , String madg , String mash ) throws  SQLException {
        if ( madg ==null  || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma doc gia khong duoc de trong ");
        }
        if ( mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong ");
        }
        try {
           MuonSach ms = muonsachRepository.getMuonSach_ByMaDG_MaSH(conn, madg, mash);
           if (ms == null) {
               System.out.println("Hien khong co giao dich nao thoa man ");
           }
           return ms;
       } catch (SQLException e ) {
           System.err.println("Loi tang Service goi tang Repository ");
           return null;
       }
    }
    // Danh sách giao dịch mượn sách trong năm
    public List <MuonSach> danhsachMuonsach_inYear (Connection conn , Date year ) throws SQLException {

        if ( year == null ) {
            throw new IllegalArgumentException("Khong duoc de trong ");
        }
        List<MuonSach> getAllMS = muonsachRepository.getListMuonSach_in_Year(conn , year) ;
        List <MuonSach> result = new ArrayList<>() ;
        for ( MuonSach ms : getAllMS ) {
            if (year.equals(ms.getNgayMuon())) {
                result.add(ms);
            }
        }
        return result ;
    }
    // Xóa giao dịch mượn sách
    public void deleteLogicMuonsach ( Connection conn , String madg , String mash  ) throws SQLException {
        if ( madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma doc gia khong duoc de trong ") ;
        }
        if ( mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong");
        }
        madg = madg.trim().toUpperCase() ;
        if ( muonsachRepository.checkExists_MaMuonsach(conn, madg) ==false) {
            throw new RuntimeException("Ma doc gia khong ton tai trong du lieu  ");
        }
        try {
            int result = muonsachRepository.deleteMuonsach(conn , madg , mash ) ;

            if ( result >0 ) {
                System.out.println("Xoa thanh cong giao dich muon sach  ");
            }
            else {
                System.out.println("Khong co giao dich muon sach nao duoc xoa ");
            }

        }catch (SQLException e ) {
            System.err.println("Loi xay ra tai Service khi goi Repo"+e.getMessage());
            throw e ;
        }

    }
    // Cập nhật giao dịch muượn sách
    public void updateLogicMuonsach ( Connection conn , String madg , String mash , Date ngaytra , String quahan   ) throws SQLException {
        if ( madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma doc gia khong duoc de trong ") ;
        }
        if ( mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong");
        }
        if ( ngaytra == null) {
            throw new IllegalArgumentException("Ngay tra khong duoc de trong ");
        }

        madg = madg.trim().toUpperCase() ;
        if ( muonsachRepository.checkExists_MaMuonsach(conn, madg) ==false) {
            throw new RuntimeException("Ma doc gia khong ton tai trong du lieu  ");
        }
        try {
            int result = muonsachRepository.updateMuonsach(conn , madg , mash , ngaytra , quahan ) ;

            if ( result >0 ) {
                System.out.println("Cap nhat thanh cong giao dich muon sach  ");
            }
            else {
                System.out.println("Khong co giao dich muon sach nao duoc cap nhat ");
            }

        }catch (SQLException e ) {
            System.err.println("Loi xay ra tai Service khi goi Repo"+e.getMessage());
            throw e ;
        }

    }
    // Thêm mới một giao dịch mượn sách
    public void insertLogicMuonsach ( Connection conn , String madg , String mash ,  Date ngmuon , Date ngtra , String quahan   ) throws SQLException {
        if ( madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma doc gia khong duoc de trong ") ;
        }
        if ( mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong");
        }
        if ( ngmuon == null) {
            throw new IllegalArgumentException("Ngay tra khong duoc de trong ");
        }
        madg = madg.trim().toUpperCase() ;
        if ( muonsachRepository.checkExists_MaMuonsach(conn, madg) ==false) {
            throw new RuntimeException("Ma doc gia khong ton tai trong du lieu  ");
        }
        try {
            int result = muonsachRepository.insertMuonsach(conn , madg , mash , ngmuon , ngtra , quahan ) ;

            if ( result >0 ) {
                System.out.println("Cap nhat thanh cong giao dich muon sach  ");

            }
            else {
                System.out.println("Khong co giao dich muon sach nao duoc cap nhat ");
            }

        }catch (SQLException e ) {
            System.err.println("Loi xay ra tai Service khi goi Repo"+e.getMessage());
            throw e ;
        }

    }
}

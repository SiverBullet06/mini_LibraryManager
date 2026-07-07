package com.example.demo.SpringBoot__Core.miniQL_ThuVien.service;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.DocGia;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.Sach;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository.DocgiaRepository;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocgiaService {
    @Autowired
    private  final DocgiaRepository docgiaRepository ;
    public DocgiaService ( DocgiaRepository docgiaRepository ) {
        this.docgiaRepository = docgiaRepository ;
    }
    // Lấy tất cả danh sách độc giả
    public List<DocGia> danhsachDocGia (Connection conn ) throws SQLException {
        return docgiaRepository.getAllDocGia(conn) ;
    }
    // Lấy độc giả theo mã độc giả
    public DocGia getDocGia_id(Connection conn, String madg) throws SQLException {
        // 1. Xử lý logic kiểm tra đầu vào (Validation) ngay tại tầng Service
        if (madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã độc giả tìm kiếm không được để trống!");
        }
        DocGia dg = docgiaRepository.getOneDocGia(conn, madg);
        if (dg == null) {
            throw new RuntimeException("Không tìm thấy độc giả nào có mã: " + madg);
        }
        return dg;
    }

//    public DocGia get ( Connection conn , String id) throws SQLException {
//        List <DocGia> getAllDG = docgiaRepository.getAllDocGia(conn) ;
//        List<DocGia> result = new ArrayList<>() ;
//        for ( DocGia dg  : getAllDG) {
//            if (id.equalsIgnoreCase(dg.getDiaChi())) {
//                result.add(dg) ;
//            }
//        }
//        return result ;
//    }

    // Lấy danh sach độc giả theo vị trí
    public List<DocGia> danhsachDocGia_in ( Connection conn , String diaChi) throws SQLException {
        if ( diaChi == null || diaChi.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống ");
        }
        try {
            List<DocGia> getAllDG = docgiaRepository.getListDG_in_Location(conn , diaChi);
            List<DocGia> result = new ArrayList<>();
            for (DocGia dg : getAllDG) {
                if (diaChi.equalsIgnoreCase(dg.getDiaChi())) {
                    result.add(dg);
                }
            }
            if (result.isEmpty()) {
                System.out.println("Hien khong co danh sach doc gia o dia chi " + diaChi + " trong du lieu");
            }
            return result;
        }catch (SQLException e ) {
            System.err.println("Loi xu li nghiep vu "+e.getMessage());
            return new ArrayList<>() ;
        }
    }
    // Lấy danh sách độc giả theo gới tính
    public List<DocGia> danhsachDocGia_sex ( Connection conn , String gioiTinh) throws SQLException {
        if ( gioiTinh== null || gioiTinh.trim().isEmpty()) {
            throw new IllegalArgumentException("Gioi tinh khong duoc de trong ");
        }
        List <DocGia> getAllDG = docgiaRepository.getDocGia_By_Sex(conn , gioiTinh) ;
        List<DocGia> result = new ArrayList<>() ;
        for ( DocGia dg  : getAllDG) {
            if (gioiTinh.equalsIgnoreCase(dg.getPhai())) {
                result.add(dg) ;
            }
        }
        if ( result.isEmpty()) {
            System.out.println("Khong co gioi tinh " + gioiTinh + " trong du lieu ");
        }
        return result ;
    }
    // Lấy độc giã theo tên
    public DocGia DocGia_name ( Connection conn , String ten) throws SQLException {
        if (ten == null || ten.trim().isEmpty()) {
            throw new IllegalArgumentException("Ten khong duoc de trong ");
        }

        try {
            DocGia dg = docgiaRepository.getOneDG_ByName(conn , ten ) ;
            if ( dg == null ) {
                System.out.println("Hiện tại không có doc gia có ten: " + ten + " trong thư viện");
            }
            return dg ;
        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return null;
        }
    }
    public void insertLogicDocGia(Connection conn, String madg, String tendg,
                             Date ngsinh, String phai, String diachi, int slMuon) throws SQLException {

        // 1. Kiểm tra logic nghiệp vụ (Validation)
        if (madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã độc giả không được để trống!");
        }
        if (tendg == null || tendg.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên độc giả không được để trống!");
        }
        if ( ngsinh == null ) {
            throw new IllegalArgumentException("Ngay sinh khong duoc de trong ");
        }
        madg = madg.trim().toUpperCase() ;
        if (docgiaRepository.checkExists_MaDG(conn , madg )) {
            throw new RuntimeException("Ma doc gia da ton tai trong he thong ") ;

        }
        try {
            // Giả sử đối tượng repo của bạn tên là docGiaRepo
            int result = docgiaRepository.insertDocGia(conn, madg, tendg, ngsinh, phai, diachi, slMuon);

            if (result > 0) {
                System.out.println("Thêm độc giả thành công ở tầng Service!");
            } else {
                System.out.println("Không có dòng nào được thêm vào database.");
            }
        } catch (SQLException e) {
            // Log lỗi hoặc xử lý lỗi nghiệp vụ nếu cần, sau đó ném tiếp (throw) ra ngoài
            System.err.println("Lỗi xảy ra tại Service khi gọi Repo: " + e.getMessage());
            throw e;
        }
    }
    // delete độc giả theo kiểm điều kiện
    public void deleteLogicDocGia ( Connection conn , String madg ) throws SQLException {
        if ( madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma doc gia khong duoc de trong ");
        }
        madg = madg.trim().toUpperCase() ;
        if (docgiaRepository.checkExists_MaDG(conn , madg )==false) {
            throw new RuntimeException("Ma doc gia khong co trong he thong ");
        }

        try {
            int result = docgiaRepository.deleteDocGia(conn , madg) ;

            if ( result >0 ) {
                System.out.println("Xoa thanh cong doc gia ");
            }
            else {
                System.out.println("Khong co dong nao duoc xoa ");
            }

        }catch (SQLException e ) {
            System.err.println("Loi xay ra tai Service khi goi Repo"+e.getMessage());
            throw e ;
        }
    }
    // update độc giả theo kiểm điều kiện
    public void updateLogicDocGia ( Connection conn , int  slmuon , String madg ) throws  SQLException{
        if ( madg == null || madg.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma doc gia khong duoc de trong ");
        }
        madg = madg.trim().toUpperCase() ;
        if (docgiaRepository.checkExists_MaDG(conn , madg )== false) {
            throw new RuntimeException("Ma doc gia khong ton tai ");
        }

        try {
            int result = docgiaRepository.updateDocGia(conn, slmuon, madg);
            if (result > 0) {
                System.out.println(" Cap nhat thanh cong !");
            } else {
                System.out.println("Khong co du lieu nao duoc update ");
            }
        }
        catch (SQLException e ) {
            System.err.println("Loi xay ra tai tang Service khi goi Repo " +e.getMessage());
        }
    }

}

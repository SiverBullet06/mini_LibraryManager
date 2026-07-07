package com.example.demo.SpringBoot__Core.miniQL_ThuVien.service;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.DocGia;
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

        // 2. Gọi xuống tầng Repo để lấy dữ liệu
        DocGia dg = docgiaRepository.getOneDocGia(conn, madg);

        // 3. (Tùy chọn) Xử lý logic kiểm tra kết quả trả về
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
        List <DocGia> getAllDG = docgiaRepository.getAllDocGia(conn) ;
        List<DocGia> result = new ArrayList<>() ;
        for ( DocGia dg  : getAllDG) {
            if (diaChi.equalsIgnoreCase(dg.getDiaChi())) {
                result.add(dg) ;
            }
        }
        return result ;
    }
    // Lấy danh sách độc giả tho gới tính
    public List<DocGia> danhsachDocGia_sex ( Connection conn , String gioiTinh) throws SQLException {
        List <DocGia> getAllDG = docgiaRepository.getAllDocGia(conn) ;
        List<DocGia> result = new ArrayList<>() ;
        for ( DocGia dg  : getAllDG) {
            if (gioiTinh.equalsIgnoreCase(dg.getDiaChi())) {
                result.add(dg) ;
            }
        }
        return result ;
    }
    // Lấy độc giã theo tên
    public List<DocGia> danhsachDocGia_name ( Connection conn , String ten) throws SQLException {
        List <DocGia> getAllDG = docgiaRepository.getAllDocGia(conn) ;
        List<DocGia> result = new ArrayList<>() ;
        for ( DocGia dg  : getAllDG) {
            if (ten.equalsIgnoreCase(dg.getDiaChi())) {
                result.add(dg) ;
            }
        }
        return result ;
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

        // 2. Gọi tầng Repo để thực hiện insert
        // Vì 'conn' được truyền từ ngoài vào làm tham số, bạn KHÔNG CẦN dùng 'try ()' ở đây để đóng nó.
        // Việc đóng 'conn' sẽ do nơi tạo ra nó (ví dụ: Controller hoặc Main) chịu trách nhiệm.
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

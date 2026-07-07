package com.example.demo.SpringBoot__Core.miniQL_ThuVien.service;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.Sach;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Service
public class SachService {
    @Autowired
    private final SachRepository sachRepository ;

    public SachService (SachRepository sachRepository) {
        this.sachRepository = sachRepository ;
    }
    //Lấy tất cả danh sách sách có trong thư viện !?
    public List<Sach> danhsachSach (Connection conn ) throws SQLException {
        return sachRepository.getAllSach(conn) ;
    }
    // Tìm sách theo id sách
    public Sach laySach_ById (Connection conn , String id  ){
        if ( id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !") ;
        }
        try {
            Sach s = sachRepository.getOneSach(conn, id);
            if ( s == null ) {
                System.out.println("Hiện tại không có sách có ID: " + id + " trong thư viện");
            }
            return s ;
        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return null;
        }
    }
    // Tìm sách theo tên sách
    public Sach laySach_ByName (Connection conn , String name  ){
        if ( name  == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !") ;

        }
        try {
            Sach s = sachRepository.getListSach_ByBookName(conn, name );
            if ( s == null ) {
                System.out.println("Hiện tại không có sách : " + name + " trong thư viện");
            }
            return s ;
        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return null;
        }
    }
    // Tìm sách theo tên tác giả
    public List<Sach> danhsachSach_ByAuthor (Connection conn , String author   ) {
        try {
            List<Sach> getAllSach = sachRepository.getListsSach_ByAuthor(conn , author ) ;
            List<Sach> result = new ArrayList<>();
            for (Sach s : getAllSach) {
                if (author.equalsIgnoreCase(s.getTacGia())) {
                    result.add(s);
                }
            }
            // 3. Logic kiểm tra nghiệp vụ: Nếu không có ai trả sách Tin học thì thông báo
            if (result.isEmpty()) {
                System.out.println("Hiện tại không có tac gia "+author+" trong thu vien ");
            }

            return result;

        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    // Tìm sách theo trạng thái
    public List<Sach> danhsachSach_status (Connection conn , String status  ) {
        try {

            List<Sach> getAllSach = sachRepository.getListsSach_ByStatus(conn , status) ;
            List<Sach> result = new ArrayList<>();
            for (Sach s : getAllSach) {
                if (status.equalsIgnoreCase(s.getTinhTrang())) {
                    result.add(s);
                }
            }
            // 3. Logic kiểm tra nghiệp vụ: Nếu không có ai trả sách Tin học thì thông báo
            if (result.isEmpty()) {
                System.out.println("Hiện tại không có tinh trang "+status+" trong thu vien ");
            }
            return result;
        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    // tìm sach theo thể loại
    public List<Sach> danhsachSach_type (Connection conn , String type  ) {
        try {
            List<Sach> getAllSach = sachRepository.getListSach_ByType(conn , type ) ;
            List<Sach> result = new ArrayList<>();
            for (Sach s : getAllSach) {
                if (type.equalsIgnoreCase(s.getLoai())) {
                    result.add(s);
                }
            }
            if (result.isEmpty()) {
                System.out.println("Hiện tại không có loai sach "+type+" trong thu vien ");
            }
            return result;
        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    // Thêm sách
    public void insertLogicSach ( Connection conn , String mash , String tensh , String tacgia ,
                                  String loai , String tinhtrang) throws SQLException {
        if (mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !");
        }
        if (tensh == null || tensh.trim().isEmpty()) {
            throw new IllegalArgumentException("Ten sach khong duoc de trong !");
        }
        if (tacgia == null || tacgia.trim().isEmpty()) {
            throw new IllegalArgumentException("Tac gia khong duoc de trong !");
        }
        if (loai == null || loai.trim().isEmpty()) {
            throw new IllegalArgumentException("Loai sach khong duoc de trong !");
        }
        mash = mash.trim().toUpperCase() ;
        if (sachRepository.checkExists_Masach(conn , mash)) {
            throw new RuntimeException("Ma sach da ton tai trong he thong ");
        }

        if (tinhtrang == null || tinhtrang.trim().isEmpty()) {
            tinhtrang = "chưa mượn";
        }

        try {
            int result = sachRepository.insert_Sach(conn, mash, tensh, tacgia, loai, tinhtrang);
            if (result > 0) {
                System.out.println("Da them sach thanh cong ");
            } else {
                System.out.println("Chua co sach nao duoc them ");
            }
        }catch (SQLException e) {
            System.err.println("Loi tai tang Sercvice khi goi Repo !");
            throw e ;
        }
    }
    // Xóa sách
    public void deleteLogicSach ( Connection conn , String mash ) throws SQLException {
        if (mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !");
        }
//
        if (sachRepository.checkExists_Masach(conn , mash ) ==false ) {
            throw new RuntimeException("Ma sach khong ton tai khong du lieu ");
        }
        try {
            int result = sachRepository.deleteSach(conn, mash);
            if (result > 0) {
                System.out.println("Da xoa sach thanh cong ");
            } else {
                System.out.println("Chua co sach nao duoc xoa ");
            }
        }catch (SQLException e) {
            System.err.println("Loi tai tang Sercvice khi goi Repo !");
            throw e ;
        }
    }
    // Cập nhật sách
    public void updateLogicSach ( Connection conn , String tinhtrang , String mash) throws SQLException {
        if (mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !");
        }
        if (tinhtrang == null || tinhtrang.trim().isEmpty()) {
            tinhtrang = "chưa mượn";
        }
        if (sachRepository.checkExists_Masach(conn , mash ) == false ) {
            throw new RuntimeException("Ma sach khong ton tai trong du lieu ");
        }
        try {
            int result = sachRepository.updateSach(conn,tinhtrang,mash);
            if (result > 0) {
                System.out.println("Cap nhat sach thanh cong ");
            } else {
                System.out.println("Chua co sach nao duoc cap nhat ");
            }
        }catch (SQLException e) {
            System.err.println("Loi tai tang Sercvice khi goi Repo !");
            throw e ;
        }
    }

}

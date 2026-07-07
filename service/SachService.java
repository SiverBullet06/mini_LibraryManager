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

    public List<Sach> danhsachSach (Connection conn ) throws SQLException {
        return sachRepository.getAllSach(conn) ;
    }

    public List<Sach> danhsachSach_bookName (Connection conn , String name  ) {
        try {

            List<Sach> getAllSach = sachRepository.getAllSach(conn) ;

            List<Sach> result = new ArrayList<>();
            for (Sach s : getAllSach) {
                if (name.equalsIgnoreCase(s.getTenSH())) {
                    result.add(s);
                }
            }

            // 3. Logic kiểm tra nghiệp vụ: Nếu không có ai trả sách Tin học thì thông báo
            if (result.isEmpty()) {
                System.out.println("Hiện tại không có sach "+name+" trong thu vien ");
            }

            return result;

        } catch (SQLException e) {
            System.err.println("Lỗi xử lý nghiệp vụ: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Sach> danhsachSach_author (Connection conn , String author  ) {
        try {

            List<Sach> getAllSach = sachRepository.getAllSach(conn) ;

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
    public List<Sach> danhsachSach_status (Connection conn , String status  ) {
        try {

            List<Sach> getAllSach = sachRepository.getAllSach(conn) ;

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

    public List<Sach> danhsachSach_type (Connection conn , String type  ) {
        try {

            List<Sach> getAllSach = sachRepository.getAllSach(conn) ;

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

        try {
            int result = sachRepository.insertSach(conn, mash, tensh, tacgia, loai, tinhtrang);
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

    public void deleteLogicSach ( Connection conn , String mash ) throws SQLException {
        if (mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !");
        }
//
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

    public void updateLogicSach ( Connection conn , String tinhtrang , String mash) throws SQLException {
        if (mash == null || mash.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma sach khong duoc de trong !");
        }
        if (tinhtrang == null || tinhtrang.trim().isEmpty()) {
            throw new IllegalArgumentException("Tinh trang  sach khong duoc de trong !");
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

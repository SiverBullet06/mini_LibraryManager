package com.example.demo.SpringBoot__Core.miniQL_ThuVien.service;

import com.example.demo.SpringBoot__Core.miniQL_ThuVien.entity.MuonSach;
import com.example.demo.SpringBoot__Core.miniQL_ThuVien.repository.MuonsachRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuonsachService {

    private final MuonsachRepository muonsachRepository ;

    public MuonsachService ( MuonsachRepository muonsachRepository ) {
        this.muonsachRepository = muonsachRepository ;
    }

    public List <MuonSach> danhsachMuonsach (Connection conn ) throws SQLException {
        return muonsachRepository.getAllMuonSach(conn ) ;
    }

    public List<MuonSach> danhsachMuocsach_byMaDG ( Connection conn , String madg ) throws  SQLException {
       List <MuonSach> getAllMS = muonsachRepository.getAllMuonSach(conn ) ;
       List<MuonSach> result = new ArrayList<>() ;
       for ( MuonSach ms : getAllMS) {
           if (madg.equalsIgnoreCase(ms.getFk_Madg())) {
               result.add(ms);

           }
       }
        return result  ;
    }
}

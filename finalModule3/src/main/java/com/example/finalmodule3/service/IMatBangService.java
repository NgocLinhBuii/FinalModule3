package com.example.finalmodule3.service;

import com.example.finalmodule3.model.MatBang;

import java.time.LocalDate;
import java.util.List;

public interface IMatBangService {
    List<MatBang> findAll();

    void save(MatBang matBang);

    void delete(String maMatBang);

    boolean existsByMaMatBang(String maMatBang);

    MatBang findByMaMatBang(String maMatBang);

    List<MatBang> searchWithConditions(String loaiMatBang, Float giaTien, Integer tang, LocalDate startDate, LocalDate endDate);
}

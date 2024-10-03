package com.example.finalmodule3.service;

import com.example.finalmodule3.model.MatBang;
import com.example.finalmodule3.repo.IMatBangRepo;
import com.example.finalmodule3.repo.MatBangRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MatBangService implements IMatBangService {
    private final IMatBangRepo iMatBangRepo = new MatBangRepository();

    @Override
    public List<MatBang> findAll() {
        return iMatBangRepo.findAll();
    }

    @Override
    public void save(MatBang matBang) {
        iMatBangRepo.save(matBang);
    }

    @Override
    public void delete(String maMatBang) {
        iMatBangRepo.delete(maMatBang);
    }

    @Override
    public boolean existsByMaMatBang(String maMatBang) {
        return iMatBangRepo.existsByMaMatBang(maMatBang);
    }

    @Override
    public MatBang findByMaMatBang(String maMatBang) {
        return iMatBangRepo.findByMaMatBang(maMatBang);
    }

    @Override
    public List<MatBang> searchWithConditions(String loaiMatBang, Float giaTien, Integer tang, LocalDate startDate, LocalDate endDate) {
        return iMatBangRepo.searchWithConditions(loaiMatBang, giaTien, tang, startDate, endDate);
    }
}


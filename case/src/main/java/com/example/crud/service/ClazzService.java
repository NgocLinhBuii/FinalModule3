package com.example.crud.service;

import com.example.crud.model.Clazz;

import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();
    void addNewClazz(Clazz clazz);
    void updateClazz(Clazz clazz);
    void deleteClazz(int id);  // Accepts ID as parameter to delete
    Clazz findClazzById(int id);
}

package com.example.crud.serviceImpl;

import com.example.crud.model.Clazz;
import com.example.crud.repository.ClazzRepository;
import com.example.crud.repositoryimpl.ClazzRepositoryImpl;
import com.example.crud.service.ClazzService;

import java.util.List;

public class ClazzServiceImpl implements ClazzService {
    private final ClazzRepository clazzRepository = new ClazzRepositoryImpl();

    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }

    @Override
    public void addNewClazz(Clazz clazz) {

    }

    @Override
    public void updateClazz(Clazz clazz) {

    }

    @Override
    public void deleteClazz(int id) {

    }

    @Override
    public Clazz findClazzById(int id) {
        return null;
    }
}
